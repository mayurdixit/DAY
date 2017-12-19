-- Table: parth_preeti.applications

-- DROP TABLE parth_preeti.applications;

CREATE TABLE parth_preeti.applications
(
    id integer NOT NULL DEFAULT nextval('parth_preeti."Applications_id_seq"'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    comment text COLLATE pg_catalog."default",
    created_on date NOT NULL,
    last_updated_on date NOT NULL,
    app_url text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Applications_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE parth_preeti.applications
    OWNER to postgres;