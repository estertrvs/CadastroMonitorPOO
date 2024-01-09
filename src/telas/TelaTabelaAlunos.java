package telas;

import cadastroMonitores.Aluno;
import cadastroMonitores.CentralDeInformacoes;
import ouvintes.OuvinteBotaoContatar;
import ouvintes.OuvinteBotaoVerPerfil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class TelaTabelaAlunos extends TelaPadrao {
    private JTable tabela;
    private JTextField campoFiltro;

    public TelaTabelaAlunos(CentralDeInformacoes central) {
        super(central, "Lista de alunos");
        setSize(1000, 700);
        setTitle("Filtrar Alunos");
        setLocationRelativeTo(null);
        adicionarTitulo();
        adicionarTabela();
        adicionarBotaoVerPerfil();
        adicionarBotaoContatar();
        adicionarCampoFiltro();
        adicionarBotaoFiltrar();
        adicionarBotaoVerInscricoes();
        setVisible(true);
    }

    public void adicionarTitulo(){
        JLabel titulo = new JLabel("LISTA DE ALUNOS");
        estilizarTitulo(titulo, 0, 25, 1000, 30);

        add(titulo);
    }

    private void adicionarTabela() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Matrícula");
        modelo.addColumn("Nome");
        modelo.addColumn("Email");
        modelo.addColumn("Sexo");

        List<Aluno> todosOsAlunos = getCentral().getTodosOsAlunos();

        for (Aluno aluno : todosOsAlunos) {
            Object[] linha = new Object[4];
            linha[0] = aluno.getMatricula();
            linha[1] = aluno.getNome();
            linha[2] = aluno.getEmail();
            linha[3] = aluno.getSexo();

            modelo.addRow(linha);
        }

        tabela = new JTable(modelo);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setDefaultEditor(Object.class, null);
        JScrollPane painelTabela = new JScrollPane(tabela);

        painelTabela.setBounds(50, 110, 900, 450);

        add(painelTabela);
    }

    private void adicionarBotaoVerPerfil() {
        JButton botaoVerPerfil = new JButton("Ver perfil");
        estilizarBotao(botaoVerPerfil, 150, 600, 200, 40);
        botaoVerPerfil.addActionListener(new OuvinteBotaoVerPerfil(this));

        add(botaoVerPerfil);
    }

    public void adicionarBotaoContatar(){
        JButton botaoContatar = new JButton("Enviar email");
        estilizarBotao(botaoContatar, 400, 600, 200, 40);
        botaoContatar.addActionListener(new OuvinteBotaoContatar(this));

        add(botaoContatar);
    }

    public void adicionarBotaoVerInscricoes(){
        JButton botaoVerInscricoes = new JButton("Ver inscrições");
        estilizarBotao(botaoVerInscricoes, 650, 600, 200, 40);
        botaoVerInscricoes.addActionListener(new OuvinteBotaoVerInscricoes());

        add(botaoVerInscricoes);
    }

    private class OuvinteBotaoVerInscricoes implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = tabela.getSelectedRow();
            if(linhaSelecionada == -1){
                JOptionPane.showMessageDialog(null, "Selecione um aluno para visualizar suas inscrições");
            }else{
                Aluno aluno = getCentral().recuperarAlunoPorMatricula((String)tabela.getValueAt(linhaSelecionada, 0));
                new TelaTabelaInscricoesDoAluno(getCentral(), aluno);
            }
        }
        
    }

    private void adicionarCampoFiltro() {
        campoFiltro = new JTextField();
        campoFiltro.setBounds(50, 75, 200, 30);
        add(campoFiltro);
    }

    private void adicionarBotaoFiltrar() {
        JButton botaoFiltrar = new JButton("Filtrar");
        estilizarBotao(botaoFiltrar, 260, 75, 80, 30);
        botaoFiltrar.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                filtrarAlunosPorNome(campoFiltro.getText());
            }
        });
        add(botaoFiltrar);
    }

    private void filtrarAlunosPorNome(String nomeFiltro) {
        DefaultTableModel modeloAtual = (DefaultTableModel) tabela.getModel();
        modeloAtual.setRowCount(0);

        List<Aluno> todosOsAlunos = getCentral().getTodosOsAlunos();

        List<Aluno> alunosFiltrados = todosOsAlunos.stream()
                .filter(aluno -> aluno.getNome().toLowerCase().contains(nomeFiltro.toLowerCase()))
                .collect(Collectors.toList());

        for (Aluno aluno : alunosFiltrados) {
            Object[] linha = new Object[4];
            linha[0] = aluno.getMatricula();
            linha[1] = aluno.getNome();
            linha[2] = aluno.getEmail();
            linha[3] = aluno.getSexo();

            modeloAtual.addRow(linha);
        }
    }
    public JTable getTabela() {
        return tabela;
    }
}
