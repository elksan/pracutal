CREATE TABLE pracutal.role_user
(
  role_id integer NOT NULL,
  rut integer NOT NULL,
  CONSTRAINT pk_role_user PRIMARY KEY (role_id, rut)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pracutal.role_user
  OWNER TO postgres;