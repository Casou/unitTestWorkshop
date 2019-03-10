package fr.basileparent.unitTestWorkshop.controller;

import fr.basileparent.unitTestWorkshop.model.Pony;
import fr.basileparent.unitTestWorkshop.repository.PonyRepository;
import fr.basileparent.unitTestWorkshop.service.PonyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PonyController {

    @Autowired
    private PonyService ponyService;

    @Autowired
    private PonyRepository ponyRepository;

    @GetMapping("/ponys")
    public List<Pony> findAll() {
        return ponyRepository.findAll();
    }

    @PostMapping("/pony/register/male")
    public ResponseEntity<String> registerMale(Pony pony) {
        if (ponyService.getGender(pony) == 2) {
            return ResponseEntity.badRequest().body("Pony in parameter should be male");
        }
        return ResponseEntity.ok("ok");
    }

}
