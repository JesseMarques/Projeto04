package managedbeans;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entidades.Professor;
import servicos.ProfessorService;

@FacesConverter("ConverterProfessor")
public class ConverterProfessor implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		if (value != null && !value.isEmpty()) {

			ProfessorService servico = new ProfessorService();

			for (Professor f : servico.allProfessores())
				if (f.getNome().equals(value))
					return f;

		}

		return null;

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object professor) {
		if (professor == null || professor.equals("")) {
			return null;
		} else {
			return ((Professor) professor).getNome();

		}
	}

}
