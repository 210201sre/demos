DROP TABLE IF EXISTS public.users CASCADE;
-- Completely remove the users table, regardless of any rlationships
-- Without the CASCADE keyword, this DROP statement would fail
-- if there were any relationships that needed to be upheld
-- Such as Foreign Keys

-- CASCADE operations are VERY dangerous and couble be very large scale
-- DO NOT do this on ANY database that you are concerned about maintaining data on

CREATE TABLE public.users (
	-- We list our columns along with their types
	-- Along with any constraints
	-- column_name DATATYPE CONSTRAINTS
	id SERIAL PRIMARY KEY,
	-- the SERIAL datatype is a type in postgres specifically
	-- that provides auto-incrementing for our primary keys
	-- This means that when inserting a new record, we can ignore
	-- inserting a value for the primary key, and it will be
	-- generated for us
	-- Aside from that, it is effectively just an INTEGER
	first_name VARCHAR (2000) NOT NULL CHECK (LENGTH(first_name) > 0),
	last_name VARCHAR (2000) NOT NULL CHECK (LENGTH(last_name) > 0),
	email VARCHAR (250) UNIQUE,
	age INTEGER NOT NULL DEFAULT 0 CHECK (age >= 0),
	supervisor INTEGER
);

ALTER TABLE public.users
	ADD CONSTRAINT users_supervisor_fk
	FOREIGN KEY (supervisor) REFERENCES users (id);
-- ALTER TABLE schema.table
--	ADD CONSTRAINT constraint_name
--  CONSTRAINT_TYPE (column) [REFERENCES table (column)]

/*
This is a multi-line
comment
*/

DROP TABLE IF EXISTS public.phonenumbers CASCADE;

CREATE TABLE public.phonenumbers (
	id SERIAL PRIMARY KEY,
	user_id INTEGER NOT NULL REFERENCES public.users (id),
	home VARCHAR (20),
	mobile VARCHAR (20),
	work VARCHAR (20)
);

DROP SCHEMA IF EXISTS project0 CASCADE;

CREATE SCHEMA project0;

DROP TABLE IF EXISTS public.accounts CASCADE;

CREATE TABLE accounts (
	id SERIAL PRIMARY KEY,
	owner_id INTEGER NOT NULL REFERENCES users (id),
	balance NUMERIC (40, 2) NOT NULL DEFAULT 0
);

INSERT INTO public.users (first_name, last_name) VALUES ('Matthew', 'Oberlies');

SELECT first_name, last_name FROM users;

SELECT * FROM users;

-- You can construct custom columns from the columns within the tables
-- The || is performing string concatenation
-- Can use the AS keyword as an alias
SELECT first_name || ' ' || last_name AS full_name FROM users;




