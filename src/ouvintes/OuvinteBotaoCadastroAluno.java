package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import cadastroMonitores.Aluno;
import cadastroMonitores.Persistencia;
import cadastroMonitores.Sexo;
import telas.TelaCadastroAluno;
import telas.TelaPrincipal;
public class OuvinteBotaoCadastroAluno implements ActionListener{
    private TelaCadastroAluno telaCadastro;

    public OuvinteBotaoCadastroAluno(TelaCadastroAluno tela){
        telaCadastro = tela;
    }

    public void actionPerformed(ActionEvent e) {

        if (telaCadastro.conferirCamposVazios() || telaCadastro.getMatricula().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }

        if(!telaCadastro.verificarSenhas()){
            JOptionPane.showMessageDialog(null, "Os campos de senha devem ser iguais.");
            return;
        }

        String matricula = telaCadastro.getMatricula().getText();
        String nome = telaCadastro.getNome().getText();
        Sexo sexo = (Sexo)telaCadastro.getSexo().getSelectedItem();
        String email = telaCadastro.getEmail().getText();
        String senha = new String(telaCadastro.getSenhaConfirmacao().getPassword());

        Aluno user = new Aluno(matricula, nome, sexo.toString(), email, senha);
        
        if (telaCadastro.getCentral().adicionarAluno(user)){
            JOptionPane.showMessageDialog(null, "Você foi cadastrado com sucesso!");
            telaCadastro.dispose();
            new TelaPrincipal(telaCadastro.getCentral(), user);
        }else{
            JOptionPane.showMessageDialog(null, "Não deu certo");
        }
        try {
            new Persistencia().salvarCentral(telaCadastro.getCentral(),"central.xml");
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

}