package servicos;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Professor;

@ApplicationScoped
public class ProfessorService {

	private EntityManagerFactory emf;
	private Professor aux = new Professor();

	public ProfessorService() {
		emf = Persistence.createEntityManagerFactory("PrAula07112015");

	}

	public List<Professor> allProfessores() {
		List<Professor> professores = null;
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select p From Professor p");
		professores = query.getResultList();
		em.close();

		return professores;
	}

	public void persist(Professor professor) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(professor);
		em.getTransaction().commit();
		em.close();
	}

	public void update(Professor professor) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.merge(professor);
		em.getTransaction().commit();
		em.close();
	}

	public void excluir(int codigo) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Professor professor = em.find(Professor.class, codigo);
		em.remove(professor);

		em.getTransaction().commit();

		em.close();

	}

	public Professor pesquisar(int codigo) {
		EntityManager em = emf.createEntityManager();

		aux = em.find(Professor.class, codigo);
		em.close();

		return aux;
	}

	public void alterar(int cod, String nome) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Professor p = em.find(Professor.class, cod);

		p.setNome(nome);
		em.getTransaction().commit();
		em.close();
	}
}
