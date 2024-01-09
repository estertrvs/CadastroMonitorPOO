package telas;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cadastroMonitores.CentralDeInformacoes;
import cadastroMonitores.EditalMonitoria;
import cadastroMonitores.Persistencia;


public class TelaEditarEdital extends TelaInformacoesDoEdital {

    private EditalMonitoria edital;

    public TelaEditarEdital(CentralDeInformacoes central, EditalMonitoria edital) {
        super(central, "Editar Edital");
        this.edital = edital;
        adicionarTitulo("EDITAR EDITAL");
        adicionarLabel();
        adicionarTextField();
        adicionarBotao();
        ImageIcon icone = new ImageIcon("Icones/icone.png");
        setIconImage(icone.getImage());
        setVisible(true);
    }

    

    public void adicionarTextField() {
        setNomeEdital(new JTextField(edital.getEdital()));
        getNomeEdital().setBounds(70, 75, 880, 30);

        MaskFormatter mascaraData = null;
        try {
            mascaraData = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setInicio(new JFormattedTextField(mascaraData));
        getInicio().setBounds(200, 115, 750, 30);
        getInicio().setText(edital.getDataInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        if (edital.status().equals("em andamento.") && !edital.getDataInicio().isEqual(LocalDate.now())){
            getInicio().setEditable(false);
        }

        setFim(new JFormattedTextField(mascaraData));
        getFim().setBounds(250, 155, 700, 30);
        getFim().setText(edital.getDataFim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        setQuantInscricoesPorAluno(new JTextField(String.valueOf(edital.getQuantidadeMaximaInscricoesPorAluno())));
        getQuantInscricoesPorAluno().setBounds(280, 195, 670, 30);

        setPesoCRE(new JTextField(String.valueOf(edital.getPesoCRE())));
        getPesoCRE().setBounds(120, 285, 830, 30);

        setPesoNota(new JTextField(String.valueOf(edital.getPesoNota())));
        getPesoNota().setBounds(120, 325, 830, 30);

        add(getNomeEdital());
        add(getInicio());
        add(getFim());
        add(getQuantInscricoesPorAluno());
        add(getPesoCRE());
        add(getPesoNota());
    }

    public void adicionarBotao() {
        JButton botaoSalvarAlteracoes = new JButton("Salvar alterações");
        estilizarBotao(botaoSalvarAlteracoes, 400, 550, 200, 40);
        botaoSalvarAlteracoes.addActionListener(new OuvinteBotaoSalvarAlteracoes());

        add(botaoSalvarAlteracoes);
    }

    public class OuvinteBotaoSalvarAlteracoes implements ActionListener {

        public void actionPerformed(ActionEvent e) {
    
            if (conferirCamposVazios()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                return;
            }
         
            LocalDate dataAtual = LocalDate.now();
            LocalDate dataInicio = LocalDate.parse(getInicio().getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate dataFim = LocalDate.parse(getFim().getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            
            if (dataFim.isAfter(dataAtual) && Integer.parseInt(getQuantInscricoesPorAluno().getText()) >= edital.getQuantidadeMaximaInscricoesPorAluno()){

                
                Float pesoCRE = Float.parseFloat(getPesoCRE().getText());
                Float pesoNota = Float.parseFloat(getPesoNota().getText());
                
                if (pesoCRE + pesoNota == 1){
                    edital.setPesoCRE(Float.parseFloat(getPesoCRE().getText()));
                    edital.setPesoNota(Float.parseFloat(getPesoNota().getText()));
                    edital.setEdital(getNomeEdital().getText());
                    edital.setDataInicio(dataInicio);
                    edital.setDataFim(dataFim);
                    edital.setQuantidadeMaximaInscricoesPorAluno(Integer.parseInt(getQuantInscricoesPorAluno().getText()));
                    edital.setEmAberto(true);
                    try {
                        new Persistencia().salvarCentral(getCentral(), "central.xml");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Alterações salvas!");
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "A soma dos pesos deve ser 1");
                }

            }else {
                JOptionPane.showMessageDialog(null, "A data não pode ter passado e/ou a quantidade máxima de inscrições não pode ser menor que a atual!");
                dispose();
            }
            new TelaEditarEdital(getCentral(), edital);
        }
    }

    public void setEdital(EditalMonitoria edital) {
        this.edital = edital;
    }

    public EditalMonitoria getEdital() {
        return edital;
    }
}
