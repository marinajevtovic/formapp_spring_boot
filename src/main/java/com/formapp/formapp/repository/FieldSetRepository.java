package com.formapp.formapp.repository;

import com.formapp.formapp.model.FieldSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldSetRepository extends JpaRepository<FieldSet,Long> {
}
