CREATE TRIGGER trigger_verifica_locacao_ativa
ON esquemaLocadora.tabelaLocacao_Ativa
AFTER INSERT
AS
BEGIN

IF (CURRENT_TIMESTAMP >= NEW.dataFim)
BEGIN
UPDATE esquemaLocadora.tabelaLocacao_Ativa
SET status = 'Finalizada'
WHERE idLocacao = NEW.idLocacao;

INSERT INTO esquemaLocadora.tabelaLocacao_Historico
(FK_idClienteLocacao, FK_idSeguroLocacao, FK_idCarroLocacao, dataCriacao, dataInicio, dataFim, valorTotal, status)
SELECT NEW.FK_idClienteLocacao, NEW.FK_idSeguroLocacao, NEW.FK_idCarroLocacao, NEW.dataCriacao, NEW.dataInicio, NEW.dataFim, NEW.valorTotal, NEW.status;
END;

END;
