package telas;

import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JTextField;

import cadastroMonitores.CentralDeInformacoes;
import cadastroMonitores.EditalMonitoria;

public abstract class TelaInformacoesDaVaga extends TelaPadrao{
    private EditalMonitoria edital;
    private JTextField disciplina;
    private JTextField vagasRemuneradas;
    private JTextField vagasVoluntarias;

    public TelaInformacoesDaVaga(CentralDeInformacoes central, EditalMonitoria edital, String titulo) {
        super(central, titulo);
        this.edital = edital;
        adicionarLabel();
    }

    public void adicionarTitulo(String nome){
        JLabel titulo = new JLabel(nome);
        estilizarTitulo(titulo, 0, 25, 500, 40);

        add(titulo);
    }

    public void adicionarLabel() {
        JLabel disciplinaLabel = new JLabel("Nome da disciplina:");
        disciplinaLabel.setBounds(20, 75, 300, 30);

        JLabel vagasRemuneradasLabel = new JLabel("Quantidade de vagas remuneradas:");
        vagasRemuneradasLabel.setBounds(20, 110, 215, 30);

        JLabel vagasVoluntariasLabel = new JLabel("Quantidade de vagas voluntárias:");
        vagasVoluntariasLabel.setBounds(20, 145, 200, 30);

        add(disciplinaLabel);
        add(vagasRemuneradasLabel);
        add(vagasVoluntariasLabel);
    }

    public EditalMonitoria getEdital() {
        return edital;
    }

    public void setEdital(EditalMonitoria edital) {
        this.edital = edital;
    }

    public JTextField getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(JTextField disciplina) {
        this.disciplina = disciplina;
    }

    public JTextField getVagasRemuneradas() {
        return vagasRemuneradas;
    }

    public void setVagasRemuneradas(JTextField vagasRemuneradas) {
        this.vagasRemuneradas = vagasRemuneradas;
    }

    public JTextField getVagasVoluntarias() {
        return vagasVoluntarias;
    }

    public void setVagasVoluntarias(JTextField vagasVoluntarias) {
        this.vagasVoluntarias = vagasVoluntarias;
    }

    public Boolean conferirCamposVazios() {
        JTextField[] campos = {
                getDisciplina(),
                getVagasRemuneradas(),
                getVagasVoluntarias()
        };

        return temCamposVazios(Arrays.asList(campos));
    }

}