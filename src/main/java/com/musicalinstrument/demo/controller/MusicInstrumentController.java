package com.musicalinstrument.demo.controller;

/*@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MusicInstrumentController{
    @Autowired
    private MusicInstrumentRepository musicInstrumentRepository;
    public MusicInstrumentController(MusicInstrumentRepository musicInstrumentRepository) {
        this.musicInstrumentRepository = musicInstrumentRepository;
    }

    @PostMapping("/addmusic")
    public void addMusicInstrument(@RequestBody String name) {
        Music_Instrument music_instrument = new Music_Instrument();
        music_instrument.setName(name);
        musicInstrumentRepository.save(music_instrument);

    }
    @DeleteMapping("/musicdelete/{id}")
    public void delete(@PathVariable("id") long id) {
        musicInstrumentRepository.deleteById(id);
    }

    @GetMapping("/music/{name}")
    public Music_Instrument getByName(@PathVariable("name") String name) {
        return musicInstrumentRepository.findByName(name);
    }

    @PostMapping("/musicupdate")
    public Music_Instrument editBank (@RequestBody Music_Instrument music) {
        return musicInstrumentRepository.saveAndFlush(music);
    }

    @GetMapping("/musics")
    public List<Music_Instrument> getAll() {
        return musicInstrumentRepository.findAll();
    }
}*/

