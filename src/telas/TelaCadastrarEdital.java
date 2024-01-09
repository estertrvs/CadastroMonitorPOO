package telas;

import java.text.ParseException;


import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import cadastroMonitores.CentralDeInformacoes;
import cadastroMonitores.Usuario;
import ouvintes.OuvinteBotaoCadastrarEdital;

public class TelaCadastrarEdital extends TelaInformacoesDoEdital{

    public TelaCadastrarEdital(CentralDeInformacoes central, Usuario user) {
        super(central, "Cadastrar edital de monitoria");
        setUser(user);
        adicionarTitulo("CADASTRAR EDITAL");
        adicionarLabel();
        adicionarTextField();
        adicionarBotao();
        
        setVisible(true);
    }

    public void adicionarTextField(){
        MaskFormatter mascara;
        try {
            mascara = new MaskFormatter("##/##/####");

            setInicio(new JFormattedTextField(mascara)); 
            getInicio().setBounds(200, 115, 750, 30);

            setFim(new JFormattedTextField(mascara)); 
            getFim().setBounds(250,155,700,30);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        setNomeEdital(new JTextField());
        getNomeEdital().setBounds(70, 75, 880, 30);

        setQuantInscricoesPorAluno(new JTextField());
        getQuantInscricoesPorAluno().setBounds(280,195, 670, 30);

        setPesoCRE(new JTextField());
        getPesoCRE().setBounds(120, 285, 830, 30);

        setPesoNota(new JTextField());
        getPesoNota().setBounds(120, 325, 830, 30);

        add(getNomeEdital());
        add(getInicio());
        add(getFim());
        add(getQuantInscricoesPorAluno());
        add(getPesoCRE());
        add(getPesoNota());
    }

    public void adicionarBotao(){
        JButton botaoCadastrarEdital = new JButton("CADASTRAR EDITAL");
        estilizarBotao(botaoCadastrarEdital, 400, 550, 200, 30);
        botaoCadastrarEdital.addActionListener(new OuvinteBotaoCadastrarEdital(this));

        add(botaoCadastrarEdital);
    }    
}