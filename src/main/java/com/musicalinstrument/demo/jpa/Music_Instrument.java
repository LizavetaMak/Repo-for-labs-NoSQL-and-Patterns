package com.musicalinstrument.demo.jpa;




import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Music_Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMusicInstrument;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "price",
            nullable = false
    )
    private Double price;
    @Column(
            name = "size",
            nullable = false
    )
    private Double size;

    @Column(
            name = "name_type",
            nullable = false
    )
    private String name_type;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Brand brand= new Brand();

    @JsonBackReference
    @ManyToOne(
            optional = false
    )
    @JoinColumn(
            name = "idSimpleInsrtument",
            nullable = true
    )
    private Simple_Instrument simpleInstrument ;
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ElectroInsrtument", nullable = true)
    private Electro_Instrument electroInstrument ;


    public Music_Instrument() {
        name="guitar";
        price=0.01;
        size=110.0;
        name_type="simple insrtument";
       // electroInstrument = new Electro_Instrument();
       // simpleInstrument=new Simple_Instrument();
    }

    public Long getId() {
        return idMusicInstrument;
    }

    public void setId(Long id) {
        this.idMusicInstrument = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getName_type() {
        return name_type;
    }

    public void setName_type(String name_type) {
        this.name_type = name_type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Simple_Instrument getSimpleInstrument() {
        return simpleInstrument;
    }

    public void setSimpleInstrument(Simple_Instrument simpleInstrument) {
        this.simpleInstrument = simpleInstrument;
    }

    public Electro_Instrument getElectroInstrument() {
        return electroInstrument;
    }

    public void setElectroInstrument(Electro_Instrument electroInstrument) {
        this.electroInstrument = electroInstrument;
    }

    @Override
    public String toString() {
        return "Music_Instrument{" +
                "idMusicInstrument=" + idMusicInstrument +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", name_type='" + name_type + '\'' +
                ", brand=" + brand +
                ", simpleInstrument=" + simpleInstrument +
                ", electroInstrument=" + electroInstrument +
                '}';
    }
}
