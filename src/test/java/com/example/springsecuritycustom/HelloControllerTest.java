package com.example.springsecuritycustom;

import com.example.springsecuritycustom.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HelloController.class)
@Import(SecurityConfig.class)
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    MockMvc mockMvc;


    @WithAnonymousUser
    @Test
    @DisplayName("인증이 필요없는 hello에 접근한다")
    void get_hello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"));
    }

    @WithMockUser
    @Test
    @DisplayName("보호된 리소스인 my에 접근한다")
    void get_my() throws Exception {
        mockMvc.perform(get("/my"))
                .andExpect(status().isOk())
                .andExpect(view().name("my"));
    }

    @WithAnonymousUser
    @Test
    @DisplayName("보호된 리소스인 my에 접근이 실패한다")
    void get_my_anonymous() throws Exception {
        mockMvc.perform(get("/my"))
                .andExpect(status().is3xxRedirection());
    }



}