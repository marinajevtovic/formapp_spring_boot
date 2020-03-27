INSERT INTO "form" ("id", "name") VALUES (1, N'Form Example');
INSERT INTO "fieldset" ("id","name","form_id") VALUES (1,N'Field Set 1',1);
INSERT INTO "fieldset" ("id","name","form_id") VALUES (2,N'Field Set 2',1);
INSERT INTO "field" ("id","type","description","fieldset_id") VALUES (1,N'checkbox',N'are you a robot',1);
INSERT INTO "field" ("id","type","description","fieldset_id") VALUES (2,N'input',N'Enter your first name',1);