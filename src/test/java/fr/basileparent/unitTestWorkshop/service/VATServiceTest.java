package fr.basileparent.unitTestWorkshop.service;

import fr.basileparent.unitTestWorkshop.enums.CompanyType;
import fr.basileparent.unitTestWorkshop.model.Company;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class VATServiceTest {

    @InjectMocks
    private VATService vatService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void get_VAT_should_return_19_6_if_company_is_a_stable() {
        // Given
        Company company = Company.builder().companyType(CompanyType.STABLE).build();

        // When
        Double vat = vatService.getVAT(company);

        // Then
        assertEquals(Double.valueOf(0.196), vat);
    }

    @Test
    public void get_VAT_should_return_2_1_if_company_is_a_food_company() {
        // Given
        Company company = Company.builder().companyType(CompanyType.FOOD).build();

        // When
        Double vat = vatService.getVAT(company);

        // Then
        assertEquals(Double.valueOf(0.021), vat);
    }

    @Test
    public void get_VAT_should_return_19_6_if_company_has_no_type() {
        // Given
        Company company = Company.builder().companyType(null).build();

        // When
        Double vat = vatService.getVAT(company);

        // Then
        assertEquals(Double.valueOf(0.196), vat);
    }

}