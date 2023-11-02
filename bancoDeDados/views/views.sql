-- VIWES (CONSULTAS) DO BANCO DE DADOS LOCADORA DE VEÍCULOS

-- CONSULTAR DADOS DE CLIENTES
CREATE VIEW VconsultaClientes AS
SELECT 
    c.idClientes,
    CONCAT(c.nomeCliente, ' ', c.sobrenomeCliente) AS NomeCompleto,
    c.cpfCliente,
    t.tipoTelefone,
    CONCAT(t.dddTelefone, ' ', t.numeroTelefone) AS contatoCliente,
    CONCAT(e.ruaCliente, ', ',e.numeroCliente,', ',e.bairroCliente,', ',e.complemento) AS Endereço1,
    CONCAT(e.cidadeCliente, ', ', e.estadoCliente, ', ', e.cepCliente) AS Endereço2
FROM esquemaLocadora.tabelaCliente c
JOIN esquemaLocadora.tabelaClienteContatos t ON c.idClientes = t.FK_idClienteContatos
JOIN esquemaLocadora.tabelaClienteEndereco e ON c.idClientes = e.FK_idClienteEndereco;

-- CONSULTAR DADOS DE FUNCIONÁRIOS
CREATE VIEW VconsultaFuncionarios AS
SELECT
    idColaborador,
    CONCAT(nomeColab, ' ', sobrenomeColab),
    tipoAcesso
FROM tabelaColaboradores

-- CONSULTAR OS CARROS
CREATE VIEW VconsultaCarros AS
SELECT
    idCarro,
    chassiCarro,
    modeloCarro,
    placaCarro,
    anoCarro,
    precoDiaria_Carro,
    status
FROM esquemaLocadora.tabelaCarro

-- CONSULTAR OS SEGUROS
CREATE VIEW VconsultaSeguro AS
SELECT
    idSeguro,
    tipoSeguro,
    descricaoSeguro,
    precoSeguro
FROM esquemaLocadora.tabelaSeguros

-- CONSULTAR LOCAÇÕES ATIVAS
CREATE VIEW VLocacaoAtiva
AS
SELECT
l.idLocacao,
CONCAT(cli.nomeCliente, ' ', cli.sobrenomeCliente) AS NomeCompleto,
s.tipoSeguro,
c.modeloCarro,
l.dataCriacao,
l.dataInicio,
l.dataFim,
l.valorTotal,
l.status
FROM esquemaLocadora.tabelaLocacao_Ativa l
JOIN esquemaLocadora.tabelaCarro c ON c.idCarro = l.FK_idCarroLocacao
JOIN esquemaLocadora.tabelaSeguros s ON s.idSeguro = l.FK_idSeguroLocacao
JOIN esquemaLocadora.tabelaCliente cli ON cli.idClientes = l.FK_idClienteLocacao

-- CONSULTAR O HISTÓRICO DAS LOCAÇÕES

CREATE VIEW VLocacaoHistorico
AS
SELECT
l.idLocacao,
CONCAT(cli.nomeCliente, ' ', cli.sobrenomeCliente) AS NomeCompleto,
s.tipoSeguro,
c.modeloCarro,
l.dataCriacao,
l.dataInicio,
l.dataFim,
l.valorTotal,
l.status
FROM esquemaLocadora.tabelaLocacao_Histórico l
JOIN esquemaLocadora.tabelaCarro c ON c.idCarro = l.FK_idCarroLocacao
JOIN esquemaLocadora.tabelaSeguros s ON s.idSeguro = l.FK_idSeguroLocacao
JOIN esquemaLocadora.tabelaCliente cli ON cli.idClientes = l.FK_idClienteLocacao