package javi.http.controller.actions;

import java.util.List;

import javi.model.clasificacion.vo.ClasificacionVO;

import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;

public abstract class GenericPrepareAction extends DefaultAction {

	private List<ClasificacionVO> clasificaciones;

	public GenericPrepareAction() {
		this.clasificaciones = (List<ClasificacionVO>) getServlet().getServletConfig().getServletContext().
        getAttribute("clasificaciones");
	}
	
	private List<ClasificacionVO> getClasificaciones() {
		return this.clasificaciones;
	}
	
}

