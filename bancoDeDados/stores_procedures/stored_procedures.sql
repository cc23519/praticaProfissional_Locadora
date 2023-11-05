-- EXCLUSÃO DE CARROS
create PROCEDURE stExcluirCarros
    @usuario VARCHAR(15),
    @idexclusao INT
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
create PROCEDURE stExcluirSeguros
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
create PROCEDURE stExcluirClientes
    @usuario VARCHAR(15),
    @idexclusao INT
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
create PROCEDURE stExcluirLocacoes
    @usuario VARCHAR(15),
    @idexclusao INT
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


