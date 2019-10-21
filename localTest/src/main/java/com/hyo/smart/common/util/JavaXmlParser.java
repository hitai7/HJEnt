package com.hyo.smart.common.util;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.hyo.smart.common.WebConstants;

public class JavaXmlParser {
	
	// XML Parse.
	public String xmlToJson( String _plainText ) {
		System.out.println("# xmlToJson #");
		return "";
	}
		
	public static void main(String[] args) throws UnsupportedEncodingException {
	            
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document xml = null;
        HashMap<String, Object> reqParam = new HashMap<String, Object>();
        
        try {        	
            String urlstr = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";           
            reqParam.put("serviceKey", WebConstants.SERVICE_KEY);
            reqParam.put("numOfRows", "10");
            reqParam.put("pageNo", "1");
            reqParam.put("sidoName", "서울");
            reqParam.put("ver", "1.3");
            
            InputSource is = new InputSource(new StringReader(HttpUrlConnection.httpGet(urlstr, reqParam)));            
//            String xmlFile = "E:\\Project\\spring_workspace\\localTest\\src\\main\\webapp\\resources\\sample\\xmlTest.xml";
//            InputSource is = new InputSource(new FileReader("E:\\Project\\spring_workspace\\localTest\\src\\main\\webapp\\resources\\sample\\xmlTest.xml"));
            
            xml = factory.newDocumentBuilder().parse(is);

            // Get root element.
            Element element = xml.getDocumentElement();
            // Get child node.
            NodeList list = element.getChildNodes();

            if(list.getLength() > 0) {
                for (int i=0; i<list.getLength(); i++) {
	                NodeList childList = list.item(i).getChildNodes();

	                if(childList.getLength() > 0) {
	                    for (int j = 0; j < childList.getLength(); j++) {
	                    	
		                    if(!childList.item(j).getNodeName().equals("#text")) {
		                        System.out.println("["+i+"] " + childList.item(j).getNodeName() + " : " + childList.item(j).getTextContent());                                
		                    }
	                    }
	                }
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
}
