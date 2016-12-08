--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.2
-- Dumped by pg_dump version 9.2.2
-- Started on 2016-12-08 15:34:57

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2147 (class 1262 OID 24705)
-- Name: pracutal; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE pracutal WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Chile.1252' LC_CTYPE = 'Spanish_Chile.1252';


\connect pracutal

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 7 (class 2615 OID 24712)
-- Name: pracutal; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA pracutal;


--
-- TOC entry 200 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2150 (class 0 OID 0)
-- Dependencies: 200
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = pracutal, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 169 (class 1259 OID 24784)
-- Name: academic_director; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE academic_director (
    rut integer NOT NULL,
    career_id integer,
    created_at timestamp without time zone,
    disabled boolean,
    updated_at timestamp without time zone
);


--
-- TOC entry 182 (class 1259 OID 49477)
-- Name: address; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE address (
    street_name character varying(100),
    street_number integer,
    apartment_number integer,
    commune character varying(50),
    id integer NOT NULL,
    city character varying(100)
);


--
-- TOC entry 199 (class 1259 OID 115891)
-- Name: address_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2151 (class 0 OID 0)
-- Dependencies: 199
-- Name: address_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE address_id_seq OWNED BY address.id;


--
-- TOC entry 192 (class 1259 OID 66383)
-- Name: application; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE application (
    id integer NOT NULL,
    offer_id integer NOT NULL,
    student_id integer NOT NULL,
    priority_score integer,
    approved boolean NOT NULL,
    status character varying(50),
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone,
    candidate boolean DEFAULT false NOT NULL
);


--
-- TOC entry 172 (class 1259 OID 24833)
-- Name: application_comment; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE application_comment (
    application_id integer,
    comment character varying(255),
    user_id integer,
    created_at timestamp without time zone,
    id integer NOT NULL
);


--
-- TOC entry 191 (class 1259 OID 66381)
-- Name: application_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE application_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2152 (class 0 OID 0)
-- Dependencies: 191
-- Name: application_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE application_id_seq OWNED BY application.id;


--
-- TOC entry 177 (class 1259 OID 32926)
-- Name: career; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE career (
    career_id integer NOT NULL,
    career_name character varying(50)
);


--
-- TOC entry 178 (class 1259 OID 32929)
-- Name: career_career_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE career_career_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2153 (class 0 OID 0)
-- Dependencies: 178
-- Name: career_career_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE career_career_id_seq OWNED BY career.career_id;


--
-- TOC entry 173 (class 1259 OID 24843)
-- Name: evaluation; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE evaluation (
    comment character varying(255),
    created_at timestamp without time zone,
    date date,
    disabled boolean,
    grade numeric,
    internship_id integer,
    updated_at timestamp without time zone,
    id integer NOT NULL,
    filename character varying(100) NOT NULL
);


--
-- TOC entry 198 (class 1259 OID 99211)
-- Name: evaluation_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE evaluation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2154 (class 0 OID 0)
-- Dependencies: 198
-- Name: evaluation_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE evaluation_id_seq OWNED BY evaluation.id;


--
-- TOC entry 171 (class 1259 OID 24804)
-- Name: internship; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE internship (
    approved boolean,
    closed boolean,
    created_at timestamp without time zone,
    description text,
    disabled boolean,
    duration character varying(255),
    start_date date,
    student_id integer,
    organization_id integer,
    updated_at timestamp without time zone,
    id integer NOT NULL,
    offer_id integer,
    title character varying(60),
    end_date date
);


--
-- TOC entry 193 (class 1259 OID 66432)
-- Name: internship_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE internship_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2155 (class 0 OID 0)
-- Dependencies: 193
-- Name: internship_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE internship_id_seq OWNED BY internship.id;


--
-- TOC entry 195 (class 1259 OID 66456)
-- Name: logbook; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE logbook (
    id integer NOT NULL,
    content text NOT NULL,
    internship_id integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone
);


--
-- TOC entry 194 (class 1259 OID 66454)
-- Name: logbook_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE logbook_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2156 (class 0 OID 0)
-- Dependencies: 194
-- Name: logbook_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE logbook_id_seq OWNED BY logbook.id;


--
-- TOC entry 190 (class 1259 OID 58125)
-- Name: offer; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE offer (
    organization_id integer NOT NULL,
    title character varying(150) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    description text NOT NULL,
    disabled boolean NOT NULL,
    duration character varying(100),
    email character varying(100),
    has_income boolean NOT NULL,
    has_locomotion boolean NOT NULL,
    has_lunch boolean NOT NULL,
    id integer NOT NULL,
    requirements text,
    start_date_available date,
    end_date_available timestamp without time zone,
    start_date_internship date NOT NULL,
    end_date_internship date NOT NULL,
    area character varying(100),
    available boolean NOT NULL,
    updated_at timestamp without time zone DEFAULT now(),
    "position" character varying(60),
    minimal_level_required integer,
    language character varying(100),
    income integer,
    locomotion integer,
    lunch integer,
    location character varying(60) NOT NULL,
    offer_type_id integer NOT NULL,
    approved boolean DEFAULT false NOT NULL,
    closed boolean DEFAULT false NOT NULL,
    created_by_student boolean DEFAULT false NOT NULL,
    student_offer_approved boolean DEFAULT false NOT NULL,
    created_by integer
);


