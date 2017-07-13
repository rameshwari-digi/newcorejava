package com.niit.coreapp;

import java.io.BufferedReader;
import java.io.FileReader;

public class UserQuery
{
	static String receivedQuery,querySplit[],classPath,firstcondition, secondcondition,columns;
boolean	hasSelect,hasAsterisk,whereExists,groupbyExists,sortbyExists,orderbyExists,byExists,hasSum,hasCount;

int queryLength, querySplitLength;



QueryParameter customObject = new QueryParameter();

public String[] splitArray()
{
	InputData inputobj = new InputData();
	receivedQuery = inputobj.passingQuery();
	querySplit= receivedQuery.split(" ");
	queryLength= receivedQuery.length();
	querySplitLength = querySplit.length;
	
	return querySplit;
	
}

public String[] csvParameter()
{
	for(int i=0;i<querySplitLength;i++)
	{
		if(querySplit[i].contains(".csv"))
		{
			classPath= querySplit[i];
			
		}
		
	}
	columns=querySplit[1];
	System.out.println(classPath+columns);
	
	return querySplit;
}


public boolean inputValidExpression(String receivedQuery)
{
	if(receivedQuery.contains("select")&& receivedQuery.contains("from")||receivedQuery.contains("*")||receivedQuery.contains("where")||receivedQuery.contains("group by")||receivedQuery.contains("sort by")||receivedQuery.contains("order by"))
    {
        return true;
    }    
    else{
    return false;    
    }

}

public void sendCSVParam()
{
	customObject.setFilepath(classPath);
	if(executeQuery(receivedQuery))
	{
		customObject.querySplit(receivedQuery);
	}

}

public boolean executeQuery(String receivedQuery)
{
   if(inputValidExpression(receivedQuery))
    {
     QueryParameter custobj=new QueryParameter();
     custobj.querySplit(receivedQuery);
    	
    
     return true;
    }
    else
    {
    System.out.println("Improper query format");    
    return false;
    } 
}



}
