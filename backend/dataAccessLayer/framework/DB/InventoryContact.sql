-- Table: parth_preeti.inventory_contact

-- DROP TABLE parth_preeti.inventory_contact;

CREATE TABLE parth_preeti.inventory_contact
(
    id integer NOT NULL DEFAULT nextval('parth_preeti.inventory_contact_id_seq'::regclass),
    inventory_id numeric NOT NULL,
    name text COLLATE pg_catalog."default",
    contact_number text COLLATE pg_catalog."default",
    email text COLLATE pg_catalog."default",
    created_on date NOT NULL,
    last_updated_on date NOT NULL,
    CONSTRAINT inventory_contact_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE parth_preeti.inventory_contact
    OWNER to postgres;