package com.formapp.formapp.service;

import com.formapp.formapp.model.FieldSet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FieldSetService {
    FieldSet createFieldSet(Long form_id, FieldSet fieldSet);
    List<FieldSet> getFieldSets();
    boolean deleteFieldSet(Long id);
    FieldSet getFieldSet(Long id);
}
