package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cadastroMonitores.CentralDeInformacoes;
import cadastroMonitores.Usuario;
import ouvintes.OuvinteBotaoVerPerfil;

public abstract class TelaPadrao extends JFrame{
    private CentralDeInformacoes central;
    private Usuario user;
    private JMenu opcoes;

    public TelaPadrao(CentralDeInformacoes central, String titulo){
        this.central = central;
        setTitle(titulo);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setSize(500,500);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        ImageIcon icone = new ImageIcon("Icones/icone.png");
        setIconImage(icone.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public TelaPadrao(CentralDeInformacoes central, String titulo, Usuario user){
        this(central, titulo);
        setUser(user);
    }

    public void adicionarMenu(){
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);

        opcoes = new JMenu("Opções");
        menu.add(opcoes);
    }

    public void adicionarItemLogin(){
        JMenuItem itemLogin = new JMenuItem("Fazer login");
        itemLogin.addActionListener(new OuvinteMenuLogin(this));

        opcoes.add(itemLogin);
    }

    public void adicionarItemCadastro(){
        JMenuItem itemCadastro = new JMenuItem("Fazer cadastro");
        itemCadastro.addActionListener(new OuvinteMenuCadastro(this));

        opcoes.add(itemCadastro);
    }

    public void adicionarItemPerfil(){
        JMenuItem itemPerfil = new JMenuItem("Ver perfil");
        itemPerfil.addActionListener(new OuvinteBotaoVerPerfil(this));
        
        opcoes.add(itemPerfil);
    }

    public boolean temCamposVazios(List<JTextField> campos) {
        for (JTextField campo: campos) {
            String texto;
            if (campo instanceof JFormattedTextField){
                texto = ((JTextField) campo).getText().replace("/", "").trim();
            }else{
                texto = campo.getText();
            }
            if (texto.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void estilizarTitulo(JLabel titulo, int x, int y, int width, int height){
        titulo.setBounds(x, y, width, height);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void estilizarBotao(JButton botao, int x, int y, int width, int height) {
        botao.setBounds(x, y, width, height);
        botao.setBackground(new Color(44, 62, 80)); 
        botao.setForeground(Color.WHITE); 
        botao.setFont(new Font("Arial", Font.PLAIN, 14)); 
        botao.setFocusPainted(false); 
    }

    private class OuvinteMenuLogin implements ActionListener{
        private TelaPadrao tela;

        public OuvinteMenuLogin(TelaPadrao tela){
            this.tela = tela;
        }

        public void actionPerformed(ActionEvent e) {
            tela.dispose();
            new TelaLogin(central);
        }
    }
    private class OuvinteMenuCadastro implements ActionListener{
        private TelaPadrao tela;

        public OuvinteMenuCadastro(TelaPadrao tela){
            this.tela = tela;
        }

        public void actionPerformed(ActionEvent e) {
            tela.dispose();
            new TelaCadastroAluno(central);
        }

    }
    
    public CentralDeInformacoes getCentral() {
        return central;
    }

    public void setCentral(CentralDeInformacoes central) {
        this.central = central;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public JMenu getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(JMenu opcoes) {
        this.opcoes = opcoes;
    }
}