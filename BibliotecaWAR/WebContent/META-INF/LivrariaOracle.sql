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
	img VARCHAR(20)
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

select * from tb_login;

select * from tb_funcionario;

insert into tb_funcionario(SALARIO,RG,NOME,ID,CPF,ADMIN) values(21000, '52500581x', 'felipe',1,10,1);

insert into tb_login(PASSWORD,ID_FUNCIONARIO,ID,EMAIL,ADMIN) values('123',1,1,'felipe@felipe.com',1);

SELECT * FROM tb_login;

insert into TB_AUTOR(id,nacionalidade,nome) values(1,'Jeff Sutherland','USA');

insert into TB_EDITORA(id,nacionalidade,nome,endereco) values(1,'br','leya','Av. Angélica');

insert into TB_GENERO(id,nome,descricao) values(1,'unknown', 'desconhecido');

select * from tb_livro;

