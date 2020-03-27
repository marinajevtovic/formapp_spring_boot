package com.formapp.formapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.formapp.formapp.constrains.TableName;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@Entity
@Table(name = TableName.FIELD_TABLE_NAME)
public class Field {
    @Id
    @Column(name = "id")
    @JsonProperty("fieldId")
    private Long id;
    @Column(name = "type")
    @JsonProperty("type")
    private String type;
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    public Field(Long id , String type, String description) {
        this.id=id;
        this.type=type;
        this.description=description;
    }

    public Field() { }

    public void setFieldSet(FieldSet fieldSet) {
        this.fieldSet = fieldSet;
    }

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = FieldSet.class,optional = false)
    @JoinColumn(name = "fieldset_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FieldSet fieldSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
