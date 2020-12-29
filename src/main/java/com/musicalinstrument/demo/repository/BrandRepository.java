package com.musicalinstrument.demo.repository;

import com.musicalinstrument.demo.jpa.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>  {
    @Transactional
    List<Brand> findAll();
    @Transactional
    void deleteById(Long Id);
    @Transactional
   Brand findAllById(Long Id);
    @Query(value = "SELECT  LAST_INSERT_ID()", nativeQuery = true)
    Long lastID();
}
