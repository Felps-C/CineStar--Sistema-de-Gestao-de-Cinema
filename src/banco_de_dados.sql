CREATE DATABASE cinema;
USE cinema;

CREATE TABLE filme (
  Idfilme int NOT NULL AUTO_INCREMENT primary key,
  Nome varchar(20) NOT NULL,
  Genero varchar(20) NOT NULL,
  Duracao time NOT NULL,
  Classificacao varchar(10) NOT NULL,
  Preco varchar(45) NOT NULL);
INSERT INTO filme
VALUES (1,'Desafio em Tóquio','Ação','01:44:00','15 anos','20.0'),
(2,'AS branquelas','Comédia','01:49:00','14 anos','24.0'),
(3,'É assim que acaba','Romance','02:10:00','14 anos','40.97'),
(4,'Ainda estou aqui','Drama','02:15:00','14 anos','19.99'),
(5,'MIB: Homens de preto','Ficção científica','01:38:00','12 anos','21.0'),
(6,'Moana 2','Infantil','01:40:00','Livre',''),
(7,'Phineas e Ferb','Infantil','01:44:00','Livre','34.0'),
(8,'A hora da estrela','ficção','02:30:15','18 anos','24.0'),
(9,'Enola holmes','ficção','02:03:16','Livre','27.0'),
(10,'Enola holmes 2','ficção','02:15:16','Livre','30.0');

CREATE TABLE funcionario (
  IdFuncionario int NOT NULL AUTO_INCREMENT primary key,
  Contato varchar(25) DEFAULT NULL,
  Nome varchar(45) NOT NULL,
  Gênero varchar(10) NOT NULL,
  Cargo varchar(20) NOT NULL,
  Idade int NOT NULL,
  Salario decimal(7,2) NOT NULL);
INSERT INTO funcionario
 VALUES (1,'(45)9934534','Cleiton','Masculino','Zelador',20,1643.67),
 (2,'(33)8765434','Marcelo','Masculino','Gerente',21,4532.98),
 (3,'(34)5623548','Jobson','Masculino','Vendedor',27,2136.73),
 (4,'(65)9842458','Cleusa','Feminino','Vendedora',19,2136.73),
 (5,'(77)2468745','Valeria','Feminino','Zeladora',19,1643.67);

CREATE TABLE produto (
  IDlanche int NOT NULL AUTO_INCREMENT primary key,
  Nome varchar(25) NOT NULL,
  Validade varchar(10) NOT NULL,
  Valor decimal(5,2) NOT NULL,
  Quantidade int NOT NULL);
INSERT INTO produto
VALUES (1,'Pipoca','1 semana',20.00,8),
(2,'Refrigerante','9 meses',12.00,7),
(3,'Suco','2 dias',10.00,13),
(4,'Pastel de Frango','3 dias',15.00,7),
(5,'Água mineral','12 meses',5.00,13),
(6,'maça do amor','2 dias',20.00,23),
(7,'brigadeiro','3 dias',5.00,50),
(8,'pipoca doce ','4 dias',25.00,7);

CREATE TABLE sessao (
  Idsessao int NOT NULL AUTO_INCREMENT primary key,
  filme_id int NOT NULL,
  horario varchar(50) NOT NULL,
  sala varchar(50) NOT NULL);
INSERT INTO sessao
VALUES (1,1,'15:30','1'),
(2,4,'16:00','3'),
(3,5,'18:00','2'),
(4,2,'17:30','3'),
(5,3,'11:00','5'),
(6,6,'12:00','1'),
(7,8,'14:00','9'),
(8,7,'15:00','2'),
(9,11,'15:00','1'),
(10,10,'19:00','1');

CREATE TABLE solicitacao_reposicao(
  Idsolicitacao int NOT NULL AUTO_INCREMENT primary key,
  produto_id int NOT NULL,
  nome_produto varchar(100) NOT NULL,
  quantidade_solicitada int NOT NULL,
  status varchar(20) DEFAULT 'PENDENTE');
INSERT INTO solicitacao_reposicao
VALUES (1,16,'brigadeiro',50,'CONCLUIDO');

CREATE TABLE usuarios(
  Id int NOT NULL AUTO_INCREMENT primary key,
  Gmail varchar(45) NOT NULL,
  Senha varchar(45) NOT NULL,
  Tipo varchar(45) NOT NULL);
INSERT INTO usuarios
VALUES (1,'felisberto','1234','Vendedor'),
(2,'josefa','4321','Gerente'),
(3,'sebastiao','5432','Cliente'),
(4,'joaquin','4345','Vendedor'),
(5,'maria','4321','Cliente'),
(6,'vitoria','2343','Gerente'),
(7,'melissa','1234','Cliente'),
(8,'lucas','3098','Cliente'),
(9,'joaooo','1234','gerente'),
(10,'Jose','0987','vendedor');