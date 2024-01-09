package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cadastroMonitores.CentralDeInformacoes;
import cadastroMonitores.EditalMonitoria;
import cadastroMonitores.Usuario;

public class TelaTabelaEditaisComResultado extends TelaPadrao{
    private JTable tabela;

    public TelaTabelaEditaisComResultado(CentralDeInformacoes central, Usuario user) {
        super(central, "Editais com resultado");
        setUser(user);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        adicionarTitulo();
        adicionarTabelaEditaisComResultado();
        adicionarBotaoVerSelecionados();
        setVisible(true);
    }

    public void adicionarTitulo(){
        JLabel titulo = new JLabel("EDITAIS COM RESULTADO");
        estilizarTitulo(titulo, 0, 25, 1000, 30);

        add(titulo);
    }
    public void adicionarTabelaEditaisComResultado(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Edital:");
        modelo.addColumn("ID:");
        modelo.addColumn("Data de início:");
        modelo.addColumn("Data de encerramento:");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (EditalMonitoria ed: getCentral().getEditaisComResultado()){
            Object[] linha = new Object[4];
            linha[0] = ed.getEdital();
            linha[1] = ed.getId();
            linha[2] = ed.getDataInicio().format(formatter);
            linha[3] = ed.getDataFim().format(formatter);

            modelo.addRow(linha);
        }
        tabela = new JTable(modelo);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setDefaultEditor(Object.class, null);
        JScrollPane painelTabela = new JScrollPane(tabela);

        painelTabela.setBounds(150, 75, 700, 500);

        add(painelTabela);
    }

    public void adicionarBotaoVerSelecionados(){
        JButton botaoVerSelecionados = new JButton("Visualizar selecionados");
        estilizarBotao(botaoVerSelecionados, 400, 600, 200, 40);
        botaoVerSelecionados.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabela.getSelectedRow();
                if (linhaSelecionada == -1){
                    JOptionPane.showMessageDialog(null, "Selecione um dos editais para ver sua classificação");
                } else {
                    new TelaEditalComResultado(getCentral(), getCentral().getEditaisComResultado().get(linhaSelecionada), getUser());
                    dispose();
                }
            }
        });

        add(botaoVerSelecionados);
    }

    public JTable getTabela() {
        return tabela;
    }

    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }

}