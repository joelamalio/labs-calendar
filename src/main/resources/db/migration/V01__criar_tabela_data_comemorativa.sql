CREATE TABLE data_comemorativa (
    id BIGINT PRIMARY KEY,
    data DATE NOT NULL,
    descricao TEXT NOT NULL,
    status BOOLEAN NOT NULL
);

CREATE SEQUENCE data_comemorativa_seq START 101;