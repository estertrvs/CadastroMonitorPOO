package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cadastroMonitores.*;

public class TelaCadastrarVaga extends TelaInformacoesDaVaga{
    public TelaCadastrarVaga(CentralDeInformacoes central, EditalMonitoria edital, Usuario user) {
        super(central, edital, "Cadastrar vaga");
        setUser(user);
        adicionarTitulo("ADICIONAR VAGA");
        adicionarLabel();
        adicionarTextField();
        adicionarBotao();
       
        setVisible(true);
    }

    public void adicionarTextField(){
        setDisciplina(new JTextField());
        getDisciplina().setBounds(225, 75, 200, 30);

        setVagasRemuneradas(new JTextField());
        getVagasRemuneradas().setBounds(225, 110, 200, 30);

        setVagasVoluntarias(new JTextField());
        getVagasVoluntarias().setBounds(225, 145, 200, 30);

        add(getDisciplina());
        add(getVagasRemuneradas());
        add(getVagasVoluntarias());
    }
    
    public void adicionarBotao(){
        JButton botaoCadastrarVaga = new JButton("CADASTRAR VAGA");
        estilizarBotao(botaoCadastrarVaga, 150, 300, 200, 40);
        botaoCadastrarVaga.addActionListener(new OuvinteBotaoCadastrarVaga());

        add(botaoCadastrarVaga);
    }

    private class OuvinteBotaoCadastrarVaga implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (conferirCamposVazios()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                return;
            }
            Vaga vaga = new Vaga(getDisciplina().getText(),Integer.parseInt(getVagasRemuneradas().getText()), Integer.parseInt(getVagasVoluntarias().getText()));
            getEdital().getVagas().add(vaga);
            int opcao = JOptionPane.showConfirmDialog(null, "Deseja cadastrar mais vagas?","Vaga cadastrada", JOptionPane.YES_NO_OPTION);
            
            if (opcao == 0){
                new TelaCadastrarVaga(getCentral(), getEdital(), getUser());
            }else{
                new TelaListaDeEditais(getCentral(),getUser());
            }
            dispose();

            try {
                new Persistencia().salvarCentral(getCentral(),"central.xml");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        
    }

}