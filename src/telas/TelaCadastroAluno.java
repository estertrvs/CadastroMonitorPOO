package telas;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cadastroMonitores.CentralDeInformacoes;
import cadastroMonitores.Sexo;
import ouvintes.OuvinteBotaoCadastroAluno;

public class TelaCadastroAluno extends TelaCadastro {
    private JTextField matricula;
    private JComboBox<Sexo> sexo;
    
    public TelaCadastroAluno(CentralDeInformacoes central){
        super(central);
        adicionarTitulo();
        super.adicionarLabel();
        adicionarLabel();
        adicionarMenu();
        adicionarItemLogin();
        adicionarBox();
        super.adicionarTextField();
        adicionarTextField();
        adicionarBotao();
        setVisible(true);
    }

    public void adicionarLabel(){
        JLabel matricula = new JLabel("Matrícula:");
        matricula.setBounds(20, 170, 70, 20);

        JLabel sexo = new JLabel("Selecione seu sexo:");
        sexo.setBounds(20, 200, 150, 20);

        add(matricula);
        add(sexo);
    }

    public void adicionarBox(){
        sexo = new JComboBox<>(Sexo.values());
        sexo.setBounds(150, 200, 150, 20);

        add(sexo);
    }

    public void adicionarTextField(){
        matricula = new JTextField();
        matricula.setBounds(90, 170, 380, 20);

        add(matricula);
    }

    public void adicionarBotao(){
        JButton botaoCadastro = new JButton("Realizar cadastro");
        estilizarBotao(botaoCadastro, 160, 280, 150, 30);

        botaoCadastro.addActionListener(new OuvinteBotaoCadastroAluno(this));

        add(botaoCadastro);
    }

    public JTextField getMatricula() {
        return matricula;
    }

    public void setMatricula(JTextField matricula) {
        this.matricula = matricula;
    }

    public JComboBox<Sexo> getSexo() {
        return sexo;
    }

    public void setSexo(JComboBox<Sexo> sexo) {
        this.sexo = sexo;
    }
}