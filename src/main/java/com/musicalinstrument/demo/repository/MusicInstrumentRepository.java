package com.musicalinstrument.demo.repository;

import com.musicalinstrument.demo.jpa.Music_Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface MusicInstrumentRepository extends JpaRepository<Music_Instrument, Long> {
    @Transactional
    void deleteByidMusicInstrument(Long idMusicInstrument);
    @Transactional
    Music_Instrument findByName(String name);
    @Transactional
    Music_Instrument save(Music_Instrument music_instrument);

    @Transactional
    Music_Instrument findAllByidMusicInstrument(Long idMusicInstrument);




}
