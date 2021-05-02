--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

-- Started on 2021-05-01 14:04:53

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 16514)
-- Name: alergia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alergia (
    idalergia bigint,
    descripcion character varying NOT NULL,
    idpaciente bigint NOT NULL
);


ALTER TABLE public.alergia OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16485)
-- Name: consulta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.consulta (
    idconsulta bigint NOT NULL,
    fechaconsulta date NOT NULL,
    idpaciente bigint,
    nombre character varying,
    apellidos character varying,
    razon character varying
);


ALTER TABLE public.consulta OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16483)
-- Name: consulta_idconsulta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.consulta_idconsulta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.consulta_idconsulta_seq OWNER TO postgres;

--
-- TOC entry 3116 (class 0 OID 0)
-- Dependencies: 216
-- Name: consulta_idconsulta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.consulta_idconsulta_seq OWNED BY public.consulta.idconsulta;


--
-- TOC entry 207 (class 1259 OID 16430)
-- Name: diente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.diente (
    iddiente bigint NOT NULL,
    nombrediente character varying NOT NULL,
    idenfermedad bigint,
    idtratamiento bigint,
    idodontograma bigint NOT NULL
);


ALTER TABLE public.diente OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16428)
-- Name: diente_iddiente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.diente_iddiente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.diente_iddiente_seq OWNER TO postgres;

--
-- TOC entry 3117 (class 0 OID 0)
-- Dependencies: 206
-- Name: diente_iddiente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.diente_iddiente_seq OWNED BY public.diente.iddiente;


--
-- TOC entry 209 (class 1259 OID 16441)
-- Name: enfermedad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.enfermedad (
    idenfermedad bigint NOT NULL,
    nombreenfermedad character varying NOT NULL,
    descripcion character varying
);


ALTER TABLE public.enfermedad OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16439)
-- Name: enfermedad_idenfermedad_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.enfermedad_idenfermedad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.enfermedad_idenfermedad_seq OWNER TO postgres;

--
-- TOC entry 3118 (class 0 OID 0)
-- Dependencies: 208
-- Name: enfermedad_idenfermedad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.enfermedad_idenfermedad_seq OWNED BY public.enfermedad.idenfermedad;


--
-- TOC entry 203 (class 1259 OID 16411)
-- Name: historiaodontologica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.historiaodontologica (
    idhistoriaodnotologica bigint NOT NULL,
    idpaciente bigint NOT NULL
);


ALTER TABLE public.historiaodontologica OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16409)
-- Name: historiaodontologica_idhistoriaodnotologica_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.historiaodontologica_idhistoriaodnotologica_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.historiaodontologica_idhistoriaodnotologica_seq OWNER TO postgres;

--
-- TOC entry 3119 (class 0 OID 0)
-- Dependencies: 202
-- Name: historiaodontologica_idhistoriaodnotologica_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.historiaodontologica_idhistoriaodnotologica_seq OWNED BY public.historiaodontologica.idhistoriaodnotologica;


--
-- TOC entry 205 (class 1259 OID 16419)
-- Name: odontograma; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.odontograma (
    idodontograma bigint NOT NULL,
    idpaciente bigint NOT NULL,
    nombrepaciente character varying,
    idhistorialodontologico bigint NOT NULL
);


ALTER TABLE public.odontograma OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16417)
-- Name: odontograma_idodontograma_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.odontograma_idodontograma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.odontograma_idodontograma_seq OWNER TO postgres;

--
-- TOC entry 3120 (class 0 OID 0)
-- Dependencies: 204
-- Name: odontograma_idodontograma_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.odontograma_idodontograma_seq OWNED BY public.odontograma.idodontograma;


--
-- TOC entry 201 (class 1259 OID 16400)
-- Name: paciente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paciente (
    nombre character varying(50) NOT NULL,
    apellidos character varying(50) NOT NULL,
    telefono character varying(20),
    escolaridad character varying,
    id bigint NOT NULL,
    edad bigint NOT NULL
);


ALTER TABLE public.paciente OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16398)
-- Name: paciente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.paciente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.paciente_id_seq OWNER TO postgres;

--
-- TOC entry 3121 (class 0 OID 0)
-- Dependencies: 200
-- Name: paciente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.paciente_id_seq OWNED BY public.paciente.id;


--
-- TOC entry 211 (class 1259 OID 16452)
-- Name: padecimiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.padecimiento (
    idpadecimiento bigint NOT NULL,
    idenfermedad bigint,
    nombrepadecimiento character varying,
    descripcion character varying,
    idpaciente bigint NOT NULL,
    idconsulta bigint
);


