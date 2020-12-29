package com.musicalinstrument.demo.jpa;
import javax.persistence.*;
import java.util.List;
@Entity
public class Electro_Instrument {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long idElectroInsrtument;
    @Column(
            name = "frequency",
            nullable = false
    )
    private Integer frequency = 144;
    @Column(
            name = "power",
            nullable = false
    )
    private Integer power= 13;
    @Column(name = "form_eguitar",nullable = true)
    private String form_eguitar;

    @Column(
            name = "type_eguitar",
            nullable = true
    )
    private String type_eguitar;

    @OneToMany( mappedBy = "electroInstrument", fetch = FetchType.LAZY)
    private List <Music_Instrument> music_instruments;


    @Column(
            name = "num_srtings_eguitar",
            nullable = true
    )
    private Integer num_srtings_eguitar;
    @Column(
            name = "molot_technic_dpiano",
            nullable = true
    )
    private String molot_technic_dpiano;
    @Column(
            name = "polifonia_dpiano",
            nullable = true
    )
    private String polifonia_dpiano;
    @Column(
            name = "fret_board_eviolin",
            nullable = true
    )
    private String fret_board_eviolin;
    @Column(
            name = "num_srtings_eviolin",
            nullable = true
    )
    private Integer num_srtings_eviolin;

    public Electro_Instrument() {
    }

    public Long getIdElectroInsrtument() {
        return idElectroInsrtument;
    }

    public void setIdElectroInsrtument(Long id_ElectroInsrtument) {
        this.idElectroInsrtument = id_ElectroInsrtument;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getForm_eguitar() {
        return form_eguitar;
    }

    public void setForm_eguitar(String form_eguitar) {
        this.form_eguitar = form_eguitar;
    }

    public String getType_eguitar() {
        return type_eguitar;
    }

    public void setType_eguitar(String type_eguitar) {
        this.type_eguitar = type_eguitar;
    }

    public List<Music_Instrument> getMusic_instruments() {
        return music_instruments;
    }

    public void setMusic_instruments(List<Music_Instrument> music_instruments) {
        this.music_instruments = music_instruments;
    }

    public Integer getNum_srtings_eguitar() {
        return num_srtings_eguitar;
    }

    public void setNum_srtings_eguitar(Integer num_srtings_eguitar) {
        this.num_srtings_eguitar = num_srtings_eguitar;
    }

    public String getMolot_technic_dpiano() {
        return molot_technic_dpiano;
    }

    public void setMolot_technic_dpiano(String molot_technic_dpiano) {
        this.molot_technic_dpiano = molot_technic_dpiano;
    }

    public String getPolifonia_dpiano() {
        return polifonia_dpiano;
    }

    public void setPolifonia_dpiano(String polifonia_dpiano) {
        this.polifonia_dpiano = polifonia_dpiano;
    }

    public String getFret_board_eviolin() {
        return fret_board_eviolin;
    }

    public void setFret_board_eviolin(String fret_board_eviolin) {
        this.fret_board_eviolin = fret_board_eviolin;
    }

    public Integer getNum_srtings_eviolin() {
        return num_srtings_eviolin;
    }

    public void setNum_srtings_eviolin(Integer num_srtings_eviolin) {
        this.num_srtings_eviolin = num_srtings_eviolin;
    }
}
