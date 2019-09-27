package Model;

public class ModelKey {

    private String nome_sala;
    private int id;
    private String bloco;
    private String status;
    
     public ModelKey(String nome_sala, String bloco, String status) {
        this.nome_sala = nome_sala;
        this.bloco = bloco;
        this.status = status;
    }

    public ModelKey() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome_sala() {
        return nome_sala;
    }

    public void setNome_sala(String nome_sala) {
        this.nome_sala = nome_sala;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
