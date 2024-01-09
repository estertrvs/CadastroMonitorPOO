package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cadastroMonitores.*;

public class TelaEditalComResultado extends TelaPadrao{
    private EditalMonitoria edital;
    private JTable tabela;

    public TelaEditalComResultado(CentralDeInformacoes central, EditalMonitoria edital, Usuario user) {
        super(central, "Detalhes do Edital");
        this.edital = edital;
        setUser(user);
        setSize(1000,700);
        setLocationRelativeTo(null);
        adicionarTitulo();
        edital.gerarRanking();
        if (user instanceof Administrador && (!getCentral().getEditaisEncerrados().contains(edital))){
            adicionarbotaoEncerrar();
        }
        adicionarTabelaDeRankeamento();
        setVisible(true);
    }

    public void adicionarTitulo(){
        JLabel titulo = new JLabel("EDITAL COM RESULTADO");
        estilizarTitulo(titulo,0,25,1000,30);

        add(titulo);
    }

    public void adicionarTabelaDeRankeamento(){
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Disciplina");
        modelo.addColumn("Nome");
        modelo.addColumn("Matrícula");
        modelo.addColumn("Pontuação obtida");
        modelo.addColumn("Tipo de vaga");

        for (Vaga vaga: edital.getVagas()){
            if (vaga.getAlunosInscritos().size()>0){
                for (Inscricao ins: vaga.alunosSelecionados()){
                    Object[] linha = new Object[5];
                    linha[0] = vaga.getNomeDisciplina();
                    linha[1] = ins.getAluno().getNome();
                    linha[2] = ins.getAluno().getMatricula();
                    linha[3] = ins.getPontuacao();
                    linha[4] = ins.getTipoDeVaga();
                    modelo.addRow(linha);
                    
                    if (getUser() instanceof Aluno && (!getCentral().getEditaisEncerrados().contains(edital))){
                        if (ins.getAluno().equals((Aluno)getUser())){
                            adicionarBotaoDesistir();
                        }
                    }
                }
            }
        }
        
        tabela = new JTable(modelo);
        JScrollPane painelTabela = new JScrollPane(tabela);

        painelTabela.setBounds(50, 75, 900, 500);

        add(painelTabela);
    }

    public void adicionarBotaoDesistir(){
        JButton botaoDesistir = new JButton("Desistir da vaga");
        estilizarBotao(botaoDesistir, 400, 600, 200, 40);
        botaoDesistir.addActionListener(new OuvinteBotaoDesistir());

        add(botaoDesistir);
    }

    public void adicionarbotaoEncerrar() {
        JButton botaoEncerrar = new JButton("Finalizar edital");
        estilizarBotao(botaoEncerrar, 400, 600, 200, 40);
        botaoEncerrar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getCentral().getEditaisEncerrados().add(edital);
                try {
                    GeradorDeRelatorios.obterResultadoFinalDeEdital(tabela,edital);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                try {
                    new Persistencia().salvarCentral(getCentral(), "central.xml");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Edital encerrado");
                dispose();
                new TelaEditalComResultado(getCentral(), edital, getUser());
            }
        });

        add(botaoEncerrar);
    }

    public class OuvinteBotaoDesistir implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = tabela.getSelectedRow();
            if(linhaSelecionada == -1){
                JOptionPane.showMessageDialog(null, "Selecione a vaga que você quer desistir.");
            }else{
                Inscricao inscricao = edital.pesquisarInscricaoPorMatricula(String.valueOf(tabela.getValueAt(linhaSelecionada, 2)));

                if (inscricao.getAluno().equals((Aluno)getUser())){
                    edital.desistirDeVaga(inscricao);
                    try {
                        new Persistencia().salvarCentral(getCentral(), "central.xml");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    dispose();
                    new TelaEditalComResultado(getCentral(), edital, getUser());
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione a vaga para a qual você foi selecionado");
                }
            }
        }   
    }

    public EditalMonitoria getEdital() {
        return edital;
    }

    public void setEdital(EditalMonitoria edital) {
        this.edital = edital;
    }

    public JTable getTabela() {
        return tabela;
    }

    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }

}