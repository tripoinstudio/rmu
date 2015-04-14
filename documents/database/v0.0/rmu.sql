--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2015-04-14 13:22:17

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE rmu;
--
-- TOC entry 1998 (class 1262 OID 57656)
-- Name: rmu; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE rmu WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE rmu OWNER TO postgres;

\connect rmu

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1999 (class 1262 OID 57656)
-- Dependencies: 1998
-- Name: rmu; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE rmu IS 'Reska Multi Usaha';


--
-- TOC entry 7 (class 2615 OID 57657)
-- Name: master; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA master;


ALTER SCHEMA master OWNER TO postgres;

--
-- TOC entry 2000 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA master; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA master IS 'Master Data';


--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2001 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 8 (class 2615 OID 57680)
-- Name: security; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA security;


ALTER SCHEMA security OWNER TO postgres;

--
-- TOC entry 2003 (class 0 OID 0)
-- Dependencies: 8
-- Name: SCHEMA security; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA security IS 'security schema';


--
-- TOC entry 9 (class 2615 OID 57681)
-- Name: trx; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA trx;


ALTER SCHEMA trx OWNER TO postgres;

--
-- TOC entry 2004 (class 0 OID 0)
-- Dependencies: 9
-- Name: SCHEMA trx; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA trx IS 'Transaction schema';


--
-- TOC entry 184 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2005 (class 0 OID 0)
-- Dependencies: 184
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = master, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 174 (class 1259 OID 57660)
-- Name: master_carriage; Type: TABLE; Schema: master; Owner: postgres; Tablespace: 
--

CREATE TABLE master_carriage (
    carriage_id bigint NOT NULL,
    carriage_code character varying(50),
    carriage_no character varying(2),
    carriage_status smallint,
    carriage_remarks character varying(255)
);


ALTER TABLE master.master_carriage OWNER TO postgres;

--
-- TOC entry 2006 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN master_carriage.carriage_id; Type: COMMENT; Schema: master; Owner: postgres
--

COMMENT ON COLUMN master_carriage.carriage_id IS 'PK';


--
-- TOC entry 2007 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN master_carriage.carriage_code; Type: COMMENT; Schema: master; Owner: postgres
--

COMMENT ON COLUMN master_carriage.carriage_code IS 'Kode gerbong';


--
-- TOC entry 2008 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN master_carriage.carriage_no; Type: COMMENT; Schema: master; Owner: postgres
--

COMMENT ON COLUMN master_carriage.carriage_no IS 'Nomor gerbong';


--
-- TOC entry 2009 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN master_carriage.carriage_status; Type: COMMENT; Schema: master; Owner: postgres
--

COMMENT ON COLUMN master_carriage.carriage_status IS 'status data, 1=''Active'', 2=''Disable''';


--
-- TOC entry 173 (class 1259 OID 57658)
-- Name: master_carriage_carriage_id_seq; Type: SEQUENCE; Schema: master; Owner: postgres
--

CREATE SEQUENCE master_carriage_carriage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE master.master_carriage_carriage_id_seq OWNER TO postgres;

--
-- TOC entry 2010 (class 0 OID 0)
-- Dependencies: 173
-- Name: master_carriage_carriage_id_seq; Type: SEQUENCE OWNED BY; Schema: master; Owner: postgres
--

ALTER SEQUENCE master_carriage_carriage_id_seq OWNED BY master_carriage.carriage_id;


--
-- TOC entry 178 (class 1259 OID 57676)
-- Name: master_menu; Type: TABLE; Schema: master; Owner: postgres; Tablespace: 
--

CREATE TABLE master_menu (
    menu_id bigint NOT NULL,
    menu_code character varying(50),
    menu_name character varying(100),
    menu_type smallint,
    menu_price numeric(10,2),
    menu_status smallint,
    menu_remarks character varying(255)
);


ALTER TABLE master.master_menu OWNER TO postgres;

--
-- TOC entry 2011 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN master_menu.menu_id; Type: COMMENT; Schema: master; Owner: postgres
--

COMMENT ON COLUMN master_menu.menu_id IS 'PK';


--
-- TOC entry 2012 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN master_menu.menu_code; Type: COMMENT; Schema: master; Owner: postgres
--

COMMENT ON COLUMN master_menu.menu_code IS 'kode menu';


--
-- TOC entry 2013 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN master_menu.menu_name; Type: COMMENT; Schema: master; Owner: postgres
--

COMMENT ON COLUMN master_menu.menu_name IS 'Nama menu';


