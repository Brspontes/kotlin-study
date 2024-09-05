CREATE TABLE IF NOT EXISTS curso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS topico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    dataCriacao DATETIME NOT NULL,
    curso_id BIGINT,
    autor_id BIGINT,
    status TEXT NOT NULL,
    FOREIGN KEY (curso_id) REFERENCES Curso(id),
    FOREIGN KEY (autor_id) REFERENCES Usuario(id)
);

CREATE TABLE IF NOT EXISTS resposta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensagem TEXT NOT NULL,
    dataCriacao DATETIME NOT NULL,
    autor_id BIGINT,
    topico_id BIGINT,
    solucao BOOLEAN NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES Usuario(id),
    FOREIGN KEY (topico_id) REFERENCES Topico(id)
);