CREATE TABLE usuario (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    login VARCHAR(50) NOT NULL,
    senha VARCHAR(120) NOT NULL,
    status boolean NOT NULL
);

CREATE SEQUENCE usuario_seq START 1;

CREATE TABLE perfil (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE autorizacao (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE usuario_perfil (
    id_usuario BIGINT NOT NULL,
    id_perfil BIGINT NOT NULL,
    PRIMARY KEY (id_usuario, id_perfil),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_perfil) REFERENCES perfil(id)
);

CREATE TABLE perfil_autorizacao (
    id_perfil BIGINT NOT NULL,
    id_autorizacao BIGINT NOT NULL,
    PRIMARY KEY (id_perfil, id_autorizacao),
    FOREIGN KEY (id_perfil) REFERENCES perfil(id),
    FOREIGN KEY (id_autorizacao) REFERENCES autorizacao(id)
);
