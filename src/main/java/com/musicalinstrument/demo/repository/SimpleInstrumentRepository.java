package com.musicalinstrument.demo.repository;

import com.musicalinstrument.demo.jpa.Simple_Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface SimpleInstrumentRepository extends JpaRepository<Simple_Instrument, Long> {
    @Transactional
    void deleteByIdSimpleInsrtument(Long idSimpleInsrtument);
    @Transactional
  Simple_Instrument findByrezonator(int rezonator);
    @Query(value = "SELECT  LAST_INSERT_ID()", nativeQuery = true)
    Long lastID();
    @Transactional
    void deleteAll();
    @Transactional
    Simple_Instrument findAllByIdSimpleInsrtument(Long Id);


}
