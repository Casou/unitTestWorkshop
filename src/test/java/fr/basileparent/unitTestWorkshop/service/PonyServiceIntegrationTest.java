package fr.basileparent.unitTestWorkshop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.basileparent.unitTestWorkshop.model.Pony;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PonyServiceIntegrationTest {

    @Autowired
    private PonyService ponyService;

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

}