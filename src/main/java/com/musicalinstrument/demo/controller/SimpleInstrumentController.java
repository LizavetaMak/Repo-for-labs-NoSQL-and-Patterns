package com.musicalinstrument.demo.controller;


/*@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class SimpleInstrumentController {
    @Autowired
    private SimpleInstrumentRepository simpleInstrumentRepository;

    public SimpleInstrumentController(SimpleInstrumentRepository simpleInstrumentRepository) {
        this.simpleInstrumentRepository = simpleInstrumentRepository;
    }


    @PostMapping("/addsimple")
    public void addMusicInstrument(@RequestBody String Material) {
        Simple_Instrument simple_instrument = new Simple_Instrument();
        simple_instrument.setMaterial(Material);
        simpleInstrumentRepository.save(simple_instrument);

    }

    @DeleteMapping("/musicsimpledelete/{id_SimpleInsrtument}")
    public void delete(@PathVariable("id_SimpleInsrtument") long id_SimpleInsrtument) {
        simpleInstrumentRepository.deleteById(id_SimpleInsrtument);
    }

   /*@GetMapping("/music/{material}")
    public Simple_Instrument getByName(@PathVariable("material") String material) {
        return simpleInstrumentRepository.findByName(material);
    }*/

   /* @PostMapping("/musicupdate")
    public Simple_Instrument editBank (@RequestBody Simple_Instrument music) {
        return simpleInstrumentRepository.saveAndFlush(music);
    }*/

   /* @GetMapping("/musicssimple")
    public List<Simple_Instrument> getAll() {
        return simpleInstrumentRepository.findAll();
    }*/

    /*public void a(Simple_Instrument i) {
         simpleInstrumentRepository.insert(i);
    }
}*/

