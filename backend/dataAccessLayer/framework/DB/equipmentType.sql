-- Table: parth_preeti.equipment_type

-- DROP TABLE parth_preeti.equipment_type;

CREATE TABLE parth_preeti.equipment_type
(
    id integer NOT NULL DEFAULT nextval('parth_preeti.equipment_type_id_seq'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    created_on date NOT NULL,
    last_updated_on date NOT NULL,
    CONSTRAINT equipment_type_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE parth_preeti.equipment_type
    OWNER to postgres;