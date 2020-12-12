package com.vapasi.hotelmenu.service;

import com.vapasi.hotelmenu.model.Menu;
import com.vapasi.hotelmenu.repository.MenuRepository;
import com.vapasi.hotelmenu.request.MenuDto;
import com.vapasi.hotelmenu.response.MenuResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;
    private MenuService menuService;

    @Before
    public void setUp() {
        menuService = new MenuService(menuRepository);
    }

    @Test
    public void shouldSaveMenu() {
        Menu toBeSavedMenu = new Menu("Biryani", 350);
        Menu savedMenu = new Menu(1, "Biryani", 350);
        MenuResponse expectedMenuResponse = new MenuResponse(1, "Biryani", 350);
        Mockito.when(menuRepository.save(toBeSavedMenu)).thenReturn(savedMenu);

        MenuResponse result = menuService.save(new MenuDto("Biryani", 350));
        System.out.println("result : " + result);

        // assert
        assertEquals(expectedMenuResponse, result);
        verify(menuRepository).save(toBeSavedMenu);
    }

    @Test
    public void shouldFindAllMenus() {
        Menu menu1 = new Menu("Fried Rice", 200.0);
        Menu menu2 = new Menu("Biryani", 400.0);

        List<Menu> menus = new ArrayList<>();
        menus.add(menu1);
        menus.add(menu2);

        Mockito.when(menuRepository.findAll()).thenReturn(menus);

        List<MenuResponse> menuResponses = menuService.getAllMenus();
        System.out.println("result : " + menuResponses);
        assertEquals(2, menuResponses.size());
        verify(menuRepository).findAll();
    }


    @Test
    public void shouldUpdateMenu() {
        MenuDto menuDto = new MenuDto(1, "Garlic Bread", 200.0);
        Menu foundMenu = new Menu(1, "GarlicBread-Wrong", 200.0);
        Menu updatedMenu = new Menu(1, menuDto.getName(),menuDto.getPrice());

        Mockito.when(menuRepository.findById(1)).thenReturn(Optional.of(foundMenu));
        Mockito.when(menuRepository.save(updatedMenu)).thenReturn(updatedMenu);

        MenuResponse expectedMenuResponse = new MenuResponse(1, "Garlic Bread", 200.0);
        MenuResponse updatedMenuResponse = menuService.update(menuDto);
        assertEquals(expectedMenuResponse, updatedMenuResponse);
        verify(menuRepository).findById(1);
        verify(menuRepository).save(updatedMenu);
    }

    @Test
    public void shouldThrowExceptionWhenMenuNotFoundForUpdate() {
        MenuDto menuDto = new MenuDto(1, "Garlic Bread", 200.0);

        Mockito.when(menuRepository.findById(1)).thenReturn(Optional.empty());

        Exception e = assertThrows(RuntimeException.class, () -> menuService.update(menuDto));
        verify(menuRepository).findById(1);
    }
}
