package fr.basileparent.unitTestWorkshop.service;

import fr.basileparent.unitTestWorkshop.factory.MyFarm;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void getGender_shouldReturn1_whenPonyIsMale() {
        // Given
        Pony pony = new Pony();
        pony.setSex("MALE");

        // When
        Integer gender = ponyService.getGender(pony);

        // Then
        assertEquals(Integer.valueOf(1), gender);
    }

    @Test
    public void createPony_shouldReturnPonyCreatedByFactory() {
        // Given
        Pony mockedPony = new Pony();
        when(myFarm.getNewPony()).thenReturn(mockedPony);

        // When
        Pony pony = ponyService.createPony();

        // Then
        assertEquals(mockedPony, pony);
    }

    @Test
    public void createPony_shouldSaveAPonyWithNameAndArrivalDate() {
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