package Model;

public class ModelUser {

    private int id;
    private String nome;
    private String email;
    private long matricula;
    private String tipo_user;
    
     public ModelUser(String nome, String email, long matricula, String tipo_user) {
        //super();
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.tipo_user = tipo_user;
    }

    public ModelUser() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getTipo_user() {
        return tipo_user;
    }

    public void setTipo_user(String tipo_user) {
        this.tipo_user = tipo_user;
    }

    @Override
    public String toString() {
        return nome;
    }

}
