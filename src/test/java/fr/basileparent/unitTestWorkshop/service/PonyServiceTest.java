package fr.basileparent.unitTestWorkshop.service;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.basileparent.unitTestWorkshop.factory.MyFarm;
import fr.basileparent.unitTestWorkshop.model.Pony;
import fr.basileparent.unitTestWorkshop.repository.PonyRepository;

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

}