package ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import telas.TelaInscricoesDoEdital;
import telas.TelaDetalhesDoEdital;

public class OuvinteBotaoVisualizar implements ActionListener{
    private TelaDetalhesDoEdital tela;

    public OuvinteBotaoVisualizar (TelaDetalhesDoEdital tela){
        this.tela = tela;
    }

    public void actionPerformed(ActionEvent e) {
        new TelaInscricoesDoEdital(tela.getCentral(), tela.getEdital(), tela.getUser());
        tela.dispose();
    }

}
