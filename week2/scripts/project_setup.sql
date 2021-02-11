DROP TYPE IF EXISTS project0.role CASCADE;
CREATE TYPE project0.role AS ENUM ('Admin', 'Employee', 'Customer');

DROP TABLE IF EXISTS project0.users CASCADE;
CREATE TABLE project0.users (
	id SERIAL PRIMARY KEY,
	username VARCHAR (250) UNIQUE NOT NULL,
	password VARCHAR (250) NOT NULL,
	role project0.ROLE NOT NULL
);

DROP TABLE IF EXISTS project0.accounts CASCADE;
CREATE TABLE project0.accounts (
	id SERIAL PRIMARY KEY,
	balance NUMERIC (50, 2) NOT NULL CHECK (balance >= 0) DEFAULT 0
--	owner INTEGER NOT NULL REFERENCES project0.users (id)
--	active BOOLEAN DEFAULT false
);

DROP TABLE IF EXISTS project0.users_accounts_jt;
CREATE TABLE project0.users_accounts_jt (
	owner INTEGER NOT NULL REFERENCES project0.users (id),
	account INTEGER NOT NULL REFERENCES project0.accounts (id)
);

DROP TABLE IF EXISTS project0.applications CASCADE;
CREATE TABLE project0.applications (
	id SERIAL PRIMARY KEY,
	owner INTEGER NOT NULL REFERENCES project0.users (id)
);

INSERT INTO project0.users (username, password, role) VALUES ('moberlies', 'password', 'Admin');