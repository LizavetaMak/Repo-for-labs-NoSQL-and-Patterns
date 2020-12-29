package com.musicalinstrument.demo.TTPP.Dao_for_brands;

import com.musicalinstrument.demo.TTPP.common.IEventListener;
import com.musicalinstrument.demo.jpa.Brand;
import com.musicalinstrument.demo.jpa.Role;
import com.musicalinstrument.demo.jpa.User;

import java.util.List;

public interface IDAO {
     public void subscribe (String eventType, IEventListener eventListener);
    public Long createBrand (Brand brand,ERole userRole) throws Exception;
     public void deleteBrand (Brand brand, ERole userRole) throws Exception;
    public void updateBrand (Brand brand, ERole userRole) throws  Exception;
    public List<Brand> getAllBrands(ERole userRole) throws Exception;
    public User login (String login, String password) throws Exception;
    public User register (String login, String password, Role role) throws Exception;
}
