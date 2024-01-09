package cadastroMonitores;

public class Inscricao {
	private EditalMonitoria edital;
    private Aluno aluno;
    private float cre;
    private float media;
    private float pontuacao;
    private String tipoDeVaga;
    private Boolean selecionado;

    public Inscricao(EditalMonitoria e,Aluno aluno, float cre, float media) {
    	this.edital = e;
        this.aluno = aluno;
        this.cre = cre;
        this.media = media;
        this.pontuacao = e.getPesoCRE()*this.getCRE()+e.getPesoNota()*this.getMedia();
        this.selecionado = false;
    }
    
    public Aluno getAluno() {
        return aluno;
    }

    public float getCRE() {
        return cre;
    }

    public float getMedia() {
        return media;
    }

	public float getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(float pontuacao) {
		this.pontuacao = pontuacao;
	}
	public EditalMonitoria getEdital() {
		return edital;
	}
	public void setEdital(EditalMonitoria edital) {
		this.edital = edital;
	}

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public float getCre() {
        return cre;
    }

    public void setCre(float cre) {
        this.cre = cre;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public String getTipoDeVaga() {
        return tipoDeVaga;
    }

    public void setTipoDeVaga(String tipoDeVaga) {
        this.tipoDeVaga = tipoDeVaga;
    }

    public Boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

}