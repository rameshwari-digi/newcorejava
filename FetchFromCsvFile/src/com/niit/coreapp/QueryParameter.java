package com.niit.coreapp;

public class QueryParameter {

	private String fromPath;
	private String firstCondition;
	private String secondCondition;
	private String orderByColumn, selectColumn, sortByColumn, filepath, groupByColumn;
	private boolean orderByExist, allColumnExist, hasorderbyField, hasgroupbyField, hasWhere;

	public String getFilepath()
	{
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public FieldsNames fieldsNames = new FieldsNames();
	CriteriaQuery criteriaQuery = new CriteriaQuery();

	public void querySplit(String inputQuery) {
		firstCondition = null;
		secondCondition = null;
		if (inputQuery.contains("order by")) {
			firstCondition = inputQuery.split("order by")[0].trim();
			orderByColumn = inputQuery.split("order by")[1].trim();
			//String relationalqry = secondCondition.split("and|or")[0].trim();
			firstCondition = firstCondition.split("from")[0].trim();
			selectColumn = firstCondition.split("select")[1].trim();
			this.columnEvaluation(selectColumn);
			//this.comparsionExpression(relationalqry);
			orderByExist = true;
		}
		if (inputQuery.contains("group by")) 
		{
			firstCondition = inputQuery.split("group by")[0].trim();
			groupByColumn = inputQuery.split("group by")[1].trim();
			if (inputQuery.contains("where")) 
			{
				secondCondition = firstCondition.split("where")[1].trim();
				String releationQry = secondCondition.split("and|or")[0].trim();
				//this.columnEvaluation(releationQry);
				this.comparsionExpression(releationQry);
				firstCondition = firstCondition.split("where")[0].trim();
				
				
			}
			firstCondition=firstCondition.split("from")[0].trim();
			secondCondition=firstCondition.split("select")[1].trim();
			this.columnEvaluation(groupByColumn);
			hasgroupbyField = true;
			
		} 
		
		else if (inputQuery.contains("where")) {
			firstCondition = inputQuery.split("where")[0];
			secondCondition = inputQuery.split("where")[1];
			secondCondition = secondCondition.trim();
			String relationalqry = secondCondition.split("and|or")[0].trim();
			//System.out.println(relationalqry);
			this.comparsionExpression(relationalqry);
			selectColumn = firstCondition.split("select")[1].trim();
			this.columnEvaluation(selectColumn);
			hasWhere = true;
		}

		else {
			firstCondition = inputQuery.split("from")[0].trim();
			selectColumn = firstCondition.split("select")[1].trim();
			this.columnEvaluation(selectColumn);
		}
		System.out.println("first=="+firstCondition);
		System.out.println("second=="+secondCondition);
		System.out.println("order by="+orderByColumn);
		System.out.println("select ="+selectColumn);
		System.out.println(criteriaQuery.getColumn() + "---->" + criteriaQuery.getOperators() + "------->"
				+ criteriaQuery.getValue());

	}

	public void columnEvaluation(String inputcolumn) {
		if (inputcolumn.trim().contains("*") && inputcolumn.length() == 1) {
			allColumnExist = true;

		}
		if (inputcolumn.trim().contains(",")) {
			String fieldList[] = inputcolumn.split(",");
			int i = 0;
			for (String column : fieldList) {
				fieldsNames.fieldname.put(column, i);
				i++;

			}
		}
	}

	@SuppressWarnings("unused")
	private void comparsionExpression(String releationQuery) {
		String operators[] = { ">", "<", ">=", "<=", "=", "!=" };

		for (String operator : operators) {
			if (releationQuery.contains(operator)) {
				criteriaQuery.setColumn(releationQuery.split(operator)[0].trim());
				criteriaQuery.setValue(releationQuery.split(operator)[1].trim());
				criteriaQuery.setOperators(operator);
				break;
			}
		}
	}
}
