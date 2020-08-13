package com.kamel.akra.ui.prayerTime.prayerModel;

import com.google.gson.annotations.SerializedName;

public class Location{

	@SerializedName("elevation")
	private double elevation;

	@SerializedName("country")
	private String country;

	@SerializedName("country_code")
	private String countryCode;

	@SerializedName("local_offset")
	private double localOffset;

	@SerializedName("city")
	private String city;

	@SerializedName("timezone")
	private String timezone;

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("longitude")
	private double longitude;

	public void setElevation(double elevation){
		this.elevation = elevation;
	}

	public double getElevation(){
		return elevation;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setLocalOffset(double localOffset){
		this.localOffset = localOffset;
	}

	public double getLocalOffset(){
		return localOffset;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setTimezone(String timezone){
		this.timezone = timezone;
	}

	public String getTimezone(){
		return timezone;
	}

	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setLongitude(double longitude){
		this.longitude = longitude;
	}

	public double getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"Location{" + 
			"elevation = '" + elevation + '\'' + 
			",country = '" + country + '\'' + 
			",country_code = '" + countryCode + '\'' + 
			",local_offset = '" + localOffset + '\'' + 
			",city = '" + city + '\'' + 
			",timezone = '" + timezone + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}