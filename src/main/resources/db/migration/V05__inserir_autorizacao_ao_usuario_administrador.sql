INSERT INTO autorizacao VALUES (1, 'ROLE_MENU_CADASTROS');
INSERT INTO autorizacao VALUES (2, 'ROLE_MENU_PERIODO');
INSERT INTO autorizacao VALUES (3, 'ROLE_MANTER_PERIODO');
INSERT INTO autorizacao VALUES (4, 'ROLE_MENU_DASHBOARD');
INSERT INTO autorizacao VALUES (5, 'ROLE_LISTAR_DASHBOARD');
INSERT INTO autorizacao VALUES (6, 'ROLE_MENU_CONFIGURACOES');
INSERT INTO autorizacao VALUES (7, 'ROLE_MENU_USUARIO');
INSERT INTO autorizacao VALUES (8, 'ROLE_MANTER_USUARIO');

INSERT INTO perfil_autorizacao (id_perfil, id_autorizacao) VALUES (1, 1);
INSERT INTO perfil_autorizacao (id_perfil, id_autorizacao) VALUES (1, 2);
INSERT INTO perfil_autorizacao (id_perfil, id_autorizacao) VALUES (1, 3);
INSERT INTO perfil_autorizacao (id_perfil, id_autorizacao) VALUES (1, 4);
INSERT INTO perfil_autorizacao (id_perfil, id_autorizacao) VALUES (1, 5);
INSERT INTO perfil_autorizacao (id_perfil, id_autorizacao) VALUES (1, 6);
INSERT INTO perfil_autorizacao (id_perfil, id_autorizacao) VALUES (1, 7);
INSERT INTO perfil_autorizacao (id_perfil, id_autorizacao) VALUES (1, 8);

INSERT INTO perfil_autorizacao (id_perfil, id_autorizacao) VALUES (2, 1);
INSERT INTO perfil_autorizacao (id_perfil, id_autorizacao) VALUES (2, 2);
INSERT INTO perfil_autorizacao (id_perfil, id_autorizacao) VALUES (2, 3);
INSERT INTO perfil_autorizacao (id_perfil, id_autorizacao) VALUES (2, 4);
INSERT INTO perfil_autorizacao (id_perfil, id_autorizacao) VALUES (2, 5);

INSERT INTO usuario_perfil (id_usuario, id_perfil) VALUES (1, 1);
INSERT INTO usuario_perfil (id_usuario, id_perfil) VALUES (2, 2);

