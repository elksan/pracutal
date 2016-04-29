--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.2
-- Dumped by pg_dump version 9.2.2
-- Started on 2016-04-29 01:04:28

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 7 (class 2615 OID 24712)
-- Name: pracutal; Type: SCHEMA; Schema: -; Owner: -
--
--DROP SCHEMA pracutal;
CREATE SCHEMA pracutal;


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
-- TOC entry 184 (class 1259 OID 49477)
-- Name: address; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE address (
    owner_id integer NOT NULL,
    street character varying(100),
    number integer,
    apartment_number integer NOT NULL,
    commune character varying(50)
);


--
-- TOC entry 172 (class 1259 OID 24828)
-- Name: application; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
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


--
-- TOC entry 173 (class 1259 OID 24833)
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
-- TOC entry 179 (class 1259 OID 32926)
-- Name: career; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE career (
    career_id integer NOT NULL,
    career_name character varying(50)
);


--
-- TOC entry 180 (class 1259 OID 32929)
-- Name: career_career_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE career_career_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2088 (class 0 OID 0)
-- Dependencies: 180
-- Name: career_career_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE career_career_id_seq OWNED BY career.career_id;


--
-- TOC entry 175 (class 1259 OID 24843)
-- Name: evaluation; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
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


--
-- TOC entry 171 (class 1259 OID 24804)
-- Name: internship; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
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


--
-- TOC entry 174 (class 1259 OID 24838)
-- Name: logbook; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE logbook (
    created_at timestamp without time zone,
    disabled boolean,
    id integer NOT NULL,
    internship_id integer,
    updated_at timestamp without time zone
);


--
-- TOC entry 192 (class 1259 OID 58125)
-- Name: offer; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE offer (
    organization_id integer NOT NULL,
    title character varying(60) NOT NULL,
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
    approved boolean DEFAULT false NOT NULL
);


--
-- TOC entry 181 (class 1259 OID 41233)
-- Name: offer_career; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE offer_career (
    offer_id integer NOT NULL,
    career_id integer NOT NULL
);


--
-- TOC entry 191 (class 1259 OID 58123)
-- Name: offer_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE offer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2089 (class 0 OID 0)
-- Dependencies: 191
-- Name: offer_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE offer_id_seq OWNED BY offer.id;


--
-- TOC entry 190 (class 1259 OID 58121)
-- Name: offer_organization_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE offer_organization_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2090 (class 0 OID 0)
-- Dependencies: 190
-- Name: offer_organization_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE offer_organization_id_seq OWNED BY offer.organization_id;


--
-- TOC entry 183 (class 1259 OID 41249)
-- Name: offer_type; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE offer_type (
    id_offer_type integer NOT NULL,
    name_type character(50) NOT NULL,
    description character(100)
);


--
-- TOC entry 182 (class 1259 OID 41247)
-- Name: offer_type_id_offer_type_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE offer_type_id_offer_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2091 (class 0 OID 0)
-- Dependencies: 182
-- Name: offer_type_id_offer_type_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE offer_type_id_offer_type_seq OWNED BY offer_type.id_offer_type;


--
-- TOC entry 187 (class 1259 OID 57772)
-- Name: organization; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE organization (
    id integer NOT NULL,
    activity character varying(100),
    area character varying(60) NOT NULL,
    created_at timestamp without time zone DEFAULT now(),
    disabled boolean NOT NULL,
    description text NOT NULL,
    updated_at timestamp without time zone DEFAULT now(),
    webpage character varying(90) NOT NULL,
    phone_number integer NOT NULL
);


--
-- TOC entry 178 (class 1259 OID 32911)
-- Name: role; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE role (
    id integer NOT NULL,
    name character varying(100),
    updated_at timestamp without time zone DEFAULT now(),
    created_at timestamp without time zone DEFAULT now()
);


--
-- TOC entry 177 (class 1259 OID 32909)
-- Name: role_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2092 (class 0 OID 0)
-- Dependencies: 177
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE role_id_seq OWNED BY role.id;


--
-- TOC entry 188 (class 1259 OID 57782)
-- Name: role_user; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE role_user (
    role_id integer NOT NULL,
    user_id integer NOT NULL
);


