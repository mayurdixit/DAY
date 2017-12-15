-- Table: parth_preeti.access_role

-- DROP TABLE parth_preeti.access_role;

CREATE TABLE parth_preeti.access_role
(
    id integer NOT NULL DEFAULT nextval('parth_preeti.access_role_id_seq'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    enabled boolean NOT NULL,
    comment text COLLATE pg_catalog."default",
    created_on date NOT NULL,
    last_updated_on date NOT NULL,
    CONSTRAINT access_role_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE parth_preeti.access_role
    OWNER to postgres;