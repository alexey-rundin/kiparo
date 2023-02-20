package com.kiparo.homework.model;

import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
@XmlRootElement(name = "element")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class NewsItem implements Serializable {

    @SerializedName("date")
    private String date;

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private int id;

    private Keywords keywordsObj;

    @SerializedName("title")
    private String title;

    @SerializedName("visible")
    private boolean visible;

    @XmlElement(name = "element")
    @SerializedName("keywords")
    private List<String> keywordsList;
}
