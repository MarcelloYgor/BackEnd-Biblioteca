package dao;

import java.util.List;

import entity.Cliente;
import entity.Livro;

public class RetornoLivroEmprestadoVO {

	private List<Livro> livro;

	private Cliente cliente;

	public List<Livro> getLivro() {
		return livro;
	}

	public void setLivro(List<Livro> livro) {
		this.livro = livro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
