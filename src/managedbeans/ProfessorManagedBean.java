package managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;

import managedbeans.ProfessorDataModel;

import org.primefaces.model.DualListModel;

import entidades.Aluno;
import entidades.Curso;
import entidades.Professor;
import servicos.AlunoService;
import servicos.CursoService;
import servicos.ProfessorService;

@ManagedBean
@SessionScoped
public class ProfessorManagedBean {

	private Professor professor = new Professor();
	private ProfessorService service = new ProfessorService();
	private CursoService servico = new CursoService();
	private AlunoService servica = new AlunoService();
	private Professor aux = new Professor();
	private Professor profSelect = new Professor();
	private DualListModel<Curso> dualList = new DualListModel<Curso>();

	private List<Curso> cursosp = null;

	public void addCursoToProfessor(Professor prof) {
		profSelect = prof;

	}

	public void salvar() {
		service.persist(professor);
		professor = new Professor();

	}

	public void PesquisarCurso() {
		cursosp = servica.allCursoProfessor(professor);
	}

	public List<Curso> getCursoProfessor() {
		if (cursosp == null)
			return cursosp;
		else {
			return cursosp;
		}
	}

	public DualListModel<Curso> getDualList() {
		return dualList;
	}

	public DualListModel<Curso> getCursos() {

		List<Curso> source = new ArrayList<Curso>();
		List<Curso> target = new ArrayList<Curso>();

		source.addAll(servico.allCursos());

		if (profSelect != null) {
			target.addAll(profSelect.getCursos());
			source.removeAll(profSelect.getCursos());
		}

		dualList.setSource(source);
		dualList.setTarget(target);

		return dualList;

	}

	public void setCursos(DualListModel<Curso> cursos) {
		this.dualList = cursos;

	}

	public List<Professor> getProfessores() {
		return service.allProfessores();

	}

	public void setCurso(Professor professor) {
		this.professor = professor;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void pesquisar() {
		aux = service.pesquisar(professor.getNumero());

		professor.setNumero(aux.getNumero());
		professor.setNome(aux.getNome());
	}

	public void alterar() {
		service.alterar(professor.getNumero(), professor.getNome());
	}

	public void excluir() {
		service.excluir(professor.getNumero());
	}

	public Professor getProfSelect() {
		return profSelect;
	}

	public void associar() {

		for (Curso f : dualList.getSource()) {
			f.getProfessores().remove(profSelect);
		}

		for (Curso f : dualList.getTarget()) {
			f.getProfessores().add(profSelect);
		}

		profSelect.getCursos().clear();
		profSelect.getCursos().addAll(dualList.getTarget());

		service.update(profSelect);

	}

	public void setProfSelect(Professor profSelect) {
		this.profSelect = profSelect;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public ProfessorDataModel getRepresentantes() {
		return new ProfessorDataModel(service.allProfessores());
	}

}
