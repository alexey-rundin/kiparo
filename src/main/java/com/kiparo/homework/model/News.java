package com.kiparo.homework.model;

import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class News implements Serializable {
    @XmlElement(name = "element")
    @SerializedName("news")
    private List<NewsItem> newsItems;
}
