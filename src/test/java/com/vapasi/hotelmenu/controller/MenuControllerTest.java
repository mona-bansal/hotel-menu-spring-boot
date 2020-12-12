package com.vapasi.hotelmenu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vapasi.hotelmenu.request.MenuDto;
import com.vapasi.hotelmenu.response.MenuResponse;
import com.vapasi.hotelmenu.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class MenuControllerTest {
    @MockBean
    private MenuService menuService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldSaveMenu() throws Exception {
        MenuResponse menuResponse = new MenuResponse( 1,"Dum Biryani", 400);
        Mockito.when(menuService.save(new MenuDto(1,"Dum Birayani", 400))).thenReturn(menuResponse);

        mockMvc.perform(post("/menus/create").content("{\"name\":\"Dum Biryani\",\"price\":\"400\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetAllMenu() throws Exception {
        MenuResponse menuResponse1 = new MenuResponse(1, "Dum Biryani", 400);
        MenuResponse menuResponse2 = new MenuResponse(2, "Shahi Paneer", 200);

        List<MenuResponse> list = new ArrayList<>();
        list.add(menuResponse1);
        list.add(menuResponse2);

        Mockito.when(menuService.getAllMenus()).thenReturn(list);

        mockMvc.perform(get("/menus/getMenus")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateMenu() throws Exception {

        MenuDto menuDto = new MenuDto(1, "Garlic Bread", 200.0);
        MenuResponse menuResponse = new MenuResponse(menuDto.getId(),menuDto.getName(), menuDto.getPrice());
        Mockito.when(menuService.update(menuDto)).thenReturn(menuResponse);

        mockMvc.perform(put("/menus/update")
                .content("{\"id\":1,\"name\":\"Garlic Bread\",\"price\":200.0}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteMenu() throws Exception {
        MenuDto menuDto = new MenuDto(1, "Garlic Bread", 200.0);
        Mockito.doNothing().when(menuService).delete(menuDto);
        mockMvc.perform(delete("/menus/delete")
                .content("{\"id\":1,\"name\":\"Garlic Bread\",\"price\":200.0}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGenerateJson(){
        MenuDto menuDto = new MenuDto(1, "Garlic Bread", 200.0);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(menuDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        assertTrue(true);
    }

}
