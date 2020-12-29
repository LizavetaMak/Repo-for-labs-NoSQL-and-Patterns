package com.musicalinstrument.demo.TTPP.builders;

import com.musicalinstrument.demo.jpa.Electro_Instrument;

public class Electro_Instrument_Build {

        private Electro_Instrument el_ins = new Electro_Instrument();
        public Electro_Instrument_Build withFrequency (int frequency) {
            this.el_ins.setFrequency(frequency);
            return this;
        }
        public Electro_Instrument_Build withPover (int pover) {
            this.el_ins.setPower(pover);
            return this;
        }

    public Electro_Instrument_Build withForm_eguitar (String form_eguitar) {
        this.el_ins.setForm_eguitar(form_eguitar);
        return this;
    }
    public Electro_Instrument_Build withType_eguitar ( String type_eguitar) {
        this.el_ins.setType_eguitar(type_eguitar);
        return this;
    }
    public Electro_Instrument_Build withPolifonia_dpiano ( String polifonia_dpiano) {
        this.el_ins.setPolifonia_dpiano(polifonia_dpiano);
        return this;
    }
    public Electro_Instrument_Build withNum_srtings_eviolin ( int num_srtings_eviolin) {
        this.el_ins.setNum_srtings_eviolin(num_srtings_eviolin);
        return this;
    }
    public Electro_Instrument_Build withNum_srtings_eguitar ( int num_srtings_eguitar) {
        this.el_ins.setNum_srtings_eguitar(num_srtings_eguitar);
        return this;
    }
    public Electro_Instrument_Build withMolot_technic_dpiano (String molot_technic_dpiano ) {
        this.el_ins.setMolot_technic_dpiano(molot_technic_dpiano);
        return this;
    }

        public Electro_Instrument build () {
            Electro_Instrument temp_Electro_Instrument = this.el_ins;
            this.el_ins = new Electro_Instrument();
            return temp_Electro_Instrument;
        }

}
