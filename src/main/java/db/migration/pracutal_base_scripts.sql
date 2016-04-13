-- Table: offer_career
CREATE TABLE pracutal.offer_career (
   offer_id int  NOT NULL,
   career_id int  NOT NULL,
   CONSTRAINT offer_career_pk PRIMARY KEY (offer_id,career_id)
);

CREATE TABLE pracutal.offer_type
(
   id_offer_type serial NOT NULL,
   name_type character(50) NOT NULL,
   description character(100),
   CONSTRAINT offer_type_pk PRIMARY KEY (id)
);

ALTER TABLE pracutal.offer ADD COLUMN offer_type_id integer;

ALTER TABLE pracutal.offer
  ADD CONSTRAINT offer_type_fk FOREIGN KEY (offer_type_id) REFERENCES pracutal.offer_type (id)
   ON UPDATE NO ACTION ON DELETE NO ACTION;
CREATE INDEX fki_offer_type_fk
  ON pracutal.offer(offer_type_id);
