package managedbeans;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entidades.Curso;
import servicos.CursoService;

@FacesConverter("ConverterCurso")
public class ConverterCurso implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		if (value != null && !value.isEmpty()) {

			CursoService servico = new CursoService();

			for (Curso f : servico.allCursos())
				if (f.getNome().equals(value))
					return f;

		}

		return null;

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object curso) {
		if (curso == null || curso.equals("")) {
			return null;
		} else {
			return ((Curso) curso).getNome();

		}
	}

}
