package entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "Funcionario")
public class Funcionario {

	@Id
	@Column(name = "funcionario_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "rg")
	private String rg;

	@Column(name = "nome")
	private String nome;

	@Column(name = "login")
	private Login login;

	@Column(name = "cpf")
	private double cpf;

	@OneToOne
	@JoinColumn(name = "administrador_id")
	@Column(name = "fk_admin", nullable = true)
	private Administrador adm;

	@OneToOne
	@JoinColumn(name = "atendente_id")
	@Column(name = "fk_atendente", nullable = true)
	private Atendente atendente;

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public double getCpf() {
		return cpf;
	}

	public void setCpf(double cpf) {
		this.cpf = cpf;
	}

	public Administrador getAdm() {
		return adm;
	}

	public void setAdm(Administrador adm) {
		this.adm = adm;
	}

	public Atendente getAtendente() {
		return atendente;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}

}
