package cadastroMonitores;

public class Administrador extends Usuario{
    private Boolean isAdmin;
    public Administrador(String nome,String email, String senha){
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        isAdmin = true;
    }

    public Boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}