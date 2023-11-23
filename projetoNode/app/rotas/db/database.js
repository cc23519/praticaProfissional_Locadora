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

module.exports = { conectarBanco};