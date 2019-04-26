package fr.basileparent.unitTestWorkshop.service;

import fr.basileparent.unitTestWorkshop.factory.MyFarm;
import fr.basileparent.unitTestWorkshop.model.Company;
import fr.basileparent.unitTestWorkshop.model.Pony;
import fr.basileparent.unitTestWorkshop.repository.PonyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PonyServiceTest {

    @InjectMocks
    private PonyService ponyService;

    @Mock
    private MyFarm myFarm;

    @Mock
    private PonyRepository ponyRepository;

    @Mock
    private VATService vatService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        when(myFarm.getNewPony()).thenReturn(new Pony());
        when(ponyRepository.save(any(Pony.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
    }

    @Test
    public void getGender_should_return_1_when_pony_is_male() {
        // Given
        Pony pony = new Pony();
        pony.setSex("MALE");

        // When
        Integer gender = ponyService.getGender(pony);

        // Then
        assertEquals(Integer.valueOf(1), gender);
    }

    @Test
    public void createPony_should_return_pony_created_by_factory() {
        // Given
        Pony mockedPony = new Pony();
        when(myFarm.getNewPony()).thenReturn(mockedPony);

        // When
        Pony pony = ponyService.createPony();

        // Then
        assertEquals(mockedPony, pony);
    }

    @Test
    public void createPony_should_save_a_pony_with_name_and_arrival_date() {
        // Given
        ArgumentCaptor<Pony> ponyArgumentCaptor = ArgumentCaptor.forClass(Pony.class);

        // When
        Pony pony = ponyService.createPony();

        // Then
        verify(ponyRepository, times(1)).save(ponyArgumentCaptor.capture());
        Pony savedPony = ponyArgumentCaptor.getValue();
        assertEquals("Paulo", savedPony.getName());
        assertEquals(LocalDate.now(), savedPony.getArrivalDate().toLocalDate());
    }

    @Test
    public void getSellPrice_should_return_2000_if_age_less_than_3() {
        // Given
        Company company = new Company();
        Pony pony = Pony.builder().age(2).build();
        when(vatService.getVAT(any(Company.class))).thenReturn(0.0);

        // When
        Double sellPrice = ponyService.getSellPrice(pony, company);

        // Then
        assertEquals(Double.valueOf(2000.0), sellPrice);
    }

    @Test
    public void getSellPrice_should_return_1000_if_age_equals_3() {
        // Given
        Company company = new Company();
        Pony pony = Pony.builder().age(3).build();
        when(vatService.getVAT(any(Company.class))).thenReturn(0.0);

        // When
        Double sellPrice = ponyService.getSellPrice(pony, company);

        // Then
        assertEquals(Double.valueOf(2000.0), sellPrice);
    }

    @Test
    public void getSellPrice_should_return_1000_if_age_more_then_3() {
        // Given
        Company company = new Company();
        Pony pony = Pony.builder().age(4).build();
        when(vatService.getVAT(any(Company.class))).thenReturn(0.0);

        // When
        Double sellPrice = ponyService.getSellPrice(pony, company);

        // Then
        assertEquals(Double.valueOf(1000.0), sellPrice);
    }

    @Test
    public void getSellPrice_should_apply_taxes_on_price() {
        // Given
        Company company = new Company();
        Pony pony = Pony.builder().age(2).build();
        when(vatService.getVAT(any(Company.class))).thenReturn(1.0);

        // When
        Double sellPrice = ponyService.getSellPrice(pony, company);

        // Then
        assertEquals(Double.valueOf(2000.0 * 2), sellPrice);
    }

}