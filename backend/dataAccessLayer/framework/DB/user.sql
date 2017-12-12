-- Table: parth_preeti."user"

-- DROP TABLE parth_preeti."user";

CREATE TABLE parth_preeti."user"
(
    first_name text COLLATE pg_catalog."default" NOT NULL,
    email text COLLATE pg_catalog."default",
    id integer NOT NULL DEFAULT nextval('parth_preeti.user_id_seq'::regclass),
    last_name text COLLATE pg_catalog."default",
    contact_number text COLLATE pg_catalog."default",
    created_on date,
    last_accessed_on date,
    enabled boolean DEFAULT true,
    user_name text COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default",
    default_password text COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = TRUE
)
TABLESPACE pg_default;

ALTER TABLE parth_preeti."user"
    OWNER to postgres;