package javi.http.view.actionforms;

import java.sql.Time;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import javi.http.view.applicationobjects.DateRanges;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;
import es.udc.fbellas.j2ee.util.struts.action.PropertyValidator;

public class AnadirSesionForm extends DefaultActionForm {

	 private String startDay;
	 private String startMonth;
	 private String startYear;
	 private Time hora;
	 private String precio;
	 private double precioAsLong;
	 private boolean numerada;
	 private String titulo;
	 private String idSala;
	 private Long idSalaAsLong;
	 private String nombre;
    
    public AnadirSesionForm() {
        reset();
    }
   
	public String getStartDay() {
		return startDay;
	}





	public Long getIdSalaAsLong() {
		return idSalaAsLong;
	}

	public void setIdSalaAsLong(Long idSalaAsLong) {
		this.idSalaAsLong = idSalaAsLong;
	}

	

	public double getPrecioAsLong() {
		return precioAsLong;
	}

	public void setPrecioAsLong(double precioAsLong) {
		this.precioAsLong = precioAsLong;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay.trim();
	}





	public String getStartMonth() {
		return startMonth;
	}





	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth.trim();
	}





	public String getStartYear() {
		return startYear;
	}





	public void setStartYear(String startYear) {
		this.startYear = startYear.trim();
	}




	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdSala() {
		return idSala;
	}





	public void setIdSala(String idSala) {
		this.idSala = idSala.trim();
	}




	public boolean isNumerada() {
		return numerada;
	}





	public void setNumerada(boolean numerada) {
		this.numerada = numerada;
	}





	public String getPrecio() {
		return precio;
	}





	public void setPrecio(String precio) {
		this.precio = precio.trim();
	}





	public String getTitulo() {
		return titulo;
	}


	 public Calendar getStartDate() {
	        return getCalendar(Integer.parseInt(startDay), 
	            Integer.parseInt(startMonth), Integer.parseInt(startYear), true);
	    }
	    
	 

	public void setTitulo(String titulo) {
		this.titulo = titulo.trim();
	}





	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }
    
    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();

        
        DateRanges dateRanges = (DateRanges)
            getServlet().getServletConfig().getServletContext().
                getAttribute("dateRanges");

      
        PropertyValidator.validateLong(errors, "startDay", startDay, true, 
            DateRanges.FIRST_DAY, DateRanges.LAST_DAY);
        PropertyValidator.validateLong(errors, "startMonth", startMonth, true, 
            DateRanges.FIRST_MONTH, DateRanges.LAST_MONTH);
        PropertyValidator.validateLong(errors, "startYear", startYear, true, 
            DateRanges.FIRST_YEAR, dateRanges.getLastYear());
        
        precioAsLong = new Double(PropertyValidator.validateDouble(errors, "precio", precio, true, 0D, Double.MAX_VALUE, new Locale("ES")));
        idSalaAsLong = new Long(PropertyValidator.validateLong(errors, "idSala", idSala, true, 1, Long.MAX_VALUE));
        PropertyValidator.validateMandatory(errors, "titulo", titulo);
        PropertyValidator.validateMandatory(errors, "nombre", nombre);
        
        
        return errors;
        
        
    }
    
    private void reset() {
    	
    	 /* Get current date. */
        Calendar calendar = Calendar.getInstance();
    	
        startDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        startMonth = Integer.toString(
            calendar.get(Calendar.MONTH) -  Calendar.JANUARY + 1);
        startYear = Integer.toString(calendar.get(Calendar.YEAR));
        hora=null;
        precio = null;
        numerada = true;
        titulo = null;
        idSala = null;
        nombre = null;
    }
    
    /**
     * Get a <code>Calendar</code> for a start date or an end date,
     * with the given day, month and year. Start dates are generated with
     * fields <code>HOUR</code>, <code>MINUTE</code> and <code>SECOND</code>
     * set to 0, and <code>AM_PM</code> to <code>AM</code>. End dates are 
     * generated with such fields set to 11, 59, 59 and <code>PM</code>, 
     * respectively. This way, this method can be used to generate a start date
     * and an end date that allow to get all account operations made on a given
     * account between these two dates, and including both. Remember that dates
     * are stored in the database with the information contained in a 
     * <code>Calendar</code> (discarding milliseconds), and not only storing 
     * day, month and year.
     *
     * @param day a day (1..31)
     * @param month a month (1..12)
     * @param year a year
     * @param startDate <code>true</code> if start date; <code>false</code> 
     *        otherwise
     * @return the <code>Calendar</code>
     */
    private Calendar getCalendar(int day, int month, int year,
        boolean startDate) {
    
        Calendar date = Calendar.getInstance();
        
        date.clear();
        date.set(Calendar.DAY_OF_MONTH, day);
        date.set(Calendar.MONTH, Calendar.JANUARY + month - 1);
        date.set(Calendar.YEAR, year);
        
        if (!startDate) {
            date.set(Calendar.HOUR, 11);
            date.set(Calendar.MINUTE, 59);
            date.set(Calendar.SECOND, 59);
            date.set(Calendar.AM_PM, Calendar.PM);
        }

        return date;
        
    }


}
