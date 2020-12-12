package com.vapasi.hotelmenu.service;

import com.vapasi.hotelmenu.model.Menu;
import com.vapasi.hotelmenu.repository.MenuRepository;
import com.vapasi.hotelmenu.request.MenuDto;
import com.vapasi.hotelmenu.response.MenuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public MenuResponse save(MenuDto menuDto) {
        Menu menu = menuRepository.save(new Menu(menuDto.getName(), menuDto.getPrice()));
        MenuResponse menuResponse = new MenuResponse(menu.getId(), menu.getName(), menu.getPrice());
        return menuResponse;
    }

    public List<MenuResponse> getAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        List<MenuResponse> menuResponses = new ArrayList<>();
        menus.forEach((menu) -> {
            menuResponses.add(new MenuResponse(menu.getId(), menu.getName(), menu.getPrice()));
        });
        return menuResponses;
    }

    public MenuResponse update(MenuDto menuDto) {
        Optional<Menu> menuOptional = menuRepository.findById(menuDto.getId());
        if (menuOptional.isPresent()) {
            Menu toBeUpdated = menuOptional.get();
            toBeUpdated.setName(menuDto.getName());
            toBeUpdated.setPrice(menuDto.getPrice());
            Menu updatedMenu = menuRepository.save(toBeUpdated);
            return new MenuResponse(updatedMenu.getId(), updatedMenu.getName(), updatedMenu.getPrice());
        }
        throw new RuntimeException("Menu not found, Given [Id]:" + menuDto.getId());
    }
    public void delete(MenuDto menuDto) {
        Optional<Menu> menuOptional = menuRepository.findById(menuDto.getId());
        if (!menuOptional.isPresent()) {
            throw new RuntimeException("Menu not found, Given [Id]:" + menuDto.getId());
        }
        Menu toBeUpdated = menuOptional.get();
        toBeUpdated.setName(menuDto.getName());
        toBeUpdated.setPrice(menuDto.getPrice());
        menuRepository.delete(toBeUpdated);
    }


}
