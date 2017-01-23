--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.2
-- Dumped by pg_dump version 9.2.2
-- Started on 2016-04-11 17:56:56

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 7 (class 2615 OID 24712)
-- Name: pracutal; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA pracutal;


ALTER SCHEMA pracutal OWNER TO postgres;

SET search_path = pracutal, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 171 (class 1259 OID 24784)
-- Name: academic_director; Type: TABLE; Schema: pracutal; Owner: postgres; Tablespace: 
--

CREATE TABLE academic_director (
    rut integer NOT NULL,
    career_id integer,
    created_at timestamp without time zone,
    disabled boolean,
    updated_at timestamp without time zone
);


ALTER TABLE pracutal.academic_director OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 24828)
-- Name: application; Type: TABLE; Schema: pracutal; Owner: postgres; Tablespace: 
--

CREATE TABLE application (
    approved boolean,
    created_at timestamp without time zone,
    id integer NOT NULL,
    offer_id integer,
    priority_score integer,
    student_id integer,
    updated_at timestamp without time zone,
    status character varying(50)
);


ALTER TABLE pracutal.application OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 24833)
-- Name: application_comment; Type: TABLE; Schema: pracutal; Owner: postgres; Tablespace: 
--

CREATE TABLE application_comment (
    application_id integer,
    comment character varying(255),
    user_id integer,
    created_at timestamp without time zone,
    id integer NOT NULL
);


ALTER TABLE pracutal.application_comment OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 32926)
-- Name: career; Type: TABLE; Schema: pracutal; Owner: postgres; Tablespace: 
--

CREATE TABLE career (
    career_id integer NOT NULL,
    career_name character varying(50)
);


ALTER TABLE pracutal.career OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 32929)
-- Name: career_career_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: postgres
--

CREATE SEQUENCE career_career_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pracutal.career_career_id_seq OWNER TO postgres;

--
-- TOC entry 2035 (class 0 OID 0)
-- Dependencies: 185
-- Name: career_career_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: postgres
--

ALTER SEQUENCE career_career_id_seq OWNED BY career.career_id;


--
-- TOC entry 179 (class 1259 OID 24843)
-- Name: evaluation; Type: TABLE; Schema: pracutal; Owner: postgres; Tablespace: 
--

CREATE TABLE evaluation (
    comment character varying(255),
    created_at timestamp without time zone,
    date date,
    disabled boolean,
    grade numeric,
    id integer NOT NULL,
    internship_id integer,
    updated_at timestamp without time zone
);


ALTER TABLE pracutal.evaluation OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 24804)
-- Name: internship; Type: TABLE; Schema: pracutal; Owner: postgres; Tablespace: 
--

CREATE TABLE internship (
    approved boolean,
    branch_office_id integer,
    closed boolean,
    created_at timestamp without time zone,
    description text,
    disable boolean,
    duration character varying(255),
    id integer NOT NULL,
    start_date date,
    student_rut integer,
    supervisor_id integer,
    updated_at timestamp without time zone
);


ALTER TABLE pracutal.internship OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 24838)
-- Name: logbook; Type: TABLE; Schema: pracutal; Owner: postgres; Tablespace: 
--

CREATE TABLE logbook (
    created_at timestamp without time zone,
    disabled boolean,
    id integer NOT NULL,
    internship_id integer,
    updated_at timestamp without time zone
);


ALTER TABLE pracutal.logbook OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 24812)
-- Name: offer; Type: TABLE; Schema: pracutal; Owner: postgres; Tablespace: 
--

CREATE TABLE offer (
    organization_id integer,
    created_at timestamp without time zone,
    description text,
    disabled boolean,
    duration character varying(100),
    email character varying(100),
    end_date date,
    has_income boolean,
    has_locomotion boolean,
    has_lunch boolean,
    id integer NOT NULL,
    income integer,
    locomotion integer,
    lunch integer,
    post character varying(255),
    quota integer,
    requirements text,
    start_date_available date,
    start_date_internship date,
    area character varying(150),
    available boolean
);


ALTER TABLE pracutal.offer OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 24820)
-- Name: organization; Type: TABLE; Schema: pracutal; Owner: postgres; Tablespace: 
--

CREATE TABLE organization (
    organization_id integer,
    created_at timestamp without time zone,
    description text,
    disabled boolean,
    duration character varying(100),
    email character varying(100),
    end_date date,
    has_income boolean,
    has_locomotion boolean,
    has_lunch boolean,
    id integer NOT NULL,
    income integer,
    locomotion integer,
    lunch integer,
    post character varying(255),
    quota integer,
    requirements text,
    start_date_available date,
    start_date_practice date,
    area character varying(150),
    available boolean
);


