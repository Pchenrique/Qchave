package Model;

public class ModelKey {
	private String nome_sala;
	private int codigo_chave;
	private String bloco;
	private boolean status;
	
	
	public ModelKey(String nome_sala, int codigo_chave, String bloco, boolean status) {
            this.nome_sala = nome_sala;
            this.codigo_chave = codigo_chave;
            this.bloco = bloco;
            this.status = status;
	}
        
        public ModelKey(){
            
        }

	public String getNome_sala() {
            return nome_sala;
	}


	public void setNome_sala(String nome_sala) {
            this.nome_sala = nome_sala;
	}


	public int getCodigo_chave() {
            return codigo_chave;
	}


	public void setCodigo_chave(int codigo_chave) {
            this.codigo_chave = codigo_chave;
	}


	public String getBloco() {
            return bloco;
	}


	public void setBloco(String bloco) {
            this.bloco = bloco;
	}


	public boolean getStatus() {
            return status;
	}


	public void setStatus(boolean status) {   
            this.status = status;
	}

    @Override
    public String toString() {
        return ""+ codigo_chave;
    }
 
    
        
        


	
	
	
}