--
-- TOC entry 189 (class 1259 OID 57846)
-- Name: student; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE student (
    id integer NOT NULL,
    birthdate date NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    job_objective text NOT NULL,
    entry_year integer NOT NULL,
    rut integer NOT NULL,
    phone_number integer,
    internships_approved integer DEFAULT 0 NOT NULL,
    priority_score integer DEFAULT 0 NOT NULL,
    updated_at timestamp without time zone DEFAULT now() NOT NULL,
    gender character varying(15) NOT NULL,
    career_id integer NOT NULL,
    registration_number integer NOT NULL
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
-- TOC entry 186 (class 1259 OID 57748)
-- Name: user; Type: TABLE; Schema: pracutal; Owner: -; Tablespace: 
--

CREATE TABLE "user" (
    id integer NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    current_sign_in_at timestamp without time zone DEFAULT now(),
    current_sign_in_ip inet,
    email character varying(50) NOT NULL,
    password character varying(512) NOT NULL,
    last_sign_in_at timestamp without time zone,
    last_sign_in_ip inet,
    remember_created_at timestamp without time zone,
    reset_password_sent_at timestamp without time zone,
    reset_password_token character varying(50),
    sign_in_count integer DEFAULT 0 NOT NULL,
    updated_at timestamp without time zone DEFAULT now() NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50),
    mother_last_name character varying(50)
);


--
-- TOC entry 185 (class 1259 OID 57746)
-- Name: user_id_seq; Type: SEQUENCE; Schema: pracutal; Owner: -
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2093 (class 0 OID 0)
-- Dependencies: 185
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: pracutal; Owner: -
--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


--
-- TOC entry 2004 (class 2604 OID 32931)
-- Name: career_id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY career ALTER COLUMN career_id SET DEFAULT nextval('career_career_id_seq'::regclass);


--
-- TOC entry 2017 (class 2604 OID 58128)
-- Name: organization_id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY offer ALTER COLUMN organization_id SET DEFAULT nextval('offer_organization_id_seq'::regclass);


--
-- TOC entry 2018 (class 2604 OID 58129)
-- Name: id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY offer ALTER COLUMN id SET DEFAULT nextval('offer_id_seq'::regclass);


--
-- TOC entry 2005 (class 2604 OID 41252)
-- Name: id_offer_type; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY offer_type ALTER COLUMN id_offer_type SET DEFAULT nextval('offer_type_id_offer_type_seq'::regclass);


--
-- TOC entry 2001 (class 2604 OID 32914)
-- Name: id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY role ALTER COLUMN id SET DEFAULT nextval('role_id_seq'::regclass);


--
-- TOC entry 2006 (class 2604 OID 57751)
-- Name: id; Type: DEFAULT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- TOC entry 2061 (class 0 OID 24784)
-- Dependencies: 169
-- Data for Name: academic_director; Type: TABLE DATA; Schema: pracutal; Owner: -
--



--
-- TOC entry 2075 (class 0 OID 49477)
-- Dependencies: 184
-- Data for Name: address; Type: TABLE DATA; Schema: pracutal; Owner: -
--



--
-- TOC entry 2064 (class 0 OID 24828)
-- Dependencies: 172
-- Data for Name: application; Type: TABLE DATA; Schema: pracutal; Owner: -
--



--
-- TOC entry 2065 (class 0 OID 24833)
-- Dependencies: 173
-- Data for Name: application_comment; Type: TABLE DATA; Schema: pracutal; Owner: -
--



--
-- TOC entry 2070 (class 0 OID 32926)
-- Dependencies: 179
-- Data for Name: career; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO career VALUES (4070, 'Ingeniería Civil en Computación');
INSERT INTO career VALUES (4060, 'Ingeniería Civil Industrial');


--
-- TOC entry 2094 (class 0 OID 0)
-- Dependencies: 180
-- Name: career_career_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('career_career_id_seq', 1, false);


--
-- TOC entry 2067 (class 0 OID 24843)
-- Dependencies: 175
-- Data for Name: evaluation; Type: TABLE DATA; Schema: pracutal; Owner: -
--



--
-- TOC entry 2063 (class 0 OID 24804)
-- Dependencies: 171
-- Data for Name: internship; Type: TABLE DATA; Schema: pracutal; Owner: -
--



--
-- TOC entry 2066 (class 0 OID 24838)
-- Dependencies: 174
-- Data for Name: logbook; Type: TABLE DATA; Schema: pracutal; Owner: -
--



