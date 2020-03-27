package com.formapp.formapp.service.impl;

import com.formapp.formapp.exception.ResourceNotFoundException;
import com.formapp.formapp.model.Field;
import com.formapp.formapp.model.FieldSet;
import com.formapp.formapp.repository.FieldRepository;
import com.formapp.formapp.repository.FieldSetRepository;
import com.formapp.formapp.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {
    @Autowired
    FieldRepository fieldRepository;
    @Autowired
    FieldSetRepository fieldSetRepository;
    @Override
    public Field createField(Long fieldset_id, Field field) {
        Assert.notNull(fieldset_id,"CAN'T BE NULL");
        return fieldSetRepository.findById(fieldset_id).map(
                fieldSet -> {
                    field.setFieldSet(fieldSet);
                    return fieldRepository.save(field);
                }
        ).orElseThrow(()->new ResourceNotFoundException("Field set with id"+fieldset_id+"don't exist"));
    }

    @Override
    public List<Field> getFields() {
        return fieldRepository.findAll();
    }

    @Override
    public Field getField(Long id) {
        Assert.notNull(id,"Id can't be null");
        return fieldRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Field with id"+id+"not found"));
    }

    @Override
    public boolean deleteField(Long id) {
        Assert.notNull(id,"Id can't be null");
        Field field=fieldRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Field with id"+id+"not found"));
        fieldRepository.delete(field);
        return !fieldRepository.existsById(id);

    }
}
