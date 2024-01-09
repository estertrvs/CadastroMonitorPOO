package telas;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cadastroMonitores.CentralDeInformacoes;
import cadastroMonitores.EditalMonitoria;
import cadastroMonitores.Inscricao;
import cadastroMonitores.Usuario;
import cadastroMonitores.Vaga;
import ouvintes.OuvinteBotaoContatar;

public class TelaInscricoesDoEdital extends TelaPadrao{
    private EditalMonitoria edital;
    private JTable tabela;

    public TelaInscricoesDoEdital(CentralDeInformacoes central, EditalMonitoria edital, Usuario user) {
        super(central, "Inscrições do edital");
        this.edital = edital;
        setUser(user);
        setSize(1000,700);
        setLocationRelativeTo(null);
        adicionarTitulo();
        adicionarTabela();
        adicionarBotaoContatar();
        setVisible(true);
    }

    public void adicionarTitulo(){
        JLabel titulo = new JLabel("INSCRIÇÕES");
        estilizarTitulo(titulo, 0, 25, 1000, 30);

        add(titulo);
    }
    
    public void adicionarTabela(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Aluno");
        modelo.addColumn("Matrícula");
        modelo.addColumn("Email");
        modelo.addColumn("Disciplina");

        Object[] linha = new Object[4];
        for (Vaga vaga: edital.getVagas()){
            for(Inscricao inscricao:vaga.getAlunosInscritos()){
                linha[0] = inscricao.getAluno().getNome();
                linha[1] = inscricao.getAluno().getMatricula();
                linha[2] = inscricao.getAluno().getEmail();
                linha[3] = vaga.getNomeDisciplina();
                modelo.addRow(linha);
            }
        }
        
        tabela = new JTable(modelo);
        JScrollPane painelTabela = new JScrollPane(tabela);

        painelTabela.setBounds(150, 75, 700, 400);

        add(painelTabela);
    }

    public void adicionarBotaoContatar(){
        JButton botaoContatar = new JButton("Enviar email");
        estilizarBotao(botaoContatar, 400, 500, 200, 40);
        botaoContatar.addActionListener(new OuvinteBotaoContatar(this));

        add(botaoContatar);
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