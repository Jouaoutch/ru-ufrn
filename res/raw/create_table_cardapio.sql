CREATE TABLE Refeicao (
	id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	nome TEXT NOT NULL,
	tipo TEXT NOT NULL
);

CREATE TABLE Alimento (
	id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	nome TEXT NOT NULL,
	descricao TEXT NOT NULL,
	imagem TEXT
);

CREATE TABLE Refeicao_Alimento (
	id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	id_refeicao INTEGER NOT NULL,
	id_alimento INTEGER NOT NULL,
	CONSTRAINT FOREIGN KEY (id_refeicao) REFERENCES Refeicao(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (id_alimento) REFERENCES Alimento(id) ON DELETE RESTRICT
);

CREATE TABLE Cardapio (
	id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	data TEXT NOT NULL
);

CREATE TABLE Cardapio_Refeicao (
	id_cardapio INTEGER NOT NULL,
	id_refeicao INTEGER NOT NULL,
	CONSTRAINT PRIMARY KEY(id_cardapio, id_refeicao),
	CONSTRAINT FOREIGN KEY (id_cardapio) REFERENCES Cardapio(id) ON DELETE RESTRICT,
	CONSTRAINT FOREIGN KEY (id_refeicao) REFERENCES Refeicao(id) ON DELETE RESTRICT
);

Create table Usuario(
idUsuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
login TEXT NOT NULL,
senha TEXT NOT NULL
);

create table Avaliacao(
idavaliacao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
avaliacao varchar(10) NOT NULL
);

insert into Avaliacao values  (Gostei);
insert into Avaliacao values (Desgostei);
insert into Avaliacao values (Indiferente);



CREATE TABLE Avaliacao_Cardapio ( 
idAvaliacao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
cardapioCumprido boolean NOT NULL,
id_refeicao INTEGER NOT NULL, 
data date NOT NULL, 
idavaliacao INTEGER NOT null, 
idUsuario  Integer NOT NULL
CONSTRAINT FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario) ON DELETE RESTRICT,
CONSTRAINT FOREIGN KEY (id_refeicao) REFERENCES Refeicao(id) ON DELETE RESTRICT,
CONSTRAINT FOREIGN KEY (idAvaliaco) REFERENCES Avaliacao (idAvaliacao) ON DELETE RESTRICT
 );
