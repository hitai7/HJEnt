package com.hyo.smart.common.util;

import java.lang.reflect.Field;

import com.hyo.smart.domain.MemberInfoDTO;

public class MakeSendFormat {

	// camelToField.
	public String camelToField( String _inputMsg ) {
		System.out.println("# camelToField #");
				
		String regex = "([a-z])([A-Z])";
		String replacement = "$1_$2";
		
		String outputMsg = _inputMsg.replaceAll(regex, replacement).toUpperCase();
		
		return outputMsg;
	}
	
	// makeSendParamFormat.
	public StringBuilder makeSendParamFormat( Object _inputMsg ) {
		System.out.println("# makeSendParamFormat #");
				
		StringBuilder outputMsg = new StringBuilder();
		
		try {
			Object obj = _inputMsg;
			for (Field field : obj.getClass().getDeclaredFields()) {
				if(outputMsg.length() != 0) outputMsg.append('&');
				field.setAccessible(true);
				Object value = field.get(obj);
				outputMsg.append(field.getName() + '=' + value);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return outputMsg;
	}
		
	public static void main(String[] args) {
		MakeSendFormat data = new MakeSendFormat();
				
		// camelToField.
		String camelStr = "testCalmelString";
		String resultMsg = data.camelToField(camelStr);
		System.out.println("[" + camelStr + "] [" + resultMsg + "]");
		
		MemberInfoDTO dto = new MemberInfoDTO();
		dto.setName("나윤찬");
		dto.setNewPassword("newpasswd");
		dto.setNote("test");
		
		// makeSendParamFormat.
		System.out.println("[" + data.makeSendParamFormat(dto) + "]");
	}    
}
