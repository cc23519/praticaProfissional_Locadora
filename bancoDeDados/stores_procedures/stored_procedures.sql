-- STORED PROCEDURES DO BANCO DE DADOS LOCADORA DE VEÍCULOS

-- AUTENTICAÇÃO DE LOGIN COLABORADORES 
-- APLICAÇÃO: JAVA
-- OBJETIVO: AUTENTICAR O LOGIN E PERMITIR A ENTRADA NO SISTEMA. 
CREATE PROCEDURE SPautenticacaoLogin
    @idcolab INT OUTPUT,
    @nomecolaborador varchar(30) OUTPUT,
    @sobrenomecolab varchar(30) OUTPUT,
    @acesso varchar(10 OUTPUT,)
    @usuario varchar(15),
    @senha varchar(30),
    @resultadoAutenticacao INT OUTPUT
AS
BEGIN
    SELECT c.idColaborador, c.nomeColab, c.sobrenomeColab, c.tipoAcesso
    INTO @idcolab, @nomecolaborador, @sobrenomecolab, @acesso
    FROM esquemaLocadora.tabelaColaborador
    INNER JOIN esquemaLocadora.tabelaColaboradorCred cred ON c.idColaborador = cred.FK_idColaborador
    WHERE cred.usuarioColab = @usuario AND cred.senhaColab = @senha

    IF @ROWCOUNT >0
    BEGIN
        SET @resultadoAutenticacao = 1;
    END
    ELSE
    BEGIN
        SET @resultadoAutenticacao = 0;
    END
END


