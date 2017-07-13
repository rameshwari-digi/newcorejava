package com.niit.coreapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueryTestCase {

	
	QueryParameter queryParameter ;
	QueryParser queryParser ;
	
	@Test
	public void testQuery() 
	{
		//queryParameter =  new QueryParameter();
		queryParser = new QueryParser();
		assertNotNull(queryParser.splitArray());
			
	}
	
	
}
