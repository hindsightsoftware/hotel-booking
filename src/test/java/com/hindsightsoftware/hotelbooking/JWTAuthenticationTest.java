package com.hindsightsoftware.hotelbooking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hindsightsoftware.hotelbooking.models.AuthModel;
import com.hindsightsoftware.hotelbooking.models.TokenModel;
import com.hindsightsoftware.hotelbooking.repositories.BookingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {})
@WebMvcTest
public class JWTAuthenticationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingRepository bookingRepository;

    @Test
    public void doLogin() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        AuthModel model = new AuthModel();
        model.setUsername("admin");
        model.setPassword("password123");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .content(objectMapper.writeValueAsString(model))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        Assertions.assertNotNull(body);

        TokenModel token = objectMapper.readValue(body, TokenModel.class);
        Assertions.assertNotNull(token);
        Assertions.assertNotNull(token.getToken());
        Assertions.assertTrue(token.getToken().length() > 1);
    }
}
