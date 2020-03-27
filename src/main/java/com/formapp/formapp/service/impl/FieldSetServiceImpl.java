package com.formapp.formapp.service.impl;

import com.formapp.formapp.exception.ResourceNotFoundException;
import com.formapp.formapp.model.FieldSet;
import com.formapp.formapp.repository.FieldSetRepository;
import com.formapp.formapp.repository.FormRepository;
import com.formapp.formapp.service.FieldSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class FieldSetServiceImpl implements FieldSetService {
    @Autowired
    FieldSetRepository fieldSetRepository;
    @Autowired
    FormRepository formRepository;
    @Override
    public FieldSet createFieldSet(Long form_id, FieldSet fieldSet) {
        Assert.notNull(fieldSet,"CAN'T BE NULL");
        Assert.notNull(form_id,"CAN'T BE NULL");

        return  formRepository.findById(form_id).map(
                form -> {
                    fieldSet.setForm(form);
                    //FieldSet fieldSet1=new FieldSet(fieldSet);
                    return fieldSetRepository.save(fieldSet);
                }
        ).orElseThrow(()->new ResourceNotFoundException("Form with id"+form_id+"don't exist"));
    }

    @Override
    public List<FieldSet> getFieldSets() {
        List<FieldSet> fieldSets=fieldSetRepository.findAll();
        return  fieldSets;
    }

    @Override
    public boolean deleteFieldSet(Long id) {
        Assert.notNull(id,"CAN'T BE NULL");
        FieldSet fieldSet=fieldSetRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Field set with id"+id+"don't exist"));
        fieldSetRepository.delete(fieldSet);
        return !fieldSetRepository.existsById(id);
    }

    @Override
    public FieldSet getFieldSet(Long id) {
        Assert.notNull(id,"Id can't be null");
        return fieldSetRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Field set with id "+id+"Not found"));
    }
}
