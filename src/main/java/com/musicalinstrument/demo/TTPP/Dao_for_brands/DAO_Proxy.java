package com.musicalinstrument.demo.TTPP.Dao_for_brands;

import com.musicalinstrument.demo.TTPP.common.IEventListener;
import com.musicalinstrument.demo.jpa.Brand;
import com.musicalinstrument.demo.jpa.Role;
import com.musicalinstrument.demo.jpa.User;

import java.util.List;

public class DAO_Proxy implements IDAO{
    private IDAO dao;

    public DAO_Proxy(IDAO dao) {
        this.dao = dao;
    }
    @Override
    public void subscribe(String eventType, IEventListener eventListener) {
        this.dao.subscribe(eventType, eventListener);
    }

    @Override
    public Long createBrand(Brand brand, ERole userRole) throws Exception {
        if (userRole == ERole.ADMIN)
            return this.dao.createBrand(brand, userRole);
        throw new NotEnoughPermissionsException();
    }

    @Override
    public void deleteBrand(Brand brand, ERole userRole) throws Exception {
        if (userRole == ERole.ADMIN){
            System.out.println(userRole);
            this.dao.deleteBrand(brand, userRole);}
        throw new NotEnoughPermissionsException();
    }

    @Override
    public void updateBrand(Brand brand, ERole userRole) throws Exception {
        if (userRole == ERole.ADMIN)
            this.dao.updateBrand(brand, userRole);
        throw new NotEnoughPermissionsException();
    }
    @Override
    public User login(String login, String password) throws Exception {
        return this.dao.login(login, password);
    }

    @Override
    public User register(String login, String password, Role role) throws Exception {
        return this.dao.register(login, password, role);
    }
    @Override
    public List<Brand> getAllBrands(ERole userRole) throws Exception {
        return this.dao.getAllBrands(userRole);
    }
}
