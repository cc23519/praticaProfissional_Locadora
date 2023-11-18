-- EXCLUSÃO DE CARROS
CREATE PROCEDURE stExcluirCarros
    @usuario VARCHAR(15),
    @idexclusao INT,
    @resultado INT OUTPUT
AS 
BEGIN
    DECLARE @tipoacesso INT;
    DECLARE @id INT;
    
	SELECT @tipoAcesso = c.tipoAcesso
	FROM esquemaLocadora.tabelaColaborador c
	INNER JOIN esquemaLocadora.tabelaColaboradorCred cc ON c.idColaborador = cc.FK_idColaborador
	WHERE cc.usuarioColab = @usuario; 

    IF @tipoacesso = 1
    BEGIN
        PRINT 'ACESSO NEGADO';
        RETURN 0; -- Retorne um código de acesso negado
    END
    ELSE
    BEGIN
        PRINT 'ACESSO PERMITIDO';
        
        -- Verifique se existe um carro em uma locação ativa
        IF EXISTS (SELECT 1
                   FROM esquemaLocadora.tabelaLocacao_Ativa
                   WHERE FK_idCarroLocacao = @idexclusao)
        BEGIN
            PRINT 'NÃO PERMITIDO EXCLUIR - HÁ LOCAÇÕES ATIVAS COM ESSE CARRO';
            RETURN 3; -- Retorne um código de não permitido excluir
        END
        ELSE -- Se não houver locação ativa, exclua o carro e retorne um código de exclusão realizada
        BEGIN
            DELETE FROM esquemaLocadora.tabelaCarro
            WHERE @idexclusao = idCarro;
            PRINT 'EXCLUSÃO REALIZADA';
            RETURN 4; -- Retorne um código de exclusão realizada
        END
    END
END


-- EXCLUSÃO DE SEGUROS
CREATE PROCEDURE stExcluirSeguros
    @usuario VARCHAR(15),
    @idexclusao INT,
    @resultado INT OUTPUT
AS 
BEGIN
    DECLARE @tipoacesso INT;
    DECLARE @id INT;
    
	SELECT @tipoAcesso = c.tipoAcesso
	FROM esquemaLocadora.tabelaColaborador c
	INNER JOIN esquemaLocadora.tabelaColaboradorCred cc ON c.idColaborador = cc.FK_idColaborador
	WHERE cc.usuarioColab = @usuario; 

	IF @tipoacesso = 1
	BEGIN
		PRINT 'ACESSO NEGADO';
		SET @resultado = 0; 
		RETURN;
	END
    ELSE 
    BEGIN
        PRINT 'ACESSO PERMITIDO';
        
        -- Verifique se existe um seguro em uma locação ativa
        IF EXISTS (SELECT 1
                   FROM esquemaLocadora.tabelaLocacao_Ativa
                   WHERE FK_idSeguroLocacao = @idexclusao)
        BEGIN
            PRINT 'NÃO PERMITIDO EXCLUIR - HÁ LOCAÇÕES ATIVAS COM ESSE SEGURO';
            SET @resultado = 3; -- Configurar o valor de retorno para 3 (não permitido excluir)
            RETURN; -- Sair da stored procedure
        END
        ELSE -- Se não houver locação ativa, exclua o seguro e retorne um código de exclusão realizada
        BEGIN
			DELETE FROM esquemaLocadora.tabelaSeguros
			WHERE idSeguro = @idexclusao;
            PRINT 'EXCLUSÃO REALIZADA';
            SET @resultado = 4; -- Configurar o valor de retorno para 4 (exclusão realizada)
            RETURN; -- Sair da stored procedure
        END
    END
END

-- EXCLUSÃO DE CLIENTES
CREATE PROCEDURE stExcluirClientes
    @usuario VARCHAR(15),
    @idexclusao INT,
    @resultado INT OUTPUT
