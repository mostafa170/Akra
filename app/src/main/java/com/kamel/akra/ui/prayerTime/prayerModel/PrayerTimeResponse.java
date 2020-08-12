package com.kamel.akra.ui.prayerTime.prayerModel;

import com.google.gson.annotations.SerializedName;

public class PrayerTimeResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("results")
	private Results results;

	@SerializedName("status")
	private String status;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setResults(Results results){
		this.results = results;
	}

	public Results getResults(){
		return results;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"PrayerTimeResponse{" + 
			"code = '" + code + '\'' + 
			",results = '" + results + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}