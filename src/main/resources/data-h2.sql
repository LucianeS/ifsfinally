-- USER
INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (1, '123@123.123', 'General User', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');
INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (2, 'admin@123.123', 'Admin', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');
INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (3, 'ana@123.123', 'Ana Correa', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');
INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (4, 'marco@123.123', 'Marco Santos', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');
INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (5, 'cintia@bol.com', 'Cintia Lages', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');
INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (6, 'lucas@123.123', 'Lucas Silva', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');
INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (7, 'carla@bol.com', 'Carla Souza', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');
INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (8, 'junior@net.com', 'Junior Vieira', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');


-- ROLES
insert into roles(id, role) values (1, 'ROLE_USER');
insert into roles(id, role) values (2, 'ROLE_ADMIN');

-- USER_ROLES
insert into users_roles (user_id, role_id) values (1, 1);
insert into users_roles (user_id, role_id) values (2, 2);
insert into users_roles (user_id, role_id) values (3, 1);
insert into users_roles (user_id, role_id) values (4, 1);
insert into users_roles (user_id, role_id) values (5, 1);
insert into users_roles (user_id, role_id) values (6, 1);
insert into users_roles (user_id, role_id) values (7, 1);
insert into users_roles (user_id, role_id) values (8, 1);

--DOACAO
insert into DOACAO (ID, CONTACT_EMAIL, DESCRIPTION, DISPONIVEL, LOCATION, PHOTO, POSTED_ON, TITLE, USER_ID ) values (1, 'ana@bol.com', 'Roupeiro com 3 anos de uso em MDF, que se interessar deve marcar uma data para buscar, pois não posso arcar com frete', true, 'Canoas/RS', null, sysdate, 'Roupeiro em ótimo estado de conservação', 3);
insert into DOACAO (ID, CONTACT_EMAIL, DESCRIPTION, DISPONIVEL, LOCATION, PHOTO, POSTED_ON, TITLE, USER_ID ) values (2, 'junior@gmail.com', 'Cama de solteiro em ferro, cor de rosa, bom estado! Mando entregar', true, 'POA/RS', null, sysdate, 'Dou cama de solteiro novinha!', 8);
insert into DOACAO (ID, CONTACT_EMAIL, DESCRIPTION, DISPONIVEL, LOCATION, PHOTO, POSTED_ON, TITLE, USER_ID ) values (3, 'lucas@bol.com', 'Bombacha tamanho 44, Camisa branca nº 3, Lenço de seda na cor vermelha e botas em couro, nº 44, cor preta', true, 'Caxias/RS', null, sysdate, 'Pilcha completa em ótimo estado', 6);
insert into DOACAO (ID, CONTACT_EMAIL, DESCRIPTION, DISPONIVEL, LOCATION, PHOTO, POSTED_ON, TITLE, USER_ID ) values (4, 'marco@outlook.com', 'Mesa de madeira com tampo de vidro, Cadeiras com estofado, o tecido encontra-se levemente desbotado, mas tem como trocar e fica novinho! - Frete por conta do novo dono!', true, 'Santa Catarina/RS', null, sysdate, 'Mesa com 4 cadeiras', 4);