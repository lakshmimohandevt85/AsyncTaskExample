package com.sdsu.cs646.lakshmi.restcall;

/**
 * Created by lakshmimohandev on 7/6/16.
 */
public class Data
{
    private  String id;
    private String date;
    private String content;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