ALTER TABLE public.padecimiento OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16450)
-- Name: padecimiento_idpadecimiento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.padecimiento_idpadecimiento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.padecimiento_idpadecimiento_seq OWNER TO postgres;

--
-- TOC entry 3122 (class 0 OID 0)
-- Dependencies: 210
-- Name: padecimiento_idpadecimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.padecimiento_idpadecimiento_seq OWNED BY public.padecimiento.idpadecimiento;


--
-- TOC entry 219 (class 1259 OID 16493)
-- Name: pago; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pago (
    idpago bigint NOT NULL,
    fechadepago date NOT NULL,
    saldopendiente numeric,
    saldototal numeric,
    idpaciente bigint
);


ALTER TABLE public.pago OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16491)
-- Name: pago_idpago_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pago_idpago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pago_idpago_seq OWNER TO postgres;

--
-- TOC entry 3123 (class 0 OID 0)
-- Dependencies: 218
-- Name: pago_idpago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pago_idpago_seq OWNED BY public.pago.idpago;


--
-- TOC entry 221 (class 1259 OID 16504)
-- Name: pagoprocedimiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pagoprocedimiento (
    idprocedimientopago bigint NOT NULL,
    idpadecimiento bigint,
    descripcion character varying,
    costo numeric,
    idpago bigint,
    idpaciente bigint,
    fechadepago date NOT NULL,
    cancelado boolean NOT NULL
);


ALTER TABLE public.pagoprocedimiento OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16502)
-- Name: pagoprocedimiento_idprocedimientopago_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pagoprocedimiento_idprocedimientopago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pagoprocedimiento_idprocedimientopago_seq OWNER TO postgres;

--
-- TOC entry 3124 (class 0 OID 0)
-- Dependencies: 220
-- Name: pagoprocedimiento_idprocedimientopago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pagoprocedimiento_idprocedimientopago_seq OWNED BY public.pagoprocedimiento.idprocedimientopago;


--
-- TOC entry 215 (class 1259 OID 16474)
-- Name: procedimiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.procedimiento (
    idprocedimiento bigint NOT NULL,
    idtratamiento bigint,
    nombreprocedimiento character varying,
    costo numeric,
    idpaciente bigint,
    idconsulta bigint,
    idpago bigint
);


ALTER TABLE public.procedimiento OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16472)
-- Name: procedimiento_idprocedimiento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.procedimiento_idprocedimiento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.procedimiento_idprocedimiento_seq OWNER TO postgres;

--
-- TOC entry 3125 (class 0 OID 0)
-- Dependencies: 214
-- Name: procedimiento_idprocedimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.procedimiento_idprocedimiento_seq OWNED BY public.procedimiento.idprocedimiento;


--
-- TOC entry 213 (class 1259 OID 16463)
-- Name: tratamiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tratamiento (
    idtratamiento bigint NOT NULL,
    nombretratamiento character varying NOT NULL,
    costotratamiento numeric NOT NULL
);


ALTER TABLE public.tratamiento OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16461)
-- Name: tratamiento_idtratamiento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tratamiento_idtratamiento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tratamiento_idtratamiento_seq OWNER TO postgres;

--
-- TOC entry 3126 (class 0 OID 0)
-- Dependencies: 212
-- Name: tratamiento_idtratamiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tratamiento_idtratamiento_seq OWNED BY public.tratamiento.idtratamiento;


--
-- TOC entry 2933 (class 2604 OID 16488)
-- Name: consulta idconsulta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consulta ALTER COLUMN idconsulta SET DEFAULT nextval('public.consulta_idconsulta_seq'::regclass);


--
-- TOC entry 2928 (class 2604 OID 16433)
-- Name: diente iddiente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.diente ALTER COLUMN iddiente SET DEFAULT nextval('public.diente_iddiente_seq'::regclass);


--
-- TOC entry 2929 (class 2604 OID 16444)
-- Name: enfermedad idenfermedad; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enfermedad ALTER COLUMN idenfermedad SET DEFAULT nextval('public.enfermedad_idenfermedad_seq'::regclass);


--
-- TOC entry 2926 (class 2604 OID 16414)
-- Name: historiaodontologica idhistoriaodnotologica; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historiaodontologica ALTER COLUMN idhistoriaodnotologica SET DEFAULT nextval('public.historiaodontologica_idhistoriaodnotologica_seq'::regclass);


--
-- TOC entry 2927 (class 2604 OID 16422)
-- Name: odontograma idodontograma; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.odontograma ALTER COLUMN idodontograma SET DEFAULT nextval('public.odontograma_idodontograma_seq'::regclass);


--
-- TOC entry 2925 (class 2604 OID 16403)
-- Name: paciente id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paciente ALTER COLUMN id SET DEFAULT nextval('public.paciente_id_seq'::regclass);


