package Model;

public class ModelAdmin {	
	private String nome;
	private String cpf;
	
	public ModelAdmin(String nome, String cpf) {
		this.nome = nome;
                this.cpf = cpf;
	}
        
        public ModelAdmin(){
            
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
	
	
}

