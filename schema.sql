CREATE TABLE users (
	id UUID NOT NULL UNIQUE,
	email TEXT NOT NULL PRIMARY KEY UNIQUE,
	password TEXT NOT NULL,
	first_name TEXT,
	last_name TEXT,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP);
ALTER TABLE users OWNER TO venatus;

CREATE TABLE roles (
	id UUID NOT NULL UNIQUE,
	name TEXT PRIMARY KEY UNIQUE,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP);
ALTER TABLE roles OWNER TO venatus;

CREATE TABLE users_roles (
	user_id UUID NOT NULL REFERENCES users(id),
	role_id UUID NOT NULL REFERENCES roles(id),
	PRIMARY KEY (user_id, role_id));
ALTER TABLE users_roles OWNER TO venatus;