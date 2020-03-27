package com.formapp.formapp.controller;

import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.formapp.formapp.model.Form;
import com.formapp.formapp.service.FormService;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONUtil;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.mockito.Mockito.*;

import java.awt.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@RunWith(SpringRunner.class)
@WebMvcTest(FormController.class)
class FormControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FormService formService;

    @Test
    void findForms() throws Exception {
        Form form1 = new Form((long) 1, "Form exampleee");
        List<Form> allForms = Arrays.asList(form1);
        given(formService.getForms()).willReturn(allForms);
        mockMvc.perform(get("/api/v1/formdatabase/form").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(form1.getName()))).andDo(print());
    }


    @Test
    void createForm() throws Exception {
        Form form1 = new Form((long) 1, "Form ");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(form1);
        given(formService.createForm(form1)).willReturn(form1);
        //System.out.println(formService.createForm(form1));
        mockMvc.perform(post("/api/v1/formdatabase/form").content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$", is(form1.getName())));
    }

    @Test
    void findForm() throws Exception {
        Form form1 = new Form((long) 1, "Form ");

        given(formService.getForm((long) 1)).willReturn(form1);

        mockMvc.perform(get("/api/v1/formdatabase/form/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(form1.getName())))
                .andExpect(jsonPath("$.formId", is(form1.getFormId().intValue())))
        .andDo(print());
    }

    @Test
    void deleteForm() throws Exception {
        Form form1 = new Form((long) 1, "Form ");
        given(formService.deleteForm((long) 1)).willReturn(true);
        mockMvc.perform(delete("/api/v1/formdatabase/form/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1))).andDo(print());

    }


}