--
-- TOC entry 2930 (class 2604 OID 16455)
-- Name: padecimiento idpadecimiento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.padecimiento ALTER COLUMN idpadecimiento SET DEFAULT nextval('public.padecimiento_idpadecimiento_seq'::regclass);


--
-- TOC entry 2934 (class 2604 OID 16496)
-- Name: pago idpago; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pago ALTER COLUMN idpago SET DEFAULT nextval('public.pago_idpago_seq'::regclass);


--
-- TOC entry 2935 (class 2604 OID 16507)
-- Name: pagoprocedimiento idprocedimientopago; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pagoprocedimiento ALTER COLUMN idprocedimientopago SET DEFAULT nextval('public.pagoprocedimiento_idprocedimientopago_seq'::regclass);


--
-- TOC entry 2932 (class 2604 OID 16477)
-- Name: procedimiento idprocedimiento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.procedimiento ALTER COLUMN idprocedimiento SET DEFAULT nextval('public.procedimiento_idprocedimiento_seq'::regclass);


--
-- TOC entry 2931 (class 2604 OID 16466)
-- Name: tratamiento idtratamiento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tratamiento ALTER COLUMN idtratamiento SET DEFAULT nextval('public.tratamiento_idtratamiento_seq'::regclass);


--
-- TOC entry 3110 (class 0 OID 16514)
-- Dependencies: 222
-- Data for Name: alergia; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alergia (idalergia, descripcion, idpaciente) FROM stdin;
0	123213	4
0	123213	4
0	41	5
0	212	2
\.


--
-- TOC entry 3105 (class 0 OID 16485)
-- Dependencies: 217
-- Data for Name: consulta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.consulta (idconsulta, fechaconsulta, idpaciente, nombre, apellidos, razon) FROM stdin;
\.


--
-- TOC entry 3095 (class 0 OID 16430)
-- Dependencies: 207
-- Data for Name: diente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.diente (iddiente, nombrediente, idenfermedad, idtratamiento, idodontograma) FROM stdin;
\.


--
-- TOC entry 3097 (class 0 OID 16441)
-- Dependencies: 209
-- Data for Name: enfermedad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.enfermedad (idenfermedad, nombreenfermedad, descripcion) FROM stdin;
1	CARIE	 ya no sirve
2	EJEMPLO	 ES UN EJEMPLO
\.


--
-- TOC entry 3091 (class 0 OID 16411)
-- Dependencies: 203
-- Data for Name: historiaodontologica; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.historiaodontologica (idhistoriaodnotologica, idpaciente) FROM stdin;
1	1
2	1
3	2
\.


--
-- TOC entry 3093 (class 0 OID 16419)
-- Dependencies: 205
-- Data for Name: odontograma; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.odontograma (idodontograma, idpaciente, nombrepaciente, idhistorialodontologico) FROM stdin;
\.


--
-- TOC entry 3089 (class 0 OID 16400)
-- Dependencies: 201
-- Data for Name: paciente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.paciente (nombre, apellidos, telefono, escolaridad, id, edad) FROM stdin;
PAOLO GIOVANNI	VELIZ SULECIO	54700020	5to Bachiller	1	20
1	2	2	002	2	1
\.


--
-- TOC entry 3099 (class 0 OID 16452)
-- Dependencies: 211
-- Data for Name: padecimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.padecimiento (idpadecimiento, idenfermedad, nombrepadecimiento, descripcion, idpaciente, idconsulta) FROM stdin;
1	12	test	este es un test	1	0
3	4	OTRO TEST	OTRO TEST	1	0
2	2	NO SE	NO SE	1	0
4	4	NO SE	NO SE PERO ES PARA EL USUARIO 2	2	0
6	1	CARIE	CARIE	2	0
5	1	CARIE	ENFERMEDAD DEL DIENTE	2	0
\.


--
-- TOC entry 3107 (class 0 OID 16493)
-- Dependencies: 219
-- Data for Name: pago; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pago (idpago, fechadepago, saldopendiente, saldototal, idpaciente) FROM stdin;
\.


--
-- TOC entry 3109 (class 0 OID 16504)
-- Dependencies: 221
-- Data for Name: pagoprocedimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pagoprocedimiento (idprocedimientopago, idpadecimiento, descripcion, costo, idpago, idpaciente, fechadepago, cancelado) FROM stdin;
\.


--
-- TOC entry 3103 (class 0 OID 16474)
-- Dependencies: 215
-- Data for Name: procedimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.procedimiento (idprocedimiento, idtratamiento, nombreprocedimiento, costo, idpaciente, idconsulta, idpago) FROM stdin;
\.


