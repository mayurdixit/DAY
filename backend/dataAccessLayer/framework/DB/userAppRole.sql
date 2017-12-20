-- Table: parth_preeti.user_app_role

-- DROP TABLE parth_preeti.user_app_role;

CREATE TABLE parth_preeti.user_app_role
(
    id integer NOT NULL DEFAULT nextval('parth_preeti.user_app_role_id_seq'::regclass),
    user_id numeric NOT NULL,
    app_id numeric,
    role_id numeric,
    created_on date NOT NULL,
    last_updated_on date NOT NULL,
    kendra_id numeric,
    CONSTRAINT user_app_role_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE parth_preeti.user_app_role
    OWNER to postgres;