ALTER TABLE pracutal.organization OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 32911)
-- Name: role; Type: TABLE; Schema: pracutal; Owner: postgres; Tablespace: 
--

CREATE TABLE role (
    id integer NOT NULL,
    name character varying(100),
    updated_at timestamp without time zone DEFAULT now(),
    created_at timestamp without time zone DEFAULT now()
);


ALTER TABLE pracutal.role OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 32909)
-- Name: role_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: postgres
--

CREATE SEQUENCE role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pracutal.role_id_seq OWNER TO postgres;

--
-- TOC entry 2036 (class 0 OID 0)
-- Dependencies: 181
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: postgres
--

ALTER SEQUENCE role_id_seq OWNED BY role.id;


--
-- TOC entry 183 (class 1259 OID 32919)
-- Name: role_user; Type: TABLE; Schema: pracutal; Owner: postgres; Tablespace: 
--

CREATE TABLE role_user (
    role_id integer NOT NULL,
    rut integer NOT NULL
);


ALTER TABLE pracutal.role_user OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 24776)
-- Name: student; Type: TABLE; Schema: pracutal; Owner: postgres; Tablespace: 
--

CREATE TABLE student (
    address character varying(100),
    birthdate date,
    city character varying(60),
    commune_id integer,
    created_at timestamp without time zone DEFAULT now(),
    job_objective text,
    entry_year integer,
    rut integer NOT NULL,
    phone_number integer,
    internships_approved integer,
    priority_score integer,
    region_id integer,
    tuition_number character varying(255),
    updated_at timestamp without time zone DEFAULT now(),
    citizenship character varying(50),
    gender character varying(15),
    career_id integer,
    registration_number integer
);


ALTER TABLE pracutal.student OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 24789)
-- Name: supervisor; Type: TABLE; Schema: pracutal; Owner: postgres; Tablespace: 
--

CREATE TABLE supervisor (
    rut integer NOT NULL,
    created_at timestamp without time zone,
    disabled boolean,
    phone_number integer
);


ALTER TABLE pracutal.supervisor OWNER TO postgres;

--
-- TOC entry 169 (class 1259 OID 24768)
-- Name: user; Type: TABLE; Schema: pracutal; Owner: postgres; Tablespace: 
--

CREATE TABLE "user" (
    created_at timestamp without time zone,
    current_sign_in_at timestamp without time zone,
    current_sign_in_ip inet,
    email character varying(50),
    password character varying,
    rut integer NOT NULL,
    rut_dv character(1),
    last_sign_in_at timestamp without time zone,
    last_sign_in_ip inet,
    remember_created_at timestamp without time zone,
    reset_password_sent_at timestamp without time zone,
    reset_password_token character varying(50),
    sign_in_count integer,
    updated_at timestamp without time zone,
    first_name character varying(100),
    last_name character varying(100),
    mother_last_name character varying(30)
);


ALTER TABLE pracutal."user" OWNER TO postgres;

--
-- TOC entry 1986 (class 2604 OID 32931)
-- Name: career_id; Type: DEFAULT; Schema: pracutal; Owner: postgres
--

ALTER TABLE ONLY career ALTER COLUMN career_id SET DEFAULT nextval('career_career_id_seq'::regclass);


--
-- TOC entry 1983 (class 2604 OID 32914)
-- Name: id; Type: DEFAULT; Schema: pracutal; Owner: postgres
--

ALTER TABLE ONLY role ALTER COLUMN id SET DEFAULT nextval('role_id_seq'::regclass);


--
-- TOC entry 2017 (class 0 OID 24784)
-- Dependencies: 171
-- Data for Name: academic_director; Type: TABLE DATA; Schema: pracutal; Owner: postgres
--

COPY academic_director (rut, career_id, created_at, disabled, updated_at) FROM stdin;
\.


--
-- TOC entry 2022 (class 0 OID 24828)
-- Dependencies: 176
-- Data for Name: application; Type: TABLE DATA; Schema: pracutal; Owner: postgres
--

COPY application (approved, created_at, id, offer_id, priority_score, student_id, updated_at, status) FROM stdin;
\.


--
-- TOC entry 2023 (class 0 OID 24833)
-- Dependencies: 177
-- Data for Name: application_comment; Type: TABLE DATA; Schema: pracutal; Owner: postgres
--

COPY application_comment (application_id, comment, user_id, created_at, id) FROM stdin;
\.


--
-- TOC entry 2029 (class 0 OID 32926)
-- Dependencies: 184
-- Data for Name: career; Type: TABLE DATA; Schema: pracutal; Owner: postgres
--

COPY career (career_id, career_name) FROM stdin;
4070	Ingeniería Civil en Computación
\.


--
-- TOC entry 2037 (class 0 OID 0)
-- Dependencies: 185
-- Name: career_career_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: postgres
--

