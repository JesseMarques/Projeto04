package servicos;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Aluno;
import entidades.Curso;
import entidades.Professor;

@ApplicationScoped
public class AlunoService {

	private EntityManagerFactory emf;
	private Aluno aux = new Aluno();

	public AlunoService() {
		emf = Persistence.createEntityManagerFactory("PrAula07112015");

	}

	public List<Aluno> allAlunos() {
		List<Aluno> alunos = null;
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select a From Aluno a");
		alunos = query.getResultList();
		em.close();

		return alunos;
	}

	public List<Aluno> allAlunosCurso(Curso curso) {
		List<Aluno> alunosCurso = null;
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createQuery("Select a From Aluno a where a.curso.codigo = :codigo ");
		query.setParameter("codigo", curso.getCodigo());
		alunosCurso = query.getResultList();
		em.close();

		return alunosCurso;
	}

	public List<Curso> allCursoProfessor(Professor professor) {
		List<Curso> professorCurso = null;
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createQuery("Select a From Curso a join a.professores p where p.numero = :codigo");
		query.setParameter("codigo", professor.getNumero());
		professorCurso = query.getResultList();
		em.close();

		return professorCurso;
	}

	public List<Professor> allProfessoresCurso(Curso curso) {
		List<Professor> professoresCurso = null;
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createQuery("Select a From Professor a join a.cursos p where p.codigo = :codigo ");
		query.setParameter("codigo", curso.getCodigo());
		professoresCurso = query.getResultList();
		em.close();

		return professoresCurso;
	}

	public void persist(Aluno aluno) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(aluno);
		em.getTransaction().commit();
		em.close();
	}

	public void excluir(Aluno alunoa) {

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Aluno aluno = em.find(Aluno.class, alunoa.getMatricula());
		em.remove(aluno);

		em.getTransaction().commit();

		em.close();

	}

	public Aluno pesquisar(Aluno aluno) {
		EntityManager em = emf.createEntityManager();

		aux = em.find(Aluno.class, aluno.getMatricula());
		em.close();

		return aux;
	}

	public void alterar(Aluno aluno) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Aluno p = em.find(Aluno.class, aluno.getMatricula());

		p.setNome(aluno.getNome());
		p.setCurso(aluno.getCurso());
		em.getTransaction().commit();
		em.close();
	}

}