--
-- TOC entry 2014 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN master_menu.menu_type; Type: COMMENT; Schema: master; Owner: postgres
--

COMMENT ON COLUMN master_menu.menu_type IS '1=''Makanan'',2=''Minuman''';


--
-- TOC entry 2015 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN master_menu.menu_price; Type: COMMENT; Schema: master; Owner: postgres
--

COMMENT ON COLUMN master_menu.menu_price IS 'Harga menu';


--
-- TOC entry 2016 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN master_menu.menu_status; Type: COMMENT; Schema: master; Owner: postgres
--

COMMENT ON COLUMN master_menu.menu_status IS 'status data, 1=''Active'', 2=''Disable''';


--
-- TOC entry 177 (class 1259 OID 57674)
-- Name: master_menu_menu_id_seq; Type: SEQUENCE; Schema: master; Owner: postgres
--

CREATE SEQUENCE master_menu_menu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE master.master_menu_menu_id_seq OWNER TO postgres;

--
-- TOC entry 2017 (class 0 OID 0)
-- Dependencies: 177
-- Name: master_menu_menu_id_seq; Type: SEQUENCE OWNED BY; Schema: master; Owner: postgres
--

ALTER SEQUENCE master_menu_menu_id_seq OWNED BY master_menu.menu_id;


--
-- TOC entry 176 (class 1259 OID 57668)
-- Name: master_seat; Type: TABLE; Schema: master; Owner: postgres; Tablespace: 
--

CREATE TABLE master_seat (
    seat_id bigint NOT NULL,
    seat_code character varying(50),
    seat_no character varying(3),
    seat_status smallint,
    remarks character varying(255)
);


ALTER TABLE master.master_seat OWNER TO postgres;

--
-- TOC entry 2018 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN master_seat.seat_id; Type: COMMENT; Schema: master; Owner: postgres
--

COMMENT ON COLUMN master_seat.seat_id IS 'PK';


--
-- TOC entry 2019 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN master_seat.seat_code; Type: COMMENT; Schema: master; Owner: postgres
--

COMMENT ON COLUMN master_seat.seat_code IS 'Kode tempat duduk';


--
-- TOC entry 2020 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN master_seat.seat_no; Type: COMMENT; Schema: master; Owner: postgres
--

COMMENT ON COLUMN master_seat.seat_no IS 'Nomor tempat duduk';


--
-- TOC entry 2021 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN master_seat.seat_status; Type: COMMENT; Schema: master; Owner: postgres
--

COMMENT ON COLUMN master_seat.seat_status IS 'status data, 1=''Active'', 2=''Disable''';


--
-- TOC entry 175 (class 1259 OID 57666)
-- Name: master_seat_seat_id_seq; Type: SEQUENCE; Schema: master; Owner: postgres
--

CREATE SEQUENCE master_seat_seat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE master.master_seat_seat_id_seq OWNER TO postgres;

--
-- TOC entry 2022 (class 0 OID 0)
-- Dependencies: 175
-- Name: master_seat_seat_id_seq; Type: SEQUENCE OWNED BY; Schema: master; Owner: postgres
--

ALTER SEQUENCE master_seat_seat_id_seq OWNED BY master_seat.seat_id;


SET search_path = security, pg_catalog;

--
-- TOC entry 181 (class 1259 OID 57691)
-- Name: security_user; Type: TABLE; Schema: security; Owner: postgres; Tablespace: 
--

CREATE TABLE security_user (
    user_id bigint NOT NULL,
    user_code character varying(20),
    user_password character varying(20),
    user_status smallint,
    user_remarks character varying(255)
);


ALTER TABLE security.security_user OWNER TO postgres;

--
-- TOC entry 2023 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN security_user.user_id; Type: COMMENT; Schema: security; Owner: postgres
--

COMMENT ON COLUMN security_user.user_id IS 'PK';


--
-- TOC entry 2024 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN security_user.user_code; Type: COMMENT; Schema: security; Owner: postgres
--

COMMENT ON COLUMN security_user.user_code IS 'Kode user';


--
-- TOC entry 2025 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN security_user.user_password; Type: COMMENT; Schema: security; Owner: postgres
--

COMMENT ON COLUMN security_user.user_password IS 'Password';


--
-- TOC entry 2026 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN security_user.user_status; Type: COMMENT; Schema: security; Owner: postgres
--

COMMENT ON COLUMN security_user.user_status IS 'status data, 1=''Active'', 2=''Disable''';


--
-- TOC entry 180 (class 1259 OID 57689)
-- Name: security_user_user_id_seq; Type: SEQUENCE; Schema: security; Owner: postgres
--

