package com.hyo.smart.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class HttpClient {
	
	private static final String DEFAULT_ENCODING = "UTF-8";

	public static String httpGet(String targetUrl, Map<String, Object> sendData) throws Exception {
		BufferedReader rd = null;
		StringBuffer output = null;
		
		CloseableHttpClient httpClient = HttpClients.createDefault();

		Builder builder = RequestConfig.custom();
		builder.setConnectionRequestTimeout(5000);
		builder.setSocketTimeout(5000);
		RequestConfig config = builder.build();
	  
        try {
        	StringBuilder param = new StringBuilder();			
			if(!sendData.isEmpty()) {
				param.append('?');
				for (Map.Entry<String, Object> data : sendData.entrySet()) {
					if(param.length() != 0) param.append('&');
					param.append(URLEncoder.encode(data.getKey(), "UTF-8"));
					param.append('=');
					param.append(URLEncoder.encode(String.valueOf(data.getValue()), "UTF-8"));
				}
				
				targetUrl = targetUrl + param.toString();
			}
			
        	HttpGet get = new HttpGet(targetUrl);
        	
            System.out.println("[TargetURL] " + get.getURI());
			
            // SET Builder.
			get.setConfig(config);
            // Get Response.
            CloseableHttpResponse response = httpClient.execute(get);
            try {
            	int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("[ResponsCode] " + statusCode);      
            	rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent())); 
            	output = new StringBuffer();
            	String line; 
            	while ((line = rd.readLine()) != null) {
                	output.append(line.trim());
                }
                rd.close();
            } catch (Exception e) {
    			e.printStackTrace();
    		} finally {
            	response.close();
            }        
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.close();
		}
        
        return output.toString();
	}
	
	
	public static String httpPost(String targetUrl, Map<String, Object> sendData) throws Exception {
		BufferedReader rd = null;
		StringBuffer output = null;
		
		CloseableHttpClient httpClient = HttpClients.createDefault();

		Builder builder = RequestConfig.custom();
		builder.setConnectionRequestTimeout(5000);
		builder.setSocketTimeout(5000);
		RequestConfig config = builder.build();
	  
        try {        	
        	HttpPost post = new HttpPost(targetUrl);
        	
            List<NameValuePair> paramList = convertParam(sendData);
            post.setEntity(new UrlEncodedFormEntity(paramList, DEFAULT_ENCODING));

            System.out.println("[TargetURL] " + post.getURI());
			System.out.println("[Params] " + post.getEntity().toString());
			
            // SET Builder.
            post.setConfig(config);
            // Get Response.
            CloseableHttpResponse response = httpClient.execute(post);
            try {
            	int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("[ResponsCode] " + statusCode);      
            	rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent())); 
            	output = new StringBuffer();
            	String line; 
            	while ((line = rd.readLine()) != null) {
                	output.append(line.trim());
                }
                rd.close();
            } catch (Exception e) {
    			e.printStackTrace();
    		} finally {
            	response.close();
            }        
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.close();
		}
        
        return output.toString();
	}
	
	
	public static List<NameValuePair> convertParam(Map<String, Object> params) {
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();		
		for(Map.Entry<String, Object> data : params.entrySet()) {
			paramList.add(new BasicNameValuePair(data.getKey(), (String) data.getValue()));
		}		
		return paramList;
	}
	
	
	public static void main(String[] args) {
	    
        HashMap<String, Object> reqParam = new HashMap<String, Object>();
        
        try {        	
            String urlstr = "http://openapi.airport.co.kr/service/rest/FlightStatusList/getFlightStatusList";           
            reqParam.put("serviceKey", "PR5A0qQASnckL9DLJCrovlrJR8GDV+BKQ9EeWX7cOpNF1HGRP8irvfXtQK1IPDO9eCVHstzp2U9UYuhrgp9oSQ==");
            reqParam.put("schStTime", "0815");
            reqParam.put("schEdTime", "1815");
            reqParam.put("schLineType", "");
            reqParam.put("schIOType", "O");
            reqParam.put("schAirCode", "GMP");
            
            // Get data.
            String resultMsg = HttpClient.httpGet(urlstr, reqParam);
            System.out.println("[resultMsg] " + resultMsg);
       
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}