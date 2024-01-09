package telas;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cadastroMonitores.CentralDeInformacoes;
import ouvintes.OuvinteBotaoLogin;

public class TelaLogin extends TelaPadrao {
    private JTextField email;
    private JPasswordField senha;

    public TelaLogin(CentralDeInformacoes central) {
        super(central, "Login Aluno");

        adicionarMenu();
        adicionarItemCadastro();
        adicionarTitulo();
        adicionarLabel();
        adicionarTextField();
        adicionarBotao();
        setVisible(true);
    }

    public void adicionarTitulo() {
        JLabel titulo = new JLabel("LOGIN");
        estilizarTitulo(titulo, 0, 20, 500, 30);

        add(titulo);
    }

    public void adicionarLabel() {
        JLabel emailLabel = new JLabel("Email: ");
        JLabel senhaLabel = new JLabel("Senha: ");

        emailLabel.setBounds(20, 70, 50, 20);
        senhaLabel.setBounds(20, 100, 50, 20);

        add(emailLabel);
        add(senhaLabel);
    }

    public void adicionarTextField() {
        email = new JTextField();
        senha = new JPasswordField();

        email.setBounds(70, 70, 350, 20);
        senha.setBounds(70, 100, 350, 20);

        add(email);
        add(senha);
    }

    public void adicionarBotao() {
        JButton botaoLogin = new JButton("Entrar");
        OuvinteBotaoLogin ouvinte = new OuvinteBotaoLogin(this);
        botaoLogin.addActionListener(ouvinte);

        estilizarBotao(botaoLogin,195, 200, 100, 30);

        add(botaoLogin);
    }

    public JTextField getEmail() {
        return email;
    }

    public void setEmail(JTextField email) {
        this.email = email;
    }

    public JPasswordField getSenha() {
        return senha;
    }

    public void setSenha(JPasswordField senha) {
        this.senha = senha;
    }
}
