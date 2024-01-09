package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cadastroMonitores.*;

public class TelaPerfilUsuario extends TelaPadrao{
    private JTextField nome;
    private JTextField email;
    private JPasswordField senha;
    private JPasswordField senhaConfirmacao;
    
    public TelaPerfilUsuario(CentralDeInformacoes central, Usuario user) {
        super(central, "Seu perfil", user);
        adicionarTitulo();
        adicionarLabel();
        adicionarTextField();
        adicionarBotaoEditar();
        setVisible(true);
    }

    public void adicionarTitulo(){
        JLabel titulo = new JLabel("PERFIL");
        estilizarTitulo(titulo, 0, 25, 500, 30);

        add(titulo);
    }

    public void adicionarLabel(){
        JLabel nome = new JLabel("Nome de usuário:");
        nome.setBounds(20, 70, 130, 20);
        
        JLabel email = new JLabel("Email:");
        email.setBounds(20, 100, 50, 20);
        
        add(nome);
        add(email);
    }

    public void adicionarLabelSenhas(){
        JLabel senha = new JLabel("Nova senha:");
        senha.setBounds(20, 130, 100, 20);
        
        JLabel senhaConfirmacao = new JLabel("Confirmação da senha:");
        senhaConfirmacao.setBounds(20, 160, 150, 20);
        
        add(senha);
        add(senhaConfirmacao);
    }
    
    public void adicionarTextField () {
        nome = new JTextField(getUser().getNome());
        nome.setBounds(150, 70, 320, 20);
        nome.setEditable(false);
        
        email = new JTextField(getUser().getEmail());
        email.setBounds(65, 100, 400, 20);
        email.setEditable(false);
        
        add(nome);
        add(email);
    }

    public void adicionarTextFieldSenhas(){
        senha = new JPasswordField();
        senha.setBounds(150, 130, 320, 20);

        senhaConfirmacao = new JPasswordField();
        senhaConfirmacao.setBounds(170, 160, 300, 20);
        
        add(senha);
        add(senhaConfirmacao);
    }

    public void adicionarBotaoEditar(){
        JButton botaoEditar = new JButton("Editar perfil");
        estilizarBotao(botaoEditar, 150, 350, 200, 30);
        
        botaoEditar.addActionListener(new OuvinteBotaoEditar());

        add(botaoEditar);
    }

    public void adicionarBotaoSalvar(){
        JButton botaoSalvar = new JButton("Salvar");
        estilizarBotao(botaoSalvar, 150, 350, 200, 30);
        botaoSalvar.addActionListener(new OuvinteBotaoSalvar());

        add(botaoSalvar);
    }

    private class OuvinteBotaoEditar implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String senha = JOptionPane.showInputDialog("Digite sua senha:");
            if(senha.equals(getUser().getSenha()) || senha.equals(getCentral().getAdmin().getSenha())){
                remove((JButton)e.getSource());
                repaint();

                adicionarBotaoSalvar();
                
                adicionarLabelSenhas();
                adicionarTextFieldSenhas();

                nome.setEditable(true);
                email.setEditable(true);                
            }else{
                JOptionPane.showMessageDialog(null, "Senha incorreta.");
            }
        }
    }
    
    private class OuvinteBotaoSalvar implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (conferirCamposVazios()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                return;
            }
            if (!Arrays.equals(getSenha().getPassword(),getSenhaConfirmacao().getPassword())){
                JOptionPane.showMessageDialog(null, "Os campos de senha devem ser iguais.");
                return;
            }
            getUser().setNome(getNome().getText());
            getUser().setEmail(getEmail().getText());
            getUser().setSenha(new String(getSenhaConfirmacao().getPassword()));
            try {
                new Persistencia().salvarCentral(getCentral(), "central.xml");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            dispose();
            new TelaPerfilUsuario(getCentral(), getUser());
        }
    }
    
    private Boolean conferirCamposVazios() {
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

    public JPasswordField getSenha() {
        return senha;
    }

    public void setSenha(JPasswordField senha) {
        this.senha = senha;
    }

    public JPasswordField getSenhaConfirmacao() {
        return senhaConfirmacao;
    }
    
    public void setSenhaConfirmacao(JPasswordField senhaConfirmacao) {
        this.senhaConfirmacao = senhaConfirmacao;
    }

}