package entidades;

//import java.util.ArrayList;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import entidades.Curso;
import entidades.Aluno;

@Entity
public class Curso {

	@Id
	private int codigo;
	private String nome;

	@OneToMany(mappedBy = "curso")
	private List<Aluno> alunos;

	@ManyToMany(mappedBy = "cursos")
	private List<Professor> professores;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void addAluno(Aluno aluno) {
		alunos.add(aluno);

	}

	/**
	 * @return the representantes
	 */
	public List<Professor> getProfessores() {
		return professores;
	}

	/**
	 * @param representantes
	 *            the representantes to set
	 */
	public void setCursos(List<Professor> professores) {
		this.professores = professores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", nome=" + nome + "]";
	}

}