AS 
BEGIN
    DECLARE @tipoacesso INT;
    DECLARE @id INT;
    
	SELECT @tipoAcesso = c.tipoAcesso
	FROM esquemaLocadora.tabelaColaborador c
	INNER JOIN esquemaLocadora.tabelaColaboradorCred cc ON c.idColaborador = cc.FK_idColaborador
	WHERE cc.usuarioColab = @usuario; 

    IF @tipoacesso = 1
    BEGIN
        PRINT 'ACESSO NEGADO';
        RETURN 0; -- Retorne um código de acesso negado
    END
    ELSE 
    BEGIN
        PRINT 'ACESSO PERMITIDO';
        
        -- Verifique se existe uma locação com o cliente
        IF EXISTS (SELECT 1
                   FROM esquemaLocadora.tabelaLocacao_Ativa
                   WHERE FK_idClienteLocacao = @idexclusao)
        BEGIN
            PRINT 'NÃO PERMITIDO EXCLUIR - HÁ LOCAÇÕES ATIVAS COM ESSE CLIENTE';
            RETURN 3; -- Retorne um código de não permitido excluir
        END
        ELSE -- Se não houver locação ativa, exclua o cliente e retorne um código de exclusão realizada
        BEGIN
            DELETE FROM esquemaLocadora.tabelaClienteCredenciais
            WHERE @idexclusao = FK_idClienteCred;
            PRINT 'EXCLUSÃO 1 REALIZADA';

            DELETE FROM esquemaLocadora.tabelaCliente
            WHERE @idexclusao = idClientes;
            PRINT 'EXCLUSÃO 2 REALIZADA';
            RETURN 4; -- Retorne um código de exclusão realizada
        END
    END
END

-- EXCLUSÃO DE LOCAÇÕES ATIVAS
CREATE PROCEDURE stExcluirLocacoes
    @usuario VARCHAR(15),
    @idexclusao INT,
    @resultado INT OUTPUT
AS 
BEGIN
    DECLARE @tipoacesso INT;
    DECLARE @id INT;
    
	SELECT @tipoAcesso = c.tipoAcesso
	FROM esquemaLocadora.tabelaColaborador c
	INNER JOIN esquemaLocadora.tabelaColaboradorCred cc ON c.idColaborador = cc.FK_idColaborador
	WHERE cc.usuarioColab = @usuario; 

    IF @tipoacesso = 1
    BEGIN
        PRINT 'ACESSO NEGADO';
        RETURN 0; -- Retorne um código de acesso negado
    END
    ELSE
    BEGIN
        PRINT 'ACESSO PERMITIDO';
        
        -- Exclua a locação ativa e retorne um código de exclusão realizada
        DELETE FROM esquemaLocadora.tabelaLocacao_Ativa
        WHERE @idexclusao = idLocacao;
        PRINT 'EXCLUSÃO REALIZADA';
        RETURN 4; -- Retorne um código de exclusão realizada
    END
END







-- ALTERAR CARROS
CREATE PROCEDURE stAlterarCarros
    @usuario VARCHAR(15),
    @idexclusao INT,
    @chassi VARCHAR(17),
    @placa varchar(7),
    @modelo varchar(30),
    @ano varchar(4),
    @valor money,
    @resultado INT OUTPUT
AS 
BEGIN
    DECLARE @tipoacesso INT;
    DECLARE @id INT;
    
	SELECT @tipoAcesso = c.tipoAcesso
	FROM esquemaLocadora.tabelaColaborador c
	INNER JOIN esquemaLocadora.tabelaColaboradorCred cc ON c.idColaborador = cc.FK_idColaborador
	WHERE cc.usuarioColab = @usuario; 

    IF @tipoacesso = 1
    BEGIN
        PRINT 'ACESSO NEGADO';
        RETURN 0; -- Retorne um código de acesso negado
    END
    ELSE
    BEGIN
        PRINT 'ACESSO PERMITIDO';
        BEGIN
            UPDATE esquemaLocadora.tabelaCarro SET chassiCarro = @chassi, 
            placaCarro = @placa,
            modeloCarro = @modelo,
            anoCarro = @ano,
            valor = valor
            WHERE idCarro = @idexclusao;
            PRINT 'Alteração Realizada';
            RETURN 4; -- Retorne um código de alteração realizada
        END
    END
