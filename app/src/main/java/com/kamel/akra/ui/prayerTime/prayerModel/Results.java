package com.kamel.akra.ui.prayerTime.prayerModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Results{

	@SerializedName("settings")
	private Settings settings;

	@SerializedName("datetime")
	private List<DatetimeItem> datetime;

	@SerializedName("location")
	private Location location;

	public void setSettings(Settings settings){
		this.settings = settings;
	}

	public Settings getSettings(){
		return settings;
	}

	public void setDatetime(List<DatetimeItem> datetime){
		this.datetime = datetime;
	}

	public List<DatetimeItem> getDatetime(){
		return datetime;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	@Override
 	public String toString(){
		return 
			"Results{" + 
			"settings = '" + settings + '\'' + 
			",datetime = '" + datetime + '\'' + 
			",location = '" + location + '\'' + 
			"}";
		}
}