package ru.homework.model;

import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * @author Alexey Rundin
 * @since 20.02.2023
 */
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ItNews {

    @SerializedName("location")
    private String location;

    @SerializedName("name")
    private String name;

    @XmlElement(name = "news")
    private News news;
}
