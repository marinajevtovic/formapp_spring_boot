package com.formapp.formapp.service.impl;

import com.formapp.formapp.exception.ResourceNotFoundException;
import com.formapp.formapp.model.Form;
import com.formapp.formapp.repository.FormRepository;
import com.formapp.formapp.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;

@Service
public class FormServiceImpl implements FormService {
    @Autowired
    private FormRepository formRepository;
    @Override
    public Form createForm(Form form) {
        Assert.notNull(form,"form can't be null");
        return formRepository.save(form);
    }

    @Override
    public boolean deleteForm(Long id) {
        Assert.notNull(id,"id can't be null");
        Form form=formRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Form with id"+id+"not found"));
        formRepository.delete(form);
        return !formRepository.existsById(id);
    }

    @Override
    public List<Form> getForms() {

        List<Form> forms = formRepository.findAll();
        return forms;    }

    @Override
    public Form getForm(Long id) {
        Assert.notNull(id,"id can't be null");
        return  formRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Form with id "+id+"not found"));
    }
}
