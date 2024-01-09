package cadastroMonitores;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class EditalMonitoria {
	private long id = System.currentTimeMillis();
	private String edital;
	private LocalDate dataInicio, dataFim;
	private boolean emAberto;
	private int quantidadeMaximaInscricoesPorAluno;
	private ArrayList<Vaga> vagas = new ArrayList<Vaga>();
	private float pesoCRE;
	private float pesoNota;
	
	public EditalMonitoria(String edital,LocalDate dataInicio, LocalDate dataFim, int quantidadeMaximaInscricoesPorAluno, float pesoCRE, float pesoNota) {
		this.edital = edital;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.quantidadeMaximaInscricoesPorAluno = quantidadeMaximaInscricoesPorAluno;
		this.pesoCRE = pesoCRE;
		this.pesoNota = pesoNota;
		setEmAberto(true);
	}
	
	public boolean inscrever(CentralDeInformacoes central, Aluno aluno,String disciplina, float cre, float media) {
		if (verificarSeAlunoPodeSeInscrever(central, aluno.getMatricula())){
			for (Vaga vaga:vagas) {
				if (vaga.getNomeDisciplina().equalsIgnoreCase(disciplina)) {
					for (Inscricao i:vaga.getAlunosInscritos()) {
						if (i.getAluno().getMatricula().equals(aluno.getMatricula())) {
							JOptionPane.showMessageDialog(null, "Você já se inscreveu para essa vaga");
							return false; 
						}
					}
					vaga.getAlunosInscritos().add(new Inscricao(this,aluno,cre,media));
					return true;
				}
			}
		}
		JOptionPane.showMessageDialog(null, "Aluno atingiu o limite de inscrições");
		return false;
	}
	
	public String status() {
		LocalDate dataAtual = LocalDate.now();
		if(dataAtual.isAfter(dataFim) || !getEmAberto()) {
			setEmAberto(false);
			return "encerradas.";
		}else if(dataAtual.isAfter(dataInicio) || dataAtual.isEqual(dataInicio)) {
			return "em andamento.";
		}
		return "em breve.";
	}

	public boolean encerrarEdital() {
		setEmAberto(false);
		setDataFim(LocalDate.now());
		gerarRanking();
		return true;
	}

	public void gerarRanking(){
		for(Vaga vaga: vagas){
			vaga.rankear();
		}
	}

	public Inscricao pesquisarInscricaoPorMatricula(String matricula){
		for (Vaga vaga: vagas){
			for(Inscricao inscricao: vaga.getAlunosInscritos()){
				if (inscricao.getAluno().getMatricula().equals(matricula)){
					return inscricao;
				}
			}
		}
		return null;
	}

	public void desistirDeVaga(Inscricao inscricao){
		for (Vaga vaga: vagas){
			for (Inscricao i: vaga.getAlunosInscritos()){
				if (i.equals(inscricao)){
					vaga.getAlunosInscritos().remove(i);
					return;
				}
			}
		}
	}
	
	public boolean verificarSeAlunoPodeSeInscrever(CentralDeInformacoes central, String matricula) {
		if (central.recuperarIncricoesDeUmAlunoEmUmEdital(matricula, this.getId()) == null) {
			return true;
		} else if (central.recuperarIncricoesDeUmAlunoEmUmEdital(matricula, this.getId()).size()<this.getQuantidadeMaximaInscricoesPorAluno()) {
			return true;
		}
		return false;
	}

	public boolean editarDataInicio(LocalDate novaData) {
		if(novaData.isAfter(LocalDate.now()) ){
			setDataInicio(novaData);
			return true;
		}
		return false;
	}

	public boolean editarDataFim(LocalDate novaData) {
		if(novaData.isAfter(LocalDate.now()) ){
			setDataInicio(novaData);
			return true;
		}
		return false;
	}

	public Vaga recuperarVagaPeloIndex(int index){
		return vagas.get(index);
	}

	//getter e setters
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEdital() {
		return edital;
	}
	
	public void setEdital(String edital) {
		this.edital = edital;
	}
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public LocalDate getDataFim() {
		return dataFim;
	}
	
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	
	public ArrayList<Vaga> getVagas() {
		return vagas;
	}
	
	public void setVagas(ArrayList<Vaga> vagas) {
		this.vagas = vagas;
	}

	public int getQuantidadeMaximaInscricoesPorAluno() {
		return quantidadeMaximaInscricoesPorAluno;
	}

	public void setQuantidadeMaximaInscricoesPorAluno(int quantidadeMaximaInscricoesPorAluno) {
		this.quantidadeMaximaInscricoesPorAluno = quantidadeMaximaInscricoesPorAluno;
	}

	public float getPesoCRE() {
		return pesoCRE;
	}

	public void setPesoCRE(float pesoCRE) {
		this.pesoCRE = pesoCRE;
	}

	public float getPesoNota() {
		return pesoNota;
	}

	public void setPesoNota(float pesoNota) {
		this.pesoNota = pesoNota;
	}

	public boolean getEmAberto() {
		return emAberto;
	}

	public void setEmAberto(boolean status) {
		this.emAberto = status;
	}
}