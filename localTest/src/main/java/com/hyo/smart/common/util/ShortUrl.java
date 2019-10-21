package com.hyo.smart.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ShortUrl {
	
	public static String apiURL = "https://openapi.naver.com/v1/util/shorturl?url=";
	public static String clientId = "vuBuJbcqM5qnKvcwtT9V";
	public static String clientSecret = "rojEg7dwGc";
	
	public static String urlShortening ( String _url ) {
		HttpURLConnection httpCon = null;
		BufferedReader rd = null;
		StringBuffer output = null;
		
		try {
            URL url = new URL(apiURL + _url);
            httpCon = (HttpURLConnection) url.openConnection();
            
            httpCon.setRequestMethod("GET");
            httpCon.setRequestProperty("X-Naver-Client-Id", clientId);
            httpCon.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            
            System.out.println(httpCon.getResponseCode());
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
		System.out.println(output.toString());
		return output.toString();
	}

    public static void main(String[] args) {
    		String url = "http://aroot.cf/api/selectTemplate/201910101521011?token=24C9E15E52AFC47C225B757E7BEE1F9D";
    		
    		urlShortening(url);		
    }
}