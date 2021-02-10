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

INSERT INTO public.accounts (owner_id) VALUES (1);

INSERT INTO public.users (first_name, last_name) VALUES ('Jared', 'Malkin');

INSERT INTO public.accounts (owner_id, balance) VALUES (2, 3);

INSERT INTO public.users (first_name, last_name) VALUES ('Fatima', 'Melgar');

SELECT * FROM public.users INNER JOIN public.accounts
	ON public.users.id = public.accounts.owner_id;

SELECT first_name || ' ' || last_name AS full_name, balance FROM public.users INNER JOIN public.accounts
	ON public.users.id = public.accounts.owner_id;

SELECT public.accounts.id AS account_id, public.users.id AS user_id, balance FROM public.users INNER JOIN public.accounts
	ON public.users.id = public.accounts.owner_id;

-- You can nest SELECT statements by creating sub-queries
-- This has a lot of useful use-cases
-- However, you must be careful about ambiguity
SELECT sub1.user_id AS user_id, sub1.balance FROM
	(SELECT public.accounts.id AS account_id, public.users.id AS user_id, balance FROM public.users INNER JOIN public.accounts
		ON public.users.id = public.accounts.owner_id) AS sub1;

SELECT * FROM (
	SELECT public.accounts.id AS account_id, public.users.id AS user_id, balance FROM public.users INNER JOIN public.accounts
			ON public.users.id = public.accounts.owner_id) AS sub1
	WHERE sub1.balance > 0;

-- WHERE
-- ORDER BY
-- GROUP BY
-- HAVING
-- LIMIT
-- HAVING and WHERE are almost the exact same clause
-- The difference is that WHERE applies the filter BEFORE data is grouped
-- And HAVING applies the filter AFTER the data is grouped

DROP TABLE IF EXISTS one CASCADE;
DROP TABLE IF EXISTS two CASCADE;

CREATE TABLE one (
	one INTEGER PRIMARY KEY,
	two INTEGER
);

CREATE TABLE two (
	one INTEGER PRIMARY KEY,
	two INTEGER
);

INSERT INTO one VALUES (1, 1), (2, 2);
INSERT INTO two VALUES (1, 1), (2, 1);

-- All SET operations only operate on results that have
-- the same number and type of columns

-- The UNION operator will combine all results together
-- However, it will not include duplicates
SELECT * FROM public.one UNION SELECT * FROM public.two;

-- UNION ALL does include duplicates
SELECT * FROM public.one UNION ALL SELECT * FROM public.two;

-- INTERSECT will only include matching results
SELECT * FROM public.one INTERSECT SELECT * FROM public.two;

-- EXCEPT will keep results from the left view, and remove any matching
-- results that came from the right
SELECT * FROM public.one EXCEPT SELECT * FROM public.two;

-- SQL supports scalar and aggregate functions that can be used
-- along with SELECT statements

-- Scalar functions are functions that operate on only a single input
-- and produce 1 output for each input
-- e.g UPPER, LOWER, TRIM, SIN, COS, TAN

-- Aggregate functions are functions that operate on an entire column as input
-- and produce 1 output for all inputs
-- e.g SUM, AVG, etc

SELECT SIN(one) FROM one;

SELECT SUM(one) FROM two;

SELECT AVG(LENGTH(first_name)) FROM users;

SELECT AVG(LENGTH(last_name)) FROM users;

INSERT INTO public.users (first_name, last_name, age) VALUES ('Rubeus', 'Hagrid', 33);

SELECT age, AVG(LENGTH(first_name)) AS first_name_length_avg FROM users GROUP BY age;

-- The below query does not work, since we must either group by id OR use id in an aggregate function
SELECT id, age, AVG(LENGTH(last_name)) AS last_name_length_avg FROM users GROUP BY age;