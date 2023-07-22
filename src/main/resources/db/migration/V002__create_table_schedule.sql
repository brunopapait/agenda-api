CREATE TABLE agenda
(
    id          serial PRIMARY KEY,
    description varchar(15),
    dh_time     timestamp,
    dh_creation timestamp,
    patient_id  integer,
    CONSTRAINT fk_schedule_patient FOREIGN KEY (patient_id) REFERENCES patient (id)
);