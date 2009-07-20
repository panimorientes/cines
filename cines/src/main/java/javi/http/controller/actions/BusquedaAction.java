package javi.http.controller.actions;

import java.io.IOException;

import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javi.http.controller.session.SessionManager;
import javi.http.view.actionforms.BusquedaForm;

import javi.model.busquedafacade.delegate.BusquedaFacadeDelegate;
import javi.model.busquedafacade.vo.PeliculaInfoVO;
import javi.model.cine.vo.CineVO;
import javi.model.pelicula.vo.PeliculaVO;
import javi.model.sesion.vo.SesionVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.struts.action.DefaultAction;

public class BusquedaAction extends DefaultAction {

	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, InternalErrorException {

		BusquedaForm busquedaForm = (BusquedaForm) form;

		String clave = busquedaForm.getClave();
		String clasificacion = busquedaForm.getClasificacion();
		boolean byDate = busquedaForm.getByDate();
		Calendar fecha = busquedaForm.getStartDate();

		BusquedaFacadeDelegate busquedaFacadeDelegate = SessionManager
				.getBusquedaFacadeDelegate(request);

		List<PeliculaInfoVO> pelicula = busquedaFacadeDelegate.busquedaPeliculas(
				clave, clasificacion, byDate, fecha);
		List<SesionVO> sesion = busquedaFacadeDelegate.mostrarSesiones();
		//List<CineVO> cines=busquedaFacadeDelegate.;
		List<CineVO> cine = busquedaFacadeDelegate.buscaCines();
		//for (SesionVO sesionVO : sesion) {

		//CineVO cineVO = busquedaFacadeDelegate.buscaCine(sesionVO.getIdCine());
		//cine.add(cineVO);

		//}

		request.setAttribute("peliculas", pelicula);
		request.setAttribute("listasesion", sesion);
		request.setAttribute("cines", cine);

		return mapping.findForward("BusquedaForm");

	}

}