--
-- TOC entry 2083 (class 0 OID 58125)
-- Dependencies: 192
-- Data for Name: offer; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO offer VALUES (1, 'Analista Web', '2016-04-25 22:12:58.4', '<p style="box-sizing: border-box; margin-top: 0px; margin-right: 0px; margin-left: 0px; color: rgb(65, 66, 75); font-family: Lato, sans-serif; font-size: 14.95px; line-height: 23.92px;">En DR buscamos profesionales que les apasione su trabajo y tengan capacidad para innovar, representando nuestra filosofía de proactividad y servicio al cliente.</p><p style="box-sizing: border-box; margin-top: 0px; margin-right: 0px; margin-left: 0px; color: rgb(65, 66, 75); font-family: Lato, sans-serif; font-size: 14.95px; line-height: 23.92px;">Esta vez estamos necesitamos integrar un analista web&nbsp;<span style="box-sizing: border-box; font-weight: 700;">para encargarse del área métricas y posicionamiento, que tenga como mínimo 2 años de experiencia.</span>&nbsp;Su responsabilidad será crear estrategias y entregar información para la toma de decisiones en proyectos de clientes externos e internos, aportando conclusiones y recomendaciones para optimizar los objetivos de negocio.</p><p style="box-sizing: border-box; margin-top: 0px; margin-right: 0px; margin-left: 0px; color: rgb(65, 66, 75); font-family: Lato, sans-serif; font-size: 14.95px; line-height: 23.92px;">Diseñar estrategias de&nbsp;<span style="box-sizing: border-box; font-weight: 700;">posicionamiento, inversión publicitaria y de medición de plataformas digitales</span>, a nivel avanzado. Para esto necesitará tener conocimientos de arquitectura de información, HTML, JavaScript, Cookies y procedimientos de implementación para equipos de desarrollo. Es requisito que cuente con<span style="box-sizing: border-box; font-weight: 700;">&nbsp;certificación en Google Analytics.</span></p><h3 style="box-sizing: border-box; font-family: Lato, sans-serif; font-weight: 900; line-height: 1.2em; color: rgb(101, 102, 116); margin-top: 30px; margin-bottom: 20px; font-size: 20px !important;">Tareas y responsabilidades</h3><ul style="box-sizing: border-box; margin: 15px 0px; padding-left: 20px; color: rgb(65, 66, 75); font-family: Lato, sans-serif; font-size: 14.95px; line-height: 23.92px;"><li style="box-sizing: border-box; list-style: disc;">Diseñar el procedimiento de implementación de la metodología de trabajo.</li><li style="box-sizing: border-box; list-style: disc;">Crear estrategias de marcado.</li><li style="box-sizing: border-box; list-style: disc;">Definir estrategias de posicionamiento (SEO y SEM).</li><li style="box-sizing: border-box; list-style: disc;">Diseñar estrategias de emailing.</li><li style="box-sizing: border-box; list-style: disc;">Construir estrategias de inversión publicitaria.</li><li style="box-sizing: border-box; list-style: disc;">Supervisar el trabajo diario de su equipo, tomando responsabilidad por los entregables que generen.</li><li style="box-sizing: border-box; list-style: disc;">Elaborar y ejecutar talleres de capacitación para los clientes y los equipos internos de trabajo.</li></ul><p style="box-sizing: border-box; margin: 0px 0px 10px; color: rgb(65, 66, 75); font-family: Lato, sans-serif; font-size: 14.95px; line-height: 23.92px;">Estamos ubicados a pasos del Metro Manuel Montt. Oficina con grato ambiente laboral. Nuestro horario es de 8:30 a 18:30 hrs. con una hora de colación. Estamos afiliados a la Caja de Compensación Los Andes la cual cuenta con varios descuentos.&nbsp;</p>', false, NULL, NULL, true, true, false, 5, NULL, NULL, NULL, '2016-04-25', '2016-04-30', 'Informática', false, '2016-04-25 22:54:58.41', 'Analista Web', 2, '', NULL, NULL, NULL, 'Curicó', 1, true);
INSERT INTO offer VALUES (1, 'Gestor Comercial', '2016-04-26 00:22:11.405', '<p style="box-sizing: border-box; margin-top: 0px; margin-right: 0px; margin-left: 0px; color: rgb(65, 66, 75); font-family: Lato, sans-serif; font-size: 14.95px; line-height: 23.92px;">SimpliRoute (www.simpliroute.com) es una startup reconocida a nivel nacional e internacional, fuimos parte de Start Up Chile y &nbsp;de 500 Startups en Silicon Valley y ahora nos encontramos en etapa de crecimiento importante.<a href="https://www.getonbrd.cl/empresas/simplit-solutions" style="box-sizing: border-box; color: rgb(55, 159, 154);"></a></p><p style="box-sizing: border-box; margin-top: 0px; margin-right: 0px; margin-left: 0px; color: rgb(65, 66, 75); font-family: Lato, sans-serif; font-size: 14.95px; line-height: 23.92px;">&nbsp;Estamos expandiendo nuestro equipo comercial para soportar la creciente demanda de nuestro servicio, para lo cual buscamos un encargado que de venta.</p><p style="box-sizing: border-box; margin-top: 0px; margin-right: 0px; margin-left: 0px; color: rgb(65, 66, 75); font-family: Lato, sans-serif; font-size: 14.95px; line-height: 23.92px;"><span style="box-sizing: border-box; font-weight: 700;">Requisitos:</span></p><ul style="box-sizing: border-box; margin: 15px 0px; padding-left: 20px; color: rgb(65, 66, 75); font-family: Lato, sans-serif; font-size: 14.95px; line-height: 23.92px;"><li style="box-sizing: border-box; list-style: disc;">Lidere los procesos de venta para empresas de tamaño pequeño y mediano.</li><li style="box-sizing: border-box; list-style: disc;">Participe en la generación de leads.</li><li style="box-sizing: border-box; list-style: disc;">Tenga manejo experto en nuestra interfaz con capacidad de configurar y administrar las cuentas de los clientes</li><li style="box-sizing: border-box; list-style: disc;">Participe en el &nbsp;diseño de las estrategias de venta</li></ul><p style="box-sizing: border-box; margin-top: 0px; margin-right: 0px; margin-left: 0px; color: rgb(65, 66, 75); font-family: Lato, sans-serif; font-size: 14.95px; line-height: 23.92px;"><span style="box-sizing: border-box; font-weight: 700;">Características</span><span style="box-sizing: border-box; font-weight: 700;">:</span></p><p style="box-sizing: border-box; margin-top: 0px; margin-right: 0px; margin-left: 0px; color: rgb(65, 66, 75); font-family: Lato, sans-serif; font-size: 14.95px; line-height: 23.92px;">Su perfil debe ser orientado a la venta, con capacidades comerciales y habilidades sociales, debe tener un buen manejo en sistemas computationales y en el lenguaje de venta relacionado al mundo de IT y debe ser una persona movida, con ideas frescas y ganas de aprender. Se valorará capacidad de auto-gestión y de trabajo bajo presión.</p><p style="box-sizing: border-box; margin-top: 0px; margin-right: 0px; margin-left: 0px; color: rgb(65, 66, 75); font-family: Lato, sans-serif; font-size: 14.95px; line-height: 23.92px;"><span style="box-sizing: border-box; font-weight: 700;">Será un plus si cuenta con:</span></p><ul style="box-sizing: border-box; margin: 15px 0px; padding-left: 20px; color: rgb(65, 66, 75); font-family: Lato, sans-serif; font-size: 14.95px; line-height: 23.92px;"><li style="box-sizing: border-box; list-style: disc;">Experiencia en venta de software.</li><li style="box-sizing: border-box; list-style: disc;">Conocimientos en el área de logística.</li></ul><p style="box-sizing: border-box; margin: 0px 0px 10px; color: rgb(65, 66, 75); font-family: Lato, sans-serif; font-size: 14.95px; line-height: 23.92px;">Trabajar con nosotros es ser parte de un emprendimiento tecnológico con contactos en Silicon Valley y con todo el ecosistema emprendedor chileno. Es una oportunidad de aprendizaje y crecimiento personal que pocas empresas en Chile pueden ofrecer</p>', false, NULL, NULL, true, false, true, 6, NULL, NULL, NULL, '2016-05-02', '2016-07-01', 'Comercial', false, NULL, 'Gestor Comercial', 6, 'Inglés deseable', NULL, NULL, NULL, 'Santiago', 5, false);


