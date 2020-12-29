package com.musicalinstrument.demo.jpa;

import javax.persistence.*;
import java.util.List;

@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name="test3";
    @Column(name = "country", nullable = false)
    private String country="Kharkiv";

    @OneToMany(mappedBy = "brand",fetch = FetchType.LAZY)
    private List<Music_Instrument> music_instruments;

    public Brand() {
    }
    public Brand(String name, String country, Long id) {
        this.name=name;
        this.country=country;
        this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Music_Instrument> getMusic_instruments() {
        return music_instruments;
    }

    public void setMusic_instruments(List<Music_Instrument> music_instruments) {
        this.music_instruments = music_instruments;
    }
    public void restore (String backup) {
        this.name = backup;
    }

    public String backup() {
        return this.name;
    }
}

