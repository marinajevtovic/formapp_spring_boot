package com.formapp.formapp.controller;

import com.formapp.formapp.constrains.APIPath;
import com.formapp.formapp.model.Form;
import com.formapp.formapp.service.FormService;
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
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }

    @PostMapping(path = APIPath.FORM_PATH)

    public ResponseEntity<Object> createForm(@Valid @RequestBody Form form) {
        Form createdForm = formService.createForm(form);
        return ResponseEntity.ok(createdForm);
        //return new ResponseEntity<>(createdForm,HttpStatus.OK);
       // return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(createdForm);
    }

    @GetMapping(path = APIPath.FORM_PATH + "/{id}")
    public ResponseEntity<Object> findForm(@PathVariable("id") Long id) {
        Form form = formService.getForm(id);
        return new ResponseEntity<>(form, HttpStatus.OK);
    }

    @DeleteMapping(path = APIPath.FORM_PATH + "/{formId}")
    public ResponseEntity<Object> deleteForm(@PathVariable("formId") Long id) {
        boolean isDeleted = formService.deleteForm(id);
        return ResponseEntity.ok(isDeleted ? 1 : 0);
    }
}
