package Model;

public class ModelAdmin {

    private int id;
    private String nome;
    private String cpf;
    
      public ModelAdmin(String nome, String cpf) {
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "" + cpf;
    }

}
