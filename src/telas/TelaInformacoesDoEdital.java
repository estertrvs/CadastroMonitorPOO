package telas;

import java.awt.Color;
import java.util.Arrays;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cadastroMonitores.CentralDeInformacoes;

public class TelaInformacoesDoEdital extends TelaPadrao{
    private JTextField nomeEdital;
    private JFormattedTextField inicio;
    private JFormattedTextField fim;
    private JTextField quantInscricoesPorAluno;
    private JTextField pesoCRE;
    private JTextField pesoNota;

    public TelaInformacoesDoEdital(CentralDeInformacoes central, String titulo) {
        super(central, titulo);
        setSize(1000,700);
        setLocationRelativeTo(null);
    }

    public void adicionarTitulo(String nome) {
        JLabel titulo = new JLabel(nome);
        estilizarTitulo(titulo, 0, 25, 1000, 40);

        add(titulo);
    }

    public void adicionarLabel(){
        JLabel nomeEdital = new JLabel("Edital:");
        nomeEdital.setBounds(20, 75, 50, 30);

        JLabel inicio = new JLabel("Data de início das incrições:");
        inicio.setBounds(20, 115, 180, 30);

        JLabel fim = new JLabel("Data de encerramento das inscrições:");
        fim.setBounds(20,155,220,30);

        JLabel quantInscricoesPorAluno = new JLabel("Quantidade máxima de inscrições por aluno:");
        quantInscricoesPorAluno.setBounds(20,195, 300, 30);

        JLabel info = new JLabel("OBS: Os pesos devem ser até 1 e a soma dos dois deve ser 1");
        info.setBounds(200, 245, 500, 30);
        info.setForeground(Color.RED);

        JLabel pesoCRE = new JLabel("Peso do CRE:");
        pesoCRE.setBounds(20, 285, 100, 30);

        JLabel pesoNota = new JLabel("Peso da nota:");
        pesoNota.setBounds(20, 325, 100, 30);

        add(nomeEdital);
        add(inicio);
        add(fim);
        add(quantInscricoesPorAluno);
        add(info);
        add(pesoCRE);
        add(pesoNota);
    }

    public Boolean conferirCamposVazios() {
        JTextField[] campos = {
                getNomeEdital(),
                getInicio(),
                getFim(),
                getQuantInscricoesPorAluno(),
                getPesoCRE(),
                getPesoNota()
        };

        return temCamposVazios(Arrays.asList(campos));
    }

    public void adicionarTextField(){
        
    }

    public JTextField getNomeEdital() {
        return nomeEdital;
    }

    public void setNomeEdital(JTextField nomeEdital) {
        this.nomeEdital = nomeEdital;
    }

    public JFormattedTextField getInicio() {
        return inicio;
    }

    public void setInicio(JFormattedTextField inicio) {
        this.inicio = inicio;
    }

    public JFormattedTextField getFim() {
        return fim;
    }

    public void setFim(JFormattedTextField fim) {
        this.fim = fim;
    }

    public JTextField getQuantInscricoesPorAluno() {
        return quantInscricoesPorAluno;
    }

    public void setQuantInscricoesPorAluno(JTextField quantInscricoesPorAluno) {
        this.quantInscricoesPorAluno = quantInscricoesPorAluno;
    }

    public JTextField getPesoCRE() {
        return pesoCRE;
    }

    public void setPesoCRE(JTextField pesoCRE) {
        this.pesoCRE = pesoCRE;
    }

    public JTextField getPesoNota() {
        return pesoNota;
    }

    public void setPesoNota(JTextField pesoNota) {
        this.pesoNota = pesoNota;
    }
}