package com.formapp.formapp.service;

import com.formapp.formapp.model.Field;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FieldService {
    Field createField(Long fieldset_id,Field field);
    List<Field> getFields();
    Field getField(Long id);
    boolean deleteField(Long id);
}
