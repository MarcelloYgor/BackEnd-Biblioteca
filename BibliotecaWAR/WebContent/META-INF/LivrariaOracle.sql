Alter user livraria  default tablespace users;

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
	data_emprestimo DATE NOT NULL,
	data_devolucao DATE,
	active int NOT NULL
);
			
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

CREATE OR REPLACE PROCEDURE numero_emprestimos (
	dt_emprestimo IN DATE,
	dt_devolucao IN DATE)
AS
BEGIN
	SELECT COUNT(*) FROM tb_emprestimo
		WHERE data_emprestimo = dt_emprestimo
			AND data_devolucao = dt_devolucao;
END numero_emprestimos;

CREATE OR REPLACE PROCEDURE numero_livros (
	id IN INT)
AS
BEGIN
	SELECT COUNT (*) FROM tb_livros_emprestados
		WHERE id_emprestimo = id;
END numero_livros;

CREATE OR REPLACE PROCEDURE livros_tops (
	id IN INT
)
AS
BEGIN
	SELECT id_livro COUNT(*) 
		AS livro_count FROM tb_livros_emprestados
			WHERE id_emprestimo = id;
END livros_tops;
			
select * from tb_login;

select * from tb_cliente;

select * from TB_EMPRESTIMO e inner join TB_livros_emprestados le on e.id = le.id_emprestimo 
    where e.active = 1;



select * from TB_EMPRESTIMO;

select * from TB_livros_emprestados;

select * from tb_funcionario;

insert into tb_funcionario(SALARIO,RG,NOME,ID,CPF,ADMIN) values(21000, '52500581x', 'felipe',1,10,1);

insert into tb_login(PASSWORD,ID_FUNCIONARIO,ID,EMAIL,ADMIN) values('123',1,1,'felipe@felipe.com',1);

SELECT * FROM tb_login;

insert into TB_AUTOR(id,nacionalidade,nome) values(1,'USA','Jeff Sutherland');

insert into TB_AUTOR(id,nacionalidade,nome) values(2,'Reino Unido','Joanne Kathleen Rowling');

insert into TB_EDITORA(id,nacionalidade,nome,endereco) values(1,'br','leya','Av. Angélica');

insert into TB_EDITORA(id,nacionalidade,nome,endereco) values(3,'br','Rocco','Desconhecido');

insert into TB_GENERO(id,nome,descricao) values(1,'unknown', 'desconhecido');

insert into TB_GENERO(id,nome,descricao) values(2,'Fantasia', 'Fantasia');

select * from tb_livro;

select * from tb_funcionario;

SELECT id, nome, nacionalidade FROM tb_autor WHERE nome = 'Jeff Sutherland';

delete from tb_autor;
