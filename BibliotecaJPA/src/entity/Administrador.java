package entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="Administrador")
public class Administrador {

	@Id
	@Column(name = "administrador_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "salario")
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
