const express = require('express');
const app = express();
app.use(express.urlencoded({ extended: true }));
const { conectarBanco, insertCliente, verificarEmailDuplicado, verificarCPFDuplicado} = require('./db/database');


module.exports = (app) => {
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
                senhaCliente
            };
    
            const emailDuplicado = await verificarEmailDuplicado(emailCliente);
            const cpfDuplicado = await verificarCPFDuplicado(cpfCliente);

            if (emailDuplicado) {
                return res.redirect('/cadastroCliente');
            }

            if (cpfDuplicado) {
                return res.redirect('/cadastroCliente');
            }
    
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
