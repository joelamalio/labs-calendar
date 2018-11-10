CREATE TABLE periodo (
    id BIGINT PRIMARY KEY,
    data_inicial DATE NOT NULL,
    data_final DATE NULL,
    descricao TEXT NOT NULL,
    status BOOLEAN NOT NULL
);

CREATE SEQUENCE periodo_seq START 1;