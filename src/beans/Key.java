package beans;

public class Key {
	private String nome_sala;
	private int codigo_chave;
	private String bloco;
	private boolean Status;
	
	
	public Key(String nome_sala, int codigo_chave, String bloco, boolean status) {
		this.nome_sala = nome_sala;
		this.codigo_chave = codigo_chave;
		this.bloco = bloco;
		this.Status = status;
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
		return Status;
	}


	public void setStatus(boolean status) {
		this.Status = status;
	}


	
	
	
}
