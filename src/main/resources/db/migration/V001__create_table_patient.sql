CREATE TABLE patient
(
    id      serial PRIMARY KEY,
    cpf     varchar(15) NOT NULL UNIQUE,
    email   varchar(100) NOT NULL UNIQUE,
    name    varchar(50),
    surname varchar(100)
);