package javi.http.view.actionforms;

import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import javi.http.view.applicationobjects.DateRanges;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.struts.action.DefaultActionForm;
import es.udc.fbellas.j2ee.util.struts.action.PropertyValidator;

public class BusquedaForm extends DefaultActionForm {

    private String clave;
    private String clasificacion;
    private boolean byDate;
    private String startDay;
	private String startMonth;
	private String startYear;
    
    public BusquedaForm() {
        reset();
    }

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getStartDay() {
		return startDay;
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
	
	public Calendar getStartDate() {
        return getCalendar(Integer.parseInt(startDay), 
            Integer.parseInt(startMonth), Integer.parseInt(startYear), true);
    }
	
	public boolean getByDate() {
		return byDate;
	}

	public void setByDate(boolean byDate) {
		this.byDate = byDate;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
        reset();
    }
	
	private void reset() {
		Calendar calendar = Calendar.getInstance();
    	clave = null;
    	clasificacion = null;
    	startDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        startMonth = Integer.toString(
            calendar.get(Calendar.MONTH) -  Calendar.JANUARY + 1);
        startYear = Integer.toString(calendar.get(Calendar.YEAR));
   		byDate = false;
    }

	
	public ActionErrors validate(ActionMapping mapping,
	        HttpServletRequest request) {
	        
	        ActionErrors errors = new ActionErrors();

	        DateRanges dateRanges = (DateRanges)
	            getServlet().getServletConfig().getServletContext().
	                getAttribute("dateRanges");
	        
	        if (byDate) { 
	        	PropertyValidator.validateLong(errors, "startDay", startDay, true, 
	        			DateRanges.FIRST_DAY, DateRanges.LAST_DAY);
	        	PropertyValidator.validateLong(errors, "startMonth", startMonth, true, 
	        			DateRanges.FIRST_MONTH, DateRanges.LAST_MONTH);
	        	PropertyValidator.validateLong(errors, "startYear", startYear, true, 
	        			DateRanges.FIRST_YEAR, dateRanges.getLastYear());
			}
	        
	        return errors;
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