--
-- TOC entry 179 (class 1259 OID 41233)
-- Name: offer_career; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE offer_career (
    offer_id integer NOT NULL,
    career_id integer NOT NULL
);


--
-- TOC entry 189 (class 1259 OID 58123)
-- Name: offer_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE offer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2157 (class 0 OID 0)
-- Dependencies: 189
-- Name: offer_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE offer_id_seq OWNED BY offer.id;


--
-- TOC entry 188 (class 1259 OID 58121)
-- Name: offer_organization_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE offer_organization_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2158 (class 0 OID 0)
-- Dependencies: 188
-- Name: offer_organization_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE offer_organization_id_seq OWNED BY offer.organization_id;


--
-- TOC entry 181 (class 1259 OID 41249)
-- Name: offer_type; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE offer_type (
    id_offer_type integer NOT NULL,
    name_type character(50) NOT NULL,
    description character(100)
);


--
-- TOC entry 180 (class 1259 OID 41247)
-- Name: offer_type_id_offer_type_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE offer_type_id_offer_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2159 (class 0 OID 0)
-- Dependencies: 180
-- Name: offer_type_id_offer_type_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE offer_type_id_offer_type_seq OWNED BY offer_type.id_offer_type;


--
-- TOC entry 185 (class 1259 OID 57772)
-- Name: organization; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE organization (
    id integer NOT NULL,
    activity character varying(100),
    area character varying(60) NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    description text NOT NULL,
    updated_at timestamp without time zone DEFAULT now(),
    webpage character varying(90) NOT NULL,
    phone_number integer NOT NULL
);


--
-- TOC entry 176 (class 1259 OID 32911)
-- Name: role; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE role (
    id integer NOT NULL,
    name character varying(100),
    updated_at timestamp without time zone DEFAULT now(),
    created_at timestamp without time zone DEFAULT now()
);


--
-- TOC entry 175 (class 1259 OID 32909)
-- Name: role_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2160 (class 0 OID 0)
-- Dependencies: 175
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE role_id_seq OWNED BY role.id;


--
-- TOC entry 186 (class 1259 OID 57782)
-- Name: role_user; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE role_user (
    role_id integer NOT NULL,
    user_id integer NOT NULL
);


--
-- TOC entry 187 (class 1259 OID 57846)
-- Name: student; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE student (
    id integer NOT NULL,
    birthdate date,
    job_objective text,
    entry_year integer NOT NULL,
    rut integer,
    phone_number integer,
    internships_approved integer DEFAULT 0 NOT NULL,
    priority_score integer DEFAULT 0 NOT NULL,
    gender character varying(15) NOT NULL,
    career_id integer NOT NULL,
    registration_number integer NOT NULL,
    last_name character varying(50),
    mother_last_name character varying(50),
    curriculum character varying(100)
);


--
-- TOC entry 170 (class 1259 OID 24789)
-- Name: supervisor; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE supervisor (
    rut integer NOT NULL,
    created_at timestamp without time zone,
    disabled boolean,
    phone_number integer
);


--
-- TOC entry 184 (class 1259 OID 57748)
-- Name: user; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE "user" (
    id integer NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    current_sign_in_at timestamp without time zone DEFAULT now(),
    current_sign_in_ip character varying,
    email character varying(50) NOT NULL,
    password character varying(512),
    last_sign_in_at timestamp without time zone,
    last_sign_in_ip character varying,
    remember_created_at timestamp without time zone,
    reset_password_sent_at timestamp without time zone,
    reset_password_token character varying(50),
    sign_in_count integer DEFAULT 0 NOT NULL,
    updated_at timestamp without time zone DEFAULT now(),
    name character varying(50) NOT NULL,
    disabled boolean DEFAULT false NOT NULL,
    profile_photo_path character varying(200)
);


--
-- TOC entry 183 (class 1259 OID 57746)
-- Name: user_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2161 (class 0 OID 0)
-- Dependencies: 183
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


--
-- TOC entry 196 (class 1259 OID 91042)
-- Name: verification_token; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE verification_token (
    token character varying(256) NOT NULL,
    expiry_date timestamp without time zone,
    token_type character varying(50) NOT NULL,
    verified boolean DEFAULT false,
    user_id integer NOT NULL,
    id integer NOT NULL
);


--
-- TOC entry 197 (class 1259 OID 91059)
-- Name: verification_token_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE verification_token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2162 (class 0 OID 0)
-- Dependencies: 197
-- Name: verification_token_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE verification_token_id_seq OWNED BY verification_token.id;


SET search_path = public, pg_catalog;