CREATE SEQUENCE security_user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.security_user_user_id_seq OWNER TO postgres;

--
-- TOC entry 2027 (class 0 OID 0)
-- Dependencies: 180
-- Name: security_user_user_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE security_user_user_id_seq OWNED BY security_user.user_id;


SET search_path = trx, pg_catalog;

--
-- TOC entry 183 (class 1259 OID 57714)
-- Name: trx_order_detail; Type: TABLE; Schema: trx; Owner: postgres; Tablespace: 
--

CREATE TABLE trx_order_detail (
    order_detail_id bigint NOT NULL,
    order_detail_menu_id bigint,
    order_detail_order_amount smallint,
    order_detail_total_amount numeric(10,2),
    order_detail_order_no character varying(100)
);


ALTER TABLE trx.trx_order_detail OWNER TO postgres;

--
-- TOC entry 2028 (class 0 OID 0)
-- Dependencies: 183
-- Name: TABLE trx_order_detail; Type: COMMENT; Schema: trx; Owner: postgres
--

COMMENT ON TABLE trx_order_detail IS 'Transaksi Detail Order';


--
-- TOC entry 2029 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN trx_order_detail.order_detail_id; Type: COMMENT; Schema: trx; Owner: postgres
--

COMMENT ON COLUMN trx_order_detail.order_detail_id IS 'Order Detail ID';


--
-- TOC entry 2030 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN trx_order_detail.order_detail_menu_id; Type: COMMENT; Schema: trx; Owner: postgres
--

COMMENT ON COLUMN trx_order_detail.order_detail_menu_id IS 'FK menu ID';


--
-- TOC entry 2031 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN trx_order_detail.order_detail_order_amount; Type: COMMENT; Schema: trx; Owner: postgres
--

COMMENT ON COLUMN trx_order_detail.order_detail_order_amount IS 'Jumlah menu uang dipesan';


--
-- TOC entry 2032 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN trx_order_detail.order_detail_total_amount; Type: COMMENT; Schema: trx; Owner: postgres
--

COMMENT ON COLUMN trx_order_detail.order_detail_total_amount IS 'harga satuan x jumlah menu yg dipesan';


--
-- TOC entry 2033 (class 0 OID 0)
-- Dependencies: 183
-- Name: COLUMN trx_order_detail.order_detail_order_no; Type: COMMENT; Schema: trx; Owner: postgres
--

COMMENT ON COLUMN trx_order_detail.order_detail_order_no IS 'FK order ID';


--
-- TOC entry 182 (class 1259 OID 57712)
-- Name: trx_order_detail_order_detail_id_seq; Type: SEQUENCE; Schema: trx; Owner: postgres
--

CREATE SEQUENCE trx_order_detail_order_detail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trx.trx_order_detail_order_detail_id_seq OWNER TO postgres;

--
-- TOC entry 2034 (class 0 OID 0)
-- Dependencies: 182
-- Name: trx_order_detail_order_detail_id_seq; Type: SEQUENCE OWNED BY; Schema: trx; Owner: postgres
--

ALTER SEQUENCE trx_order_detail_order_detail_id_seq OWNED BY trx_order_detail.order_detail_id;


--
-- TOC entry 179 (class 1259 OID 57682)
-- Name: trx_order_header; Type: TABLE; Schema: trx; Owner: postgres; Tablespace: 
--

CREATE TABLE trx_order_header (
    order_header_no character varying(100) NOT NULL,
    user_id bigint,
    carriage_id bigint,
    seat_id bigint,
    order_order_date timestamp without time zone,
    order_total_paid numeric(20,2),
    order_status smallint,
    order_remarks character varying(255)
);


ALTER TABLE trx.trx_order_header OWNER TO postgres;

--
-- TOC entry 2035 (class 0 OID 0)
-- Dependencies: 179
-- Name: TABLE trx_order_header; Type: COMMENT; Schema: trx; Owner: postgres
--

COMMENT ON TABLE trx_order_header IS 'Transaction Order Header';


--
-- TOC entry 2036 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN trx_order_header.order_header_no; Type: COMMENT; Schema: trx; Owner: postgres
--

COMMENT ON COLUMN trx_order_header.order_header_no IS 'PK, auto generate number with format = ''ODR-[randorm number]'', digenerate sewaktu user klik add order';


--
-- TOC entry 2037 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN trx_order_header.user_id; Type: COMMENT; Schema: trx; Owner: postgres
--

COMMENT ON COLUMN trx_order_header.user_id IS 'FK user ID';


