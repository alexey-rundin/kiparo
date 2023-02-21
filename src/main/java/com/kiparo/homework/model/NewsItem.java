package com.kiparo.homework.model;

import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsItem implements Serializable {

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
}
