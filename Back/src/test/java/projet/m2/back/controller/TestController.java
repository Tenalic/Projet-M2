//package projet.m2.back.controller;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import projet.m2.back.entity.Account;
//import projet.m2.back.service.IaccountService;
//
//import javax.persistence.Column;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest
//public class TestController {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private IaccountService iaccountService;
//
//    private static ObjectMapper mapper = new ObjectMapper();
//
//    @Test
//    public void testPostExempleConnexion() throws Exception{
//        String email = "test@test.fr";
//        String password = "test";
//        String lastname = "test";
//        String firstname = "test";
//        String nickname = "test";
//        int nbDice = 0;
//        int credit = 0;
//        Account a = new Account( lastname,  firstname,  nickname,  email,  password);
//        Mockito.when(iaccountService.connexion(email, password)).thenReturn(a);
//        String json = mapper.writeValueAsString(a);
//        mockMvc.perform(post("/account/connection").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
//                .content(json).accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.nickname", Matchers.equalTo("test")));
//    }
//}
