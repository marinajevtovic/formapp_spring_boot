CREATE TABLE  "form"(
                        "id" INT NOT NULL,
                        "name" VARCHAR(100) NOT NULL ,
                        CONSTRAINT "pk_f" PRIMARY KEY ("id")
);
CREATE TABLE "fieldset"(
                           "id" INT NOT NULL ,
                           "name" VARCHAR(100) NOT NULL ,
                           "form_id" INT NOT NULL ,
                           CONSTRAINT "pk_fieldset" PRIMARY KEY ("id")
);
ALTER TABLE "fieldset" ADD CONSTRAINT "fk_fieldset_form_id"
    FOREIGN KEY ("form_id") REFERENCES "form" ("id") ON DELETE NO ACTION  ON UPDATE NO ACTION ;


CREATE TABLE "field"(
                        "id" INT NOT NULL ,
                        "type" VARCHAR(100) NOT NULL ,
                        "description" VARCHAR(100) NOT NULL ,
                        "fieldset_id" INT NOT NULL ,
                        CONSTRAINT  "pk_field" PRIMARY KEY ("id")
);
ALTER TABLE "field" ADD CONSTRAINT "fk_field_fieldset_id"
    FOREIGN KEY ("fieldset_id") REFERENCES "fieldset"("id") ON DELETE NO ACTION  ON UPDATE NO ACTION ;