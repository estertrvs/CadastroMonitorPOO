package cadastroMonitores;

public class Aluno extends Usuario{
	private String matricula;
	private Sexo sexo;
	
	public Aluno(String matricula, String nome, String sexo,String email,String senha) {
		this.matricula = matricula; 
		this.setNome(nome);
		this.setSexo(Sexo.valueOf(sexo));
		this.setEmail(email);
		this.setSenha(senha);
	}

	public Boolean equals(Aluno outro){
		if(this.getMatricula().equals(outro.getMatricula())){
			return true;
		}
		return false;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
}
