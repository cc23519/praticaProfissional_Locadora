USE esquemaLocadora;

CREATE UNIQUE INDEX idx_cliente_cpf ON tabelaCliente (cpfCliente);

CREATE UNIQUE INDEX idx_cliente_email ON tabelaClienteCredenciais (emailCliente);

CREATE UNIQUE INDEX idx_carro_chassi_placa ON tabelaCarro (chassiCarro, placaCarro);

CREATE INDEX idx_locacao_ativa_cliente ON tabelaLocacao_Ativa (FK_idClienteLocacao);

CREATE INDEX idx_locacao_ativa_data ON tabelaLocacao_Ativa (dataInicio, dataFim);

CREATE INDEX idx_cliente_contatos ON tabelaClienteContatos (FK_idClienteContatos);
CREATE INDEX idx_cliente_endereco ON tabelaClienteEndereco (FK_idClienteEndereco);
CREATE INDEX idx_cliente_credenciais ON tabelaClienteCredenciais (FK_idClienteCred);
CREATE INDEX idx_locacao_ativa_seguro ON tabelaLocacao_Ativa (FK_idSeguroLocacao);
CREATE INDEX idx_locacao_ativa_carro ON tabelaLocacao_Ativa (FK_idCarroLocacao);