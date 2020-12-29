package com.musicalinstrument.demo.repository;

import com.musicalinstrument.demo.jpa.Electro_Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ElectroInstrumentRepository extends JpaRepository<Electro_Instrument, Long> {
    @Transactional
    List<Electro_Instrument> findAll();
    //Electro_Instrument findByName(String type);
    @Transactional
    void deleteById(Long id);
    @Transactional
    Electro_Instrument findAllByIdElectroInsrtument(Long IdElectroInsrtument);
}
