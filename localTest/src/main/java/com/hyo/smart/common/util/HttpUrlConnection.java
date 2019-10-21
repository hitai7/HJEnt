package com.hyo.smart.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUrlConnection {

	public static String httpGet(String targetUrl, HashMap<String, Object> sendData) {
		HttpURLConnection httpCon = null;
		BufferedReader rd = null;
		StringBuffer output = null;
		
		try {
			StringBuilder param = new StringBuilder();
			
			if(!sendData.isEmpty()) {
				for (Map.Entry<String, Object> data : sendData.entrySet()) {
					if(param.length() != 0) {
						param.append('&');
					} else {
						param.append('?');
					}
					param.append(URLEncoder.encode(data.getKey(), "UTF-8"));
					param.append('=');
					param.append(URLEncoder.encode(String.valueOf(data.getValue()), "UTF-8"));
				}
				
				targetUrl = targetUrl + param.toString();
			}
			
			System.out.println("[TargetURL] " + URLDecoder.decode(targetUrl, "UTF-8"));
			
            URL url = new URL(targetUrl);
            httpCon = (HttpURLConnection) url.openConnection();
            
            // Get Response.
            rd = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), "UTF-8"));
            output = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
            	output.append(line);
            }
            rd.close();
            
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(httpCon != null) {
				httpCon.disconnect();
			}
		}
		
		return output.toString();
	}
	
	
	public static String httpPost(String targetUrl, HashMap<String, Object> sendData) {
		HttpURLConnection httpCon = null;
		BufferedReader rd = null;
		StringBuffer output = null;
		
		try {			
			StringBuilder param = new StringBuilder();
			
			for (Map.Entry<String, Object> data : sendData.entrySet()) {
				if(param.length() != 0) param.append('&');
				param.append(URLEncoder.encode(data.getKey(), "UTF-8"));
				param.append('=');
				param.append(URLEncoder.encode(String.valueOf(data.getValue()), "UTF-8"));
			}
			
			byte[] paramBytes = param.toString().getBytes("UTF-8");
			
			System.out.println("[TargetURL] " + targetUrl);
			System.out.println("[Params] " + URLDecoder.decode(param.toString(), "UTF-8"));
			
            URL url = new URL(targetUrl);
            httpCon = (HttpURLConnection) url.openConnection();
            
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            httpCon.setRequestProperty("content-Length", String.valueOf(paramBytes.length));
            httpCon.setRequestProperty("charset", "utf-8");
            httpCon.setUseCaches(false);
            
            // Send POST.
            httpCon.getOutputStream().write(paramBytes);
            httpCon.getOutputStream().flush();
            
            // Get Response.
            rd = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), "UTF-8"));
            output = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
            	output.append(line);
            }
            rd.close();
            
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(httpCon != null) {
				httpCon.disconnect();
			}
		}
		
		return output.toString();
	}

		
	public static void main(String[] args) {
	    
        HashMap<String, Object> reqParam = new HashMap<String, Object>();
        List dataList = new ArrayList<>();
        dataList.add("dataList1");
        dataList.add("dataList2");
        dataList.add("dataList3");
        dataList.add("dataList4");
        try {        	
            String urlstr = "http://openapi.airport.co.kr/service/rest/FlightStatusList/getFlightStatusList";           
            reqParam.put("serviceKey", "PR5A0qQASnckL9DLJCrovlrJR8GDV+BKQ9EeWX7cOpNF1HGRP8irvfXtQK1IPDO9eCVHstzp2U9UYuhrgp9oSQ==");
            reqParam.put("schStTime", "0815");
            reqParam.put("schEdTime", "1815");
            reqParam.put("schLineType", "");
            reqParam.put("schIOType", "O");
            reqParam.put("schAirCode", "GMP");
            reqParam.put("listData", dataList);
            
            // Get data.
            String resultMsg = HttpUrlConnection.httpGet(urlstr, reqParam);
            System.out.println("[resultMsg] " + resultMsg);
       
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