--
-- TOC entry 2038 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN trx_order_header.carriage_id; Type: COMMENT; Schema: trx; Owner: postgres
--

COMMENT ON COLUMN trx_order_header.carriage_id IS 'FK carriage ID';


--
-- TOC entry 2039 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN trx_order_header.seat_id; Type: COMMENT; Schema: trx; Owner: postgres
--

COMMENT ON COLUMN trx_order_header.seat_id IS 'FK seat ID';


--
-- TOC entry 2040 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN trx_order_header.order_order_date; Type: COMMENT; Schema: trx; Owner: postgres
--

COMMENT ON COLUMN trx_order_header.order_order_date IS 'tanggal order';


--
-- TOC entry 2041 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN trx_order_header.order_total_paid; Type: COMMENT; Schema: trx; Owner: postgres
--

COMMENT ON COLUMN trx_order_header.order_total_paid IS 'jumlah yg dibayar';


--
-- TOC entry 2042 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN trx_order_header.order_status; Type: COMMENT; Schema: trx; Owner: postgres
--

COMMENT ON COLUMN trx_order_header.order_status IS 'status pesanan,1=''NEW'',2=''PROCESS'',3=''PREPARED'',4=''DONE'',5=''CANCEL''';


SET search_path = master, pg_catalog;

--
-- TOC entry 1854 (class 2604 OID 57663)
-- Name: carriage_id; Type: DEFAULT; Schema: master; Owner: postgres
--

ALTER TABLE ONLY master_carriage ALTER COLUMN carriage_id SET DEFAULT nextval('master_carriage_carriage_id_seq'::regclass);


--
-- TOC entry 1856 (class 2604 OID 57679)
-- Name: menu_id; Type: DEFAULT; Schema: master; Owner: postgres
--

ALTER TABLE ONLY master_menu ALTER COLUMN menu_id SET DEFAULT nextval('master_menu_menu_id_seq'::regclass);


--
-- TOC entry 1855 (class 2604 OID 57671)
-- Name: seat_id; Type: DEFAULT; Schema: master; Owner: postgres
--

ALTER TABLE ONLY master_seat ALTER COLUMN seat_id SET DEFAULT nextval('master_seat_seat_id_seq'::regclass);


SET search_path = security, pg_catalog;

--
-- TOC entry 1857 (class 2604 OID 57694)
-- Name: user_id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY security_user ALTER COLUMN user_id SET DEFAULT nextval('security_user_user_id_seq'::regclass);


SET search_path = trx, pg_catalog;

--
-- TOC entry 1858 (class 2604 OID 57717)
-- Name: order_detail_id; Type: DEFAULT; Schema: trx; Owner: postgres
--

ALTER TABLE ONLY trx_order_detail ALTER COLUMN order_detail_id SET DEFAULT nextval('trx_order_detail_order_detail_id_seq'::regclass);


SET search_path = master, pg_catalog;

--
-- TOC entry 1984 (class 0 OID 57660)
-- Dependencies: 174
-- Data for Name: master_carriage; Type: TABLE DATA; Schema: master; Owner: postgres
--



--
-- TOC entry 2043 (class 0 OID 0)
-- Dependencies: 173
-- Name: master_carriage_carriage_id_seq; Type: SEQUENCE SET; Schema: master; Owner: postgres
--

SELECT pg_catalog.setval('master_carriage_carriage_id_seq', 1, false);


--
-- TOC entry 1988 (class 0 OID 57676)
-- Dependencies: 178
-- Data for Name: master_menu; Type: TABLE DATA; Schema: master; Owner: postgres
--



--
-- TOC entry 2044 (class 0 OID 0)
-- Dependencies: 177
-- Name: master_menu_menu_id_seq; Type: SEQUENCE SET; Schema: master; Owner: postgres
--

SELECT pg_catalog.setval('master_menu_menu_id_seq', 1, false);


--
-- TOC entry 1986 (class 0 OID 57668)
-- Dependencies: 176
-- Data for Name: master_seat; Type: TABLE DATA; Schema: master; Owner: postgres
--



--
-- TOC entry 2045 (class 0 OID 0)
-- Dependencies: 175
-- Name: master_seat_seat_id_seq; Type: SEQUENCE SET; Schema: master; Owner: postgres
--

SELECT pg_catalog.setval('master_seat_seat_id_seq', 1, false);


SET search_path = security, pg_catalog;

--
-- TOC entry 1991 (class 0 OID 57691)
-- Dependencies: 181
-- Data for Name: security_user; Type: TABLE DATA; Schema: security; Owner: postgres
--



