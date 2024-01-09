package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cadastroMonitores.CentralDeInformacoes;
import cadastroMonitores.Usuario;
import telas.TelaLogin;
import telas.TelaPrincipal;

public class OuvinteBotaoLogin implements ActionListener{
    private TelaLogin tela;

    public OuvinteBotaoLogin(TelaLogin tela){
        this.tela = tela;
    } 

    public void actionPerformed(ActionEvent a){
        if (conferirCamposVazios()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }
        CentralDeInformacoes central = tela.getCentral();
        Usuario user = central.login(tela.getEmail().getText(), new String(tela.getSenha().getPassword()));
        if (user != null){
            tela.setUser(user);
            new TelaPrincipal(central, tela.getUser());
            tela.dispose();
        }else{
            JOptionPane.showMessageDialog(null,"Email ou senha incorretos");
        }
    }

    private Boolean conferirCamposVazios() {
        JTextField[] campos = {
                tela.getEmail(),
                tela.getSenha(),
        };

        return tela.temCamposVazios(Arrays.asList(campos));
    }
    
}