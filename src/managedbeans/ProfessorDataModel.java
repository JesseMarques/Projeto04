package managedbeans;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import entidades.Professor;
import servicos.ProfessorService;

public class ProfessorDataModel extends ListDataModel<Professor> implements
		SelectableDataModel<Professor> {
	private static ProfessorService servico = new ProfessorService();

	public ProfessorDataModel() {

	}

	public ProfessorDataModel(List<Professor> list) {
		super(list);
	}

	@Override
	public Professor getRowData(String rowKey) {

		for (Professor r : servico.allProfessores())
			if (Integer.parseInt(rowKey) == r.getNumero())
				return r;

		return null;
	}

	@Override
	public Object getRowKey(Professor prof) {
		return prof.getNumero();
	}

}