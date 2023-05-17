ALTER TABLE links RENAME COLUMN updated_date TO scheduler_updated_date;
ALTER TABLE links ADD COLUMN page_updated_date TIMESTAMP WITHOUT TIME ZONE default now() NOT NULL;
