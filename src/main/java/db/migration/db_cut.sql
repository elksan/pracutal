CREATE TABLE comuna (
    "COMUNA_ID" integer DEFAULT 0 NOT NULL,
    "COMUNA_NOMBRE" character varying(20),
    "COMUNA_PROVINCIA_ID" integer
);


ALTER TABLE public.comuna OWNER TO postgres;

--
-- Name: provincia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE provincia (
    "PROVINCIA_ID" integer DEFAULT 0 NOT NULL,
    "PROVINCIA_NOMBRE" character varying(23),
    "PROVINCIA_REGION_ID" integer
);


ALTER TABLE public.provincia OWNER TO postgres;

--
-- Name: region; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE region (
    "REGION_ID" integer DEFAULT 0 NOT NULL,
    "REGION_NOMBRE" character varying(50),
    "ISO_3166_2_CL" character varying(5)
);


ALTER TABLE public.region OWNER TO postgres;

--
-- Data for Name: comuna; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY comuna ("COMUNA_ID", "COMUNA_NOMBRE", "COMUNA_PROVINCIA_ID") FROM stdin;
1101	Iquique	11
1107	Alto Hospicio	11
1401	Pozo Almonte	14
1402	Cami�a	14
1403	Colchane	14
1404	Huara	14
1405	Pica	14
2101	Antofagasta	21
2102	Mejillones	21
2103	Sierra Gorda	21
2104	Taltal	21
2201	Calama	22
2202	Ollag�e	22
2203	San Pedro de Atacama	22
2301	Tocopilla	23
2302	Mar�a Elena	23
3101	Copiap�	31
3102	Caldera	31
3103	Tierra Amarilla	31
3201	Cha�aral	32
3202	Diego de Almagro	32
3301	Vallenar	33
3302	Alto del Carmen	33
3303	Freirina	33
3304	Huasco	33
4101	La Serena	41
4102	Coquimbo	41
4103	Andacollo	41
4104	La Higuera	41
4105	Paihuano	41
4106	Vicu�a	41
4201	Illapel	42
4202	Canela	42
4203	Los Vilos	42
4204	Salamanca	42
4301	Ovalle	43
4302	Combarbal�	43
4303	Monte Patria	43
4304	Punitaqui	43
4305	R�o Hurtado	43
5101	Valpara�so	51
5102	Casablanca	51
5103	Conc�n	51
5104	Juan Fern�ndez	51
5105	Puchuncav�	51
5107	Quintero	51
5109	Vi�a del Mar	51
5201	Isla de Pascua	52
5301	Los Andes	53
5302	Calle Larga	53
5303	Rinconada	53
5304	San Esteban	53
5401	La Ligua	54
5402	Cabildo	54
5403	Papudo	54
5404	Petorca	54
5405	Zapallar	54
5501	Quillota	55
5502	La Calera	55
5503	Hijuelas	55
5504	La Cruz	55
5506	Nogales	55
5601	San Antonio	56
5602	Algarrobo	56
5603	Cartagena	56
5604	El Quisco	56
5605	El Tabo	56
5606	Santo Domingo	56
5701	San Felipe	57
5702	Catemu	57
5703	Llay Llay	57
5704	Panquehue	57
5705	Putaendo	57
5706	Santa Mar�a	57
5801	Quilpu�	58
5802	Limache	58
5803	Olmu�	58
5804	Villa Alemana	58
6101	Rancagua	61
6102	Codegua	61
6103	Coinco	61
6104	Coltauco	61
6105	Do�ihue	61
6106	Graneros	61
6107	Las Cabras	61
6108	Machal�	61
6109	Malloa	61
6110	Mostazal	61
6111	Olivar	61
6112	Peumo	61
6113	Pichidegua	61
6114	Quinta de Tilcoco	61
6115	Rengo	61
6116	Requ�noa	61
6117	San Vicente	61
6201	Pichilemu	62
6202	La Estrella	62
6203	Litueche	62
6204	Marchihue	62
6205	Navidad	62
6206	Paredones	62
6301	San Fernando	63
6302	Ch�pica	63
6303	Chimbarongo	63
6304	Lolol	63
6305	Nancagua	63
6306	Palmilla	63
6307	Peralillo	63
6308	Placilla	63
6309	Pumanque	63
6310	Santa Cruz	63
7101	Talca	71
7102	Constituci�n	71
7103	Curepto	71
7104	Empedrado	71
7105	Maule	71
7106	Pelarco	71
7107	Pencahue	71
7108	R�o Claro	71
7109	San Clemente	71
7110	San Rafael	71
7201	Cauquenes	72
7202	Chanco	72
7203	Pelluhue	72
7301	Curic�	73
7302	Huala��	73
7303	Licant�n	73
7304	Molina	73
7305	Rauco	73
7306	Romeral	73
7307	Sagrada Familia	73
7308	Teno	73
7309	Vichuqu�n	73
7401	Linares	74
7402	Colb�n	74
7403	Longav�	74
7404	Parral	74
7405	Retiro	74
7406	San Javier	74
7407	Villa Alegre	74
7408	Yerbas Buenas	74
8101	Concepci�n	81
8102	Coronel	81
8103	Chiguayante	81
8104	Florida	81
8105	Hualqui	81
8106	Lota	81
8107	Penco	81
8108	San Pedro de la Paz	81
8109	Santa Juana	81
8110	Talcahuano	81
8111	Tom�	81
8112	Hualp�n	81
8201	Lebu	82
8202	Arauco	82
8203	Ca�ete	82
8204	Contulmo	82
8205	Curanilahue	82
8206	Los �lamos	82
8207	Tir�a	82
8301	Los �ngeles	83
8302	Antuco	83
8303	Cabrero	83
8304	Laja	83
8305	Mulch�n	83
8306	Nacimiento	83
8307	Negrete	83
8308	Quilaco	83
8309	Quilleco	83
8310	San Rosendo	83
8311	Santa B�rbara	83
8312	Tucapel	83
8313	Yumbel	83
8314	Alto Biob�o	83
8401	Chill�n	84
8402	Bulnes	84
8403	Cobquecura	84
8404	Coelemu	84
8405	Coihueco	84
8406	Chill�n Viejo	84
8407	El Carmen	84
8408	Ninhue	84
8409	�iqu�n	84
8410	Pemuco	84
8411	Pinto	84
8412	Portezuelo	84
8413	Quill�n	84
8414	Quirihue	84
8415	R�nquil	84
8416	San Carlos	84
8417	San Fabi�n	84
8418	San Ignacio	84
8419	San Nicol�s	84
8420	Treguaco	84
8421	Yungay	84
9101	Temuco	91
9102	Carahue	91
9103	Cunco	91
9104	Curarrehue	91
9105	Freire	91
9106	Galvarino	91
9107	Gorbea	91
9108	Lautaro	91
9109	Loncoche	91
9110	Melipeuco	91
9111	Nueva Imperial	91
9112	Padre las Casas	91
9113	Perquenco	91
9114	Pitrufqu�n	91
9115	Puc�n	91
9116	Saavedra	91
9117	Teodoro Schmidt	91
9118	Tolt�n	91
9119	Vilc�n	91
9120	Villarrica	91
9121	Cholchol	91
9201	Angol	92
9202	Collipulli	92
9203	Curacaut�n	92
9204	Ercilla	92
9205	Lonquimay	92
9206	Los Sauces	92
9207	Lumaco	92
9208	Pur�n	92
9209	Renaico	92
9210	Traigu�n	92
9211	Victoria	92
10101	Puerto Montt	101
10102	Calbuco	101
10103	Cocham�	101
10104	Fresia	101
10105	Frutillar	101
10106	Los Muermos	101
10107	Llanquihue	101
10108	Maull�n	101
10109	Puerto Varas	101
10201	Castro	102
10202	Ancud	102
10203	Chonchi	102
10204	Curaco de V�lez	102
10205	Dalcahue	102
10206	Puqueld�n	102
10207	Queil�n	102
10208	Quell�n	102
10209	Quemchi	102
10210	Quinchao	102
10301	Osorno	103
10302	Puerto Octay	103
10303	Purranque	103
10304	Puyehue	103
10305	R�o Negro	103
10306	San Juan de la Costa	103
10307	San Pablo	103
10401	Chait�n	104
10402	Futaleuf�	104
10403	Hualaihu�	104
10404	Palena	104
11101	Coyhaique	111
11102	Lago Verde	111
11201	Ays�n	112
11202	Cisnes	112
11203	Guaitecas	112
11301	Cochrane	113
11302	O'Higgins	113
11303	Tortel	113
11401	Chile Chico	114
11402	R�o Ib��ez	114
12101	Punta Arenas	121
12102	Laguna Blanca	121
12103	R�o Verde	121
12104	San Gregorio	121
12201	Cabo de Hornos	122
12202	Ant�rtica	122
12301	Porvenir	123
12302	Primavera	123
12303	Timaukel	123
12401	Natales	124
12402	Torres del Paine	124
13101	Santiago	131
13102	Cerrillos	131
13103	Cerro Navia	131
13104	Conchal�	131
13105	El Bosque	131
13106	Estaci�n Central	131
13107	Huechuraba	131
13108	Independencia	131
13109	La Cisterna	131
13110	La Florida	131
13111	La Granja	131
13112	La Pintana	131
13113	La Reina	131
13114	Las Condes	131
13115	Lo Barnechea	131
13116	Lo Espejo	131
13117	Lo Prado	131
13118	Macul	131
13119	Maip�	131
13120	�u�oa	131
13121	Pedro Aguirre Cerda	131
13122	Pe�alol�n	131
13123	Providencia	131
13124	Pudahuel	131
13125	Quilicura	131
13126	Quinta Normal	131
13127	Recoleta	131
13128	Renca	131
13129	San Joaqu�n	131
13130	San Miguel	131
13131	San Ram�n	131
13132	Vitacura	131
13201	Puente Alto	132
13202	Pirque	132
13203	San Jos� de Maipo	132
13301	Colina	133
13302	Lampa	133
13303	Tiltil	133
13401	San Bernardo	134
13402	Buin	134
13403	Calera de Tango	134
13404	Paine	134
13501	Melipilla	135
13502	Alhu�	135
13503	Curacav�	135
13504	Mar�a Pinto	135
13505	San Pedro	135
13601	Talagante	136
13602	El Monte	136
13603	Isla de Maipo	136
13604	Padre Hurtado	136
13605	Pe�aflor	136
14101	Valdivia	141
14102	Corral	141
14103	Lanco	141
14104	Los Lagos	141
14105	M�fil	141
14106	Mariquina	141
14107	Paillaco	141
14108	Panguipulli	141
14201	La Uni�n	142
14202	Futrono	142
14203	Lago Ranco	142
14204	R�o Bueno	142
15101	Arica	151
15102	Camarones	151
15201	Putre	152
15202	General Lagos	152
\.
--
-- Data for Name: provincia; Type: TABLE DATA; Schema: public; Owner: postgres
--
COPY provincia ("PROVINCIA_ID", "PROVINCIA_NOMBRE", "PROVINCIA_REGION_ID") FROM stdin;
11	Iquique	1
14	Tamarugal	1
21	Antofagasta	2
22	El Loa	2
23	Tocopilla	2
31	Copiap�	3
32	Cha�aral	3
33	Huasco	3
41	Elqui	4
42	Choapa	4
43	Limar�	4
51	Valpara�so	5
52	Isla de Pascua	5
53	Los Andes	5
54	Petorca	5
55	Quillota	5
56	San Antonio	5
57	San Felipe de Aconcagua	5
58	Marga Marga	5
61	Cachapoal	6
62	Cardenal Caro	6
63	Colchagua	6
71	Talca	7
72	Cauquenes	7
73	Curic�	7
74	Linares	7
81	Concepci�n	8
82	Arauco	8
83	Biob�o	8
84	�uble	8
91	Caut�n	9
92	Malleco	9
101	Llanquihue	10
102	Chilo�	10
103	Osorno	10
104	Palena	10
111	Coihaique	11
112	Ais�n	11
113	Capit�n Prat	11
114	General Carrera	11
121	Magallanes	12
122	Ant�rtica Chilena	12
123	Tierra del Fuego	12
124	�ltima Esperanza	12
131	Santiago	13
132	Cordillera	13
133	Chacabuco	13
134	Maipo	13
135	Melipilla	13
136	Talagante	13
141	Valdivia	14
142	Ranco	14
151	Arica	15
152	Parinacota	15
\.
--
-- Data for Name: region; Type: TABLE DATA; Schema: public; Owner: postgres
--
COPY region ("REGION_ID", "REGION_NOMBRE", "ISO_3166_2_CL") FROM stdin;
1	Tarapac�	CL-TA
2	Antofagasta	CL-AN
3	Atacama	CL-AT
4	Coquimbo	CL-CO
5	Valpara�so	CL-VS
6	Regi�n del Libertador Gral. Bernardo O�Higgins	CL-LI
7	Regi�n del Maule	CL-ML
8	Regi�n del Biob�o	CL-BI
9	Regi�n de la Araucan�a	CL-AR
10	Regi�n de Los Lagos	CL-LL
11	Regi�n Ais�n del Gral. Carlos Ib��ez del Campo	CL-AI
12	Regi�n de Magallanes y de la Ant�rtica Chilena	CL-MA
13	Regi�n Metropolitana de Santiago	CL-RM
14	Regi�n de Los R�os	CL-LR
15	Arica y Parinacota	CL-AP
\.
--
-- Name: comuna_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--
ALTER TABLE ONLY comuna
    ADD CONSTRAINT comuna_pkey PRIMARY KEY ("COMUNA_ID");
