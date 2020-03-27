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
        Assert.notNull(fieldSet,"fieldSet can't be null");
        Assert.notNull(form_id,"formId can't be null");

        return  formRepository.findById(form_id).map(
                form -> {
                    fieldSet.setForm(form);
                    return fieldSetRepository.save(fieldSet);
                }
        ).orElseThrow(()->new ResourceNotFoundException("Form with id"+form_id+"not found"));
    }

    @Override
    public List<FieldSet> getFieldSets() {
        List<FieldSet> fieldSets=fieldSetRepository.findAll();
        return  fieldSets;
    }

    @Override
    public boolean deleteFieldSet(Long id) {
        Assert.notNull(id,"id can't be null");
        FieldSet fieldSet=fieldSetRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("fieldSet with id"+id+" not found"));
        fieldSetRepository.delete(fieldSet);
        return !fieldSetRepository.existsById(id);
    }

    @Override
    public FieldSet getFieldSet(Long id) {
        Assert.notNull(id,"id can't be null");
        return fieldSetRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("fieldSet with id "+id+" not found"));
    }
}
