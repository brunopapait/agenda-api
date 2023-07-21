CREATE TABLE agenda
(
    id          serial PRIMARY KEY,
    descricao   varchar(15),
    dh_horario  timestamp,
    dh_criacao  timestamp,
    paciente_id integer,
    CONSTRAINT fk_agente_paciente FOREIGN KEY (paciente_id) REFERENCES paciente (id)
);