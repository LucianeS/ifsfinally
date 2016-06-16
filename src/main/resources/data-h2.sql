-- USER
INSERT INTO USER(ID, EMAIL, FULL_NAME, PASSWORD) VALUES (1, '123@123.123', 'General User', '$2a$10$cy42kPdxwRGcrx02umaL3O7f7xUKXQtugjXLrM9/DNay.D.WNK3HC');

-- ROLES
insert into roles(id, role) values (1, 'ROLE_USER');

-- USER_ROLES
insert into users_roles (user_id, role_id) values (1, 1);
