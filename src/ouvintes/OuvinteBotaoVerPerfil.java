package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;

import telas.*;

public class OuvinteBotaoVerPerfil implements ActionListener{

    private TelaPadrao tela;

    public OuvinteBotaoVerPerfil(TelaPadrao tela){
        this.tela = tela;
    }
    
    public void actionPerformed(ActionEvent e) {
        if (tela instanceof TelaPrincipal){
            new TelaPerfilUsuario(tela.getCentral(), tela.getUser());
        } else{
            TelaTabelaAlunos novaTela = (TelaTabelaAlunos)tela;
            int linhaSelecionada = novaTela.getTabela().getSelectedRow();

            if (linhaSelecionada == -1){
                    JOptionPane.showMessageDialog(null, "Selecione um dos perfis para visualizar.");
            }else{
                new TelaPerfilUsuario(novaTela.getCentral(), novaTela.getCentral().getTodosOsAlunos().get(linhaSelecionada));
            }
        }
        
    }
    
}