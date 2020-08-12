package com.kamel.akra.ui.Azkar.AzkarlListan.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AzkarResponse {

	@SerializedName("AzkarItemData")
	private List<AzkarItemData> azkarItemData;

	public List<AzkarItemData> getAzkarItemData() {
		return azkarItemData;
	}

	public void setAzkarItemData(List<AzkarItemData> azkarItemData) {
		this.azkarItemData = azkarItemData;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"AzkarItemData = '" + azkarItemData + '\'' +
			"}";
		}
}