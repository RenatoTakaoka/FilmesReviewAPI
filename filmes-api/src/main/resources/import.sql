-- Inserir gêneros
INSERT INTO tb_genero (nome) VALUES ('Ação');
INSERT INTO tb_genero (nome) VALUES ('Comédia');
INSERT INTO tb_genero (nome) VALUES ('Drama');
INSERT INTO tb_genero (nome) VALUES ('Terror');

-- Inserir filmes
INSERT INTO tb_filme (titulo, ano, genero_id) VALUES ('Vingadores: Ultimato', 2019, 1);
INSERT INTO tb_filme (titulo, ano, genero_id) VALUES ('A Grande Aposta', 2015, 3);
INSERT INTO tb_filme (titulo, ano, genero_id) VALUES ('Os Caça-Fantasmas', 1984, 2);
INSERT INTO tb_filme (titulo, ano, genero_id) VALUES ('O Exorcista', 1973, 4);

-- Inserir usuários
INSERT INTO tb_user (name, email, password) VALUES ('Alice', 'alice@example.com', 'senha123');
INSERT INTO tb_user (name, email, password) VALUES ('Bob', 'bob@example.com', 'senha123');
INSERT INTO tb_user (name, email, password) VALUES ('Charlie', 'charlie@example.com', 'senha123');

-- Inserir avaliações
INSERT INTO tb_review (texto, user_id, filme_id) VALUES ('Incrível! Um dos melhores filmes que já vi.', 1, 1);
INSERT INTO tb_review (texto, user_id, filme_id) VALUES ('Muito divertido! Rimos muito.', 2, 3);
INSERT INTO tb_review (texto, user_id, filme_id) VALUES ('Uma história emocionante e bem contada.', 3, 2);
INSERT INTO tb_review (texto, user_id, filme_id) VALUES ('Me deu medo do início ao fim!', 1, 4);

-- Inserir papéis (roles)
INSERT INTO tb_role (authority) VALUES ('ROLE_USER');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_MODERATOR');

-- Inserir associações entre usuários e papéis
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);  -- Alice é ROLE_USER
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);  -- Bob é ROLE_USER
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);  -- Charlie é ROLE_ADMIN