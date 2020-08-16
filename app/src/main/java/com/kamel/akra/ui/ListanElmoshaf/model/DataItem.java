package com.kamel.akra.ui.ListanElmoshaf.model;

public class DataItem{
	private String pageNumber;
	private String readerName;
	private String link;
	private String id;
	private String type;
	private String sora;
	private String soraNumber;
	private String ayatsNumber;

	public void setPageNumber(String pageNumber){
		this.pageNumber = pageNumber;
	}

	public String getPageNumber(){
		return pageNumber;
	}

	public void setReaderName(String readerName){
		this.readerName = readerName;
	}

	public String getReaderName(){
		return readerName;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setSora(String sora){
		this.sora = sora;
	}

	public String getSora(){
		return sora;
	}

	public void setSoraNumber(String soraNumber){
		this.soraNumber = soraNumber;
	}

	public String getSoraNumber(){
		return soraNumber;
	}

	public void setAyatsNumber(String ayatsNumber){
		this.ayatsNumber = ayatsNumber;
	}

	public String getAyatsNumber(){
		return ayatsNumber;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"pageNumber = '" + pageNumber + '\'' + 
			",readerName = '" + readerName + '\'' + 
			",link = '" + link + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",sora = '" + sora + '\'' + 
			",soraNumber = '" + soraNumber + '\'' + 
			",ayatsNumber = '" + ayatsNumber + '\'' + 
			"}";
		}
}
