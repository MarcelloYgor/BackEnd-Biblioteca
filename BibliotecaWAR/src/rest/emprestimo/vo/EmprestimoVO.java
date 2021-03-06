package rest.emprestimo.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import entity.Cliente;
import entity.Livro;

public class EmprestimoVO {

	@XmlElement(required = false)
	private int id;

	private Livro livro;

	private Cliente cliente;

	private Date dt_emprestimo;

	private Date dt_devolucao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
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

	public Date getDt_emprestimo() {
		return dt_emprestimo;
	}

	public void setDt_emprestimo(Date dt_emprestimo) {
		this.dt_emprestimo = dt_emprestimo;
	}

	public Date getDt_devolucao() {
		return dt_devolucao;
	}

	public void setDt_devolucao(Date dt_devolucao) {
		this.dt_devolucao = dt_devolucao;
	}

}
