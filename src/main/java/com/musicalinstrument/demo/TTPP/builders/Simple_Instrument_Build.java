package com.musicalinstrument.demo.TTPP.builders;

import com.musicalinstrument.demo.jpa.Simple_Instrument;

public class Simple_Instrument_Build {
    private Simple_Instrument sp_ins = new Simple_Instrument();
    public Simple_Instrument_Build withRezonator (int rezonator) {
        this.sp_ins.setRezonator(rezonator);
        return this;
    }
    public Simple_Instrument_Build withMaterial ( String material) {
        this.sp_ins.setMaterial(material);
        return this;
    }
    public Simple_Instrument_Build withNum_srtings_stringed ( int num_srtings_stringed) {
        this.sp_ins.setNum_srtings_stringed(num_srtings_stringed);
        return this;
    }
    public Simple_Instrument_Build withSours_wind ( String sours_wind) {
        this.sp_ins.setSours_wind(sours_wind);
        return this;
    }
    public Simple_Instrument_Build withNum_pedal_keyboard (int num_pedal_keyboard) {
        this.sp_ins.setNum_pedal_keyboard(num_pedal_keyboard);
        return this;
    }
    public Simple_Instrument_Build withLenght_string_stringed (double lenght_string_stringed) {
        this.sp_ins.setLenght_string_stringed(lenght_string_stringed);
        return this;
    }
    public Simple_Instrument_Build withRaspro_keyboard ( String raspro_keyboard) {
        this.sp_ins.setRaspro_keyboard(raspro_keyboard);
        return this;
    }
    public Simple_Instrument build () {
        Simple_Instrument temp_Simple_Instrument = this.sp_ins;
        this.sp_ins = new Simple_Instrument();
        return temp_Simple_Instrument;
    }
}
