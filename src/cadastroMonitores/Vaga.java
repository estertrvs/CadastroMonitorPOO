package cadastroMonitores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Vaga {
	private String nomeDisciplina;
	private int quantVagasRemuneradas;
	private int quantVagasVoluntarias;
	private ArrayList<Inscricao> alunosInscritos;
	
	public Vaga(String nomeDisciplina,int quantVagasRemuneradas, int quantVagasVoluntarias) {
		this.nomeDisciplina = nomeDisciplina;
		this.quantVagasRemuneradas = quantVagasRemuneradas;
		this.quantVagasVoluntarias = quantVagasVoluntarias;
		alunosInscritos = new ArrayList<>();
	}
	
	public void rankear() {
		Collections.sort(alunosInscritos, Comparator.comparing(Inscricao::getPontuacao).reversed());
	}
	
	public ArrayList<Inscricao> alunosSelecionados() {
		rankear();

		ArrayList<Inscricao> alunosSelecionados = new ArrayList<>();
		int contadorVagasRemuneradas = quantVagasRemuneradas;
		int contadorVagasVoluntarias = quantVagasVoluntarias;
		
		for(Inscricao inscricao:alunosInscritos){
			if(contadorVagasRemuneradas-- > 0){
				inscricao.setTipoDeVaga("Remunerada.");
				inscricao.setSelecionado(true);
				alunosSelecionados.add(inscricao);
			}else if(contadorVagasVoluntarias-- > 0){
				inscricao.setTipoDeVaga("Voluntária");
				inscricao.setSelecionado(true);
				alunosSelecionados.add(inscricao);
			}else{
				inscricao.setTipoDeVaga("Em lista de espera");
				alunosSelecionados.add(inscricao);
			}
		}
		return alunosSelecionados;
	}

	public String toString() {
		return nomeDisciplina;
	}
	
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public int getQuantVagasRemuneradas() {
		return quantVagasRemuneradas;
	}

	public void setQuantVagasRemuneradas(int quantVagasRemuneradas) {
		this.quantVagasRemuneradas = quantVagasRemuneradas;
	}

	public ArrayList<Inscricao> getAlunosInscritos() {
		return alunosInscritos;
	}

	public void setAlunosInscritos(ArrayList<Inscricao> alunosInscritos) {
		this.alunosInscritos = alunosInscritos;
	}

    public int getQuantVagasVoluntarias() {
        return quantVagasVoluntarias;
    }

    public void setQuantVagasVoluntarias(int quantVagasVoluntarias) {
        this.quantVagasVoluntarias = quantVagasVoluntarias;
    }
}