--
-- TOC entry 2072 (class 0 OID 41233)
-- Dependencies: 181
-- Data for Name: offer_career; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO offer_career VALUES (5, 4070);
INSERT INTO offer_career VALUES (5, 4060);
INSERT INTO offer_career VALUES (6, 4060);


--
-- TOC entry 2095 (class 0 OID 0)
-- Dependencies: 191
-- Name: offer_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('offer_id_seq', 6, true);


--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 190
-- Name: offer_organization_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('offer_organization_id_seq', 1, false);


--
-- TOC entry 2074 (class 0 OID 41249)
-- Dependencies: 183
-- Data for Name: offer_type; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO offer_type VALUES (1, 'Práctica                                          ', NULL);
INSERT INTO offer_type VALUES (2, 'Memoria de Título                                 ', NULL);
INSERT INTO offer_type VALUES (3, 'Full Time                                         ', NULL);
INSERT INTO offer_type VALUES (4, 'Part Time                                         ', NULL);
INSERT INTO offer_type VALUES (5, 'Freelance                                         ', NULL);


--
-- TOC entry 2097 (class 0 OID 0)
-- Dependencies: 182
-- Name: offer_type_id_offer_type_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('offer_type_id_offer_type_seq', 5, true);


--
-- TOC entry 2078 (class 0 OID 57772)
-- Dependencies: 187
-- Data for Name: organization; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO organization VALUES (1, 'Videojuegos', 'Informatica', '2016-04-24 15:33:49.349', false, 'Empresa dedicada al desarrollo de videojuegos', '2016-04-24 15:33:49.349', 'www.utalca.cl', 82456409);
INSERT INTO organization VALUES (4, 'Consultoría Informática', 'Tecnologías de Información', '2016-04-26 22:49:43.763', false, 'Empresa', '2016-04-26 22:49:43.763', 'www.kikosolutions.com', 123456789);


