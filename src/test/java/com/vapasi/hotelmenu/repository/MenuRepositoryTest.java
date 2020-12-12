package com.vapasi.hotelmenu.repository;

import com.vapasi.hotelmenu.model.Menu;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void shouldSaveMenu() {
        Menu menu = new Menu("Kadai Paneer", 200.0);
        Menu savedMenu = menuRepository.save(menu);
        assertEquals(1, menuRepository.findAll().size());
        assertEquals(menu.getName(),savedMenu.getName());
        assertEquals(menu.getPrice(),savedMenu.getPrice());
    }

    @Test
    public void shouldFindAllMenus() {
        Menu menu1 = new Menu("Fried Rice", 200.0);
        Menu savedMenu1 = menuRepository.save(menu1);

        Menu menu2 = new Menu("Biryani", 400.0);
        Menu savedMenu2 = menuRepository.save(menu2);

        List<Menu> menus = menuRepository.findAll();
        assertEquals(2, menus.size());
        assertEquals(savedMenu1,menus.get(0));
        assertEquals(savedMenu2,menus.get(1));
    }

    @Test
    public void shouldUpdateMenu() {
        Menu menu1 = new Menu("NotPasta", 200.0);
        Menu savedMenu1 = menuRepository.save(menu1);

        Menu menu2 = new Menu("Pizza", 400.0);
        menuRepository.save(menu2);

        Menu foundMenu = menuRepository.findById(savedMenu1.getId()).get();
        assertEquals(savedMenu1,foundMenu);
        foundMenu.setName("Pasta");
        Menu updatedMenu = menuRepository.save(foundMenu);

        assertEquals("Pasta",updatedMenu.getName());
    }
}
