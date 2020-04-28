package Control;

import javax.servlet.http.HttpServletResponse;

import Model.Model;

import java.io.IOException;
import java.util.*;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Controller {
	
	private String firstNum;
	private String scndNum;
	private String operation;
	private String JSONString;
	private ArrayList <String> res;
	
	public Controller (String firstNum, String scndNum, String operation) {
		this.firstNum =  firstNum;
		this.scndNum = scndNum;
		this.operation = operation;
	}
	
	public Controller (String JSONString) {
		this.JSONString = JSONString;
	}
	
	public ArrayList <String> doGet () {
		
		byte[] decodedBytes = Base64.getDecoder().decode(operation);
		String decodedOperation = new String(decodedBytes);
		
		if (decodedOperation.equals("+") || decodedOperation.equals("-") || decodedOperation.equals("*") || decodedOperation.equals("/") || decodedOperation.equals("%")) {
        	
        	try {
        		try {
        			Model <Long> longModel = new Model <> (Long.valueOf(Long.parseLong(firstNum)),Long.valueOf(Long.parseLong(scndNum)));        		
        			Long longAns = null; 
        			Double divLongAns = null;
        			res = new ArrayList <> ();
        			
        			switch (decodedOperation) {
        				case "+" : longAns = longModel.addNumbers();         						   
        						   res.add("Server Response Code " + Integer.toString(HttpServletResponse.SC_OK));
        						   res.add("Addition of " + firstNum + " & " + scndNum + " is : " + longAns.toString());        						   
        						   break;
        				
        				case "-" : longAns = longModel.substractNumbers(); 
        						   res.add("Server Response Code " + Integer.toString(HttpServletResponse.SC_OK));
        						   res.add("Substracting " + scndNum + " From " + firstNum + " is : " + longAns.toString());						 		   
						 		   break;
						 
        				case "*" : longAns = longModel.multiplyNumbers();
        						   res.add("Server Response Code " + Integer.toString(HttpServletResponse.SC_OK));
        						   res.add("Multiplication of " + firstNum + " & " + scndNum + " is : " + longAns.toString());
		 				 		   break;
		 				 				 
        				case "/" : divLongAns = longModel.<Double>divideNumbers();
        						   res.add("Server Response Code " + Integer.toString(HttpServletResponse.SC_OK));
        						   res.add("Division of " + firstNum + " By " + scndNum + " is : " + divLongAns.toString());
		 				 		   break;
		 				 
        				case "%" : longAns = longModel.modulusNumbers(); 
        						   res.add("Server Response Code " + Integer.toString(HttpServletResponse.SC_OK));
        						   res.add("Modulus of " + firstNum + " by " + scndNum + " is : " + longAns.toString());
		 				 		   break;
        			  }     
        			  return res;
        		 }
        		 catch (NumberFormatException e){
        			Model <Double> doubleModel = new Model <> (Double.valueOf(Double.parseDouble(firstNum)),Double.valueOf(Double.parseDouble(scndNum)));
        			Double doubleAns = null;
        			res = new ArrayList <> ();
        			
        			switch (decodedOperation) {
        				case "+" : doubleAns = doubleModel.addNumbers();
        						   res.add("Server Response Code " + Integer.toString(HttpServletResponse.SC_OK));
        						   res.add("Addition of " + firstNum + " & " + scndNum + " is : " + doubleAns.toString());
        						   break;
        				
        				case "-" : doubleAns = doubleModel.substractNumbers(); 
        						   res.add("Server Response Code " + Integer.toString(HttpServletResponse.SC_OK));
        						   res.add("Substracting  " + scndNum + " From " + firstNum + " is : " + doubleAns.toString());
						 		   break;
						 
        				case "*" : doubleAns = doubleModel.multiplyNumbers();
        				           res.add("Server Response Code " + Integer.toString(HttpServletResponse.SC_OK));
        				           res.add("Multiplication of " + firstNum + " & " + scndNum + " is : " + doubleAns.toString());
        				           
		 				 		   break;
		 				 				 
        				case "/" : doubleAns = doubleModel.<Double>divideNumbers();
        					       res.add("Server Response Code " + Integer.toString(HttpServletResponse.SC_OK));
        					       res.add("Division of " + firstNum + " By " + scndNum + " is : " + doubleAns.toString());
		 				 		   break;
		 				 
        				case "%" : doubleAns = doubleModel.modulusNumbers();
        						   res.add("Server Response Code " + Integer.toString(HttpServletResponse.SC_OK));				   
        						   res.add("Modulus of " + firstNum + " by " + scndNum + " is : " + doubleAns.toString());
		 				 		   break;
        		    }
        			return res;
        	   }
        	}
        	catch (Exception e){
        		//System.out.println (e);
        		res = new ArrayList <> ();
        		res.add("Srever Response Code " + Integer.toString(HttpServletResponse.SC_BAD_REQUEST));
        		res.add("Wrong Formatting of Input Numbers. The System Only Supports Integers and/or Doubles" );        		
        		return res;
        	}
        	
        }
        else {
        	res = new ArrayList <> ();
        	res.add("Srever Response Code " + Integer.toString(HttpServletResponse.SC_BAD_REQUEST));
        	res.add("Wrong Operation Requested. System Only Supports +, -, *, /, %" );
        	return res;
        }     
	}
	
	void parseJson () throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(JSONString.getBytes());
		JsonNode fstNumber = rootNode.path("FirstNumber");	
		JsonNode scndNumber = rootNode.path("SecondNumber");
		JsonNode opration = rootNode.path("Operation");
		
		firstNum =  fstNumber.asText();
		scndNum = scndNumber.asText();
		operation = opration.asText();
		 
	}
	
	public ArrayList <String> doPost () {
		try {
			parseJson ();	
			return (doGet());
		}
		catch (IOException e) {
			res = new ArrayList <> ();
        	res.add("Srever Response Code " + Integer.toString(HttpServletResponse.SC_BAD_REQUEST));
        	res.add("Wrong Formatting of JSON data" );
        	return res;
		}
		
		
	}
	

}
