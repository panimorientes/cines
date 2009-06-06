package javi.model.adminfacade.plain.actions;

import java.sql.Connection;

import javi.model.butaca.dao.SQLButacaDAO;
import javi.model.butaca.dao.SQLButacaDAOFactory;
import javi.model.butaca.vo.ButacaVO;
import javi.model.cine.dao.SQLCineDAO;
import javi.model.cine.dao.SQLCineDAOFactory;
import javi.model.cine.vo.CineVO;
import javi.model.sala.dao.SQLSalaDAO;
import javi.model.sala.dao.SQLSalaDAOFactory;
import javi.model.sala.vo.SalaVO;

import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.TransactionalPlainAction;

public class AnadirSalaAction 
    implements TransactionalPlainAction {

    private Long idSala;
    private Long numFilas;
    private Long asientos;
    private String cine;	
	private boolean [] array;
  
    
    public AnadirSalaAction(Long idSala, Long numFilas, Long asientos, String cine,boolean [] array) {
        
        this.idSala = idSala;
        this.numFilas = numFilas;
        this.asientos = asientos;
        this.cine = cine;
        this.array = array;
        
        
    }
    
    /**
     *
     * @return <code>null</code>
     * @throws DuplicateInstanceException 
     */
    public Object execute(Connection connection) 
        throws InstanceNotFoundException, InternalErrorException, DuplicateInstanceException {
                
        SQLCineDAO CineDAO = SQLCineDAOFactory.getDAO();  
    	SQLButacaDAO adminButacaDAO = SQLButacaDAOFactory.getDAO();    
        SQLSalaDAO adminSalaDAO = SQLSalaDAOFactory.getDAO();  
        
        CineVO cineVO = CineDAO.find1(connection,cine);
        ButacaVO adminButacaVO = new ButacaVO(numFilas,asientos,false,idSala, cineVO.getIdCine());
        SalaVO adminSalaVO = new SalaVO(idSala,numFilas, asientos, cineVO.getIdCine());
        
            
        adminSalaDAO.create(connection, adminSalaVO);
        adminButacaDAO.create(connection, adminButacaVO, array);
        
        
        
        return null;            

    }
    
    /* 
     * This class is tested by
     * "es.udc.fbellas.j2ee.miniportal.model.userfacade.delegate.
     * UserFacadeDelegateFactory".
     */    

}
