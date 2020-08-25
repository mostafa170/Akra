package com.kamel.akra.ui.prayerTime.cityEgy;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("country")
	private String country;

	@SerializedName("capital")
	private String capital;

	@SerializedName("lng")
	private String lng;

	@SerializedName("city")
	private String city;

	@SerializedName("admin")
	private String admin;

	@SerializedName("population_proper")
	private String populationProper;

	@SerializedName("iso2")
	private String iso2;

	@SerializedName("lat")
	private String lat;

	@SerializedName("population")
	private String population;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCapital(String capital){
		this.capital = capital;
	}

	public String getCapital(){
		return capital;
	}

	public void setLng(String lng){
		this.lng = lng;
	}

	public String getLng(){
		return lng;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setAdmin(String admin){
		this.admin = admin;
	}

	public String getAdmin(){
		return admin;
	}

	public void setPopulationProper(String populationProper){
		this.populationProper = populationProper;
	}

	public String getPopulationProper(){
		return populationProper;
	}

	public void setIso2(String iso2){
		this.iso2 = iso2;
	}

	public String getIso2(){
		return iso2;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
	}

	public void setPopulation(String population){
		this.population = population;
	}

	public String getPopulation(){
		return population;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"country = '" + country + '\'' + 
			",capital = '" + capital + '\'' + 
			",lng = '" + lng + '\'' + 
			",city = '" + city + '\'' + 
			",admin = '" + admin + '\'' + 
			",population_proper = '" + populationProper + '\'' + 
			",iso2 = '" + iso2 + '\'' + 
			",lat = '" + lat + '\'' + 
			",population = '" + population + '\'' + 
			"}";
		}
}