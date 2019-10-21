package com.hyo.smart.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyo.smart.common.WebConstants;
import com.hyo.smart.domain.UserInfoDTO;

public class JsonParser {
		
	// JsonToMap using JSONParser.
	public List<Map<String, Object>> jsonToMapJSONParser( String _jsonData ) throws ParseException {
		System.out.println("# JsonToMapJSONParser #");
		
		JSONParser parser = new JSONParser();
    	JSONObject output = (JSONObject)parser.parse(_jsonData);
    	JSONArray outputList = (JSONArray)output.get("list");
		
		return outputList;
	}
	
	// JsonToMap using Gson.
	public List<Map<String, Object>> jsonToMapGson( String _jsonData ) {
		System.out.println("# JsonToMapGson #");
		
		Gson gson = new Gson();
		
		Map<String, Object> jsonObject = gson.fromJson(_jsonData, new TypeToken<Map<String, Object>>(){}.getType());
		List<Map<String, Object>> jsonList = (List) jsonObject.get("list");
		
		return jsonList;
	}
		
	// JsonToDto.
	public String jsonToDto( String _jsonData ) {
		System.out.println("# JsonToDto #");
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			// Convert JSON string from file to Object
			String jsonFile = "E:\\Project\\spring_workspace\\localTest\\src\\main\\webapp\\resources\\sample\\jsonTest.json";
			UserInfoDTO dto = mapper.readValue(new File(jsonFile), UserInfoDTO.class);
			System.out.println(dto);

			// Convert JSON string to Object
//			UserInfoDTO dto1 = mapper.readValue(_jsonData, UserInfoDTO.class);
//			System.out.println(dto1);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	// DtoToJson.
	public String dtoToJson( String _jsonData ) {
		System.out.println("# JsonToDto #");
		
		ObjectMapper mapper = new ObjectMapper();
		UserInfoDTO dto = createDummyData();
		
		try {
			//Convert object to JSON string and save into file directly 
			String jsonFile = "E:\\Project\\spring_workspace\\localTest\\src\\main\\webapp\\resources\\sample\\jsonTest.json";
			mapper.writeValue(new File(jsonFile), dto);
			
			// Convert Object to JSON string.
			String jsonString = mapper.writeValueAsString(dto);
			System.out.println(jsonString);
			
			// PrettyPrinter.
			jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
			System.out.println(jsonString);
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	private static UserInfoDTO createDummyData(){
			
		UserInfoDTO user = new UserInfoDTO();
		
		user.setUserId("170704");
		user.setName("나윤찬");

		List<String> roll = new ArrayList<>();
		roll.add("잘생김");
		roll.add("멋짐");
		roll.add("잘남");

		user.setRoll(roll);
		
		return user;		
	}
		
	
	public static void main(String[] args) {
	    
		JsonParser parser = new JsonParser();
        HashMap<String, Object> reqParam = new HashMap<String, Object>();
                
        try {        	
            String urlstr = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";           
            reqParam.put("serviceKey", WebConstants.SERVICE_KEY);
            reqParam.put("numOfRows", "10");
            reqParam.put("pageNo", "1");
            reqParam.put("sidoName", "서울");
            reqParam.put("ver", "1.3");
            reqParam.put("_returnType", "json");

            // Get data.
            String resultMsg = HttpUrlConnection.httpGet(urlstr, reqParam);
            System.out.println("[resultMsg] " + resultMsg);
            
            if(!"".equals(resultMsg) || resultMsg != null) {
//            	System.out.println(parser.jsonToMapGson(resultMsg));
//            	System.out.println(parser.jsonToMapJSONParser(resultMsg));
            	System.out.println(parser.dtoToJson(resultMsg));
            	System.out.println(parser.jsonToDto(resultMsg));
            }
            
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