--
-- TOC entry 174 (class 1259 OID 32897)
-- Name: schema_version; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE schema_version (
    version_rank integer NOT NULL,
    installed_rank integer NOT NULL,
    version character varying(50) NOT NULL,
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


SET search_path = pracutal, pg_catalog;

--
-- TOC entry 2045 (class 2604 OID 66386)
-- Name: id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY application ALTER COLUMN id SET DEFAULT nextval('application_id_seq'::regclass);


--
-- TOC entry 2025 (class 2604 OID 32931)
-- Name: career_id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY career ALTER COLUMN career_id SET DEFAULT nextval('career_career_id_seq'::regclass);


--
-- TOC entry 2020 (class 2604 OID 99213)
-- Name: id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY evaluation ALTER COLUMN id SET DEFAULT nextval('evaluation_id_seq'::regclass);


--
-- TOC entry 2019 (class 2604 OID 66434)
-- Name: id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY internship ALTER COLUMN id SET DEFAULT nextval('internship_id_seq'::regclass);


--
-- TOC entry 2046 (class 2604 OID 66459)
-- Name: id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY logbook ALTER COLUMN id SET DEFAULT nextval('logbook_id_seq'::regclass);


--
-- TOC entry 2037 (class 2604 OID 58128)
-- Name: organization_id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY offer ALTER COLUMN organization_id SET DEFAULT nextval('offer_organization_id_seq'::regclass);


--
-- TOC entry 2038 (class 2604 OID 58129)
-- Name: id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY offer ALTER COLUMN id SET DEFAULT nextval('offer_id_seq'::regclass);


--
-- TOC entry 2026 (class 2604 OID 41252)
-- Name: id_offer_type; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY offer_type ALTER COLUMN id_offer_type SET DEFAULT nextval('offer_type_id_offer_type_seq'::regclass);


--
-- TOC entry 2022 (class 2604 OID 32914)
-- Name: id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY role ALTER COLUMN id SET DEFAULT nextval('role_id_seq'::regclass);


--
-- TOC entry 2027 (class 2604 OID 57751)
-- Name: id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- TOC entry 2048 (class 2604 OID 91061)
-- Name: id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY verification_token ALTER COLUMN id SET DEFAULT nextval('verification_token_id_seq'::regclass);


--
-- TOC entry 2112 (class 0 OID 24784)
-- Dependencies: 169
-- Data for Name: academic_director; Type: TABLE DATA; Schema: pracutal; Owner: -
--



--
-- TOC entry 2125 (class 0 OID 49477)
-- Dependencies: 182
-- Data for Name: address; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO address VALUES ('Avenida del Valle', 515, NULL, 'Huechuraba', 255, 'Santiago');
INSERT INTO address VALUES ('El Bosque Norte', 345, 401, 'Las Condes', 238, 'Santiago');
INSERT INTO address VALUES ('asdasd', NULL, NULL, '', 227, '');
INSERT INTO address VALUES ('calle', NULL, NULL, '', 229, '');
INSERT INTO address VALUES ('Yungai', 3484, NULL, 'Curicó', 251, 'Curicó');
INSERT INTO address VALUES ('Los Niches', 1, NULL, '', 252, '');


--
-- TOC entry 2163 (class 0 OID 0)
-- Dependencies: 199
-- Name: address_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('address_id_seq', 4, true);


--
-- TOC entry 2135 (class 0 OID 66383)
-- Dependencies: 192
-- Data for Name: application; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO application VALUES (36, 31, 251, NULL, false, 'EN_PROCESO', '2016-08-28 02:44:53.198', NULL, false);
INSERT INTO application VALUES (35, 31, 226, NULL, true, 'ACEPTADA', '2016-08-28 02:44:15.879', '2016-08-28 02:54:19.704', true);
INSERT INTO application VALUES (37, 36, 226, NULL, false, 'EN_PROCESO', '2016-09-20 22:19:05.457', NULL, false);
INSERT INTO application VALUES (38, 36, 252, NULL, false, 'EN_PROCESO', '2016-10-27 22:28:12.83', NULL, false);


--
-- TOC entry 2115 (class 0 OID 24833)
-- Dependencies: 172
-- Data for Name: application_comment; Type: TABLE DATA; Schema: pracutal; Owner: -
--



--
-- TOC entry 2164 (class 0 OID 0)
-- Dependencies: 191
-- Name: application_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('application_id_seq', 38, true);


--
-- TOC entry 2120 (class 0 OID 32926)
-- Dependencies: 177
-- Data for Name: career; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO career VALUES (4070, 'Ingeniería Civil en Computación');
INSERT INTO career VALUES (4060, 'Ingeniería Civil Industrial');


--
-- TOC entry 2165 (class 0 OID 0)
-- Dependencies: 178
-- Name: career_career_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('career_career_id_seq', 1, false);


--
-- TOC entry 2116 (class 0 OID 24843)
-- Dependencies: 173
-- Data for Name: evaluation; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO evaluation VALUES (NULL, NULL, '2016-09-21', NULL, NULL, 23, NULL, 35, '227_41256.mobi');


--
-- TOC entry 2166 (class 0 OID 0)
-- Dependencies: 198
-- Name: evaluation_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('evaluation_id_seq', 35, true);


--
-- TOC entry 2114 (class 0 OID 24804)
-- Dependencies: 171
-- Data for Name: internship; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO internship VALUES (false, true, '2016-08-28 02:54:19.602', NULL, false, NULL, '2016-09-30', 226, 227, '2016-09-21 00:01:13.405', 23, 31, 'Oferta 1', '2016-10-28');


--
-- TOC entry 2167 (class 0 OID 0)
-- Dependencies: 193
-- Name: internship_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('internship_id_seq', 25, true);


--
-- TOC entry 2138 (class 0 OID 66456)
-- Dependencies: 195
-- Data for Name: logbook; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO logbook VALUES (16, '<p>texto texto asfasdf asdf&nbsp;</p>
<p>&nbsp;</p>
<p>as</p>
<p>df</p>
<p>asdf</p>
<p>asdf</p>
<p>asfd</p>
<p>&nbsp;</p>', 23, '2016-10-27 22:35:54.299', NULL);


--
-- TOC entry 2168 (class 0 OID 0)
-- Dependencies: 194
-- Name: logbook_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('logbook_id_seq', 16, true);


--
-- TOC entry 2133 (class 0 OID 58125)
-- Dependencies: 190
-- Data for Name: offer; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO offer VALUES (227, 'Oferta 1', '2016-08-27 18:37:43.977', '<p>Descripcion de la oferta 1</p>', false, NULL, NULL, false, false, true, 31, NULL, NULL, NULL, '2016-09-30', '2016-10-28', 'Minería', true, '2016-08-28 02:54:19.709', 'Cargo 1', 8, '', NULL, NULL, NULL, 'Rancagua', 1, true, true, false, false, NULL);
INSERT INTO offer VALUES (238, 'Desarrollar un Portal de trabajos prácticos para la Universidad de Talca', '2016-08-28 23:53:16.105', '<p>Desarrollar un Portal de trabajos pr&aacute;cticos para la Universidad de Talca</p>
<p>&nbsp;</p>', false, NULL, NULL, false, false, false, 32, NULL, NULL, NULL, '2016-08-28', '2016-09-30', 'Informática', false, NULL, '', 8, '', NULL, NULL, NULL, 'Curicó', 2, false, false, true, false, NULL);
INSERT INTO offer VALUES (229, 'Oferta2', '2016-09-04 14:15:18.779', '<p>Oferta 2&nbsp;</p>', false, NULL, NULL, false, true, false, 33, NULL, NULL, NULL, '2016-09-04', '2016-09-30', 'Agrícola', true, '2016-09-04 15:25:32.683', '', 8, '', NULL, NULL, NULL, 'Curicó', 1, false, false, true, false, NULL);
INSERT INTO offer VALUES (238, 'Oferta4', '2016-09-09 00:27:38.561', '<p>oferta 4.1</p>', false, NULL, NULL, true, false, false, 35, NULL, '2016-09-21', '2016-09-21 00:00:00', '2016-09-05', '2016-09-21', '', false, '2016-09-09 21:50:42.63', '', NULL, '', NULL, NULL, NULL, 'Santiago', 5, true, false, false, false, NULL);
INSERT INTO offer VALUES (255, 'Software Developer II', '2016-09-20 21:57:45.715', '<p>descripcion de la oferta</p>', false, NULL, NULL, true, true, true, 36, NULL, '2016-09-20', '2016-09-20 00:00:00', '2016-09-20', '2016-09-20', 'Informatica', true, '2016-09-20 22:01:23.522', 'Software Developer II', 10, 'Inglés', NULL, NULL, NULL, 'Ciudad Empresarial', 5, true, false, false, false, 255);


--
-- TOC entry 2122 (class 0 OID 41233)
-- Dependencies: 179
-- Data for Name: offer_career; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO offer_career VALUES (31, 4070);
INSERT INTO offer_career VALUES (31, 4060);
INSERT INTO offer_career VALUES (32, 4070);
INSERT INTO offer_career VALUES (33, 4060);
INSERT INTO offer_career VALUES (35, 4070);
INSERT INTO offer_career VALUES (36, 4070);
INSERT INTO offer_career VALUES (36, 4060);


--
-- TOC entry 2169 (class 0 OID 0)
-- Dependencies: 189
-- Name: offer_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('offer_id_seq', 36, true);


--
-- TOC entry 2170 (class 0 OID 0)
-- Dependencies: 188
-- Name: offer_organization_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('offer_organization_id_seq', 1, false);


--
-- TOC entry 2124 (class 0 OID 41249)
-- Dependencies: 181
-- Data for Name: offer_type; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO offer_type VALUES (1, 'Práctica                                          ', NULL);
INSERT INTO offer_type VALUES (2, 'Memoria de Título                                 ', NULL);
INSERT INTO offer_type VALUES (3, 'Full Time                                         ', NULL);
INSERT INTO offer_type VALUES (4, 'Part Time                                         ', NULL);
INSERT INTO offer_type VALUES (5, 'Freelance                                         ', NULL);


--
-- TOC entry 2171 (class 0 OID 0)
-- Dependencies: 180
-- Name: offer_type_id_offer_type_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('offer_type_id_offer_type_seq', 5, true);


--
-- TOC entry 2128 (class 0 OID 57772)
-- Dependencies: 185
-- Data for Name: organization; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO organization VALUES (255, NULL, 'Financiera', '2016-09-10 01:20:00.94', '<p>experian</p>', '2016-09-10 01:20:00.94', 'www.experian.com', 1213123123);
INSERT INTO organization VALUES (238, NULL, 'Financiera', '2016-08-25 22:20:14.72', '<p>Equifax descripci&oacute;n 2</p>', '2016-08-25 22:20:14.72', 'www.equifax.com', 754865236);
INSERT INTO organization VALUES (227, NULL, 'Minería', '2016-08-21 18:48:52.618', '<p>Codelco es la empresa m&aacute;s importante de miner&iacute;a de Chileeeeaa</p>', '2016-08-21 18:48:52.618', 'www.codelco.cl', 23456789);
INSERT INTO organization VALUES (229, NULL, 'Agrícola', '2016-08-25 00:05:30.648', '<p>Descripcion de empresa&nbsp;SuperFruit 345</p>', '2016-08-25 00:05:30.648', 'http://www.superfruit.cl/', 123456789);
INSERT INTO organization VALUES (246, NULL, 'Telecomunicaciones', '2016-08-25 22:53:30.661', '<p>Descripcion de entel 2 + +</p>', '2016-08-25 22:53:30.661', 'www.entel.cl', 123456789);


--
-- TOC entry 2119 (class 0 OID 32911)
-- Dependencies: 176
-- Data for Name: role; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO role VALUES (1, 'ADMINISTRADOR', '2016-01-11 23:49:42.938', '2016-01-11 23:49:42.938');
INSERT INTO role VALUES (4, 'DIRECTOR', '2016-08-07 14:06:39.325', '2016-08-07 14:06:39.325');
INSERT INTO role VALUES (3, 'ESTUDIANTE', '2016-04-24 13:37:52.226', '2016-04-24 13:37:52.226');
INSERT INTO role VALUES (2, 'EMPRESA', '2016-01-12 00:15:59.075', '2016-01-12 00:15:59.075');


--
-- TOC entry 2172 (class 0 OID 0)
-- Dependencies: 175
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('role_id_seq', 3, true);


--
-- TOC entry 2129 (class 0 OID 57782)
-- Dependencies: 186
-- Data for Name: role_user; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO role_user VALUES (1, 159);
INSERT INTO role_user VALUES (4, 160);
INSERT INTO role_user VALUES (4, 161);
INSERT INTO role_user VALUES (3, 226);
INSERT INTO role_user VALUES (2, 238);
INSERT INTO role_user VALUES (2, 246);
INSERT INTO role_user VALUES (2, 227);
INSERT INTO role_user VALUES (2, 229);
INSERT INTO role_user VALUES (3, 251);
INSERT INTO role_user VALUES (3, 252);
INSERT INTO role_user VALUES (2, 255);


--
-- TOC entry 2130 (class 0 OID 57846)
-- Dependencies: 187
-- Data for Name: student; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO student VALUES (251, '2016-09-16', NULL, 2005, NULL, NULL, 0, 0, 'Masculino', 4060, 2005406001, 'Perez', '', NULL);
INSERT INTO student VALUES (226, NULL, NULL, 2005, NULL, NULL, 0, 0, 'Masculino', 4070, 2005407011, 'Riveros', 'Pinilla', '226_cv Informático Diego Riveros Pinilla.docx');
INSERT INTO student VALUES (252, NULL, NULL, 2005, NULL, NULL, 0, 0, 'Masculino', 4070, 2005407002, 'Lagos', 'Soto', '252_Carta de reincorporación.docx');


--
-- TOC entry 2113 (class 0 OID 24789)
-- Dependencies: 170
-- Data for Name: supervisor; Type: TABLE DATA; Schema: pracutal; Owner: -
--



--
-- TOC entry 2127 (class 0 OID 57748)
-- Dependencies: 184
-- Data for Name: user; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO "user" VALUES (159, '2016-08-21 14:28:51.931', NULL, NULL, 'cdp@curi.co.uk', '$2a$16$HK1csfdnBLNx0wRO7BMYz.y3H5wnBiIKUGJNDsMZ1GMa.wl.ode2.', NULL, NULL, NULL, NULL, NULL, 0, NULL, 'CdeP', false, NULL);
INSERT INTO "user" VALUES (160, '2016-08-21 14:37:36.427', NULL, NULL, 'escuela1@curi.co.uk', '$2a$16$DPdPTBdNjVuyWhr7L2q8tu9cCT8RD.azPiwUM9tAnmLBa5GC1dTLO', NULL, NULL, NULL, NULL, NULL, 0, NULL, 'Ruth', false, NULL);
INSERT INTO "user" VALUES (161, '2016-08-21 14:38:30.181', NULL, NULL, 'escuela2@curi.co.uk', '$2a$16$YvbdXeDRTdUcXLtIox7KIe/Lr/sKORlAnTgJtFAtqynXgzxKpMEZG', NULL, NULL, NULL, NULL, NULL, 0, NULL, 'Diego', false, NULL);
INSERT INTO "user" VALUES (227, '2016-08-21 18:48:52.628', NULL, NULL, 'empresa1@curi.co.uk', '$2a$16$539NZafZPYj0ZFShrdCoIO30u.Z22lnpW.7BwfXPThl59QqBZG7Vi', NULL, NULL, NULL, NULL, NULL, 0, '2016-09-13 00:14:37.095', 'Codelco', true, '227_Codelco.png');
INSERT INTO "user" VALUES (229, '2016-08-25 00:05:30.699', NULL, NULL, 'empresa2@curi.co.uk', '$2a$16$539NZafZPYj0ZFShrdCoIO30u.Z22lnpW.7BwfXPThl59QqBZG7Vi', NULL, NULL, NULL, NULL, NULL, 0, '2016-09-13 01:25:23.382', 'SuperFruit', true, NULL);
INSERT INTO "user" VALUES (251, '2016-08-28 02:34:32.086', NULL, NULL, 'estudiante1@curi.co.uk', '$2a$16$v.VY6Dvywe5ke.IVdO1QL.u.KkFq1r/0XoNdzNsuQHDrIniTg8PQW', NULL, NULL, NULL, NULL, NULL, 0, '2016-08-28 02:35:40.501', 'Juan', false, NULL);
INSERT INTO "user" VALUES (252, '2016-08-28 13:31:57.288', NULL, NULL, 'estudiante2@curi.co.uk', '$2a$16$whylnfTGC317eKNDVW//s.4x/YId3OW1UYrgQxbB9FFCNXQ84s0eO', NULL, NULL, NULL, NULL, NULL, 0, '2016-08-28 13:33:41.204', 'Julio', false, NULL);
INSERT INTO "user" VALUES (246, '2016-08-25 22:53:30.665', NULL, NULL, 'empresa4@curi.co.uk', NULL, NULL, NULL, NULL, NULL, NULL, 0, '2016-10-27 22:09:59.196', 'Entel', true, NULL);
INSERT INTO "user" VALUES (255, '2016-09-10 01:20:00.969', NULL, NULL, 'empresa5@curi.co.uk', '$2a$16$mXDPkmvYdaYqRXVkdeS.m.0sBdV9qWWcL2oEEq5nP2L1pkn2/i/y2', NULL, NULL, NULL, NULL, NULL, 0, '2016-09-11 18:06:31.463', 'Experian', false, '255_800px-Experian.svg.png');
INSERT INTO "user" VALUES (238, '2016-08-25 22:20:14.746', NULL, NULL, 'empresa3@curi.co.uk', '$2a$16$h0sc2R8dAWrJdPN9nIKOiO.UDZ3u9iCAE.z5BW8ATIgXFJNrolmz6', NULL, NULL, NULL, NULL, NULL, 0, '2016-09-11 18:40:24.099', 'Equifax', false, '238_Equifax-logo.jpg');
INSERT INTO "user" VALUES (226, '2016-08-21 17:28:36.742', NULL, NULL, 'diego.riveros.pinilla@gmail.com', '$2a$16$539NZafZPYj0ZFShrdCoIO30u.Z22lnpW.7BwfXPThl59QqBZG7Vi', NULL, NULL, NULL, NULL, NULL, 0, '2016-10-27 22:21:26.24', 'Diego', false, '226_dark_souls_3-1920x1080.jpg');


--
-- TOC entry 2173 (class 0 OID 0)
-- Dependencies: 183
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('user_id_seq', 255, true);


--
-- TOC entry 2139 (class 0 OID 91042)
-- Dependencies: 196
-- Data for Name: verification_token; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO verification_token VALUES ('vg7r85b3c7d1grn0dus440f78b', '2016-08-21 17:38:36.735', 'EMAIL_REGISTRATION', true, 226, 119);
INSERT INTO verification_token VALUES ('9shggdbhu80qoamia7d2g0kppr', '2016-08-21 18:58:52.615', 'EMAIL_REGISTRATION', true, 227, 120);
INSERT INTO verification_token VALUES ('pu1qto3jjjn1pqkhsa2onginpb', '2016-08-25 00:15:30.612', 'EMAIL_REGISTRATION', true, 229, 121);
INSERT INTO verification_token VALUES ('ug8ctehftaabbi7ec3l1gdha0p', '2016-08-25 22:30:14.718', 'EMAIL_REGISTRATION', true, 238, 122);
INSERT INTO verification_token VALUES ('gbv5930mbudeeg7ghqv5jtk2vu', '2016-08-25 23:03:30.659', 'EMAIL_REGISTRATION', false, 246, 123);
INSERT INTO verification_token VALUES ('egospsd0552mneohla4kcs71tq', '2016-08-28 02:44:31.989', 'EMAIL_REGISTRATION', true, 251, 124);
INSERT INTO verification_token VALUES ('2coijkj272hvnjl720ea0cmknn', '2016-08-28 13:41:57.241', 'EMAIL_REGISTRATION', true, 252, 125);
INSERT INTO verification_token VALUES ('sd2mbeajk7pov98ktlgqlcioj9', '2016-09-10 01:30:00.938', 'EMAIL_REGISTRATION', true, 255, 128);


--
-- TOC entry 2174 (class 0 OID 0)
-- Dependencies: 197
-- Name: verification_token_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('verification_token_id_seq', 128, true);


SET search_path = public, pg_catalog;

--
-- TOC entry 2117 (class 0 OID 32897)
-- Dependencies: 174
-- Data for Name: schema_version; Type: TABLE DATA; Schema: public; Owner: -
--



SET search_path = pracutal, pg_catalog;

--
-- TOC entry 2069 (class 2606 OID 32936)
-- Name: PK_CAREER; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY career
    ADD CONSTRAINT "PK_CAREER" PRIMARY KEY (career_id);


--
-- TOC entry 2075 (class 2606 OID 115898)
-- Name: address_pkey; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- TOC entry 2089 (class 2606 OID 66390)
-- Name: application_ak_1; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY application
    ADD CONSTRAINT application_ak_1 UNIQUE (offer_id, student_id);


--
-- TOC entry 2091 (class 2606 OID 66388)
-- Name: application_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY application
    ADD CONSTRAINT application_pk PRIMARY KEY (id);


--
-- TOC entry 2060 (class 2606 OID 99221)
-- Name: evaluation_pkey; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY evaluation
    ADD CONSTRAINT evaluation_pkey PRIMARY KEY (id);


--
-- TOC entry 2054 (class 2606 OID 66442)
-- Name: internship_pkey; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY internship
    ADD CONSTRAINT internship_pkey PRIMARY KEY (id);


--
-- TOC entry 2056 (class 2606 OID 66444)
-- Name: internship_student_id_offer_id_key; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY internship
    ADD CONSTRAINT internship_student_id_offer_id_key UNIQUE (student_id, offer_id);


--
-- TOC entry 2093 (class 2606 OID 66464)
-- Name: logbook_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY logbook
    ADD CONSTRAINT logbook_pk PRIMARY KEY (id);


--
-- TOC entry 2071 (class 2606 OID 41237)
-- Name: offer_career_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY offer_career
    ADD CONSTRAINT offer_career_pk PRIMARY KEY (offer_id, career_id);


--
-- TOC entry 2087 (class 2606 OID 58135)
-- Name: offer_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY offer
    ADD CONSTRAINT offer_pk PRIMARY KEY (id);


--
-- TOC entry 2073 (class 2606 OID 41254)
-- Name: offer_type_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY offer_type
    ADD CONSTRAINT offer_type_pk PRIMARY KEY (id_offer_type);


--
-- TOC entry 2081 (class 2606 OID 57781)
-- Name: organization_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY organization
    ADD CONSTRAINT organization_pk PRIMARY KEY (id);


--
-- TOC entry 2050 (class 2606 OID 24788)
-- Name: pk_academic_director; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY academic_director
    ADD CONSTRAINT pk_academic_director PRIMARY KEY (rut);


--
-- TOC entry 2058 (class 2606 OID 24837)
-- Name: pk_application_comment; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY application_comment
    ADD CONSTRAINT pk_application_comment PRIMARY KEY (id);


--
-- TOC entry 2067 (class 2606 OID 32918)
-- Name: pk_role; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY role
    ADD CONSTRAINT pk_role PRIMARY KEY (id);


--
-- TOC entry 2052 (class 2606 OID 24793)
-- Name: pk_supervisor; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY supervisor
    ADD CONSTRAINT pk_supervisor PRIMARY KEY (rut);


--
-- TOC entry 2083 (class 2606 OID 57786)
-- Name: role_user_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY role_user
    ADD CONSTRAINT role_user_pk PRIMARY KEY (role_id, user_id);


--
-- TOC entry 2085 (class 2606 OID 57857)
-- Name: student_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY student
    ADD CONSTRAINT student_pk PRIMARY KEY (id);


--
-- TOC entry 2077 (class 2606 OID 107433)
-- Name: user_email_key; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_email_key UNIQUE (email);


--
-- TOC entry 2079 (class 2606 OID 57759)
-- Name: user_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- TOC entry 2095 (class 2606 OID 91066)
-- Name: verification_token_pkey; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY verification_token
    ADD CONSTRAINT verification_token_pkey PRIMARY KEY (id);


SET search_path = public, pg_catalog;

--
-- TOC entry 2063 (class 2606 OID 32905)
-- Name: schema_version_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY schema_version
    ADD CONSTRAINT schema_version_pk PRIMARY KEY (version);


--
-- TOC entry 2061 (class 1259 OID 32907)
-- Name: schema_version_ir_idx; Type: INDEX; Schema: public; Owner: -; Tablespace: 
--

CREATE INDEX schema_version_ir_idx ON schema_version USING btree (installed_rank);


--
-- TOC entry 2064 (class 1259 OID 32908)
-- Name: schema_version_s_idx; Type: INDEX; Schema: public; Owner: -; Tablespace: 
--

CREATE INDEX schema_version_s_idx ON schema_version USING btree (success);


--
-- TOC entry 2065 (class 1259 OID 32906)
-- Name: schema_version_vr_idx; Type: INDEX; Schema: public; Owner: -; Tablespace: 
--

CREATE INDEX schema_version_vr_idx ON schema_version USING btree (version_rank);


SET search_path = pracutal, pg_catalog;

--
-- TOC entry 2102 (class 2606 OID 115944)
-- Name: address_id_fkey; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_id_fkey FOREIGN KEY (id) REFERENCES "user"(id);


--
-- TOC entry 2108 (class 2606 OID 66420)
-- Name: application_offer; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY application
    ADD CONSTRAINT application_offer FOREIGN KEY (offer_id) REFERENCES offer(id);


--
-- TOC entry 2109 (class 2606 OID 66425)
-- Name: application_student; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY application
    ADD CONSTRAINT application_student FOREIGN KEY (student_id) REFERENCES student(id);


--
-- TOC entry 2099 (class 2606 OID 107557)
-- Name: evaluation_internship_id_fkey; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY evaluation
    ADD CONSTRAINT evaluation_internship_id_fkey FOREIGN KEY (internship_id) REFERENCES internship(id);


--
-- TOC entry 2098 (class 2606 OID 107548)
-- Name: internship_offer_id_fkey; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY internship
    ADD CONSTRAINT internship_offer_id_fkey FOREIGN KEY (offer_id) REFERENCES offer(id);


--
-- TOC entry 2097 (class 2606 OID 107543)
-- Name: internship_organization_id_fkey; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY internship
    ADD CONSTRAINT internship_organization_id_fkey FOREIGN KEY (organization_id) REFERENCES organization(id);


--
-- TOC entry 2096 (class 2606 OID 107538)
-- Name: internship_student_id_fkey; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY internship
    ADD CONSTRAINT internship_student_id_fkey FOREIGN KEY (student_id) REFERENCES student(id);


--
-- TOC entry 2110 (class 2606 OID 74635)
-- Name: logbook_internship; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY logbook
    ADD CONSTRAINT logbook_internship FOREIGN KEY (internship_id) REFERENCES internship(id);


--
-- TOC entry 2100 (class 2606 OID 57934)
-- Name: offer_career_career; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY offer_career
    ADD CONSTRAINT offer_career_career FOREIGN KEY (career_id) REFERENCES career(career_id);


--
-- TOC entry 2101 (class 2606 OID 58136)
-- Name: offer_career_offer; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY offer_career
    ADD CONSTRAINT offer_career_offer FOREIGN KEY (offer_id) REFERENCES offer(id);


--
-- TOC entry 2107 (class 2606 OID 107664)
-- Name: offer_organization_id_fkey; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY offer
    ADD CONSTRAINT offer_organization_id_fkey FOREIGN KEY (organization_id) REFERENCES organization(id);


--
-- TOC entry 2103 (class 2606 OID 115924)
-- Name: organization_user; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY organization
    ADD CONSTRAINT organization_user FOREIGN KEY (id) REFERENCES "user"(id);


--
-- TOC entry 2104 (class 2606 OID 57792)
-- Name: role_user_role; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY role_user
    ADD CONSTRAINT role_user_role FOREIGN KEY (role_id) REFERENCES role(id);


--
-- TOC entry 2106 (class 2606 OID 115949)
-- Name: student_id_fkey; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY student
    ADD CONSTRAINT student_id_fkey FOREIGN KEY (id) REFERENCES "user"(id);


--
-- TOC entry 2105 (class 2606 OID 57802)
-- Name: user_role_user; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY role_user
    ADD CONSTRAINT user_role_user FOREIGN KEY (user_id) REFERENCES "user"(id);


--
-- TOC entry 2111 (class 2606 OID 107519)
-- Name: verification_token_user_id_fkey; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY verification_token
    ADD CONSTRAINT verification_token_user_id_fkey FOREIGN KEY (user_id) REFERENCES "user"(id);


--
-- TOC entry 2149 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-12-08 15:34:59

--
-- PostgreSQL database dump complete
--

