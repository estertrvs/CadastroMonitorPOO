package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import cadastroMonitores.Administrador;
import cadastroMonitores.Persistencia;
import telas.*;

public class OuvinteBotaoCadastroADM implements ActionListener{
    private TelaCadastro tela;

    public OuvinteBotaoCadastroADM(TelaCadastro tela){
        this.tela = tela;
    }
    
    public void actionPerformed(ActionEvent e) {

        if (tela.conferirCamposVazios()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }

        if(!tela.verificarSenhas()){
            JOptionPane.showMessageDialog(null, "Os campos de senha devem ser iguais.");
            return;
        }

        Administrador user = new Administrador(tela.getNome().getText(),tela.getEmail().getText(),new String(tela.getSenhaConfirmacao().getPassword()));

        tela.getCentral().setAdmin(user);
        JOptionPane.showMessageDialog(null, "Administrador cadastrado com sucesso.");
        tela.dispose();
        new TelaPrincipal(tela.getCentral(), user);
        
        try {
            new Persistencia().salvarCentral(tela.getCentral(),"central.xml");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}