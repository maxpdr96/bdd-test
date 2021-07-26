package com.hidarisoft.bddautomationapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BddControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private BddController bddController;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(bddController).build();
    }

    @Test
    void addUser() throws Exception {
        String jsonResponse = """
                {
                 "id":1,
                 "username":"miu",
                 "firstName":"miu",
                 "lastName":"santos"
                }""";

        var bddModel = objectMapper.writeValueAsString(jsonResponse);

        mockMvc.perform(
                post("/api/v3/user/")
                        .content(bddModel)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.teste").doesNotExist())
                .andExpect(jsonPath("$.username", is("miu")))
                .andExpect(jsonPath("$.firstName", is("miu")))
                .andExpect(jsonPath("$.lastName", is("santos")));
    }


    @Test
    void getUser() throws Exception {

        mockMvc.perform(
                get("/api/v3/user/{name}", "miu")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.teste").doesNotExist())
                .andExpect(jsonPath("$.username", is("miu")))
                .andExpect(jsonPath("$.firstName", is("miu")))
                .andExpect(jsonPath("$.lastName", is("santos")));
    }

}