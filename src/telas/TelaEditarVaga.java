package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cadastroMonitores.CentralDeInformacoes;
import cadastroMonitores.EditalMonitoria;
import cadastroMonitores.Persistencia;
import cadastroMonitores.Vaga;

public class TelaEditarVaga extends TelaInformacoesDaVaga{
    private Vaga vaga;

    public TelaEditarVaga(CentralDeInformacoes central, EditalMonitoria edital, Vaga vaga) {
        super(central, edital, "Editar vaga");
        this.vaga = vaga;
        adicionarTitulo("EDITAR VAGA");
        adicionarLabel();
        adicionarTextField();
        adicionarBotao();
        ImageIcon icone = new ImageIcon("Icones/icone.png");
        setIconImage(icone.getImage());
        setVisible(true);
    }

    public void adicionarTextField(){
        setDisciplina(new JTextField(vaga.getNomeDisciplina()));
        getDisciplina().setBounds(225, 75, 200, 30);

        setVagasRemuneradas(new JTextField(String.valueOf(vaga.getQuantVagasRemuneradas())));
        getVagasRemuneradas().setBounds(225, 110, 200, 30);

        setVagasVoluntarias(new JTextField(String.valueOf(vaga.getQuantVagasVoluntarias())));
        getVagasVoluntarias().setBounds(225, 145, 200, 30);

        add(getDisciplina());
        add(getVagasRemuneradas());
        add(getVagasVoluntarias());
    }

    public void adicionarBotao(){
        JButton botaoSalvar = new JButton("Salvar alterações");
        estilizarBotao(botaoSalvar, 150, 300, 200, 40);
        botaoSalvar.addActionListener(new OuvinteBotaoSalvar());

        add(botaoSalvar);
    }
    public class OuvinteBotaoSalvar implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            try {

                if (conferirCamposVazios()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                    return;
                }
            } catch(Exception a){
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            }

            vaga.setNomeDisciplina(getDisciplina().getText());
            vaga.setQuantVagasRemuneradas(Integer.parseInt(getVagasRemuneradas().getText()));
            vaga.setQuantVagasVoluntarias(Integer.parseInt(getVagasVoluntarias().getText()));

            try {
                new Persistencia().salvarCentral(getCentral(),"central.xml");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            dispose();
        }

    }

    
}