package telas;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cadastroMonitores.CentralDeInformacoes;

public abstract class TelaCadastro extends TelaPadrao{
    private JTextField nome;
    private JTextField email;
    private JPasswordField campoSenha;
    private JPasswordField senhaConfirmacao;
    
    public TelaCadastro(CentralDeInformacoes central){
        super(central,"Cadastro de usuário");
      
    }

    public Boolean verificarSenhas(){
        if (Arrays.equals(getCampoSenha().getPassword(),getSenhaConfirmacao().getPassword())){
            return true;
        }
        return false;
    }

    public void adicionarTitulo(){
        JLabel titulo = new JLabel("CADASTRO");
        estilizarTitulo(titulo, 0, 20, 500, 30);

        add(titulo);
    }

    public void adicionarLabel(){
        JLabel nome = new JLabel("Nome completo:");
        nome.setBounds(20, 50, 95, 20);

        JLabel email = new JLabel("Email: ");
        email.setBounds(20, 80, 50, 20);

        JLabel senha = new JLabel("Senha: ");
        senha.setBounds(20, 110, 50, 20);

        JLabel senhaConfirmacao = new JLabel("Confirme sua senha:");
        senhaConfirmacao.setBounds(20, 140, 120, 20);

        add(nome);
        add(email);
        add(senha);
        add(senhaConfirmacao);
    }

    public void adicionarTextField(){
        nome = new JTextField();
        nome.setBounds(115, 50, 355, 20);

        email = new JTextField();
        email.setBounds(115, 80, 355, 20);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(115, 110, 355, 20);

        senhaConfirmacao = new JPasswordField();
        senhaConfirmacao.setBounds(140, 140, 330, 20);

        add(nome);
        add(email);
        add(campoSenha);
        add(senhaConfirmacao);
    }

    public Boolean conferirCamposVazios() {
        JTextField[] campos = {
            getNome(),
            getEmail(),
            getSenhaConfirmacao(),
        };
        return temCamposVazios(Arrays.asList(campos));
    }

    public JTextField getNome() {
        return nome;
    }

    public void setNome(JTextField nome) {
        this.nome = nome;
    }

    public JTextField getEmail() {
        return email;
    }

    public void setEmail(JTextField email) {
        this.email = email;
    }

    public JPasswordField getSenhaConfirmacao() {
        return senhaConfirmacao;
    }

    public void setSenhaConfirmacao(JPasswordField senha) {
        this.senhaConfirmacao = senha;
    }

    public JPasswordField getCampoSenha() {
        return campoSenha;
    }

    public void setCampoSenha(JPasswordField campoSenha) {
        this.campoSenha = campoSenha;
    }

}