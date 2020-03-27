package com.formapp.formapp.controller;

import com.formapp.formapp.constrains.APIPath;
import com.formapp.formapp.model.FieldSet;
import com.formapp.formapp.service.FieldSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(APIPath.ROOT_PATH)
public class FieldSetController {
    @Autowired
    FieldSetService fieldSetService;

    @GetMapping(path = APIPath.FIELDSET_PATH)
    public ResponseEntity<Object> findFieldSets() {
        List<FieldSet> fieldSets = fieldSetService.getFieldSets();
        return new ResponseEntity<>(fieldSets, HttpStatus.OK);
    }

    @PostMapping(path = APIPath.FORM_PATH+"/{form_id}"+APIPath.FIELDSET_PATH)
    public ResponseEntity<Object> createFieldSet(@PathVariable("form_id") Long form_id,@Valid @RequestBody FieldSet fieldSet) {
        FieldSet createdFieldSet = fieldSetService.createFieldSet(form_id,fieldSet);
        return ResponseEntity.ok(createdFieldSet);
    }
    @GetMapping(path = APIPath.FIELDSET_PATH+"/{fieldsetId}")
    public ResponseEntity<Object> findFieldSet(@PathVariable("fieldsetId") Long id){
        FieldSet fieldSet=fieldSetService.getFieldSet(id);
        return new ResponseEntity<>(fieldSet,HttpStatus.OK);
    }
    @DeleteMapping(path =APIPath.FIELDSET_PATH+"/{fieldsetId}" )
    public ResponseEntity<Object> deleteFieldSet(@PathVariable("fieldsetId") Long fieldsetId){
        boolean isDeleted=fieldSetService.deleteFieldSet(fieldsetId);
        return ResponseEntity.ok(isDeleted ? 1:0);
    }
}
