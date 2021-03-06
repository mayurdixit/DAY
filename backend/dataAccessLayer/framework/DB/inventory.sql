-- Table: parth_preeti.inventory

-- DROP TABLE parth_preeti.inventory;

CREATE TABLE parth_preeti.inventory
(
    id integer NOT NULL DEFAULT nextval('parth_preeti.inventory_id_seq'::regclass),
    equipment_type numeric NOT NULL,
    serial_model_number text COLLATE pg_catalog."default",
    in_use boolean NOT NULL,
    purchased_on date,
    used_since date,
    stored_at text COLLATE pg_catalog."default",
    comment text COLLATE pg_catalog."default",
    created_on date NOT NULL,
    last_updated_on date NOT NULL,
    updated_by numeric NOT NULL,
    kendra_id numeric NOT NULL,
    contact1_name text COLLATE pg_catalog."default",
    contact1_phone text COLLATE pg_catalog."default",
    contact1_email text COLLATE pg_catalog."default",
    contact2_name text COLLATE pg_catalog."default",
    contact2_phone text COLLATE pg_catalog."default",
    contact2_email text COLLATE pg_catalog."default",
    owned_by text COLLATE pg_catalog."default",
    CONSTRAINT inventory_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE parth_preeti.inventory
    OWNER to postgres;