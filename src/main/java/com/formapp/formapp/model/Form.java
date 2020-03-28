package com.formapp.formapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.formapp.formapp.constrains.TableName;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = TableName.FORM_TABLE_NAME)
public class Form {

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("formId")
    private Long formId;
    @Column(name = "name")
    @JsonProperty("name")
    private String name;
    @OneToMany(mappedBy = "form",cascade = CascadeType.ALL)
    @JsonProperty("fieldsets")

    private List<FieldSet> fieldSets;
public Form(){ }
    public Form(Long id, String name) {
        this.formId=id;
        this.name=name;
    }

    public Long getFormId() {
        return formId;
    }

    public String getName() {
        return name;
    }

}
