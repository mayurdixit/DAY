-- Table: parth_preeti.kendra_info

-- DROP TABLE parth_preeti.kendra_info;

CREATE TABLE parth_preeti.kendra_info
(
    id integer NOT NULL DEFAULT nextval('parth_preeti.kendra_info_id_seq'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    parent numeric,
    active boolean NOT NULL,
    created_on date NOT NULL,
    last_updated_on date NOT NULL,
    CONSTRAINT kendra_info_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE parth_preeti.kendra_info
    OWNER to postgres;