--
-- TOC entry 2069 (class 0 OID 32911)
-- Dependencies: 178
-- Data for Name: role; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO role VALUES (1, 'admin', '2016-01-11 23:49:42.938', '2016-01-11 23:49:42.938');
INSERT INTO role VALUES (2, 'organization', '2016-01-12 00:15:59.075', '2016-01-12 00:15:59.075');
INSERT INTO role VALUES (3, 'student', '2016-04-24 13:37:52.226', '2016-04-24 13:37:52.226');


--
-- TOC entry 2098 (class 0 OID 0)
-- Dependencies: 177
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('role_id_seq', 3, true);


--
-- TOC entry 2079 (class 0 OID 57782)
-- Dependencies: 188
-- Data for Name: role_user; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO role_user VALUES (2, 1);
INSERT INTO role_user VALUES (3, 3);
INSERT INTO role_user VALUES (2, 4);
INSERT INTO role_user VALUES (1, 7);


--
-- TOC entry 2080 (class 0 OID 57846)
-- Dependencies: 189
-- Data for Name: student; Type: TABLE DATA; Schema: pracutal; Owner: -
--



--
-- TOC entry 2062 (class 0 OID 24789)
-- Dependencies: 170
-- Data for Name: supervisor; Type: TABLE DATA; Schema: pracutal; Owner: -
--



--
-- TOC entry 2077 (class 0 OID 57748)
-- Dependencies: 186
-- Data for Name: user; Type: TABLE DATA; Schema: pracutal; Owner: -
--

INSERT INTO "user" VALUES (1, '2016-04-23 19:20:11.218', '2016-04-23 19:20:11.218', NULL, 'diego.riveros.pinilla@gmail.com', 'pass', NULL, NULL, NULL, NULL, NULL, 0, '2016-04-23 19:20:11.218', 'Diego', 'Riveros', 'Pinilla');
INSERT INTO "user" VALUES (3, '2016-04-25 23:57:56.237', '2016-04-25 23:57:56.237', NULL, 'bob@gmail.com', 'secret', NULL, NULL, NULL, NULL, NULL, 0, '2016-04-25 23:57:56.237', 'Bob', 'Smith', NULL);
INSERT INTO "user" VALUES (4, '2016-04-26 22:48:24.34', '2016-04-26 22:48:24.34', NULL, 'kiko@kikosolutions.com', 'kiko', NULL, NULL, NULL, NULL, NULL, 0, '2016-04-26 22:48:24.34', 'KikoSolutions', NULL, NULL);
INSERT INTO "user" VALUES (7, '2016-04-28 21:54:42.483', '2016-04-28 21:54:42.483', NULL, 'admin', 'admin', NULL, NULL, NULL, NULL, NULL, 0, '2016-04-28 21:54:42.483', 'Admin', NULL, NULL);


--
-- TOC entry 2099 (class 0 OID 0)
-- Dependencies: 185
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: pracutal; Owner: -
--

SELECT pg_catalog.setval('user_id_seq', 7, true);


--
-- TOC entry 2038 (class 2606 OID 32936)
-- Name: PK_CAREER; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY career
    ADD CONSTRAINT "PK_CAREER" PRIMARY KEY (career_id);


