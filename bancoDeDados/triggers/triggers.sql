CREATE TRIGGER trigger_verifica_locacao_ativa
ON esquemaLocadora.tabelaLocacao_Ativa
AFTER INSERT
AS
BEGIN

DECLARE @dataInicio DATE,
      @dataFim DATE;

SELECT @dataInicio = dataInicio, @dataFim = dataFim
FROM inserted;

IF (CURRENT_TIMESTAMP >= @dataFim)
BEGIN
UPDATE esquemaLocadora.tabelaLocacao_Ativa
SET status = 'Finalizada'
WHERE idLocacao = (SELECT idLocacao FROM inserted);
END;

END;

