package com.formapp.formapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formapp.formapp.model.Field;
import com.formapp.formapp.model.FieldSet;
import com.formapp.formapp.service.FieldService;
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
@WebMvcTest(FieldController.class)
class FieldControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FieldService fieldService;

    @Test
    void findFields() throws Exception {
        Field field = new Field((long) 1, "input", "Name");
        List<Field> allFields = Arrays.asList(field);
        given(fieldService.getFields()).willReturn(allFields);
        mockMvc.perform(get("/api/v1/formdatabase/field").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].type", is(field.getType())))
                .andExpect(jsonPath("$[0].description", is(field.getDescription())))
                .andDo(print());
    }

    @Test
    void createField() throws Exception{
        Field field= new Field((long) 1, "input", "Name");
        given(fieldService.createField((long)1,field)).willReturn(field);
        mockMvc.perform(post("/api/v1/formdatabase/form/1/fieldset/1/field")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(field))).andExpect(status().isOk()).andDo(print());
        //.andExpect(jsonPath("$.type",is(field.getType())));

    }

    @Test
    void findField() throws Exception{
        Field field = new Field((long) 1, "input", "Name");
        given(fieldService.getField((long)1)).willReturn(field);
        mockMvc.perform(get("/api/v1/formdatabase/field/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fieldId",is(field.getId().intValue())))
                .andExpect(jsonPath("$.type", is(field.getType())))
                .andExpect(jsonPath("$.description", is(field.getDescription())))
                .andDo(print());

    }

    @Test
    void deleteField() throws Exception{
        given(fieldService.deleteField((long)1)).willReturn(true);
        mockMvc.perform(delete("/api/v1/formdatabase/field/1"))
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