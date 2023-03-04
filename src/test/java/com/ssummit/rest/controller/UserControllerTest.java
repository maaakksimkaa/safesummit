//package com.ssummit.rest.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.ssummit.CommonTest;
//import com.ssummit.dto.AddTourDto;
//import com.ssummit.dto.RestorePasswordWithEmailDTO;
//import com.ssummit.dto.RoleDto;
//import com.ssummit.dto.UserDto;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//
//import java.time.LocalDate;
//import java.util.HashSet;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class UserControllerTest extends CommonTest {
//    private int id=1;
//    private RoleDto roleDto = new RoleDto(99L, "test", "test");
//    private UserDto userDto = new UserDto(
//            "testName",
//            "testMidleName",
//            "testLastName",
//            LocalDate.of(1999, 10, 10),
//            "Telephone",
//            "Email"+id,
//            "Adres",
//            "Pasport",
//            "LOGIN"+id,
//            "PASSWORD",
//            roleDto,
//            new HashSet<>()
//    );
//
//
//
//
//    @Order(1) //очередность выполнения
//    @Test
//    void createUser() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(
//                        post("/user/create")
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .headers(headers)
//                                .content(asJsonString(userDto)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        String s2 = result.substring(result.indexOf(':') + 1, result.indexOf(','));
//        id = Integer.parseInt(s2);
//        System.out.println(result);
//    }
//
//    @Order(2) //очередность выполнения
//    @Test
//    void updateUser() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(
//                        post("/user/update/" + id)
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .headers(headers)
//                                .content(asJsonString(userDto)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    @Order(3)
//    @Test
//    void registration() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(
//                        post("/user/registration")
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .headers(headers)
//                                .content(asJsonString(userDto)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    @Order(4)
//    @Test
//    void createGuide() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(
//                        post("/user/create-guide")
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .headers(headers)
//                                .content(asJsonString(userDto)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    @Order(5)
//    @Test
//    void createSpectator() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(
//                        post("/user/create-spectator")
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .headers(headers)
//                                .content(asJsonString(userDto)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    @Order(6)
//    @Test
//    void addTour() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(
//                        post("/user/tour-register/")
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .headers(headers)
//                                .content(asJsonString(new AddTourDto() )))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    @Order(7)
//    @Test
//    void revokeTour() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(
//                        post("/user/tour-revoke")
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .headers(headers)
//                                .content(asJsonString(new AddTourDto())))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    @Order(8)
//    @Test
//    void getScheduledTours() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(get("/user/scheduled_tours").headers(headers))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    @Order(9)
//    @Test
//    void getAllGuides() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(get("/user/list-all-guides").headers(headers))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    @Order(10)
//    @Test
//    void getAllParticipants() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(get("/user/list-all-participants").headers(headers))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    @Order(11)
//    @Test
//    void restorePassword() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(get("/user/restore-password").headers(headers))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    @Order(12)
//    @Test
//    void testRestorePassword() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(
//                        post("/user/restore-password")
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .headers(headers).param("uuid")
//                                .content(asJsonString(new RestorePasswordWithEmailDTO())))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    @Order(13)
//    @Test
//    void changePassword() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(get("/user/change-password").headers(headers))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//
//    @Order(14)
//    @Test
//    void testChangePassword() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(
//                        post("/user/change-password" + id)
//                                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                .headers(headers)
//                                .content(asJsonString(userDto)))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    @Order(15)
//    @Test
//    void getUser() throws Exception {
//        headers.add("Authorization", "Bearer " + token);
//        String result = mvc.perform(get("/user/get/" + id).headers(headers))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    @Order(16)
//    @Test
//    void getUserList() throws Exception {
//        String result = mvc.perform(get("/user/list").headers(headers))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    @Order(17)
//    @Test
//    void deleteUser() throws Exception {
//        String result = mvc.perform(delete("/user/delete/" + id).headers(headers))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        System.out.println(result);
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            final ObjectMapper mapper = new ObjectMapper();
//            mapper.registerModule(new JavaTimeModule());
//            return mapper.writeValueAsString(obj);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
