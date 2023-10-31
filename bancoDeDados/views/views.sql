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
FROM tabelaCarros

-- CONSULTAR OS SEGUROS
CREATE VIEW VconsultaSeguro AS
SELECT
    idSeguro,
    tipoSeguro,
    descricaoSeguro,
    precoSeguro
FROM tabelaSeguro

-- CONSULTAR LOCAÇÕES ATIVAS

-- CONSULTAR O HISTÓRICO DAS LOCAÇÕES