package com.musicalinstrument.demo.TTPP.memento;

import com.musicalinstrument.demo.jpa.Brand;

public class BrandMemento {
    private String backup;
    private Brand brand;

    public BrandMemento (Brand city) {
        this.brand = city;
        this.backup = this.brand.backup();
    }

    public Brand restore () {
        brand.restore(backup);

        return brand;
    }
}

