package cadastroMonitores;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe que representa a central de informações do sistema, responsável por armazenar e gerenciar dados relacionados a editais, alunos e administrador.
 */
public class CentralDeInformacoes {
    private Administrador admin;
    private ArrayList<Aluno> todosOsAlunos;
    private ArrayList<EditalMonitoria> todosOsEditais;
    private ArrayList<EditalMonitoria> editaisComResultado;
    private ArrayList<EditalMonitoria> editaisEncerrados;

    /**
     * Construtor padrão que inicializa as listas de alunos e editais.
     */
    public CentralDeInformacoes() {
        todosOsAlunos = new ArrayList<>();
        todosOsEditais = new ArrayList<>();
        editaisComResultado = new ArrayList<>();
        editaisEncerrados = new ArrayList<>();
    }

    /**
     * Adiciona um novo edital à lista de editais se não houver outro com o mesmo ID.
     *
     * @param edital O edital a ser adicionado.
     * @return True se o edital foi adicionado com sucesso, false se já existe um edital com o mesmo ID.
     */
    public boolean adicionarEdital(EditalMonitoria edital) {
        if (recuperarEditalPorID(edital.getId()) == null) {
            todosOsEditais.add(edital);
            return true;
        }
        return false;
    }

    /**
     * Clona um edital existente, criando uma cópia com a data atual, e o adiciona à lista de editais.
     *
     * @param edital O edital a ser clonado.
     * @return A cópia clonada do edital.
     */
    public EditalMonitoria clonarEdital(EditalMonitoria edital) {
        EditalMonitoria ed = new EditalMonitoria(edital.getEdital(), LocalDate.now(), edital.getDataFim(),
                edital.getQuantidadeMaximaInscricoesPorAluno(), edital.getPesoCRE(), edital.getPesoNota());
        for (Vaga vaga : edital.getVagas()) {
            ed.getVagas().add(new Vaga(vaga.getNomeDisciplina(), vaga.getQuantVagasRemuneradas(),
                    vaga.getQuantVagasVoluntarias()));
        }
        todosOsEditais.add(ed);
        return ed;
    }

    /**
     * Recupera um edital com base no ID fornecido.
     *
     * @param id O ID do edital a ser recuperado.
     * @return O edital correspondente ao ID, ou null se nenhum edital for encontrado.
     */
    public EditalMonitoria recuperarEditalPorID(long id) {
        for (EditalMonitoria editalMonitoria : todosOsEditais) {
            if (editalMonitoria.getId() == id) {
                return editalMonitoria;
            }
        }
        return null;
    }

    /**
     * Recupera um edital com base no índice fornecido na lista de editais.
     *
     * @param index O índice do edital na lista.
     * @return O edital correspondente ao índice.
     */
    public EditalMonitoria recuperarEditalPeloIndex(Integer index) {
        return todosOsEditais.get(index);
    }

    /**
     * Realiza o processo de login para um usuário com base no email e senha fornecidos.
     *
     * @param email O email do usuário que está tentando fazer login.
     * @param senha A senha associada ao email do usuário.
     * @return Um objeto do tipo Usuario representando o usuário logado ou null se as credenciais não forem válidas.
     */
    public Usuario login(String email, String senha) {
        if (email.equals(admin.getEmail()) && senha.equals(admin.getSenha())) {
            return admin;
        }
        for (Aluno a : todosOsAlunos) {
            if (a.getEmail().equals(email) && a.getSenha().equals(senha)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Adiciona um aluno à lista de todos os alunos, verificando se já existe um aluno com a mesma matrícula.
     *
     * @param aluno O objeto Aluno a ser adicionado.
     * @return True se o aluno foi adicionado com sucesso, false se já existe um aluno com a mesma matrícula.
     */
    public boolean adicionarAluno(Aluno aluno) {
        if (recuperarAlunoPorMatricula(aluno.getMatricula()) == null) {
            todosOsAlunos.add(aluno);
            return true;
        }
        return false;
    }

    /**
     * Recupera as inscrições de um aluno em um edital específico, identificado por sua matrícula e o ID do edital.
     *
     * @param matricula A matrícula do aluno.
     * @param id O ID do edital.
     * @return Uma lista de inscrições relacionadas ao aluno no edital, ou null se o aluno ou o edital não forem encontrados.
     */
    public ArrayList<Inscricao> recuperarIncricoesDeUmAlunoEmUmEdital(String matricula, long id) {
        ArrayList<Inscricao> vagasQueOAlunoSeInscreveu = new ArrayList<>();
        if (recuperarAlunoPorMatricula(matricula) == null || recuperarEditalPorID(id) == null) {
            return null;
        }
        EditalMonitoria edital = recuperarEditalPorID(id);

        for (Vaga vaga : edital.getVagas()) {
            for (Inscricao i : vaga.getAlunosInscritos()) {
                if (i.getAluno().getMatricula().equals(matricula)) {
                    vagasQueOAlunoSeInscreveu.add(i);
                }
            }
        }
        return vagasQueOAlunoSeInscreveu;
    }

    /**
     * Recupera um aluno com base na matrícula fornecida.
     *
     * @param matricula A matrícula do aluno a ser recuperado.
     * @return O objeto Aluno correspondente à matrícula, ou null se nenhum aluno for encontrado.
     */
    public Aluno recuperarAlunoPorMatricula(String matricula) {
        for (Aluno aluno : todosOsAlunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }

	public ArrayList<Aluno> getTodosOsAlunos() {
		return todosOsAlunos;
	}
	
	public void setTodosOsAlunos(ArrayList<Aluno> alunos) {
        todosOsAlunos = alunos;
    }
	
	public ArrayList<EditalMonitoria> getTodosOsEditais() {
		return todosOsEditais;
	}

	public void setTodosOsEditais(ArrayList<EditalMonitoria> todosOsEditais) {
		this.todosOsEditais = todosOsEditais;
	}

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    public ArrayList<EditalMonitoria> getEditaisComResultado() {
        return editaisComResultado;
    }

    public void setEditaisComResultado(ArrayList<EditalMonitoria> editaisComResultado) {
        this.editaisComResultado = editaisComResultado;
    }

    public ArrayList<EditalMonitoria> getEditaisEncerrados() {
        return editaisEncerrados;
    }

    public void setEditaisEncerrados(ArrayList<EditalMonitoria> editaisEncerrados) {
        this.editaisEncerrados = editaisEncerrados;
    }

}
