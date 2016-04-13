-- Table: offer_career
CREATE TABLE pracutal.offer_career (
   offer_id int  NOT NULL,
   career_id int  NOT NULL,
   CONSTRAINT offer_career_pk PRIMARY KEY (offer_id,career_id)
);