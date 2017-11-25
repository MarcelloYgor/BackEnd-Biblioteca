package entity;

public class Atendente extends Funcionario {
	
	private double salario;

	private double calcularBonus;

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
