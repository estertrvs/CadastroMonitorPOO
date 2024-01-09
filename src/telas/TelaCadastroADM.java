package telas;

import javax.swing.JButton;

import cadastroMonitores.CentralDeInformacoes;
import ouvintes.OuvinteBotaoCadastroADM;

public class TelaCadastroADM extends TelaCadastro{

    public TelaCadastroADM(CentralDeInformacoes central){
        super(central);
        adicionarMenu();
        adicionarItemLogin();
        adicionarTitulo();
        adicionarLabel();
        adicionarTextField();
        adicionarBotao();
        setVisible(true);
    }

    public void adicionarBotao(){
        JButton botaoCadastroADM = new JButton("Realizar cadastro");
        estilizarBotao(botaoCadastroADM, 160, 280, 150, 30);

        botaoCadastroADM.addActionListener(new OuvinteBotaoCadastroADM(this));

        add(botaoCadastroADM);
    }
}