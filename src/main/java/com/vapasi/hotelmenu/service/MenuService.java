package com.vapasi.hotelmenu.service;

import com.vapasi.hotelmenu.model.Menu;
import com.vapasi.hotelmenu.repository.MenuRepository;
import com.vapasi.hotelmenu.request.MenuDto;
import com.vapasi.hotelmenu.response.MenuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public MenuResponse save(MenuDto menuDto){
        Menu menu = menuRepository.save(new Menu(menuDto.getName(),menuDto.getPrice()));
        MenuResponse menuResponse = new MenuResponse(menu.getId(),menu.getName(),menu.getPrice());
       return menuResponse;
    }
}
