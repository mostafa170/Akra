package com.kamel.akra.ui.prayerTime.prayerModel;

import com.google.gson.annotations.SerializedName;

public class Settings{

	@SerializedName("school")
	private String school;

	@SerializedName("juristic")
	private String juristic;

	@SerializedName("timeformat")
	private String timeformat;

	@SerializedName("highlat")
	private String highlat;

	@SerializedName("fajr_angle")
	private double fajrAngle;

	@SerializedName("isha_angle")
	private double ishaAngle;

	public void setSchool(String school){
		this.school = school;
	}

	public String getSchool(){
		return school;
	}

	public void setJuristic(String juristic){
		this.juristic = juristic;
	}

	public String getJuristic(){
		return juristic;
	}

	public void setTimeformat(String timeformat){
		this.timeformat = timeformat;
	}

	public String getTimeformat(){
		return timeformat;
	}

	public void setHighlat(String highlat){
		this.highlat = highlat;
	}

	public String getHighlat(){
		return highlat;
	}

	public void setFajrAngle(double fajrAngle){
		this.fajrAngle = fajrAngle;
	}

	public double getFajrAngle(){
		return fajrAngle;
	}

	public void setIshaAngle(double ishaAngle){
		this.ishaAngle = ishaAngle;
	}

	public double getIshaAngle(){
		return ishaAngle;
	}

	@Override
 	public String toString(){
		return 
			"Settings{" + 
			"school = '" + school + '\'' + 
			",juristic = '" + juristic + '\'' + 
			",timeformat = '" + timeformat + '\'' + 
			",highlat = '" + highlat + '\'' + 
			",fajr_angle = '" + fajrAngle + '\'' + 
			",isha_angle = '" + ishaAngle + '\'' + 
			"}";
		}
}