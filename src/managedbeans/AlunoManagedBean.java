package managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import servicos.AlunoService;
import servicos.CursoService;
import entidades.Aluno;
import entidades.Curso;
import entidades.Professor;

@ManagedBean(name = "alunoManagedBean")
@RequestScoped
public class AlunoManagedBean {

	private Aluno aluno = new Aluno();
	private AlunoService servico = new AlunoService();
	private CursoService service = new CursoService();
	private Aluno aux = new Aluno();
	private Curso curso;

	private List<Aluno> alunos = null;
	private List<Aluno> alunosCurso = null;

	private List<Professor> professoresCurso = null;

	public void salvar() {
		aluno.setCurso(curso);
		curso.addAluno(aluno);
		servico.persist(aluno);
		aluno = new Aluno();
		curso = null;
		alunos = null;

	}

	public List<Curso> getCursos() {
		return service.allCursos();
	}

	public List<Aluno> getAlunos() {
		if (alunos == null)
			alunos = servico.allAlunos();

		return alunos;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void pesquisar() {
		aux = servico.pesquisar(aluno);

		aluno.setMatricula(aux.getMatricula());
		aluno.setNome(aux.getNome());
		aluno.setCurso(aux.getCurso());
	}

	public void PesquisarAlunos() {
		alunosCurso = servico.allAlunosCurso(curso);
		professoresCurso = servico.allProfessoresCurso(curso);
	}

	public List<Aluno> getAlunosCurso() {
		if (alunosCurso == null)
			return alunosCurso;
		else {
			return alunosCurso;
		}
	}

	public List<Professor> getProfessorCurso() {
		if (professoresCurso == null)
			return professoresCurso;
		else {
			return professoresCurso;
		}
	}

	public void alterar() {
		aluno.setCurso(curso);
		curso.addAluno(aluno);
		servico.alterar(aluno);
		aluno = new Aluno();
		curso = null;
		alunos = null;

	}

	public void excluir() {
		alunos.remove(aluno);
		servico.excluir(aluno);
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}
