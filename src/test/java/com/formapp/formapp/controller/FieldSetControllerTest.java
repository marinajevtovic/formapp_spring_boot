package com.formapp.formapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formapp.formapp.model.Field;
import com.formapp.formapp.model.FieldSet;
import com.formapp.formapp.model.Form;
import com.formapp.formapp.service.FieldService;
import com.formapp.formapp.service.FieldSetService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FieldSetController.class)
class FieldSetControllerTest {
@Autowired
    private MockMvc mockMvc;
@MockBean
private FieldSetService fieldSetService;


    @Test
    void findFieldSets() throws Exception{
        FieldSet fieldSet = new FieldSet((long) 1, "Field Set exampleee");
        List<FieldSet> allFieldSets = Arrays.asList(fieldSet);
        given(fieldSetService.getFieldSets()).willReturn(allFieldSets);
        mockMvc.perform(get("/api/v1/formdatabase/fieldset").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(fieldSet.getName())));
    }

    @Test
    void createFieldSet() throws Exception {

        FieldSet fieldSet=new FieldSet((long)1,"Field set example");
        given(fieldSetService.createFieldSet((long)1,fieldSet)).willReturn(fieldSet);
        mockMvc.perform(post("/api/v1/formdatabase/form/1/fieldset")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fieldSet))).andExpect(status().isOk()).andDo(print());
                //.andExpect(jsonPath("$.name",is(fieldSet.getName())));
    }

    @Test
    void findFieldSet() throws Exception {

        FieldSet fieldSet=new FieldSet((long)1,"Field");
        given(fieldSetService.getFieldSet((long)1)).willReturn(fieldSet);
        mockMvc.perform(get("/api/v1/formdatabase/fieldset/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(fieldSet.getName())))
                .andExpect(jsonPath("$.fieldsetId",is(fieldSet.getId().intValue())))
                .andDo(print());
    }

    @Test
    void deleteFieldSet() throws Exception {

        FieldSet fieldSet=new FieldSet((long)1,"Field");
given(fieldSetService.deleteFieldSet((long)1)).willReturn(true);
mockMvc.perform(delete("/api/v1/formdatabase/fieldset/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$",is(1))).andDo(print());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}