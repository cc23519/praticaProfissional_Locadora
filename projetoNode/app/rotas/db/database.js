const sql = require('mssql');

const config = {
  user: 'BD23519',
  password: 'BD23519',
  server: 'regulus.cotuca.unicamp.br',
  database: 'BD23519',
  options: {
    enableArithAbort: true,
    encrypt: false,
  },
};

async function conectarBanco() {
  try {
    await sql.connect(config);
    console.log('Conexão ao banco de dados MSSQL estabelecida com sucesso!');
  } catch (err) {
    console.error('Erro de conexão ao banco de dados MSSQL:', err);
    throw err;
  }
}

async function verificarEmailDuplicado(email) {
  try {
      await sql.connect(config);

      const result = await sql.query`SELECT COUNT(*) as count FROM esquemaLocadora.tabelaClienteCredenciais WHERE emailCliente = ${email}`;

      const rowCount = result.recordset[0].count;

      return rowCount > 0;
  } catch (error) {
      console.error('Erro ao verificar e-mail duplicado:', error.message);
      throw error;
  } finally {
  }
}

async function verificarCPFDuplicado(cpf) {
  try {
      await sql.connect(config);

      const result = await sql.query`SELECT COUNT(*) as count FROM esquemaLocadora.tabelaCliente WHERE cpfCliente = ${cpf}`;

      const rowCount = result.recordset[0].count;

      return rowCount > 0;
  } catch (error) {
      console.error('Já existe um CPF cadastrado:', error.message);
      throw error;
  } finally {
  }
}

async function insertCliente(cliente) {
  try {
    const result = await sql.query`
      INSERT INTO esquemaLocadora.tabelaCliente
      (nomeCliente, sobrenomeCliente, cpfCliente)
      VALUES (${cliente.nomeCliente}, ${cliente.sobrenomeCliente}, ${cliente.cpfCliente});
      
      INSERT INTO esquemaLocadora.tabelaClienteContatos
      (tipoTelefone, dddTelefone, numeroTelefone)
      VALUES (${cliente.tipoContatoCliente}, ${cliente.dddTelefone}, ${cliente.numeroTelefone});
      
      INSERT INTO esquemaLocadora.tabelaClienteEndereco
      (ruaCliente, numeroCliente, bairroCliente, cidadeCliente, estadoCliente, cepCliente, complemento)
      VALUES (${cliente.enderecoCliente}, ${cliente.numeroEnderecoCliente}, ${cliente.bairroCliente}, ${cliente.cidadeCliente}, ${cliente.estadoCliente}, ${cliente.cepCliente}, ${cliente.complementoCliente});
      
      INSERT INTO esquemaLocadora.tabelaClienteCredenciais
      (emailCliente, senhaCliente)
      VALUES (${cliente.emailCliente}, ${cliente.senhaCliente});
    `;
    return result;
  } catch (error) {
    console.error('Erro ao inserir cliente:', error);
    throw error;
  }
}

module.exports = { conectarBanco, insertCliente, verificarEmailDuplicado,verificarCPFDuplicado};