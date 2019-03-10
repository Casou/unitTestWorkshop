package fr.basileparent.unitTestWorkshop.service;

import fr.basileparent.unitTestWorkshop.model.Pony;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PonyServiceIntegrationTest {

    @Autowired
    private PonyService ponyService;

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

}