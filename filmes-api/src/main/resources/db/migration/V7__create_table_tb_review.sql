CREATE TABLE IF NOT EXISTS tb_review (
    id BIGINT NOT NULL AUTO_INCREMENT,
    texto TEXT NOT NULL,
    user_id BIGINT,
    filme_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES tb_user(id) ON DELETE CASCADE,
    FOREIGN KEY (filme_id) REFERENCES tb_filme(id) ON DELETE CASCADE
);
