INSERT INTO project0.accounts (owner, balance) VALUES (1, RANDOM() * 1000000);


INSERT INTO project0.accounts (owner, balance) (
	SELECT owner, balance FROM project0.accounts
);

CREATE USER postgres WITH PASSWORD 'postgres';

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA project0 TO postgres;

DROP ROLE IF EXISTS ADMIN;

CREATE ROLE ADMIN WITH
	CREATEDB
	CREATEROLE
	LOGIN
	INHERIT;
	
GRANT ADMIN TO postgres;

CREATE SCHEMA myschema;