package com.hyo.smart.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlEncDec {

	// URLEncode.
	public String urlEncoding( String _text, String _format ) throws UnsupportedEncodingException {
		System.out.println("# URLEncode #");
		return URLEncoder.encode(_text, _format);
	}

	// URLDecode.
	public String urlDecoding( String _text, String _format ) throws UnsupportedEncodingException {
		System.out.println("# URLDecode #");
		return URLDecoder.decode(_text, _format);
	}
		
	public static void main(String[] args) {
		UrlEncDec data = new UrlEncDec();
		
		try {
			String format = "UTF-8";
			String text = "HJ엔터테이먼트";
			String encStr;
			String decStr;
			
			// URLEncode.
			encStr = data.urlEncoding(text, format);
			System.out.println("[" + text + "] [" + encStr + "] [Len: " + encStr.length() + "]");	
			
			// URLDecode.
			decStr = data.urlDecoding(encStr, format);
			System.out.println("[" + encStr + "] [" + decStr + "] [Len: " + decStr.length() + "]");	

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}    
}
