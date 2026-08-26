package com.example.restful.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSubscribe() throws Exception {
        var request = MockMvcRequestBuilders.post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "윤서준",
                            "email": "SeojunYoon@campus.co.kr",
                            "age": 10
                        }
                        """);

        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                  {
                    "name":  "윤서준",
                    "email": "SeojunYoon@campus.co.kr"
                  }
                """, JsonCompareMode.LENIENT))
                .andExpect(jsonPath("$.id").isNumber())
        ;

    }
}
