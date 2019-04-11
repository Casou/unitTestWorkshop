package fr.basileparent.unitTestWorkshop.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.basileparent.unitTestWorkshop.model.Pony;

import static org.junit.Assert.*;

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
    public void save_should_not_interfere_with_other_tests() {
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
    public void findAll_should_return_3_entries() {
        // Given (nothing - data in data-test.sql)

        // When
        List<Pony> ponies = ponyRepository.findAll();

        // Then
        assertEquals(3, ponies.size());
    }

    @Test
    public void findTooOldPonies_should_return_1_pony_older_than_10_years() {
        // Given (nothing - data in data-test.sql)

        // When
        List<Pony> ponies = ponyRepository.findTooOldPonies();

        // Then
        assertEquals(1, ponies.size());
        assertTrue(ponies.get(0).getAge() > 10);
    }

    @Test
    public void findTooOldPonies_should_return_2_pony_older_than_2_years() {
        // Given (nothing - data in data-test.sql)

        // When
        List<Pony> ponies = ponyRepository.findPoniesOlderThan(2);

        // Then
        assertEquals(2, ponies.size());
        assertTrue(ponies.get(0).getAge() > 2);
        assertTrue(ponies.get(1).getAge() > 2);
    }

}