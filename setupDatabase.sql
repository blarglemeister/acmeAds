CREATE DATABASE ACME_ADS;

CREATE TABLE ad(
    id SERIAL PRIMARY KEY,
    title text,
    body text
);

CREATE TABLE newspaper(
    id SERIAL PRIMARY KEY,
    name text
);

CREATE TABLE paper_ad_links(
    paper_id integer REFERENCES newspaper (id) ON DELETE CASCADE,
    ad_id integer REFERENCES ad (id) ON DELETE CASCADE,
    CONSTRAINT ad_link_id PRIMARY KEY (paper_id, ad_id)
);