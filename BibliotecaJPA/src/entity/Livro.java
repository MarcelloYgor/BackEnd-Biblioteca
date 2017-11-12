package entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "Livro")
public class Livro {

	@Id
	@Column(name = "livro_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "nome")
	private String nome;

	@OneToMany
	@JoinColumn(name="autor_id")
	@Column(name = "autor")
	private Autor autor;

	@OneToOne
	@JoinColumn(name="editora_id")
	@Column(name = "editora")
	private Editora editora;

	@Column(name = "ano")
	private int ano;

	@Column(name = "edicao")
	private int edicao;

	@Column(name = "numpaginas")
	private int numPaginas;

	@OneToOne
	@JoinColumn(name="genero_id")
	@Column(name = "genero")
	private Genero genero;

	@Column(name = "idioma")
	private String idioma;

	@Column(name = "qtddisponivel")
	private static int qtdDisponivel; // Tem que mudar

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public static int getQtdDisponivel() {
		return qtdDisponivel;
	}

	public static void setQtdDisponivel(int qtdDisponivel) {
		Livro.qtdDisponivel = qtdDisponivel;
	}

}
