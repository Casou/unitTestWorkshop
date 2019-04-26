package fr.basileparent.unitTestWorkshop.service;

import fr.basileparent.unitTestWorkshop.enums.CompanyType;
import fr.basileparent.unitTestWorkshop.model.Company;
import org.springframework.stereotype.Service;

@Service
public class VATService {

    public Double getVAT(Company company) {
        if (CompanyType.FOOD.equals(company.getCompanyType())) {
            return 0.021;
        }
        return 0.196;
    }
}
