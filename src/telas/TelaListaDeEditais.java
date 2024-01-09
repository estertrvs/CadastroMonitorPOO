package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cadastroMonitores.*;

public class TelaListaDeEditais extends TelaPadrao{
    private JTable tabela;

    public TelaListaDeEditais(CentralDeInformacoes central, Usuario user) {
        super(central,"Listagem de editais.", user);
        setSize(1000,700);
        setLocationRelativeTo(null);
        adicionarTitulo();
        adicionarTabela();
        adicionarJButton();
       setVisible(true);
    }

    public void adicionarTitulo(){
        JLabel titulo = new JLabel("LISTA DE EDITAIS");
        estilizarTitulo(titulo, 0, 25, 1000, 30);

        add(titulo);
    }

    public void adicionarTabela(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Edital:");
        modelo.addColumn("ID:");
        modelo.addColumn("Data de início:");
        modelo.addColumn("Data de encerramento:");
        modelo.addColumn("Status:");

        ArrayList<EditalMonitoria> todosOsEditais = getCentral().getTodosOsEditais();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (EditalMonitoria ed: todosOsEditais){
            Object[] linha = new Object[5];
            linha[0] = ed.getEdital();
            linha[1] = ed.getId();
            linha[2] = ed.getDataInicio().format(formatter);
            linha[3] = ed.getDataFim().format(formatter);
            linha[4] = ed.status();

            modelo.addRow(linha);
        }
        tabela = new JTable(modelo);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setDefaultEditor(Object.class, null);
        JScrollPane painelTabela = new JScrollPane(tabela);

        painelTabela.setBounds(150, 75, 700, 500);

        add(painelTabela);
    }

    public void adicionarJButton(){
        JButton botaoDetalhar = new JButton("Detalhar Edital");
        estilizarBotao(botaoDetalhar,425 , 600, 150, 40);

        botaoDetalhar.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabela.getSelectedRow();
                if(linhaSelecionada == -1){
                    JOptionPane.showMessageDialog(null, "Selecione um dos editais para detalhar.");
                }else{
                    EditalMonitoria edital = getCentral().recuperarEditalPeloIndex(linhaSelecionada);
                    new TelaDetalhesDoEdital(getCentral(), edital, getUser());
                }
            }

        });

        add(botaoDetalhar);
    }
}