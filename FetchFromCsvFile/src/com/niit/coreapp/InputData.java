package com.niit.coreapp;

import java.util.*;
public class InputData 
{
	static UserQuery userQuery = new UserQuery();
	
	
	@SuppressWarnings("resource")
	public String passingQuery()
	{
		String inputQuery;
		Scanner scannerObj = new Scanner(System.in);
		System.out.println("Enter the Query");
		inputQuery= scannerObj.nextLine();
		userQuery.executeQuery(inputQuery);
		return inputQuery;		
	}
	
	public static void main(String[] arg) throws Exception
	{
		userQuery.splitArray();
		userQuery.csvParameter();
		userQuery.sendCSVParam();
				
	}

	
}
