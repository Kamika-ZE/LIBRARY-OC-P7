--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 12.2

-- Started on 2020-05-21 04:34:53

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

DROP DATABASE "library-ms-customer";
--
-- TOC entry 2830 (class 1262 OID 68294)
-- Name: library-ms-customer; Type: DATABASE; Schema: -; Owner: admin_library
--

CREATE DATABASE "library-ms-customer" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_France.1252' LC_CTYPE = 'French_France.1252';


ALTER DATABASE "library-ms-customer" OWNER TO admin_library;

\connect -reuse-previous=on "dbname='library-ms-customer'"

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

--
-- TOC entry 197 (class 1259 OID 68297)
-- Name: address; Type: TABLE; Schema: public; Owner: admin_library
--

CREATE TABLE public.address (
    address_id integer NOT NULL,
    housenumber character varying NOT NULL,
    street character varying NOT NULL,
    postcode character varying(5) NOT NULL,
    city character varying(50) NOT NULL
);


ALTER TABLE public.address OWNER TO admin_library;

--
-- TOC entry 196 (class 1259 OID 68295)
-- Name: address_address_id_seq; Type: SEQUENCE; Schema: public; Owner: admin_library
--

CREATE SEQUENCE public.address_address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.address_address_id_seq OWNER TO admin_library;

--
-- TOC entry 2831 (class 0 OID 0)
-- Dependencies: 196
-- Name: address_address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin_library
--

ALTER SEQUENCE public.address_address_id_seq OWNED BY public.address.address_id;


--
-- TOC entry 199 (class 1259 OID 68308)
-- Name: customer; Type: TABLE; Schema: public; Owner: admin_library
--

CREATE TABLE public.customer (
    customer_id integer NOT NULL,
    last_name character varying NOT NULL,
    first_name character varying NOT NULL,
    address_id integer NOT NULL,
    email character varying NOT NULL,
    phone_number character varying NOT NULL,
    registration_date timestamp without time zone NOT NULL,
    update_date timestamp without time zone,
    user_id integer NOT NULL
);


ALTER TABLE public.customer OWNER TO admin_library;

--
-- TOC entry 198 (class 1259 OID 68306)
-- Name: customer_customer_id_seq; Type: SEQUENCE; Schema: public; Owner: admin_library
--

CREATE SEQUENCE public.customer_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_customer_id_seq OWNER TO admin_library;

--
-- TOC entry 2832 (class 0 OID 0)
-- Dependencies: 198
-- Name: customer_customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin_library
--

ALTER SEQUENCE public.customer_customer_id_seq OWNED BY public.customer.customer_id;


--
-- TOC entry 2693 (class 2604 OID 68300)
-- Name: address address_id; Type: DEFAULT; Schema: public; Owner: admin_library
--

ALTER TABLE ONLY public.address ALTER COLUMN address_id SET DEFAULT nextval('public.address_address_id_seq'::regclass);


--
-- TOC entry 2694 (class 2604 OID 68311)
-- Name: customer customer_id; Type: DEFAULT; Schema: public; Owner: admin_library
--

ALTER TABLE ONLY public.customer ALTER COLUMN customer_id SET DEFAULT nextval('public.customer_customer_id_seq'::regclass);


--
-- TOC entry 2822 (class 0 OID 68297)
-- Dependencies: 197
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: admin_library
--

INSERT INTO public.address (address_id, housenumber, street, postcode, city) VALUES (1, '1', 'rue principal', '77777', 'Ocland');
INSERT INTO public.address (address_id, housenumber, street, postcode, city) VALUES (2, '3', 'rue des pots', '77777', 'Ocland');


--
-- TOC entry 2824 (class 0 OID 68308)
-- Dependencies: 199
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: admin_library
--

INSERT INTO public.customer (customer_id, last_name, first_name, address_id, email, phone_number, registration_date, update_date, user_id) VALUES (1, 'batch', 'bacth', 1, 'mc.ocform@gmail.com', '7777777777', '2020-05-15 02:37:35', '2020-05-15 02:37:39', 1);
INSERT INTO public.customer (customer_id, last_name, first_name, address_id, email, phone_number, registration_date, update_date, user_id) VALUES (2, 'Coz', 'Mickael', 2, 'coz.mickael@gmail.com', '0123456789', '2020-05-15 02:38:15', '2020-05-15 02:38:18', 2);


--
-- TOC entry 2833 (class 0 OID 0)
-- Dependencies: 196
-- Name: address_address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin_library
--

SELECT pg_catalog.setval('public.address_address_id_seq', 1, false);


--
-- TOC entry 2834 (class 0 OID 0)
-- Dependencies: 198
-- Name: customer_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin_library
--

SELECT pg_catalog.setval('public.customer_customer_id_seq', 1, false);


--
-- TOC entry 2696 (class 2606 OID 68305)
-- Name: address address_pk; Type: CONSTRAINT; Schema: public; Owner: admin_library
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pk PRIMARY KEY (address_id);


--
-- TOC entry 2698 (class 2606 OID 68316)
-- Name: customer customer_pk; Type: CONSTRAINT; Schema: public; Owner: admin_library
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pk PRIMARY KEY (customer_id);


--
-- TOC entry 2699 (class 2606 OID 68317)
-- Name: customer address_account_fk; Type: FK CONSTRAINT; Schema: public; Owner: admin_library
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT address_account_fk FOREIGN KEY (address_id) REFERENCES public.address(address_id);


-- Completed on 2020-05-21 04:34:53

--
-- PostgreSQL database dump complete
--

