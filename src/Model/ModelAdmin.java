package Model;

public class ModelAdmin {

    private int id;
    private String nome;
    private long cpf;
    private int token;
    
    public ModelAdmin(String nome, long cpf, int token) {
        this.nome = nome;
        this.cpf = cpf;
        this.token = token;
    }
    
    public ModelAdmin(String nome, long cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public ModelAdmin() {

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

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
    
    

    @Override
    public String toString() {
        return "" + cpf;
    }

}
