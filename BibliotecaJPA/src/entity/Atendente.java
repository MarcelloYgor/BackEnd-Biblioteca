package entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Atendente")
public class Atendente extends Funcionario {

	@Id
	@Column(name = "atendente_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private double salario;

	private double calcularBonus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getCalcularBonus() {
		return calcularBonus;
	}

	public void setCalcularBonus(double calcularBonus) {
		this.calcularBonus = calcularBonus;
	}

}
