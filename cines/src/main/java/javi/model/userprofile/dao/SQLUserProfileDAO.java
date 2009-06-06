package javi.model.userprofile.dao;

import java.sql.Connection;

import javi.model.userprofile.vo.UserProfileVO;
import es.udc.fbellas.j2ee.util.exceptions.DuplicateInstanceException;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;

public interface SQLUserProfileDAO {

    public void create(Connection connection, UserProfileVO userProfile)
        throws DuplicateInstanceException, InternalErrorException;
        
    public boolean exists(Connection connection, String loginName)
        throws InternalErrorException;
        
    public UserProfileVO find(Connection connection, String loginName)
        throws InstanceNotFoundException, InternalErrorException;
        
    public void update(Connection connection, UserProfileVO userProfile) 
        throws InstanceNotFoundException, InternalErrorException;
        
    public void remove(Connection connection, String loginName) 
        throws InstanceNotFoundException, InternalErrorException;

}
