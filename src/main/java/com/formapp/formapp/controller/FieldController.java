package com.formapp.formapp.controller;

import com.formapp.formapp.constrains.APIPath;
import com.formapp.formapp.model.Field;
import com.formapp.formapp.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(APIPath.ROOT_PATH)
public class FieldController {
    @Autowired
    FieldService fieldService;
    @GetMapping(path = APIPath.FIELD_PATH)
    public ResponseEntity<Object> findFields() {
        List<Field> field = fieldService.getFields();
        return new ResponseEntity<>(field, HttpStatus.OK);
    }
    @PostMapping(path = APIPath.FORM_PATH+"/{form_id}"+APIPath.FIELDSET_PATH+"/{fieldset_id}"+APIPath.FIELD_PATH)
    public ResponseEntity<Object> createField(@PathVariable("form_id") Long form_id,@PathVariable("fieldset_id") Long fieldset_id
            ,@Valid @RequestBody Field field) {
        Field createdField = fieldService.createField(fieldset_id,field);
        return ResponseEntity.ok(createdField);

    }
    @GetMapping(path = APIPath.FIELD_PATH+"/{fieldId}")
    public ResponseEntity<Object> findField(@PathVariable("fieldId") Long id){
        Field field=fieldService.getField(id);
        return new ResponseEntity<>(field,HttpStatus.OK);

    }
    @DeleteMapping(path = APIPath.FIELD_PATH+"/{fieldId}")
    public ResponseEntity<Object> deleteField(@PathVariable("fieldId") Long id){
       boolean isDeleted= fieldService.deleteField(id);
        return ResponseEntity.ok(isDeleted?1:0);
    }
}
