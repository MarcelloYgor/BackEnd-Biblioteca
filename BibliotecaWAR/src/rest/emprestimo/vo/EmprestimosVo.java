package rest.emprestimo.vo;

import java.util.Date;

import entity.Cliente;
import entity.Livro;

public class EmprestimosVo {

	private int id;

	private Livro livro;

	private Cliente cliente;

	private Date dataEmprestimo;

	private Date dataDevolucao;

	public Livro getLivro() {
		return livro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

}
