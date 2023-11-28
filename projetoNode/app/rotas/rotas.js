const express = require('express');
const app = express();
const bcrypt = require('bcrypt');
const session = require('express-session');
const saltRounds = 10;
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
const { conectarBanco, insertCliente, verificarEmailDuplicado, verificarCPFDuplicado, loginCliente, SelectCarros, SelectSeguros, finalizarLocacao} = require('./db/database');

module.exports = (app) => {
    app.use(express.json());
    app.use(session({
        secret: 'seuSegredo',
        resave: false,
        saveUninitialized: true,
    }));

    app.use((req, res, next) => {
        res.header("Access-Control-Allow-Origin", "*");
        next();
    });

    app.get('/', async (req, res) => {
        let sql;

        try {
            await conectarBanco(); 
        } catch (error) {
            console.error('Erro ao buscar dados:', error);
        } finally {
            res.render("loginUsuario.ejs");
        }
    });

    app.get("/cadastroCliente", function(req, res) {
        res.render("cadastroNovoCliente.ejs");
    });

    app.post('/cadastro', async (req, res) => {
        try {
            await conectarBanco();
    
            const {
                nomeCliente,
                sobrenomeCliente,
                cpfCliente,
                tipoContatoCliente,
                dddTelefone,
                numeroTelefone,
                enderecoCliente,
                numeroEnderecoCliente,
                bairroCliente,
                cidadeCliente,
                estadoCliente,
                cepCliente,
                complementoCliente,
                cnhCliente,
                emailCliente,
                senhaCliente
            } = req.body;
    
            const emailDuplicado = await verificarEmailDuplicado(emailCliente);
            const cpfDuplicado = await verificarCPFDuplicado(cpfCliente);
    
            if (emailDuplicado) {
                console.log("Email duplicado");
                return res.redirect('/cadastroCliente');
            }
    
            if (cpfDuplicado) {
                console.log("CPF duplicado");
                return res.redirect('/cadastroCliente');
            }

            const hashedPassword = await bcrypt.hash(senhaCliente, saltRounds);
    
            const cliente = {
                nomeCliente,
                sobrenomeCliente,
                cpfCliente,
                tipoContatoCliente,
                dddTelefone,
                numeroTelefone,
                enderecoCliente,
                numeroEnderecoCliente,
                bairroCliente,
                cidadeCliente,
                estadoCliente,
                cepCliente,
                complementoCliente,
                cnhCliente,
                emailCliente,
                senhaCliente: hashedPassword
            };
    
            const result = await insertCliente(cliente);
            res.redirect('/login');
        } catch (error) {
            console.error('Erro ao cadastrar cliente:', error);
            res.status(500).send('Erro interno do servidor');
        }
    });

    app.set('view engine', 'ejs');
    app.use(express.static('arquivos'));

    app.get("/login", function(req, res) {
        res.render("loginUsuario.ejs");
    });

    app.post('/loginClientes', async (req, res) => {
        try {
            const { email, senha } = req.body;
    
            const cliente = await loginCliente(email);
    
            if (!cliente) {
                console.log("Cliente não cadastrado");
                return res.redirect('/login');
            }
    
            const senhaCorreta = await bcrypt.compare(senha, cliente.senhaCliente);
            console.log('Antes de definir clienteId na sessão:', req.session);
            if (senhaCorreta) {
                req.session.clienteId = cliente.FK_idClienteCred;
                console.log('Antes de definir clienteId na sessão:', req.session);
                res.redirect('/dashboard');
            } else {
                console.log("Senha Incorreta");
                res.redirect('/login');
            }
        } catch (error) {
            console.error('Erro ao realizar login:', error);
            res.status(500).send('Erro interno do servidor');
        }
    });


    app.get('/dashboard', async (req, res) => {
        try {
          if (req.session.clienteId) {
            const carros = await SelectCarros();
            const seguros = await SelectSeguros();
            const clienteId = req.session.clienteId;
            res.render('escolhaDoCarro.ejs', { carros, seguros });
          } else {
            res.redirect('/login');
          }
        } catch (error) {
          console.error('Erro na rota /dashboard:', error.message || error);
          res.status(500).send('Erro interno do servidor');
        }
      });

      app.post('/processar-dados-locacao', async (req, res) => {
        try {
          console.log('Dados recebidos:', req.body);
          const clienteId = req.session.clienteId;
          const { diaRetirada, diaDevolucao, selectValorDiaria, selectSeguros, total } = req.body;
      
          console.log(clienteId, selectSeguros, selectValorDiaria, diaRetirada, diaDevolucao, total);
      
          const sucesso = await finalizarLocacao(clienteId, selectSeguros, selectValorDiaria, diaRetirada, diaDevolucao, total);
      
          if (sucesso) {
            res.json({ mensagem: 'Locação finalizada com sucesso.' });
          } else {
            res.status(500).json({ erro: 'Erro ao finalizar locação.' });
          }
        } catch (error) {
          console.error('Erro ao processar dados de locação:', error);
          res.status(500).send('Erro interno do servidor ao processar dados de locação');
        }
      });
      
      

    app.get("/esqueciMinhaSenha", function(req, res) {
        res.render("esqueciMinhaSenha.ejs");
    });

    app.get("/emailTrocaDeSenha", function(req, res) {
        res.render("emailTrocaDeSenha.ejs");
    });

    app.get("/redefinirSenha", function(req, res) {
        res.render("redefinirSenha.ejs");
    });

    app.get("/escolhaDoCarro", function(req, res) {
        res.render("escolhaDoCarro.ejs");
    });

    app.get("/aluguelCompleto", function(req, res) {
        res.render("aluguelCompleto.ejs");
    });
};
