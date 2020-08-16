package com.kamel.akra.ui.ListanElmoshaf.model;

import java.util.List;

public class ResponseQuran {
	private List<DataItem> data;
	private int count;
	private String message;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"data = '" + data + '\'' + 
			",count = '" + count + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}