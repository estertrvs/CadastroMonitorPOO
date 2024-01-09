package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import cadastroMonitores.*;
import telas.TelaCadastrarVaga;
import telas.TelaInformacoesDoEdital;

public class OuvinteBotaoCadastrarEdital implements ActionListener{
    private TelaInformacoesDoEdital tela;

    public OuvinteBotaoCadastrarEdital(TelaInformacoesDoEdital tela){
        this.tela = tela;
    }

    public void actionPerformed(ActionEvent e) {
        if (tela.conferirCamposVazios()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }
        
        Float pesoCRE = Float.parseFloat(tela.getPesoCRE().getText());
        Float pesoNota = Float.parseFloat(tela.getPesoNota().getText());

        if (pesoCRE + pesoNota == 1) {
            EditalMonitoria edital = new EditalMonitoria(
                    tela.getNomeEdital().getText(),
                    LocalDate.parse(tela.getInicio().getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    LocalDate.parse(tela.getFim().getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    Integer.parseInt(tela.getQuantInscricoesPorAluno().getText()),
                    pesoCRE,
                    pesoNota
            );

            tela.getCentral().adicionarEdital(edital);
            try {
                new Persistencia().salvarCentral(tela.getCentral(), "central.xml");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            new TelaCadastrarVaga(tela.getCentral(), edital, tela.getUser());
            tela.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "A soma dos pesos deve ser 1!");
        }

    }
    
}