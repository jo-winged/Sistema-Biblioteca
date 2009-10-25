
package library.src;

public class Pessoa {
	private String nome;
	public Pessoa() {
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public boolean equals(Object obj) {
		Pessoa p = (Pessoa) obj;
		return p.getNome().equals(this.getNome());
	}
	
}
