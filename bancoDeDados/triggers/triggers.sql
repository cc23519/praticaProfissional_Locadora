CREATE OR REPLACE FUNCTION verificarETransferirLocacoes()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO esquemaLocadora.tabelaLocacao_Histórico
    SELECT *
    FROM esquemaLocadora.tabelaLocacao_Ativa
    WHERE status = 'Ativa' AND dataFim < CURRENT_DATE;

    DELETE FROM esquemaLocadora.tabelaLocacao_Ativa
    WHERE status = 'Ativa' AND dataFim < CURRENT_DATE;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION agendarVerificacaoDiaria()
RETURNS TRIGGER AS $$
BEGIN
    PERFORM cron.schedule(
        '0 0 * * *',
        'SELECT verificarETransferirLocacoes()'
    );

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

DO $$ BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_proc
        WHERE proname = 'agendarVerificacaoDiaria'
    ) THEN
        PERFORM agendarVerificacaoDiaria();
    END IF;
END $$;
