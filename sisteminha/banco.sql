CREATE DATABASE db_sisteminha;

CREATE TABLE item (
    oid INT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(8,2) NOT NULL,
    PRIMARY KEY (oid)
);

#POPULAR O BD

INSERT INTO item (descricao, valor) VALUES ('PS4', '1600');
INSERT INTO item (descricao, valor) VALUES ('XBOX', '1500');
INSERT INTO item (descricao, valor) VALUES ('NOTEBOOK', '2300');
INSERT INTO item (descricao, valor) VALUES ('IMPRESSORA', '500');
INSERT INTO item (descricao, valor) VALUES ('MONITOR', '550');
INSERT INTO item (descricao, valor) VALUES ('MOUSE', '30');
INSERT INTO item (descricao, valor) VALUES ('FONE', '60');
INSERT INTO item (descricao, valor) VALUES ('TECLADO', '25');
INSERT INTO item (descricao, valor) VALUES ('CARTAO', '5');
INSERT INTO item (descricao, valor) VALUES ('PEN DRIVE', '35');
INSERT INTO item (descricao, valor) VALUES ('ARMA DE BRINQUEDO', '30');
INSERT INTO item (descricao, valor) VALUES ('AMOEBA', '30');
INSERT INTO item (descricao, valor) VALUES ('AMIDO', '35');