--
-- TOC entry 3101 (class 0 OID 16463)
-- Dependencies: 213
-- Data for Name: tratamiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tratamiento (idtratamiento, nombretratamiento, costotratamiento) FROM stdin;
1	RELLENO	10000000
2	SUELDO DE AMILCAR	100000000000
4	EJEMPLO	213456
\.


--
-- TOC entry 3127 (class 0 OID 0)
-- Dependencies: 216
-- Name: consulta_idconsulta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.consulta_idconsulta_seq', 1, false);


--
-- TOC entry 3128 (class 0 OID 0)
-- Dependencies: 206
-- Name: diente_iddiente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.diente_iddiente_seq', 1, false);


--
-- TOC entry 3129 (class 0 OID 0)
-- Dependencies: 208
-- Name: enfermedad_idenfermedad_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.enfermedad_idenfermedad_seq', 1, false);


--
-- TOC entry 3130 (class 0 OID 0)
-- Dependencies: 202
-- Name: historiaodontologica_idhistoriaodnotologica_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.historiaodontologica_idhistoriaodnotologica_seq', 1, false);


--
-- TOC entry 3131 (class 0 OID 0)
-- Dependencies: 204
-- Name: odontograma_idodontograma_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.odontograma_idodontograma_seq', 1, false);


--
-- TOC entry 3132 (class 0 OID 0)
-- Dependencies: 200
-- Name: paciente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.paciente_id_seq', 1, false);


--
-- TOC entry 3133 (class 0 OID 0)
-- Dependencies: 210
-- Name: padecimiento_idpadecimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.padecimiento_idpadecimiento_seq', 1, false);


--
-- TOC entry 3134 (class 0 OID 0)
-- Dependencies: 218
-- Name: pago_idpago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pago_idpago_seq', 1, false);


--
-- TOC entry 3135 (class 0 OID 0)
-- Dependencies: 220
-- Name: pagoprocedimiento_idprocedimientopago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pagoprocedimiento_idprocedimientopago_seq', 1, false);


--
-- TOC entry 3136 (class 0 OID 0)
-- Dependencies: 214
-- Name: procedimiento_idprocedimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.procedimiento_idprocedimiento_seq', 1, false);


--
-- TOC entry 3137 (class 0 OID 0)
-- Dependencies: 212
-- Name: tratamiento_idtratamiento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tratamiento_idtratamiento_seq', 1, false);


--
-- TOC entry 2953 (class 2606 OID 16490)
-- Name: consulta consulta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT consulta_pkey PRIMARY KEY (idconsulta);


--
-- TOC entry 2943 (class 2606 OID 16438)
-- Name: diente diente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.diente
    ADD CONSTRAINT diente_pkey PRIMARY KEY (iddiente);


--
-- TOC entry 2945 (class 2606 OID 16449)
-- Name: enfermedad enfermedad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enfermedad
    ADD CONSTRAINT enfermedad_pkey PRIMARY KEY (idenfermedad);


--
-- TOC entry 2939 (class 2606 OID 16416)
-- Name: historiaodontologica historiaodontologica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.historiaodontologica
    ADD CONSTRAINT historiaodontologica_pkey PRIMARY KEY (idhistoriaodnotologica);


--
-- TOC entry 2941 (class 2606 OID 16427)
-- Name: odontograma odontograma_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.odontograma
    ADD CONSTRAINT odontograma_pkey PRIMARY KEY (idodontograma);


--
-- TOC entry 2937 (class 2606 OID 16408)
-- Name: paciente paciente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (id);


--
-- TOC entry 2947 (class 2606 OID 16460)
-- Name: padecimiento padecimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.padecimiento
    ADD CONSTRAINT padecimiento_pkey PRIMARY KEY (idpadecimiento);


--
-- TOC entry 2955 (class 2606 OID 16501)
-- Name: pago pago_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pago
    ADD CONSTRAINT pago_pkey PRIMARY KEY (idpago);


--
-- TOC entry 2957 (class 2606 OID 16512)
-- Name: pagoprocedimiento pagoprocedimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pagoprocedimiento
    ADD CONSTRAINT pagoprocedimiento_pkey PRIMARY KEY (idprocedimientopago);


--
-- TOC entry 2951 (class 2606 OID 16482)
-- Name: procedimiento procedimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.procedimiento
    ADD CONSTRAINT procedimiento_pkey PRIMARY KEY (idprocedimiento);


--
-- TOC entry 2949 (class 2606 OID 16471)
-- Name: tratamiento tratamiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tratamiento
    ADD CONSTRAINT tratamiento_pkey PRIMARY KEY (idtratamiento);


-- Completed on 2021-05-01 14:04:57

--
-- PostgreSQL database dump complete
--

