package telas;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cadastroMonitores.CentralDeInformacoes;
import cadastroMonitores.EditalMonitoria;
import cadastroMonitores.Usuario;

public class TelaInscricaoDeMonitoria extends TelaPadrao{
    private JTable tabela;

    public TelaInscricaoDeMonitoria(CentralDeInformacoes central, Usuario user) {
        super(central, "Inscrição de monitoria");
        setUser(user);
        setSize(1000,700);
        setLocationRelativeTo(null);
        adicionarTitulo();
        adicionarTabela();
        adicionarBotaoInscrever();
        setVisible(true);
    }

    public void adicionarTitulo(){
        JLabel titulo = new JLabel("EDITAIS EM ABERTO");
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
                if (ed.status().equals("em andamento.")){
                    Object[] linha = new Object[5];
                    linha[0] = ed.getEdital();
                    linha[1] = ed.getId();
                    linha[2] = ed.getDataInicio().format(formatter);
                    linha[3] = ed.getDataFim().format(formatter);
                    linha[4] = ed.status();
        
                    modelo.addRow(linha);
                }
            }

            tabela = new JTable(modelo);
            tabela.setDefaultEditor(Object.class, null);
            tabela.getTableHeader().setReorderingAllowed(false);
            JScrollPane painelTabela = new JScrollPane(tabela);
    
            painelTabela.setBounds(150, 75, 700, 500);
    
            add(painelTabela);
        
    }

    public void adicionarBotaoInscrever(){
        JButton botaoInscrever = new JButton("VISUALIZAR VAGAS");
        estilizarBotao(botaoInscrever, 400, 600, 170, 40);
        botaoInscrever.addActionListener(new OuvinteBotaoInscrever());

        add(botaoInscrever);
    }

    public class OuvinteBotaoInscrever implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = tabela.getSelectedRow();
                if(linhaSelecionada == -1){
                    JOptionPane.showMessageDialog(null, "Selecione um dos editais para visualizar suas vagas.");
                }else{
                    EditalMonitoria edital = getCentral().recuperarEditalPorID((Long)tabela.getValueAt(linhaSelecionada, 1));
                    new TelaDetalhesDoEdital(getCentral(), edital, getUser());
                    dispose();
                }
        }

    }
    
}