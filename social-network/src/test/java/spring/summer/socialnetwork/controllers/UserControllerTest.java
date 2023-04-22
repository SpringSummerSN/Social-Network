package spring.summer.socialnetwork.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import spring.summer.socialnetwork.dto.UserDTO;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//@WebMvcTest(UserController.class)

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void add() throws Exception {

        UserDTO user = UserDTO.builder()
                .name("Tomasz")
                .surname("Korniszuk")
                .email("tomek@o2.pl")
                .password("password")
                .build();

        MvcResult response = mockMvc.perform((post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))).andReturn();

        //        response.andDo(print())
//                .andExpect(status().isCreated());



    }

    @Test
    void delete() {
    }

    @Test
    void getUserByID() {



    }

    @Test
    void userList() throws Exception {
        ResultActions response = mockMvc.perform((get("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)));
        response.andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void update() {
    }
}