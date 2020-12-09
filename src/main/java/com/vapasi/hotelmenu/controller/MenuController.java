package com.vapasi.hotelmenu.controller;

import com.vapasi.hotelmenu.model.Menu;
import com.vapasi.hotelmenu.request.MenuDto;
import com.vapasi.hotelmenu.response.MenuResponse;
import com.vapasi.hotelmenu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/menus")
public class MenuController {

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(@RequestBody MenuDto menuDto) throws URISyntaxException {
        MenuResponse menuResponse = menuService.save(menuDto);
        return ResponseEntity.created(new URI("/menus")).body(menuResponse);
//        ResponseEntity<MenuResponse> responseEntity = new ResponseEntity<MenuResponse>(new MenuResponse(menu.getId(), menu.getName(), menu.getPrice()));
//        return responseEntity ;
    }


}
