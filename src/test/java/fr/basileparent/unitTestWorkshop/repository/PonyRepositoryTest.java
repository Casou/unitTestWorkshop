package fr.basileparent.unitTestWorkshop.repository;

import fr.basileparent.unitTestWorkshop.model.Pony;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PonyRepositoryTest {

    @Autowired
    private PonyRepository ponyRepository;

    /**
     *  Ce test ne doit jamais être fait !
     *  C'est uniquement pour prouver qu'il ne doit pas avoir d'incidence sur les autres tests.
     */
    @Test
    public void save_shouldNotInterfereWithOtherTests() {
        // Given
        Pony p = new Pony();
        p.setId(1000);
        p.setSex("FEMALE");
        p.setAge(15);
        p.setName("Michou");

        assertEquals(3, ponyRepository.findAll().size());

        // When
        Pony savedPony = ponyRepository.save(p);

        // Then
        assertNotNull(savedPony.getId());
        assertEquals(4, ponyRepository.findAll().size());
    }

    @Test
    public void findAll_shouldReturn3Entries() {
        // Given (nothing - data in data-test.sql)

        // When
        List<Pony> ponies = ponyRepository.findAll();

        // Then
        assertEquals(3, ponies.size());
    }

    @Test
    public void findTooOldPonies_shouldReturn1Pony_olderThan10Years() {
        // Given (nothing - data in data-test.sql)

        // When
        List<Pony> ponies = ponyRepository.findTooOldPonies();

        // Then
        assertEquals(1, ponies.size());
        assertTrue(ponies.get(0).getAge() > 10);
    }

    @Test
    public void findTooOldPonies_shouldReturn2Pony_olderThan2Years() {
        // Given (nothing - data in data-test.sql)

        // When
        List<Pony> ponies = ponyRepository.findPoniesOlderThan(2);

        // Then
        assertEquals(2, ponies.size());
        assertTrue(ponies.get(0).getAge() > 2);
        assertTrue(ponies.get(1).getAge() > 2);
    }

}