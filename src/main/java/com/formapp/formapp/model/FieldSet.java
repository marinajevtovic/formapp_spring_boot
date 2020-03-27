package com.formapp.formapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.formapp.formapp.constrains.TableName;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = TableName.FIELDSET_TABLE_NAME)
public class FieldSet {
    public FieldSet(){

    }
    public FieldSet(FieldSet fieldSet) {
        this.id=fieldSet.id;
        this.name=fieldSet.name;
    }

    public FieldSet( Long id, String name) {
        this.id=id;
        this.name=name;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    @Id

    @Column(name = "id")
    @JsonProperty("fieldsetId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    @JsonProperty("name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Form.class,optional = false)
    @JoinColumn(name = "form_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Form form;
    @OneToMany(mappedBy = "fieldSet",cascade = CascadeType.ALL)
    @JsonProperty("fields")
    private List<Field> fields;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
