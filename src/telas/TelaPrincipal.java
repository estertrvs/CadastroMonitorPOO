package telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import cadastroMonitores.Administrador;
import cadastroMonitores.Aluno;
import cadastroMonitores.CentralDeInformacoes;
import cadastroMonitores.Usuario;

public class TelaPrincipal extends TelaPadrao {
    private JLabel menuLateral;

    public TelaPrincipal(CentralDeInformacoes central, Usuario user) {
        super(central, "Tela Principal");
        setUser(user);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        adicionarMenu();
        adicionarItemPerfil();
        adicionarJLabel();
        adicionarJButton();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icone = new ImageIcon("Icones/icone.png");
        setIconImage(icone.getImage());
        setVisible(true);
    }

    public void adicionarJLabel() {
        menuLateral = new JLabel();
        menuLateral.setBounds(325, 0, 350, 700);
        menuLateral.setOpaque(true);
        menuLateral.setBackground(new Color(52, 73, 94)); 
        
        add(menuLateral);
    }
    
    public void adicionarJButton() {

        JButton botaoListar = new JButton("LISTAR EDITAIS");
        estilizarBotao(botaoListar, 25, 100, 300, 50);
        botaoListar.addActionListener(new OuvinteBotaoListar());

        JButton botaoEditaisComResultado = new JButton("EDITAIS COM RESULTADO");
        estilizarBotao(botaoEditaisComResultado, 25, 170, 300, 50);
        botaoEditaisComResultado.addActionListener(new OuvinteBotaoEditaisComResultado());

        JButton botaoCadastrarEdital = new JButton("CADASTRAR EDITAL");
        estilizarBotao(botaoCadastrarEdital, 25, 380, 300, 50);
        botaoCadastrarEdital.addActionListener(new OuvinteBotaoCadastrarEdital());

        JButton botaoListarAlunos = new JButton("TABELA DOS ALUNOS");
        estilizarBotao(botaoListarAlunos, 25, 310, 300, 50);
        botaoListarAlunos.addActionListener(new OuvinteBotaoListarAlunos());

        JButton botaoInscrever = new JButton("INSCREVER-SE EM MONITORIA");
        estilizarBotao(botaoInscrever, 25, 240, 300, 50);
        botaoInscrever.addActionListener(new OuvinteBotaoInscrever());

        JButton botaoVisualizarInscricoes = new JButton("VISUALIZAR SUAS INSCRIÇÕES");
        estilizarBotao(botaoVisualizarInscricoes,25,310,300,50);
        botaoVisualizarInscricoes.addActionListener(new OuvinteBotaoVisualizarInscricao());

        menuLateral.add(botaoListar);
        menuLateral.add(botaoEditaisComResultado);
        menuLateral.add(botaoInscrever);
        menuLateral.add(botaoVisualizarInscricoes);
        if (getUser() instanceof Administrador) {
            botaoInscrever.setText("VISUALIZAR EDITAIS EM ANDAMENTO");
            menuLateral.add(botaoListarAlunos);
            menuLateral.add(botaoCadastrarEdital);
            botaoVisualizarInscricoes.setVisible(false);
        }
    }

    private class OuvinteBotaoListarAlunos implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new TelaTabelaAlunos(getCentral());
        }
    }

    private class OuvinteBotaoListar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new TelaListaDeEditais(getCentral(), getUser());
        }
    }

    public class OuvinteBotaoCadastrarEdital implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new TelaCadastrarEdital(getCentral(), getUser());
        }
    }

    public class OuvinteBotaoInscrever implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new TelaInscricaoDeMonitoria(getCentral(), getUser());
        }
    }

    public class OuvinteBotaoEditaisComResultado implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new TelaTabelaEditaisComResultado(getCentral(), getUser());
        }
    }

    public class OuvinteBotaoVisualizarInscricao implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Aluno aluno = (Aluno)getUser();
            new TelaTabelaInscricoesDoAluno(getCentral(),aluno);
        }

    }
}
