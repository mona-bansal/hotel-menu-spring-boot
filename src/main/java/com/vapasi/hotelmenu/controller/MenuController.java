package com.vapasi.hotelmenu.controller;

import com.vapasi.hotelmenu.request.MenuDto;
import com.vapasi.hotelmenu.response.MenuResponse;
import com.vapasi.hotelmenu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/create")
    public ResponseEntity<MenuResponse> createMenu(@RequestBody MenuDto menuDto) throws URISyntaxException {
        MenuResponse menuResponse = menuService.save(menuDto);
        return ResponseEntity.created(new URI("/menus")).body(menuResponse);
    }

    @GetMapping("/getMenus")
    public ResponseEntity<List<MenuResponse>> getAllMenus() throws URISyntaxException {
        List<MenuResponse> menuResponses = menuService.getAllMenus();
        return ResponseEntity.ok().body(menuResponses);
    }
    @PutMapping("/update")
    public ResponseEntity<MenuResponse> update(@RequestBody MenuDto menuDto) throws URISyntaxException {
        MenuResponse menuResponses = menuService.update(menuDto);
        return ResponseEntity.ok().body(menuResponses);
    }
    @DeleteMapping("/delete")
    public HttpStatus delete(@RequestBody MenuDto menuDto) throws URISyntaxException {
        menuService.delete(menuDto);
        return HttpStatus.OK;
    }
}
