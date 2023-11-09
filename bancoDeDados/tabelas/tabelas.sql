-- BANCO DE DADOS LOCADORA DE VEÍCULOS
-- SCRIPT PARA A CRIAÇÃO DE TABELAS + SUAS RELAÇÕES


-- Criando o esquema usado nesse banco.
CREATE SCHEMA esquemaLocadora


-- Criando tabelas do banco
CREATE TABLE esquemaLocadora.tabelaColaborador (
    idColaborador INT IDENTITY(1,1) PRIMARY KEY,
    nomeColab varchar(30) NOT NULL,
    sobrenomeColab varchar(30) NOT NULL,
    tipoAcesso INT NOT NULL
);

CREATE TABLE esquemaLocadora.tabelaColaboradorCred (
    FK_idColaborador INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    usuarioColab varchar(15) NOT NULL UNIQUE,
    senhaColab varchar(60) NOT NULL,
);

CREATE TABLE esquemaLocadora.tabelaCliente (
    idClientes INT IDENTITY(1,1) PRIMARY KEY,
    nomeCliente varchar(30) NOT NULL,
    sobrenomeCliente varchar(30) NOT NULL,
    cpfCliente varchar(11) NOT NULL UNIQUE
);

CREATE TABLE esquemaLocadora.tabelaClienteContatos (
    FK_idClienteContatos INT IDENTITY(1,1) PRIMARY KEY,
    tipoTelefone varchar(10) NOT NULL,
    dddTelefone varchar(2) NOT NULL,
    numeroTelefone varchar(11) NOT NULL
);

CREATE TABLE esquemaLocadora.tabelaClienteEndereco (
    FK_idClienteEndereco INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    ruaCliente varchar(30) NOT NULL,
    numeroCliente varchar(5) NOT NULL,
    bairroCliente varchar(30) NOT NULL,
    cidadeCliente varchar(15) NOT NULL,
    estadoCliente varchar(15) NOT NULL,
    cepCliente varchar(8) NOT NULL,
    complemento varchar(40)
);

CREATE TABLE esquemaLocadora.tabelaClienteCredenciais (
    FK_idClienteCred INT IDENTITY(1,1) PRIMARY KEY,
    emailCliente varchar(30) NOT NULL UNIQUE,
    senhaCliente varchar(30) NOT NULL
);

CREATE TABLE esquemaLocadora.tabelaCarro (
    idCarro INT IDENTITY(1,1) PRIMARY KEY,
    chassiCarro varchar(17) NOT NULL,
    modeloCarro varchar(30) NOT NULL,
    placaCarro varchar(7) NOT NULL,
    anoCarro varchar(4) NOT NULL,
    precoDiaria_Carro money NOT NULL,
    status varchar(10) NOT NULL,
    UNIQUE (chassiCarro, placaCarro)
);

CREATE TABLE esquemaLocadora.tabelaSeguros (
    idSeguro INT IDENTITY(1,1) PRIMARY KEY,
    tipoSeguro varchar(30) NOT NULL,
    descricaoSeguro varchar(50) NOT NULL,
    precoSeguro money NOT NULL
);

CREATE TABLE esquemaLocadora.tabelaLocacao_Ativa (
    idLocacao INT IDENTITY(1,1) PRIMARY KEY,
    FK_idClienteLocacao INT NOT NULL,
    FK_idSeguroLocacao INT NOT NULL,
    FK_idCarroLocacao INT NOT NULL,
    dataCriacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    dataInicio date NOT NULL,
    dataFim date NOT NULL,
    valorTotal money not null,
    status varchar(11) NOT NULL
);

CREATE TABLE esquemaLocadora.tabelaLocacao_Histórico (
    idLocacao INT IDENTITY(1,1) PRIMARY KEY,
    FK_idClienteLocacao INT NOT NULL,
    FK_idSeguroLocacao INT NOT NULL,
    FK_idCarroLocacao INT NOT NULL,
    dataCriacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    dataInicio date NOT NULL,
    dataFim date NOT NULL,
    valorTotal money not null,
    status varchar(11) NOT NULL
);


--Criando as relações entre as tabelas
ALTER TABLE esquemaLocadora.tabelaClienteContatos ADD CONSTRAINT FK_tabelaClienteContatos
    FOREIGN KEY (FK_idClienteContatos)
    REFERENCES esquemaLocadora.tabelaCliente (idClientes);
 
ALTER TABLE esquemaLocadora.tabelaClienteEndereco ADD CONSTRAINT FK_tabelaClienteEndereco
    FOREIGN KEY (FK_idClienteEndereco)
    REFERENCES esquemaLocadora.tabelaCliente (idClientes);
 
ALTER TABLE esquemaLocadora.tabelaClienteCredenciais ADD CONSTRAINT FK_tabelaClienteCredenciais
    FOREIGN KEY (FK_idClienteCred)
    REFERENCES esquemaLocadora.tabelaCliente (idClientes);
 
ALTER TABLE esquemaLocadora.tabelaLocacao_Ativa ADD CONSTRAINT FK_tabelaLocacao_AtivaCliente
    FOREIGN KEY (FK_idClienteLocacao)
    REFERENCES esquemaLocadora.tabelaCliente (idClientes);
 
ALTER TABLE esquemaLocadora.tabelaLocacao_Ativa ADD CONSTRAINT FK_tabelaLocacao_AtivaSeguro
    FOREIGN KEY (FK_idSeguroLocacao)
    REFERENCES esquemaLocadora.tabelaSeguros (idSeguro);
 
ALTER TABLE esquemaLocadora.tabelaLocacao_Ativa ADD CONSTRAINT FK_tabelaLocacao_AtivaCarro
    FOREIGN KEY (FK_idCarroLocacao)
    REFERENCES esquemaLocadora.tabelaCarro (idCarro);

ALTER TABLE esquemaLocadora.tabelaColaboradorCred ADD CONSTRAINT FK_tabelaColaboradorCred_3
    FOREIGN KEY (FK_idColaborador)
    REFERENCES esquemaLocadora.tabelaColaborador (idColaborador);