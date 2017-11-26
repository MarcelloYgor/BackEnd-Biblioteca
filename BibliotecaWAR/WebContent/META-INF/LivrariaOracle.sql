
CREATE TABLE tb_cliente (
	id INTEGER primary key,
	nome VARCHAR(35) NOT NULL,
	rg VARCHAR(9) NOT NULL,
	cpf int NOT NULL,
	endereco VARCHAR(100) NOT NULL,
	telefone INTEGER NOT NULL,
	cidade VARCHAR(35) NOT NULL,
	dt_nascimento DATE NOT NULL
);
CREATE SEQUENCE cliente_seq START WITH 1;

CREATE OR REPLACE TRIGGER cliente_bir 
BEFORE INSERT ON tb_cliente
FOR EACH ROW

BEGIN
  SELECT cliente_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
		
CREATE TABLE tb_editora (
	id INT PRIMARY KEY,
	nome VARCHAR(35) NOT NULL,
	endereco VARCHAR(100) NOT NULL,
	nacionalidade VARCHAR(20) NOT NULL
);
CREATE SEQUENCE editora_seq START WITH 1;

CREATE OR REPLACE TRIGGER cliente_bir 
BEFORE INSERT ON tb_editora
FOR EACH ROW

BEGIN
  SELECT editora_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
		
CREATE TABLE tb_autor (
	id INT PRIMARY KEY,
	nome VARCHAR(35) NOT NULL,
	nacionalidade VARCHAR(20) NOT NULL
);
CREATE SEQUENCE autor_seq START WITH 1;

CREATE OR REPLACE TRIGGER autor_bir 
BEFORE INSERT ON tb_autor
FOR EACH ROW

BEGIN
  SELECT autor_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
		
CREATE TABLE tb_genero (
	id INT PRIMARY KEY,
	nome VARCHAR(35) NOT NULL,
	descricao VARCHAR(100)
);
CREATE SEQUENCE genero_seq START WITH 1;

CREATE OR REPLACE TRIGGER genero_bir 
BEFORE INSERT ON tb_genero
FOR EACH ROW

BEGIN
  SELECT genero_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
		
CREATE TABLE tb_funcionario (
	id INT PRIMARY KEY,
	nome VARCHAR(35) NOT NULL,
	rg VARCHAR(9) NOT NULL,
	cpf integer NOT NULL,
	salario FLOAT NOT NULL,
	admin int NOT NULL
);
CREATE SEQUENCE funcionario_seq START WITH 1;

CREATE OR REPLACE TRIGGER funcionario_bir 
BEFORE INSERT ON tb_funcionario
FOR EACH ROW

BEGIN
  SELECT funcionario_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
		
CREATE TABLE tb_login (
	id INT PRIMARY KEY,
	id_funcionario INT constraint fk_funcionario_login references tb_funcionario,
	email VARCHAR(35) NOT NULL,
	password VARCHAR(20) NOT NULL,
	admin int NOT NULL
);
CREATE SEQUENCE login_seq START WITH 1;

CREATE OR REPLACE TRIGGER login_bir 
BEFORE INSERT ON tb_login
FOR EACH ROW

BEGIN
  SELECT login_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
			
CREATE TABLE tb_livro (
	id INT PRIMARY KEY,
	nome VARCHAR(35) NOT NULL,
	id_autor constraint fk_autor_livro references tb_autor,
	id_editora INT constraint fk_editora_livro references tb_editora,
	ano INT NOT NULL,
	edicao INT NOT NULL,
	num_paginas INT NOT NULL,
	id_genero INT constraint fk_genero_livro references tb_genero,
	idioma VARCHAR(20),
	img VARCHAR(20)
);

CREATE SEQUENCE livro_seq START WITH 1;

CREATE OR REPLACE TRIGGER livro_bir 
BEFORE INSERT ON tb_livro 
FOR EACH ROW

BEGIN
  SELECT livro_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
			
CREATE TABLE tb_emprestimo (
	id INT PRIMARY KEY,
	id_cliente constraint fk_cliente_emprestimo references tb_cliente,
	id_funcionario INT constraint fk_funcionario_emprestimo references tb_funcionario,
	data_emprestimo DATE NOT NULL,
	data_devolucao DATE,
	active int NOT NULL
);

CREATE SEQUENCE emprestimo_seq START WITH 1;

CREATE OR REPLACE TRIGGER emprestimo_bir 
BEFORE INSERT ON tb_emprestimo 
FOR EACH ROW

BEGIN
  SELECT emprestimo_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
			
CREATE TABLE tb_livros_emprestados (
	id INT PRIMARY KEY,
	id_emprestimo INT constraint fk_emprestimo_le references tb_emprestimo,
	id_livro INT constraint fk_livro_le references tb_livro
);

CREATE SEQUENCE livros_emprestados_seq START WITH 1;

CREATE OR REPLACE TRIGGER livros_emprestados_bir 
BEFORE INSERT ON tb_livros_emprestados 
FOR EACH ROW

BEGIN
  SELECT livros_emprestados_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;