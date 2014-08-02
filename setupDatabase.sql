

CREATE TABLE newspaper(
    id SERIAL PRIMARY KEY,
    name text
);

CREATE TABLE ad(
    id SERIAL PRIMARY KEY,
    newspaper_id integer REFERENCES newspaper (id) ON DELETE CASCADE,
    title text,
    body text
);
