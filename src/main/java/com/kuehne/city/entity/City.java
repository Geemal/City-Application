package com.kuehne.city.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class City {

    public City() {
    }

    public City(String name, String link) {
        this.name = name;
        this.link = link;
    }

    @Id
    @SequenceGenerator(
            name = "city_seq",
            sequenceName = "city_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "city_seq"
    )
    private Integer citySeq;
    private String name;

    private String link;

    public Integer getCitySeq() {
        return citySeq;
    }

    public void setCitySeq(Integer citySeq) {
        this.citySeq = citySeq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
