package com.example.helloworld;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HelloworldApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /hello retourne une liste vide")
    void getAllMessages_returnsEmptyList() throws Exception {
        mockMvc.perform(get("/hello"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("POST /hello crée un message")
    void createMessage_returnsCreatedMessage() throws Exception {
        String body = "{\"content\": \"Hello Test!\"}";

        mockMvc.perform(post("/hello")
               .contentType(MediaType.APPLICATION_JSON)
               .content(body))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content").value("Hello Test!"))
               .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @DisplayName("POST /hello avec contenu vide")
    void createMessage_withEmptyContent() throws Exception {
        String body = "{\"content\": \"\"}";

        mockMvc.perform(post("/hello")
               .contentType(MediaType.APPLICATION_JSON)
               .content(body))
               .andExpect(status().isOk());
    }
}