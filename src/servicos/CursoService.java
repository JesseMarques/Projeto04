package servicos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import entidades.Curso;

@ManagedBean(name = "servCurso")
@ApplicationScoped
public class CursoService {

	private EntityManagerFactory emf;
	private Curso aux = new Curso();

	public CursoService() {
		emf = Persistence.createEntityManagerFactory("PrAula07112015");

	}

	public List<Curso> allCursos() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select c From Curso c");
		List<Curso> cursos = query.getResultList();
		em.close();

		return cursos;
	}

	public void persist(Curso curso) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(curso);
		em.getTransaction().commit();
		em.close();
	}

	public void excluir(Curso curso) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Curso produtos2 = em.find(Curso.class, curso.getCodigo());
		em.remove(produtos2);

		em.getTransaction().commit();

		em.close();

	}

	public Curso pesquisar(int codigo) {
		EntityManager em = emf.createEntityManager();

		aux = em.find(Curso.class, codigo);
		em.close();

		return aux;
	}

	public void alterar(Curso curso) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Curso p = em.find(Curso.class, curso.getCodigo());

		p.setNome(curso.getNome());
		em.getTransaction().commit();
		em.close();
	}

	public void upDateCurso(Curso c) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(c);
		em.getTransaction().commit();
		em.close();

	}

}