--
-- TOC entry 2044 (class 2606 OID 49481)
-- Name: address_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pk PRIMARY KEY (owner_id);


--
-- TOC entry 2040 (class 2606 OID 41237)
-- Name: offer_career_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY offer_career
    ADD CONSTRAINT offer_career_pk PRIMARY KEY (offer_id, career_id);


--
-- TOC entry 2054 (class 2606 OID 58135)
-- Name: offer_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY offer
    ADD CONSTRAINT offer_pk PRIMARY KEY (id);


--
-- TOC entry 2042 (class 2606 OID 41254)
-- Name: offer_type_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY offer_type
    ADD CONSTRAINT offer_type_pk PRIMARY KEY (id_offer_type);


--
-- TOC entry 2048 (class 2606 OID 57781)
-- Name: organization_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY organization
    ADD CONSTRAINT organization_pk PRIMARY KEY (id);


--
-- TOC entry 2022 (class 2606 OID 24788)
-- Name: pk_academic_director; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY academic_director
    ADD CONSTRAINT pk_academic_director PRIMARY KEY (rut);


--
-- TOC entry 2028 (class 2606 OID 24832)
-- Name: pk_application; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY application
    ADD CONSTRAINT pk_application PRIMARY KEY (id);


--
-- TOC entry 2030 (class 2606 OID 24837)
-- Name: pk_application_comment; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY application_comment
    ADD CONSTRAINT pk_application_comment PRIMARY KEY (id);


--
-- TOC entry 2034 (class 2606 OID 24850)
-- Name: pk_evaluation; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY evaluation
    ADD CONSTRAINT pk_evaluation PRIMARY KEY (id);


--
-- TOC entry 2026 (class 2606 OID 24811)
-- Name: pk_internship; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY internship
    ADD CONSTRAINT pk_internship PRIMARY KEY (id);


--
-- TOC entry 2032 (class 2606 OID 24842)
-- Name: pk_logbook; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY logbook
    ADD CONSTRAINT pk_logbook PRIMARY KEY (id);


--
-- TOC entry 2036 (class 2606 OID 32918)
-- Name: pk_role; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY role
    ADD CONSTRAINT pk_role PRIMARY KEY (id);


--
-- TOC entry 2024 (class 2606 OID 24793)
-- Name: pk_supervisor; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY supervisor
    ADD CONSTRAINT pk_supervisor PRIMARY KEY (rut);


--
-- TOC entry 2050 (class 2606 OID 57786)
-- Name: role_user_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY role_user
    ADD CONSTRAINT role_user_pk PRIMARY KEY (role_id, user_id);


--
-- TOC entry 2052 (class 2606 OID 57857)
-- Name: student_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY student
    ADD CONSTRAINT student_pk PRIMARY KEY (id);


--
-- TOC entry 2046 (class 2606 OID 57759)
-- Name: user_pk; Type: CONSTRAINT; Schema: pracutal; Owner: -; Tablespace: 
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- TOC entry 2057 (class 2606 OID 57787)
-- Name: address_user; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_user FOREIGN KEY (owner_id) REFERENCES "user"(id);


--
-- TOC entry 2055 (class 2606 OID 57934)
-- Name: offer_career_career; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY offer_career
    ADD CONSTRAINT offer_career_career FOREIGN KEY (career_id) REFERENCES career(career_id);


--
-- TOC entry 2056 (class 2606 OID 58136)
-- Name: offer_career_offer; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY offer_career
    ADD CONSTRAINT offer_career_offer FOREIGN KEY (offer_id) REFERENCES offer(id);


--
-- TOC entry 2058 (class 2606 OID 57797)
-- Name: organization_user; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY organization
    ADD CONSTRAINT organization_user FOREIGN KEY (id) REFERENCES "user"(id);


--
-- TOC entry 2059 (class 2606 OID 57792)
-- Name: role_user_role; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY role_user
    ADD CONSTRAINT role_user_role FOREIGN KEY (role_id) REFERENCES role(id);


--
-- TOC entry 2060 (class 2606 OID 57802)
-- Name: user_role_user; Type: FK CONSTRAINT; Schema: pracutal; Owner: -
--

ALTER TABLE ONLY role_user
    ADD CONSTRAINT user_role_user FOREIGN KEY (user_id) REFERENCES "user"(id);


-- Completed on 2016-04-29 01:04:28

--
-- PostgreSQL database dump complete
--

