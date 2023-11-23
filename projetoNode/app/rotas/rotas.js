const express = require('express');
const app = express();
const { conectarBanco, queryDatabase } = require('./db/database');

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

    app.get("/cadastroCliente", function(req, res) {
        res.render("cadastroNovoCliente.ejs");
    });

    app.get("/escolhaDoCarro", function(req, res) {
        res.render("escolhaDoCarro.ejs");
    });

    app.get("/aluguelCompleto", function(req, res) {
        res.render("aluguelCompleto.ejs");
    });
};
