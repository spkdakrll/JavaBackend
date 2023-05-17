CREATE SEQUENCE IF NOT EXISTS link_id_seq
    START WITH 1
    INCREMENT BY 1
    CACHE 1
;

CREATE TABLE IF NOT EXISTS links(
    id BIGINT primary key default nextval('link_id_seq'),
    link TEXT UNIQUE NOT NULL,
    updated_date TIMESTAMP WITHOUT TIME ZONE default now() NOT NULL,
    user_check_date TIMESTAMP WITHOUT TIME ZONE         default now() NOT NULL,
    domain_id BIGINT REFERENCES domains(id) NOT NULL,
    data_changes JSONB NOT NULL
);