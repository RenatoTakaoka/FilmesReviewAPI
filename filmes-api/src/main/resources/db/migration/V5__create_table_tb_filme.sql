CREATE TABLE IF NOT EXISTS tb_filme (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    ano INT,
    genero_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (genero_id) REFERENCES tb_genero(id) ON DELETE CASCADE
);
