package telas;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cadastroMonitores.Aluno;
import cadastroMonitores.CentralDeInformacoes;
import cadastroMonitores.EditalMonitoria;
import cadastroMonitores.Inscricao;
import cadastroMonitores.Vaga;

public class TelaTabelaInscricoesDoAluno extends TelaPadrao{
    private Aluno aluno;

    public TelaTabelaInscricoesDoAluno(CentralDeInformacoes central, Aluno aluno) {
        super(central, "Inscrições do aluno");
        this.aluno = aluno;
        setSize(1000, 700);
        setLocationRelativeTo(null);
        adicionarTitulo();
        adicionarTabelaInscricoes();
        setVisible(true);
    }

    public void adicionarTitulo(){
        JLabel titulo = new JLabel(aluno.getNome().toUpperCase());
        estilizarTitulo(titulo, 0, 25, 1000, 40);

        add(titulo);
    }

    public void adicionarTabelaInscricoes(){
        DefaultTableModel modelo = new DefaultTableModel() {  
        public boolean isCellEditable(int row, int column) {
            return false;
        }};

        modelo.addColumn("Edital");
        modelo.addColumn("Disciplina");
        modelo.addColumn("Pontuação obtida");
        modelo.addColumn("Tipo de vaga");

        for (Inscricao inscricao : recuperarInscricoes()){
            Object[] linha = new Object[4];
            linha[0] = inscricao.getEdital().getId();
            for(Vaga vaga : inscricao.getEdital().getVagas()){
                if(vaga.getAlunosInscritos().contains(inscricao)){
                    linha[1] = vaga.getNomeDisciplina();
                }
            }
            linha[2] = inscricao.getPontuacao();
            linha[3] = inscricao.getTipoDeVaga();

            modelo.addRow(linha);
        }
        
        JTable tabela = new JTable(modelo);
        tabela.getTableHeader().setReorderingAllowed(false);
        JScrollPane painelTabela = new JScrollPane(tabela);

        painelTabela.setBounds(150, 75, 700, 500);

        add(painelTabela);
    }

    public ArrayList<Inscricao> recuperarInscricoes(){
        ArrayList<Inscricao> inscricoes = new ArrayList<>();
        for(EditalMonitoria em : getCentral().getEditaisEncerrados()){
            for (Inscricao inscricao : getCentral().recuperarIncricoesDeUmAlunoEmUmEdital(aluno.getMatricula(),em.getId())){
                if(inscricao.isSelecionado() && inscricao.getAluno().equals(aluno)){
                    inscricoes.add(inscricao);
                }
            }
        }
        return inscricoes;
    }
    
}