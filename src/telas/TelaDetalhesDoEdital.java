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
import ouvintes.OuvinteBotaoInscreverEmMonitoria;
import ouvintes.OuvinteBotaoVisualizar;

public class TelaDetalhesDoEdital extends TelaPadrao{
    private EditalMonitoria edital;
    private JTable tabelaVagas;

    public TelaDetalhesDoEdital(CentralDeInformacoes central, EditalMonitoria edital, Usuario user) {
        super(central, "Detalhes desse edital");
        this.edital = edital;
        setUser(user);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        adicionarTitulo();
        adicionarTabelaVagas();
        if (user instanceof Administrador){
            adicionarBotaoVisualizarInscricoes();
            adicionarBotaoEditarEdital();
            adicionarBotaoClonar();
            adicionarBotaoEditarVaga();
            adicionarBotaoAddVaga();
            if (edital.status().equals("encerradas.")){
                adicionarBotaoGerarResultado();
            }else{
                adicionarBotaoEncerrar();
            }
        } else if (edital.status().equals("em andamento.")) {
            adicionarBotaoInscrever();
        }
        setVisible(true);
    }

    public void adicionarTitulo(){
        JLabel titulo = new JLabel(edital.getEdital());
        estilizarTitulo(titulo, 0, 25, 1000, 30);

        add(titulo);
    }

    public void adicionarTabelaVagas() {
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("Disciplina:");
        modelo.addColumn("Quantidade de vagas remuneradas:");
        modelo.addColumn("Quantidade de vagas Voluntárias:");

        for (Vaga vaga : edital.getVagas()) {
            Object[] linha = new Object[3];
            linha[0] = vaga.getNomeDisciplina();
            linha[1] = vaga.getQuantVagasRemuneradas();
            linha[2] = vaga.getQuantVagasVoluntarias();

            modelo.addRow(linha);
        }
        tabelaVagas = new JTable(modelo);
        JScrollPane painelTabela = new JScrollPane(tabelaVagas);
        tabelaVagas.setDefaultEditor(Object.class, null);
        tabelaVagas.getTableHeader().setReorderingAllowed(false);


        painelTabela.setBounds(150, 75, 700, 400);

        add(painelTabela);
    }

    public void adicionarBotaoVisualizarInscricoes(){
        JButton botaoVisualizar = new JButton("Visualizar Inscrições");
        estilizarBotao(botaoVisualizar, 30, 600, 180, 40);
        botaoVisualizar.addActionListener(new OuvinteBotaoVisualizar(this));

        add(botaoVisualizar);
    }

    public void adicionarBotaoInscrever(){
        JButton botaoInscrever = new JButton("Inscrever-se");
        estilizarBotao(botaoInscrever, 450, 600, 150, 40);
        botaoInscrever.addActionListener(new OuvinteBotaoInscreverEmMonitoria(this));

        add(botaoInscrever);
    }

    public void adicionarBotaoEditarEdital(){
        JButton botaoEditarEdital = new JButton("Editar edital");
        estilizarBotao(botaoEditarEdital, 250, 600, 150, 40);
        botaoEditarEdital.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new TelaEditarEdital(getCentral(), getEdital());
            }
            
        });

        add(botaoEditarEdital);
    }

    public void adicionarBotaoClonar(){
        JButton botaoClonar = new JButton("Clonar edital");
        estilizarBotao(botaoClonar, 425, 600, 150, 40);
        botaoClonar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    new TelaEditarEdital(getCentral(),getCentral().clonarEdital(edital));
                    new Persistencia().salvarCentral(getCentral(), "central.xml");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }   
        });

        add(botaoClonar);
    }

    public void adicionarBotaoEditarVaga(){
        JButton botaoEditarVaga = new JButton("Editar vaga");
        estilizarBotao(botaoEditarVaga, 625, 600, 150, 40);
        botaoEditarVaga.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabelaVagas.getSelectedRow();

                if(linhaSelecionada == -1){
                    JOptionPane.showMessageDialog(null, "Selecione uma das vagas para editar.");
                }else{
                    new TelaEditarVaga(getCentral(), edital, edital.recuperarVagaPeloIndex(linhaSelecionada));
                }
            }
        });

        add(botaoEditarVaga);
    }

    public void adicionarBotaoAddVaga(){
        JButton botaoAddVaga = new JButton("Adicionar vaga");
        estilizarBotao(botaoAddVaga, 800, 600, 150, 40);
        botaoAddVaga.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new TelaCadastrarVaga(getCentral(), edital, getUser());
            }
            
        });

        add(botaoAddVaga);
    }

    public void adicionarBotaoGerarResultado() {
        JButton botaoResultado = new JButton("Gerar resultado");
        estilizarBotao(botaoResultado, 400, 520, 150, 40);
        botaoResultado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!getCentral().getEditaisComResultado().contains(edital)){
                    getCentral().getEditaisComResultado().add(edital);
                }
                new TelaEditalComResultado(getCentral(), edital, getUser());
                try {
                    new Persistencia().salvarCentral(getCentral(), "central.xml");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        add(botaoResultado);
    }

    public void adicionarBotaoEncerrar(){
        JButton botaoEncerrar = new JButton("ENCERRAR EDITAL");
        estilizarBotao(botaoEncerrar, 400, 500, 200, 40);
        botaoEncerrar.addActionListener(new OuvinteBotaoEncerrar());

        add(botaoEncerrar);
    }
    public class OuvinteBotaoEncerrar implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            edital.encerrarEdital();
            JOptionPane.showMessageDialog(null, "Edital encerrado.");
            try {
                new Persistencia().salvarCentral(getCentral(), "central.xml");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            new TelaListaDeEditais(getCentral(), getUser());
            dispose();
        }
        
    }

    public EditalMonitoria getEdital() {
        return edital;
    }

    public void setEdital(EditalMonitoria edital) {
        this.edital = edital;
    }

    public JTable getTabelaVagas() {
        return tabelaVagas;
    }

    public void setTabelaVagas(JTable tabelaVagas) {
        this.tabelaVagas = tabelaVagas;
    }
}