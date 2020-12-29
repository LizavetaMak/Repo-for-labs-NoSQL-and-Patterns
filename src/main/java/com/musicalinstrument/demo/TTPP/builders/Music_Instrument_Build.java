package com.musicalinstrument.demo.TTPP.builders;

import com.musicalinstrument.demo.jpa.Brand;
import com.musicalinstrument.demo.jpa.Electro_Instrument;
import com.musicalinstrument.demo.jpa.Music_Instrument;
import com.musicalinstrument.demo.jpa.Simple_Instrument;

public class Music_Instrument_Build {
    private Music_Instrument ms_ins = new Music_Instrument();
    public Music_Instrument_Build withName (String name) {
        this.ms_ins.setName(name);
        return this;
    }
    public Music_Instrument_Build withSize (Double size) {
        this.ms_ins.setSize(size);
        return this;
    }
    public Music_Instrument_Build withName_type (String name_type) {
        this.ms_ins.setName_type(name_type);
        return this;
    }
    public Music_Instrument_Build withPrice (Double price) {
        this.ms_ins.setPrice(price);
        return this;
    }
    public Music_Instrument_Build withBrand (Brand brand) {
        this.ms_ins.setBrand(brand);
        return this;
    }
    public Music_Instrument_Build withElectroInstrument (Electro_Instrument electroInstrument) {
        this.ms_ins.setElectroInstrument(electroInstrument);
        return this;
    }
    public Music_Instrument_Build withSimpleInstrument (Simple_Instrument simpleInstrument) {
        this.ms_ins.setSimpleInstrument(simpleInstrument);
        return this;
    }
    public Music_Instrument build () {
        Music_Instrument temp_Music_Instrument = this.ms_ins;
        this.ms_ins = new Music_Instrument();
        return temp_Music_Instrument;
    }
}
