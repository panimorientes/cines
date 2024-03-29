package javi.http.controller.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

/**
 * A base clase for all preprocessing filters. Usually, concrete filters only
 * need to provide an implementation of <code>doProcess</code>. 
 */
public abstract class PreProcessingFilter {

    private PreProcessingFilter nextFilter;

    public PreProcessingFilter(PreProcessingFilter nextFilter) {
        this.nextFilter = nextFilter;
    }
    
    /**
     * Calls upon <code>doProcess</code>, and if necessary, continues to call 
     * <code>process</code> on the next filter.
     */
    public ActionForward process(HttpServletRequest request, 
        HttpServletResponse response, Action action, ActionForm form,
        ActionMapping mapping) throws IOException, ServletException {
        
        ActionForward actionForward = null;
        
        /* Process this filter. */
        try {
            actionForward = doProcess(request, response, action, form, mapping);
        } catch (InternalErrorException e) {
            return doOnInternalErrorException(request, response, action, form,
                mapping, e);
        }
        
        /* Process next filter in the chain. */
        if ((actionForward == null) && (nextFilter != null)) {
            return nextFilter.process(request, response, action, form, mapping);
        } else {
            return actionForward;
        }
        
    }

    /**
     * Does the processing of this filter.
     *
     * @return <code>null</code> if the next filter must be processed; an
     *         <code>ActionForward</code> otherwise
     */
    protected abstract ActionForward doProcess(HttpServletRequest request, 
        HttpServletResponse response, Action action, ActionForm form,
        ActionMapping mapping) throws IOException, ServletException,
            InternalErrorException;


    /**
     * Treats an <code>InternalErrorException</code> thrown by
     * <code>doProcess</code>. In particular, it works as follows:
     *
     * <ul>
     * <li>Logs the <code>InternalErrorException</code>.</li>
     * <li>Returns the <code>ActionForward</code> returned by
     * <code>mapping.findForward("InternalError")</code>.</li>
     * </ul>
     *
     */
    protected ActionForward doOnInternalErrorException(HttpServletRequest request, 
        HttpServletResponse response, Action action, ActionForm form,
        ActionMapping mapping, InternalErrorException internalErrorException)
        throws IOException, ServletException {

        /* 
         * Log error, even with debug level <= 0, because it is a severe
         * error. 
         */
        ServletContext servletContext = 
            action.getServlet().getServletConfig().getServletContext();
        servletContext.log(internalErrorException.getMessage(), 
            internalErrorException);

        /* Redirect to input page. */
        return mapping.findForward("InternalError");
        
    }    

}
