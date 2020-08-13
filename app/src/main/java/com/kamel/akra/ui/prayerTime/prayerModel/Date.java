package com.kamel.akra.ui.prayerTime.prayerModel;

import com.google.gson.annotations.SerializedName;

public class Date{

	@SerializedName("hijri")
	private String hijri;

	@SerializedName("gregorian")
	private String gregorian;

	@SerializedName("timestamp")
	private int timestamp;

	public void setHijri(String hijri){
		this.hijri = hijri;
	}

	public String getHijri(){
		return hijri;
	}

	public void setGregorian(String gregorian){
		this.gregorian = gregorian;
	}

	public String getGregorian(){
		return gregorian;
	}

	public void setTimestamp(int timestamp){
		this.timestamp = timestamp;
	}

	public int getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return 
			"Date{" + 
			"hijri = '" + hijri + '\'' + 
			",gregorian = '" + gregorian + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}