DROP TABLE personas IF EXISTS;

CREATE TABLE personas  (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20),
    surname VARCHAR(20),
    age int
);