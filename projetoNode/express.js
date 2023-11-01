const express = require ("express");
const app = express();

const bodyParser = require("body-parser");

const rotas = require("./app/rotas/rotas");
rotas(app);

app.use(express.static('public'));

app.use(
    bodyParser.urlencoded({ //recuperar dados do formulário - interação com formulário - reflete no CMD
        extended: true,
    })
);

app.listen(3000, function(){
    console.log("Servidor no ar.")
});

module.exports = app;