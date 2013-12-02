package br.ufrn.ru_ufrn.model.dao;

import android.content.ContentValues;
import android.database.Cursor;

public class SQLiteEntity {
	
	private String tableName;
	private ContentValues values;
	private String query;
	private String[] args;
	private Cursor result ;
	private Integer idSaved;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public ContentValues getValues() {
		return values;
	}
	public void setValues(ContentValues values) {
		this.values = values;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Cursor getResult() {
		return result;
	}
	public void setResult(Cursor result) {
		this.result = result;
	}
	public Integer getIdSaved() {
		return idSaved;
	}
	public void setIdSaved(Integer idSaved) {
		this.idSaved = idSaved;
	}
	public String[] getArgs() {
		return args;
	}
	public void setArgs(String[] args) {
		this.args = args;
	}
	
	
}