--
-- Name: provincia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--
ALTER TABLE ONLY provincia
    ADD CONSTRAINT provincia_pkey PRIMARY KEY ("PROVINCIA_ID");
--
-- Name: region_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--
ALTER TABLE ONLY region
    ADD CONSTRAINT region_pkey PRIMARY KEY ("REGION_ID");
--
-- Name: COMUNA_PROVINCIA_ID; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--
CREATE INDEX "COMUNA_PROVINCIA_ID" ON comuna USING btree ("COMUNA_PROVINCIA_ID");
--
-- Name: PROVINCIA_REGION_ID; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--
CREATE INDEX "PROVINCIA_REGION_ID" ON provincia USING btree ("PROVINCIA_REGION_ID");
--
-- Name: comuna_COMUNA_PROVINCIA_ID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--
ALTER TABLE ONLY comuna
    ADD CONSTRAINT "comuna_COMUNA_PROVINCIA_ID_fkey" FOREIGN KEY ("COMUNA_PROVINCIA_ID") REFERENCES provincia("PROVINCIA_ID");
--
-- Name: provincia_PROVINCIA_REGION_ID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--
ALTER TABLE ONLY provincia
    ADD CONSTRAINT "provincia_PROVINCIA_REGION_ID_fkey" FOREIGN KEY ("PROVINCIA_REGION_ID") REFERENCES region("REGION_ID");
--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--
REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;