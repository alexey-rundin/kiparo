package com.kiparo.homework.model;

import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsItem implements Serializable, Comparable<NewsItem> {

    @SerializedName("id")
    private int id;

    @SerializedName("date")
    private String date;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("keywords")
    @XmlElementWrapper(name = "keywords")
    @XmlElement(name = "element")
    private List<String> keywords;

    @SerializedName("visible")
    private boolean visible;

    @SneakyThrows
    public String getDate() {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        SimpleDateFormat changedFormat = new SimpleDateFormat("dd MMMM yyyy");
        Date parsedDate = originalFormat.parse(this.date);
        return changedFormat.format(parsedDate);
    }

    @SneakyThrows
    @Override
    public int compareTo(NewsItem item) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        return sdf.parse(getDate()).compareTo(sdf.parse(item.getDate()));
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "\nДата: " + getDate() +
                "\nЗаголовок: " + title +
                "\nОписание: " + description +
                "\nПоисковые теги: " + keywords;
    }
}
