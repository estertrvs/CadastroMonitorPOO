package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import cadastroMonitores.*;
import telas.TelaDetalhesDoEdital;

public class OuvinteBotaoInscreverEmMonitoria implements ActionListener{
    private TelaDetalhesDoEdital tela;

    public OuvinteBotaoInscreverEmMonitoria(TelaDetalhesDoEdital tela){
        this.tela = tela;
    }

    public void actionPerformed(ActionEvent e) {
        int linhaSelecionada = tela.getTabelaVagas().getSelectedRow();
        if(linhaSelecionada == -1){
            JOptionPane.showMessageDialog(null, "Selecione um dos editais para detalhar.");
        }else{
            float CRE = Float.parseFloat(JOptionPane.showInputDialog(null, "Informe seu CRE:"));
            float media = Float.parseFloat(JOptionPane.showInputDialog(null, "Digite sua média:"));

            if (tela.getEdital().inscrever(tela.getCentral(),(Aluno)tela.getUser(),(String)tela.getTabelaVagas().getValueAt(linhaSelecionada,0), CRE, media)) {
                try {
                    //Mensageiro.enviarEmail(tela.getUser().getEmail(), "Confirmação de inscrição em monitoria", "Sua inscrição foi realizada com sucesso.");
                    JOptionPane.showMessageDialog(null, "Inscrição realizada com sucesso");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        try{
            Persistencia p = new Persistencia();
            p.salvarCentral(tela.getCentral(), "central.xml");
        }catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}