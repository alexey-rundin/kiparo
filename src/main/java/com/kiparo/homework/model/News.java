package com.kiparo.homework.model;

import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class News implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private String location;

    @SerializedName("news")
    @XmlElementWrapper(name = "news")
    @XmlElement(name = "element")
    private List<NewsItem> newsItems;
}
