CREATE TABLE paciente
(
    id        serial PRIMARY KEY,
    cpf       varchar(15),
    email     varchar(100),
    nome      varchar(50),
    sobrenome varchar(100)
);