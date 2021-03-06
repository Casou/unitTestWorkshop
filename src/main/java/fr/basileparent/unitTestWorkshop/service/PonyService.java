package fr.basileparent.unitTestWorkshop.service;

import fr.basileparent.unitTestWorkshop.factory.MyFarm;
import fr.basileparent.unitTestWorkshop.model.Company;
import fr.basileparent.unitTestWorkshop.model.Pony;
import fr.basileparent.unitTestWorkshop.repository.PonyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PonyService {

    @Autowired
    private MyFarm myFarm;

    @Autowired
    private PonyRepository ponyRepository;

    @Autowired
    private VATService vatService;

    public Integer getGender(Pony pony) {
        if (pony.getSex().equals("MALE")) {
            return 1;
        }
        return 2;
    }

    public Boolean isPonyNamed(Pony pony) {
        return pony.getName() != null;
    }

    public Pony createPony() {
        Pony pony = myFarm.getNewPony();
        pony.setName("Paulo");
        pony.setArrivalDate(LocalDateTime.now());
        pony = ponyRepository.save(pony);
        return pony;
    }

    public Double getSellPrice(Pony pony, Company company) {
        Double taxFreePrice = 1000.0;
        if (pony.getAge() <= 3) {
            taxFreePrice = 2000.0;
        }
        taxFreePrice = taxFreePrice * (1 + vatService.getVAT(company));
        return taxFreePrice;
    }

}
