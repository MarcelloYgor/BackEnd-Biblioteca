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
		
CREATE TABLE tb_editora (
	id INT PRIMARY KEY,
	nome VARCHAR(35) NOT NULL,
	endereco VARCHAR(100) NOT NULL,
	nacionalidade VARCHAR(20) NOT NULL
);
		
CREATE TABLE tb_autor (
	id INT PRIMARY KEY,
	nome VARCHAR(35) NOT NULL,
	nacionalidade VARCHAR(20) NOT NULL
);
		
CREATE TABLE tb_genero (
	id INT PRIMARY KEY,
	nome VARCHAR(35) NOT NULL,
	descricao VARCHAR(100)
);
		
CREATE TABLE tb_funcionario (
	id INT PRIMARY KEY,
	nome VARCHAR(35) NOT NULL,
	rg VARCHAR(9) NOT NULL,
	cpf integer NOT NULL,
	salario FLOAT NOT NULL,
	admin int NOT NULL
);
		
CREATE TABLE tb_login (
	id INT PRIMARY KEY,
	id_funcionario INT constraint fk_funcionario_login references tb_funcionario,
	email VARCHAR(35) NOT NULL,
	password VARCHAR(20) NOT NULL,
	admin int NOT NULL
);
			
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
	qtd_disponivel INT NOT NULL
);
			
CREATE TABLE tb_emprestimo (
	id INT PRIMARY KEY,
	id_cliente constraint fk_cliente_emprestimo references tb_cliente,
	id_funcionario INT constraint fk_funcionario_emprestimo references tb_funcionario,
	data_emprestimo DATE NOT NULL,
	data_devolucao DATE,
	active int NOT NULL
);
			
CREATE TABLE tb_livros_emprestados (
	id INT PRIMARY KEY,
	id_emprestimo INT constraint fk_emprestimo_le references tb_emprestimo,
	id_livro INT constraint fk_livro_le references tb_livro
);

select * from tb_livros_emprestados;
select * from tb_emprestimo;
select * from tb_livro;
select * from tb_login;
select * from tb_funcionario;
select * from tb_genero;
select * from tb_autor;
select * from tb_editora;
select * from tb_cliente;

DELETE FROM tb_livros_emprestados;
DELETE FROM tb_emprestimo;
DELETE FROM tb_livro;
DELETE FROM tb_login;
DELETE FROM tb_funcionario;
DELETE FROM tb_genero;
DELETE FROM tb_autor;
DELETE FROM tb_editora;
DELETE FROM tb_cliente;