package com.formapp.formapp.service;

import com.formapp.formapp.model.Form;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FormService {
    Form createForm(Form form);
    boolean deleteForm(Long id);
    List<Form> getForms();
    Form getForm(Long id);
}
