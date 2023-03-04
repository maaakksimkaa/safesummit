package com.ssummit.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssummit.CommonTest;
import com.ssummit.dto.RouteDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RouteControllerTest extends CommonTest {


    private final RouteDto routeDto = new RouteDto("testFIO", "testLifePeriod", 1, "testDesc", new HashSet<>(), new HashSet<>());
    private int id;


    @Order(1) //очередность выполнения
    @Test
    void createRoute() throws Exception {
        headers.add("Authorization", "Bearer " + token);
        String result = mvc.perform(
                        post("/route/create")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .headers(headers)
                                .content(asJsonString(routeDto)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();
        String s2 = result.substring(result.indexOf(':')+1, result.indexOf(','));
        id = Integer.parseInt(s2);
        System.out.println(result);
    }
    @Order(2) //очередность выполнения
    @Test
    void updateRoute() throws Exception {
        headers.add("Authorization", "Bearer " + token);
        String result = mvc.perform(
                        post("/route/update/"+id)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .headers(headers)
                                .content(asJsonString(routeDto)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }

    @Order(3)
    @Test
    void getRoute() throws Exception {
        headers.add("Authorization", "Bearer " + token);
        String result = mvc.perform(get("/route/get/"+id).headers(headers))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }

    @Order(4)
    @Test
    void getRouteList() throws Exception {
        String result = mvc.perform(get("/route/list").headers(headers))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }

    @Order(5)
    @Test
    void deleteRoute() throws Exception {
        String result = mvc.perform(delete("/route/delete/"+id).headers(headers))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