--
-- TOC entry 2046 (class 0 OID 0)
-- Dependencies: 180
-- Name: security_user_user_id_seq; Type: SEQUENCE SET; Schema: security; Owner: postgres
--

SELECT pg_catalog.setval('security_user_user_id_seq', 1, false);


SET search_path = trx, pg_catalog;

--
-- TOC entry 1993 (class 0 OID 57714)
-- Dependencies: 183
-- Data for Name: trx_order_detail; Type: TABLE DATA; Schema: trx; Owner: postgres
--



--
-- TOC entry 2047 (class 0 OID 0)
-- Dependencies: 182
-- Name: trx_order_detail_order_detail_id_seq; Type: SEQUENCE SET; Schema: trx; Owner: postgres
--

SELECT pg_catalog.setval('trx_order_detail_order_detail_id_seq', 1, false);


--
-- TOC entry 1989 (class 0 OID 57682)
-- Dependencies: 179
-- Data for Name: trx_order_header; Type: TABLE DATA; Schema: trx; Owner: postgres
--



SET search_path = master, pg_catalog;

--
-- TOC entry 1860 (class 2606 OID 57665)
-- Name: master_carriage_pkey; Type: CONSTRAINT; Schema: master; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY master_carriage
    ADD CONSTRAINT master_carriage_pkey PRIMARY KEY (carriage_id);


--
-- TOC entry 1864 (class 2606 OID 57688)
-- Name: master_menu_pkey; Type: CONSTRAINT; Schema: master; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY master_menu
    ADD CONSTRAINT master_menu_pkey PRIMARY KEY (menu_id);


--
-- TOC entry 1862 (class 2606 OID 57673)
-- Name: master_seat_pkey; Type: CONSTRAINT; Schema: master; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY master_seat
    ADD CONSTRAINT master_seat_pkey PRIMARY KEY (seat_id);


SET search_path = security, pg_catalog;

--
-- TOC entry 1868 (class 2606 OID 57696)
-- Name: security_user_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY security_user
    ADD CONSTRAINT security_user_pkey PRIMARY KEY (user_id);


SET search_path = trx, pg_catalog;

--
-- TOC entry 1870 (class 2606 OID 57723)
-- Name: trx_order_detail_pkey; Type: CONSTRAINT; Schema: trx; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY trx_order_detail
    ADD CONSTRAINT trx_order_detail_pkey PRIMARY KEY (order_detail_id);


--
-- TOC entry 1866 (class 2606 OID 57686)
-- Name: trx_order_header_pkey; Type: CONSTRAINT; Schema: trx; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY trx_order_header
    ADD CONSTRAINT trx_order_header_pkey PRIMARY KEY (order_header_no);


--
-- TOC entry 1872 (class 2606 OID 57702)
-- Name: fk_carriage_id; Type: FK CONSTRAINT; Schema: trx; Owner: postgres
--

ALTER TABLE ONLY trx_order_header
    ADD CONSTRAINT fk_carriage_id FOREIGN KEY (carriage_id) REFERENCES master.master_carriage(carriage_id);


--
-- TOC entry 1875 (class 2606 OID 57729)
-- Name: fk_menu_id; Type: FK CONSTRAINT; Schema: trx; Owner: postgres
--

ALTER TABLE ONLY trx_order_detail
    ADD CONSTRAINT fk_menu_id FOREIGN KEY (order_detail_menu_id) REFERENCES master.master_menu(menu_id);


--
-- TOC entry 1874 (class 2606 OID 57724)
-- Name: fk_order_no; Type: FK CONSTRAINT; Schema: trx; Owner: postgres
--

ALTER TABLE ONLY trx_order_detail
    ADD CONSTRAINT fk_order_no FOREIGN KEY (order_detail_order_no) REFERENCES trx_order_header(order_header_no);


--
-- TOC entry 1873 (class 2606 OID 57707)
-- Name: fk_seat_id; Type: FK CONSTRAINT; Schema: trx; Owner: postgres
--

ALTER TABLE ONLY trx_order_header
    ADD CONSTRAINT fk_seat_id FOREIGN KEY (seat_id) REFERENCES master.master_seat(seat_id);


--
-- TOC entry 1871 (class 2606 OID 57697)
-- Name: fk_user_id; Type: FK CONSTRAINT; Schema: trx; Owner: postgres
--

ALTER TABLE ONLY trx_order_header
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES security.security_user(user_id);


--
-- TOC entry 2002 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-04-14 13:22:17

--
-- PostgreSQL database dump complete
--

