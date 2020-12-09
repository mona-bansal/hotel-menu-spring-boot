package com.vapasi.hotelmenu.service;

import com.vapasi.hotelmenu.model.Menu;
import com.vapasi.hotelmenu.repository.MenuRepository;
import com.vapasi.hotelmenu.request.MenuDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;
    private MenuService menuService;

    @Before
    public void setUp(){
        menuService = new MenuService(menuRepository);
    }

  /*  @Test
    public void shouldSaveMenu(){
        Menu toBeSavedMenu = new Menu("Biryani",350);
        Menu expectedMenu = new Menu(1,"Biryani",350);
        Mockito.when(menuRepository.save(toBeSavedMenu)).thenReturn(expectedMenu);

        Menu result = menuService.save(new MenuDto("Biryani",350));
        System.out.println("result : "+result);

        // assert
        assertEquals(expectedMenu, result);
        verify(menuRepository).save(toBeSavedMenu);
    }*/
}
