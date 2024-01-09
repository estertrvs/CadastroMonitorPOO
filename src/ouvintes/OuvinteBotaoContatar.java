package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import cadastroMonitores.Mensageiro;
import telas.TelaInscricoesDoEdital;
import telas.TelaPadrao;
import telas.TelaTabelaAlunos;

public class OuvinteBotaoContatar implements ActionListener{
    private JTable tabela;

    public OuvinteBotaoContatar(TelaPadrao tela){
        if(tela instanceof TelaTabelaAlunos){
            tabela = ((TelaTabelaAlunos)tela).getTabela();
        }else{
            tabela = ((TelaInscricoesDoEdital)tela).getTabela();
        }
        
    }
    public void actionPerformed(ActionEvent e) {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada == -1){
            JOptionPane.showMessageDialog(null, "Selecione um aluno para contatar!");
        }else {
            String email = (String)tabela.getValueAt(linhaSelecionada, 2);
            String assunto = JOptionPane.showInputDialog(null, "Digite o assunto do email:");
            String mensagem = JOptionPane.showInputDialog(null, "Digite a mensagem que deseja enviar:");
            try {
                Mensageiro.enviarEmail(email, assunto, mensagem);
                JOptionPane.showMessageDialog(null, "Email enviado com sucesso");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
}