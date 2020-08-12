package com.kamel.akra.ui.prayerTime.prayerModel;

import com.google.gson.annotations.SerializedName;

public class Times{

	@SerializedName("Sunset")
	private String sunset;

	@SerializedName("Asr")
	private String asr;

	@SerializedName("Isha")
	private String isha;

	@SerializedName("Fajr")
	private String fajr;

	@SerializedName("Dhuhr")
	private String dhuhr;

	@SerializedName("Maghrib")
	private String maghrib;

	@SerializedName("Sunrise")
	private String sunrise;

	@SerializedName("Midnight")
	private String midnight;

	@SerializedName("Imsak")
	private String imsak;

	public void setSunset(String sunset){
		this.sunset = sunset;
	}

	public String getSunset(){
		return sunset;
	}

	public void setAsr(String asr){
		this.asr = asr;
	}

	public String getAsr(){
		return asr;
	}

	public void setIsha(String isha){
		this.isha = isha;
	}

	public String getIsha(){
		return isha;
	}

	public void setFajr(String fajr){
		this.fajr = fajr;
	}

	public String getFajr(){
		return fajr;
	}

	public void setDhuhr(String dhuhr){
		this.dhuhr = dhuhr;
	}

	public String getDhuhr(){
		return dhuhr;
	}

	public void setMaghrib(String maghrib){
		this.maghrib = maghrib;
	}

	public String getMaghrib(){
		return maghrib;
	}

	public void setSunrise(String sunrise){
		this.sunrise = sunrise;
	}

	public String getSunrise(){
		return sunrise;
	}

	public void setMidnight(String midnight){
		this.midnight = midnight;
	}

	public String getMidnight(){
		return midnight;
	}

	public void setImsak(String imsak){
		this.imsak = imsak;
	}

	public String getImsak(){
		return imsak;
	}

	@Override
 	public String toString(){
		return 
			"Times{" + 
			"sunset = '" + sunset + '\'' + 
			",asr = '" + asr + '\'' + 
			",isha = '" + isha + '\'' + 
			",fajr = '" + fajr + '\'' + 
			",dhuhr = '" + dhuhr + '\'' + 
			",maghrib = '" + maghrib + '\'' + 
			",sunrise = '" + sunrise + '\'' + 
			",midnight = '" + midnight + '\'' + 
			",imsak = '" + imsak + '\'' + 
			"}";
		}
}