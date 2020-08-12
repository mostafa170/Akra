package com.kamel.akra.ui.Azkar.AzkarlListan.model;
import com.google.gson.annotations.SerializedName;

public class AzkarItemData {
    @SerializedName("reader_img")
    private String readerImg;

    @SerializedName("reader_name")
    private String readerName;

    @SerializedName("link")
    private String link;

    @SerializedName("Name")
    private String name;

    public void setReaderImg(String readerImg){
        this.readerImg = readerImg;
    }

    public String getReaderImg(){
        return readerImg;
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

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return
                "Response{" +
                        "reader_img = '" + readerImg + '\'' +
                        ",reader_name = '" + readerName + '\'' +
                        ",link = '" + link + '\'' +
                        ",name = '" + name + '\'' +
                        "}";
    }
}