SELECT pg_catalog.setval('career_career_id_seq', 1, false);


--
-- TOC entry 2025 (class 0 OID 24843)
-- Dependencies: 179
-- Data for Name: evaluation; Type: TABLE DATA; Schema: pracutal; Owner: postgres
--

COPY evaluation (comment, created_at, date, disabled, grade, id, internship_id, updated_at) FROM stdin;
\.


--
-- TOC entry 2019 (class 0 OID 24804)
-- Dependencies: 173
-- Data for Name: internship; Type: TABLE DATA; Schema: pracutal; Owner: postgres
--

COPY internship (approved, branch_office_id, closed, created_at, description, disable, duration, id, start_date, student_rut, supervisor_id, updated_at) FROM stdin;
\.


--
-- TOC entry 2024 (class 0 OID 24838)
-- Dependencies: 178
-- Data for Name: logbook; Type: TABLE DATA; Schema: pracutal; Owner: postgres
--

COPY logbook (created_at, disabled, id, internship_id, updated_at) FROM stdin;
\.


--
-- TOC entry 2020 (class 0 OID 24812)
-- Dependencies: 174
-- Data for Name: offer; Type: TABLE DATA; Schema: pracutal; Owner: postgres
--

COPY offer (organization_id, created_at, description, disabled, duration, email, end_date, has_income, has_locomotion, has_lunch, id, income, locomotion, lunch, post, quota, requirements, start_date_available, start_date_internship, area, available) FROM stdin;
1	\N	Se necesita analista programador para reemplazo	f	3 Meses	contacto@utalca.cl	2016-01-22	t	t	t	2	\N	\N	\N	\N	\N	\N	\N	\N	\N	t
1	2016-01-18 22:56:37.85	Dscripasdofpaskdfopasdkfpask fopas dfaspdf pasdfp asdf sf asf a fas sdafsafasdfafasdfadfa ds f sdf asdf as dfa sfd sf as df asdf asd f asf asd a sf asdf asfa sdf asdfa sdf sdf asdf asfd asfd asdf asfa sdf asfda sdf adsf asdf asdf  afd a fa sd asd a a a a a a s ddddddddddfffffffffffffffffffffffffwwwwwwwwww feeeeeeeeeeeeeee wwwwwwwwwwwwww aaaaaaaaaaaa qqqqqqqqqqqq ddddddddddddd rrrrrrrrrrrr	f	5 dias	postula@entel.cl	2016-01-20	t	t	f	1	\N	\N	\N	\N	\N	\N	\N	\N	\N	t
\.


--
-- TOC entry 2021 (class 0 OID 24820)
-- Dependencies: 175
-- Data for Name: organization; Type: TABLE DATA; Schema: pracutal; Owner: postgres
--

COPY organization (organization_id, created_at, description, disabled, duration, email, end_date, has_income, has_locomotion, has_lunch, id, income, locomotion, lunch, post, quota, requirements, start_date_available, start_date_practice, area, available) FROM stdin;
\.


--
-- TOC entry 2027 (class 0 OID 32911)
-- Dependencies: 182
-- Data for Name: role; Type: TABLE DATA; Schema: pracutal; Owner: postgres
--

COPY role (id, name, updated_at, created_at) FROM stdin;
1	admin	2016-01-11 23:49:42.938	2016-01-11 23:49:42.938
2	organization	2016-01-12 00:15:59.075	2016-01-12 00:15:59.075
\.


--
-- TOC entry 2038 (class 0 OID 0)
-- Dependencies: 181
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: postgres
--

SELECT pg_catalog.setval('role_id_seq', 1, true);


--
-- TOC entry 2028 (class 0 OID 32919)
-- Dependencies: 183
-- Data for Name: role_user; Type: TABLE DATA; Schema: pracutal; Owner: postgres
--

COPY role_user (role_id, rut) FROM stdin;
1	16494662
\.


--
-- TOC entry 2016 (class 0 OID 24776)
-- Dependencies: 170
-- Data for Name: student; Type: TABLE DATA; Schema: pracutal; Owner: postgres
--

COPY student (address, birthdate, city, commune_id, created_at, job_objective, entry_year, rut, phone_number, internships_approved, priority_score, region_id, tuition_number, updated_at, citizenship, gender, career_id, registration_number) FROM stdin;
Carmen 237, depto 706	1987-05-28	Santiago	\N	\N	Egresado de Ingeniería civil en computación con 2 años de experiencia en ingeniería, desarrollo de software y mantención de sistemas, trabajando en equipo y liderando pequeños grupos de personas. Con motivación en aprender nuevas tecnologías y crecer tanto laboralmente como personalmente.	2005	16494662	82456409	3	40	\N	\N	\N	Chilena	Masculino	4070	2005407011
\N	\N	\N	\N	2016-01-13 02:58:42.566	\N	\N	151883699	\N	\N	\N	\N	\N	2016-01-13 02:58:42.566	\N	\N	4070	\N
\.


