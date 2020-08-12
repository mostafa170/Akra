package com.kamel.akra.ui.prayerTime.prayerModel;

import com.google.gson.annotations.SerializedName;

public class DatetimeItem{

	@SerializedName("date")
	private Date date;

	@SerializedName("times")
	private Times times;

	public void setDate(Date date){
		this.date = date;
	}

	public Date getDate(){
		return date;
	}

	public void setTimes(Times times){
		this.times = times;
	}

	public Times getTimes(){
		return times;
	}

	@Override
 	public String toString(){
		return 
			"DatetimeItem{" + 
			"date = '" + date + '\'' + 
			",times = '" + times + '\'' + 
			"}";
		}
}