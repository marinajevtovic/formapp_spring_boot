package com.formapp.formapp.controller;

import com.formapp.formapp.constrains.APIPath;
import com.formapp.formapp.model.Form;
import com.formapp.formapp.service.FormService;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController

@RequestMapping(APIPath.ROOT_PATH)
public class FormController {
    @Autowired
    private FormService formService;

    @GetMapping(path = APIPath.FORM_PATH)
    public ResponseEntity<Object> findForms() {
        List<Form> forms = formService.getForms();
        System.out.println(forms);
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }

    @PostMapping(path = APIPath.FORM_PATH)

    public ResponseEntity<Object> createForm(@Valid @RequestBody Form form) {
        Form createdForm = formService.createForm(form);
        System.out.println(form+" Forma kao argument");
System.out.println(createdForm+"Kreirana forma");
        return ResponseEntity.ok(createdForm);
        //return new ResponseEntity<>(createdForm,HttpStatus.OK);
       // return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(createdForm);
    }

    @GetMapping(path = APIPath.FORM_PATH + "/{id}")
    public ResponseEntity<Object> findForm(@PathVariable("id") Long id) {
        Form form = formService.getForm(id);
        System.out.println(form+" sjsjsjjsjsjsj");
        return new ResponseEntity<>(form, HttpStatus.OK);
    }

    @DeleteMapping(path = APIPath.FORM_PATH + "/{formId}")
    public ResponseEntity<Object> deleteForm(@PathVariable("formId") Long id) {

        boolean isDeleted = formService.deleteForm(id);
        System.out.println(isDeleted+"Jel il ne");
        return ResponseEntity.ok(isDeleted ? 1 : 0);
    }
}