END



 -- ALTERAR CLIENTES
 CREATE PROCEDURE stAlterarClientes
     @usuario VARCHAR(15),
     @idexclusao INT,
	 @nome varchar(30),
	 @sobrenome varchar(30),
	 @cpf varchar(11),
	 @tipotelefone varchar(10),
	 @ddd varchar(2),
	 @numero varchar(11),
	 @rua varchar(30),
	 @numerocasa varchar(5),
	 @bairro varchar(30),
	 @cidade varchar(15),
	 @estado varchar(15),
	 @cep varchar(8),
	 @complemento varchar(40),
     @resultado INT OUTPUT
 AS 
 BEGIN
     DECLARE @tipoacesso INT;
     DECLARE @id INT;
    
 	SELECT @tipoAcesso = c.tipoAcesso
 	FROM esquemaLocadora.tabelaColaborador c
 	INNER JOIN esquemaLocadora.tabelaColaboradorCred cc ON c.idColaborador = cc.FK_idColaborador
 	WHERE cc.usuarioColab = @usuario; 

     IF @tipoacesso = 1
     BEGIN
         PRINT 'ACESSO NEGADO';
         RETURN 0; -- Retorne um código de acesso negado
     END
     ELSE
     BEGIN
         PRINT 'ACESSO PERMITIDO';
         BEGIN
		 select * from esquemaLocadora.tabelaCliente
             UPDATE esquemaLocadora.tabelaClienteEndereco SET 
			 ruaCliente = @rua,
			 numeroCliente = @numerocasa,
			 bairroCliente = @bairro,
			 cidadeCliente = @cidade,
			 estadoCliente = @estado,
			 cepCliente = @cep,
			 complemento = complemento
             WHERE FK_idClienteEndereco = @idexclusao;

			 UPDATE esquemaLocadora.tabelaClienteContatos SET 
			 tipoTelefone = @tipotelefone,
			 dddTelefone = @ddd,
			 numeroTelefone = @numero
             WHERE FK_idClienteContatos = @idexclusao;

			 UPDATE esquemaLocadora.tabelaCliente SET 
			 nomeCliente = @nome,
			 sobrenomeCliente = @sobrenome,
			 cpfCliente = @cpf
             WHERE idClientes = @idexclusao;

             PRINT 'Alteração Realizada';
             RETURN 4; -- Retorne um código de alteração realizada
         END
     END
 END


-- ALTERAR Carros
CREATE PROCEDURE stAlterarCarros
     @usuario VARCHAR(15),
     @idexclusao INT,
     @chassi VARCHAR(17),
     @placa varchar(7),
     @modelo varchar(30),
     @ano varchar(4),
     @valor money,
     @resultado INT OUTPUT
AS 
BEGIN
     DECLARE @tipoacesso INT;
     DECLARE @id INT;
    
 	SELECT @tipoAcesso = c.tipoAcesso
 	FROM esquemaLocadora.tabelaColaborador c
 	INNER JOIN esquemaLocadora.tabelaColaboradorCred cc ON c.idColaborador = cc.FK_idColaborador
 	WHERE cc.usuarioColab = @usuario; 

     IF @tipoacesso = 1
     BEGIN
         PRINT 'ACESSO NEGADO';
         RETURN 0; -- Retorne um código de acesso negado
     END
     ELSE
     BEGIN
         PRINT 'ACESSO PERMITIDO';
         BEGIN
             UPDATE esquemaLocadora.tabelaCarro SET chassiCarro = @chassi, 
             placaCarro = @placa,
             modeloCarro = @modelo,
             anoCarro = @ano,
             precoDiaria_Carro = @valor
             WHERE idCarro = @idexclusao;
             PRINT 'Alteração Realizada';
             RETURN 4; -- Retorne um código de alteração realizada
         END
     END
 END

 -- Alterar Seguros
 CREATE PROCEDURE stAlterarSeguros
     @usuario VARCHAR(15),
     @idexclusao INT,
     @tipo VARCHAR(30),
     @descricao varchar(50),
     @preco money,
     @resultado INT OUTPUT
AS 
BEGIN
     DECLARE @tipoacesso INT;
     DECLARE @id INT;
    
 	SELECT @tipoAcesso = c.tipoAcesso
 	FROM esquemaLocadora.tabelaColaborador c
 	INNER JOIN esquemaLocadora.tabelaColaboradorCred cc ON c.idColaborador = cc.FK_idColaborador
 	WHERE cc.usuarioColab = @usuario; 

     IF @tipoacesso = 1
     BEGIN
         PRINT 'ACESSO NEGADO';
         RETURN 0; -- Retorne um código de acesso negado
     END
     ELSE
     BEGIN
         PRINT 'ACESSO PERMITIDO';
         BEGIN
             UPDATE esquemaLocadora.tabelaSeguros 
			 SET tipoSeguro = @tipo,
             descricaoSeguro = @descricao,
             precoSeguro = @preco
             WHERE idSeguro = @idexclusao;
             PRINT 'Alteração Realizada';
             RETURN 4; -- Retorne um código de alteração realizada
         END
     END
 END