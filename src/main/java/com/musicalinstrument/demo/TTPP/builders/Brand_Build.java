package com.musicalinstrument.demo.TTPP.builders;

import com.musicalinstrument.demo.jpa.Brand;

public class Brand_Build {
    private Brand brand = new Brand();
    public Brand_Build withName (String name) {
        this.brand.setName(name);
        return this;
    }
    public Brand_Build withCounty (String county) {
        this.brand.setName(county);
        return this;
    }
    public Brand build () {
        Brand tempBrand = this.brand;
        this.brand = new Brand();
        return tempBrand;
    }
}
