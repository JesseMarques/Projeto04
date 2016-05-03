package managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.RowEditEvent;
import servicos.CursoService;
import entidades.Curso;

@ManagedBean
@RequestScoped
public class CursoManagedBean {

	private Curso curso = new Curso();
	private CursoService service = new CursoService();
	private Curso aux = new Curso();

	public void onRowEdit(RowEditEvent event) {
		Curso p = (Curso) event.getObject();
		service.upDateCurso(p);

	}

	public void salvar() {
		service.persist(curso);
		curso = new Curso();

	}

	public List<Curso> getCursos() {
		return service.allCursos();

	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Curso getCurso() {
		return curso;
	}

	public void pesquisar() {
		aux = service.pesquisar(curso.getCodigo());

		curso.setCodigo(aux.getCodigo());
		curso.setNome(aux.getNome());
	}

	public void alterar() {
		service.alterar(curso);
	}

	public void excluir() {
		service.excluir(curso);
	}

}
