package com.musicalinstrument.demo.jpa;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
@Entity
public class Simple_Instrument {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long idSimpleInsrtument;
    @Column(
            name = "Material",
            nullable = false
    )
    private String Material = "дерево";
    @Column(
            name = "rezonator",
            nullable = false
    )
    private Integer rezonator = 13;
    @Column(
            name = "Num_pedal_keyboard",
            nullable = true
    )
    private Integer Num_pedal_keyboard;

    @Column(
            name = "raspro_keyboard",
            nullable = true
    )
    private String raspro_keyboard;
    @JsonManagedReference
    @OneToMany(mappedBy = "simpleInstrument",fetch = FetchType.LAZY)
    private List<Music_Instrument> music_instruments;

    @Column(
            name = "Sours_wind",
            nullable = true
    )
    private String Sours_wind = "";
    @Column(
            name = "num_srtings_stringed",
            nullable = true
    )
    private Integer num_srtings_stringed;
    @Column(
            name = "type",
            nullable = false
    )
    private String type = "stringed";
    @Column(
            name = "lenght_string_stringed",
            nullable = true
    )
    private Double lenght_string_stringed;

    public void setIdSimpleInsrtument(Long idSimpleInsrtument) {
        this.idSimpleInsrtument = idSimpleInsrtument;
    }

    public void setMaterial(String material) {
        Material = material;
    }



    public void setNum_pedal_keyboard(Integer num_pedal_keyboard) {
        Num_pedal_keyboard = num_pedal_keyboard;
    }

    public void setRaspro_keyboard(String raspro_keyboard) {
        this.raspro_keyboard = raspro_keyboard;
    }

    public void setMusic_instruments(List<Music_Instrument> music_instruments) {
        this.music_instruments = music_instruments;
    }

    public void setSours_wind(String sours_wind) {
        Sours_wind = sours_wind;
    }

    public void setNum_srtings_stringed(Integer num_srtings_stringed) {
        this.num_srtings_stringed = num_srtings_stringed;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLenght_string_stringed(Double lenght_string_stringed) {
        this.lenght_string_stringed = lenght_string_stringed;
    }

    public Long getIdSimpleInsrtument() {
        return idSimpleInsrtument;
    }

    public String getMaterial() {
        return Material;
    }

    public Integer getRezonator() {
        return rezonator;
    }

    public void setRezonator(Integer rezonator) {
        this.rezonator = rezonator;
    }

    public Integer getNum_pedal_keyboard() {
        return Num_pedal_keyboard;
    }

    public String getRaspro_keyboard() {
        return raspro_keyboard;
    }

    public List<Music_Instrument> getMusic_instruments() {
        return music_instruments;
    }

    public String getSours_wind() {
        return Sours_wind;
    }

    public Integer getNum_srtings_stringed() {
        return num_srtings_stringed;
    }

    public String getType() {
        return type;
    }

    public Double getLenght_string_stringed() {
        return lenght_string_stringed;
    }

    public Simple_Instrument() {
    }

    @Override
    public String toString() {
        return "Simple_Instrument{" +
                "idSimpleInsrtument=" + idSimpleInsrtument +
                ", Material='" + Material + '\'' +
                ", rezonator=" + rezonator +
                ", Num_pedal_keyboard=" + Num_pedal_keyboard +
                ", raspro_keyboard='" + raspro_keyboard + '\'' +
                ", music_instruments=" + music_instruments +
                ", Sours_wind='" + Sours_wind + '\'' +
                ", num_srtings_stringed=" + num_srtings_stringed +
                ", type='" + type + '\'' +
                ", lenght_string_stringed=" + lenght_string_stringed +
                '}';
    }
}
