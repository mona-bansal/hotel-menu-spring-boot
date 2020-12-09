package com.vapasi.hotelmenu.repository;

import com.vapasi.hotelmenu.model.Menu;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void shouldSaveMenu() {
        Menu menu = new Menu("Fried Rice", 200.0);
        menuRepository.save(menu);
        assertEquals(1, menuRepository.findAll().size());
    }
}
