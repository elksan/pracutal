CREATE TABLE pracutal.role
(
  id serial NOT NULL,
  name character varying(100),
  updated_at timestamp without time zone default now(),
  created_at timestamp without time zone default now(),
  CONSTRAINT pk_role PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pracutal.role
  OWNER TO postgres;