--
-- TOC entry 2018 (class 0 OID 24789)
-- Dependencies: 172
-- Data for Name: supervisor; Type: TABLE DATA; Schema: pracutal; Owner: postgres
--

COPY supervisor (rut, created_at, disabled, phone_number) FROM stdin;
\.


--
-- TOC entry 2015 (class 0 OID 24768)
-- Dependencies: 169
-- Data for Name: user; Type: TABLE DATA; Schema: pracutal; Owner: postgres
--

COPY "user" (created_at, current_sign_in_at, current_sign_in_ip, email, password, rut, rut_dv, last_sign_in_at, last_sign_in_ip, remember_created_at, reset_password_sent_at, reset_password_token, sign_in_count, updated_at, first_name, last_name, mother_last_name) FROM stdin;
\N	\N	\N	diego.riveros.pinilla@gmail.com	pass	16494662	2	\N	\N	\N	\N	\N	0	\N	Diego	Riveros	Pinilla
\N	\N	\N	bob@gmail.com	secret	11111111	1	\N	\N	\N	\N	\N	0	\N	\N	\N	\N
\.


--
-- TOC entry 2014 (class 2606 OID 32936)
-- Name: PK_CAREER; Type: CONSTRAINT; Schema: pracutal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY career
    ADD CONSTRAINT "PK_CAREER" PRIMARY KEY (career_id);


--
-- TOC entry 1992 (class 2606 OID 24788)
-- Name: pk_academic_director; Type: CONSTRAINT; Schema: pracutal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY academic_director
    ADD CONSTRAINT pk_academic_director PRIMARY KEY (rut);


--
-- TOC entry 2002 (class 2606 OID 24832)
-- Name: pk_application; Type: CONSTRAINT; Schema: pracutal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY application
    ADD CONSTRAINT pk_application PRIMARY KEY (id);


--
-- TOC entry 2004 (class 2606 OID 24837)
-- Name: pk_application_comment; Type: CONSTRAINT; Schema: pracutal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY application_comment
    ADD CONSTRAINT pk_application_comment PRIMARY KEY (id);


--
-- TOC entry 2008 (class 2606 OID 24850)
-- Name: pk_evaluation; Type: CONSTRAINT; Schema: pracutal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY evaluation
    ADD CONSTRAINT pk_evaluation PRIMARY KEY (id);


--
-- TOC entry 1996 (class 2606 OID 24811)
-- Name: pk_internship; Type: CONSTRAINT; Schema: pracutal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY internship
    ADD CONSTRAINT pk_internship PRIMARY KEY (id);


--
-- TOC entry 2006 (class 2606 OID 24842)
-- Name: pk_logbook; Type: CONSTRAINT; Schema: pracutal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY logbook
    ADD CONSTRAINT pk_logbook PRIMARY KEY (id);


--
-- TOC entry 1998 (class 2606 OID 24819)
-- Name: pk_offer; Type: CONSTRAINT; Schema: pracutal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY offer
    ADD CONSTRAINT pk_offer PRIMARY KEY (id);


--
-- TOC entry 2000 (class 2606 OID 24827)
-- Name: pk_organization; Type: CONSTRAINT; Schema: pracutal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY organization
    ADD CONSTRAINT pk_organization PRIMARY KEY (id);


--
-- TOC entry 2010 (class 2606 OID 32918)
-- Name: pk_role; Type: CONSTRAINT; Schema: pracutal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY role
    ADD CONSTRAINT pk_role PRIMARY KEY (id);


--
-- TOC entry 2012 (class 2606 OID 32923)
-- Name: pk_role_user; Type: CONSTRAINT; Schema: pracutal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY role_user
    ADD CONSTRAINT pk_role_user PRIMARY KEY (role_id, rut);


--
-- TOC entry 1990 (class 2606 OID 24783)
-- Name: pk_student; Type: CONSTRAINT; Schema: pracutal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY student
    ADD CONSTRAINT pk_student PRIMARY KEY (rut);


--
-- TOC entry 1994 (class 2606 OID 24793)
-- Name: pk_supervisor; Type: CONSTRAINT; Schema: pracutal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY supervisor
    ADD CONSTRAINT pk_supervisor PRIMARY KEY (rut);


--
-- TOC entry 1988 (class 2606 OID 24775)
-- Name: pk_user; Type: CONSTRAINT; Schema: pracutal; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT pk_user PRIMARY KEY (rut);


-- Completed on 2016-04-11 17:56:57

--
-- PostgreSQL database dump complete
--

