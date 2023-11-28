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

    console.log(rowCount);
    return rowCount;
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

    console.log(rowCount);
    return rowCount;
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


async function loginCliente(email) {
  try {
    await sql.connect(config);

    const result = await sql.query`SELECT * FROM esquemaLocadora.tabelaClienteCredenciais WHERE emailCliente = ${email}`;

    if (result.recordset.length > 0) {
      return result.recordset[0];
    } else {
      return null;
    }
  } catch (error) {
    console.error('Erro ao buscar cliente por e-mail:', error);
    throw error;
  } finally {
    await sql.close();
  }
}

async function autenticacao(email, senha) {
  try {
    const clienteEncontrado = await loginCliente(email);

    if (clienteEncontrado) {
      const senhaCorreta = await bcrypt.compare(senha, clienteEncontrado.senhaCliente);

      if (senhaCorreta) {
        console.log('Cliente autenticado:', clienteEncontrado);
      } else {
        console.log('Senha incorreta');
      }
    } else {
      console.log('Cliente não encontrado');
    }
  } catch (error) {
    console.error('Erro durante a autenticação:', error);
  }
}

async function SelectCarros() {
  try {
    await sql.connect(config);
    const result = await sql.query('SELECT * FROM esquemaLocadora.tabelaCarro');
    console.log(result);
    return result.recordset;
  } catch (error) {
    console.error('Erro ao obter dados dos carros:', error.message || error);
    throw error;
  } finally {
  }
}

async function SelectSeguros() {
  try {
    await sql.connect(config);
    const result = await sql.query('SELECT * FROM esquemaLocadora.tabelaSeguros');
    console.log(result);
    return result.recordset;
  } catch (error) {
    console.error('Erro ao obter dados dos carros:', error.message || error);
    throw error;
  } finally {
  }
}

async function finalizarLocacao(clienteId, seguroId, carroId, dataRetirada, dataDevolucao, valorTotal) {
  try {
    await sql.connect(config);

    const query = `
      INSERT INTO esquemaLocadora.tabelaLocacao_Ativas (clienteId, seguroId, carroId, dataRetirada, dataDevolucao, valorTotal, status)
      VALUES (@clienteId, @seguroId, @carroId, @dataRetirada, @dataDevolucao, @valorTotal, 'ativa')
    `;

    const result = await sql.query(query, {
      clienteId: { type: sql.Int, value: clienteId },
      seguroId: { type: sql.Int, value: seguroId },
      carroId: { type: sql.Int, value: carroId },
      dataRetirada: { type: sql.Date, value: dataRetirada },
      dataDevolucao: { type: sql.Date, value: dataDevolucao },
      valorTotal: { type: sql.Float, value: valorTotal },
    });

    console.log('Locação finalizada com sucesso.');
    return true;
  } catch (error) {
    console.error('Erro ao finalizar locação:', error.message || error);
    throw error;
  } finally {
    if (sql && sql.close) {
      await sql.close();
    }
  }
}



module.exports = { conectarBanco, insertCliente, verificarEmailDuplicado, verificarCPFDuplicado, loginCliente, autenticacao, SelectCarros, SelectSeguros, finalizarLocacao };