const { conectarBanco, insertCliente, verificarEmailDuplicado, verificarCPFDuplicado} = require('../app/rotas/db/database');

function mostrarNotificacao(message) {
    const notificacao = document.createElement('div');
    notificacao.className = 'notification';
    notificacao.textContent = message;

    document.body.appendChild(notificacao);
}

function removerNotificacao() {
    const notificacao = document.querySelector('.notification');
    if (notificacao) {
        notificacao.remove();
    }
}

async function cadastrar(){
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
            mostrarNotificacao("Já há um cadastro para o Email digitado.");
        }

        if (cpfDuplicado) {
            mostrarNotificacao("Já há um cadastro para o CPF digitado.");
        }

        const result = await insertCliente(cliente);
        res.redirect('/login');
    } catch (error) {
        mostrarNotificacao("Erro ao cadastrar cliente: ", error);
        console.error('Erro ao cadastrar cliente:', error);
        res.status(500).send('Erro interno do servidor');
    }
};