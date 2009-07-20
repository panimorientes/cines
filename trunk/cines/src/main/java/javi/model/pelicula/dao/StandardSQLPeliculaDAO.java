package javi.model.pelicula.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javi.model.busquedafacade.vo.PeliculaInfoVO;
import javi.model.pelicula.vo.PeliculaVO;
import es.udc.fbellas.j2ee.util.exceptions.InstanceNotFoundException;
import es.udc.fbellas.j2ee.util.exceptions.InternalErrorException;
import es.udc.fbellas.j2ee.util.sql.GeneralOperations;


public abstract class StandardSQLPeliculaDAO implements SQLPeliculaDAO {


	public boolean exists(Connection connection, Long idPelicula)
	throws InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT idPelicula FROM PELICULA" +
			" WHERE idPelicula = ?";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, idPelicula);

			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			return resultSet.next();

		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}

	}

	public PeliculaVO find(Connection connection, String nombre)
	throws InstanceNotFoundException, InternalErrorException {
		
		PeliculaVO pelicula = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT idPelicula, director, clasificacion, descripcion FROM PELICULA " +
			"WHERE titulo LIKE ? ";

			preparedStatement = connection.prepareStatement(queryString);

			
			preparedStatement.setString(1, nombre);


			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			if(!resultSet.next()){
				//throw new InstanceNotFoundException(PeliculaVO.class,PeliculaVO.class.getName());
			}else{
				
				int i=1;
				long idPelicula = resultSet.getLong(i++);
				String director = resultSet.getString(i++);
				String clasificacion = resultSet.getString(i++);
				String descripcion = resultSet.getString(i++);

				pelicula = new PeliculaVO(idPelicula, nombre, director, clasificacion, descripcion);
			
			}

		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}  
		
		/* Return the value object. */
		return pelicula; 


	}
	
	public PeliculaVO find(Connection connection,long idPelicula)
	throws InstanceNotFoundException, InternalErrorException {
		
		PeliculaVO pelicula = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT titulo, director, clasificacion, descripcion FROM PELICULA " +
			"WHERE idPelicula = ? ";

			preparedStatement = connection.prepareStatement(queryString);

			
			preparedStatement.setLong(1, idPelicula);


			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			if(!resultSet.next()){
				//throw new InstanceNotFoundException(PeliculaVO.class,PeliculaVO.class.getName());
			}else{
				
				int i=1;
				String titulo = resultSet.getString(i++);
				String director = resultSet.getString(i++);
				String clasificacion = resultSet.getString(i++);
				String descripcion = resultSet.getString(i++);

				pelicula = new PeliculaVO(idPelicula, titulo, director, clasificacion, descripcion);
			
			}

		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}  
		
		/* Return the value object. */
		return pelicula; 


	}
	
	//XXX que sentido tiene este metodo??devuelve solo la 1ª pelicula de la BDs??
	public PeliculaVO find1(Connection connection)
	throws InstanceNotFoundException, InternalErrorException {
		
		PeliculaVO pelicula = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT idPelicula,titulo, director, clasificacion, descripcion FROM PELICULA ";

			preparedStatement = connection.prepareStatement(queryString);


			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			if(!resultSet.next()){
				//throw new InstanceNotFoundException(PeliculaVO.class,PeliculaVO.class.getName());
			}else{
				
				int i=1;
				long idPelicula = resultSet.getLong(i++);
				String titulo = resultSet.getString(i++);
				String director = resultSet.getString(i++);
				String clasificacion = resultSet.getString(i++);
				String descripcion = resultSet.getString(i++);

				pelicula = new PeliculaVO(idPelicula, titulo, director, clasificacion, descripcion);
			
			}

		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}  
		
		/* Return the value object. */
		return pelicula; 


	}
	
	
	public List<PeliculaVO> find(Connection connection)
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<PeliculaVO> peliculas = new ArrayList<PeliculaVO>();

		try {

			/* Create "preparedStatement". */
			String queryString = "SELECT idPelicula, titulo, director, clasificacion, descripcion FROM PELICULA";

			preparedStatement = connection.prepareStatement(queryString);


			/* Execute query. */
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				int i=1;
				long idPelicula = resultSet.getLong(i++);
				String titulo = resultSet.getString(i++);
				String director = resultSet.getString(i++);
				String clasificacion = resultSet.getString(i++);
				String descripcion = resultSet.getString(i++);

				peliculas.add(new PeliculaVO(idPelicula, titulo, director, clasificacion, descripcion));
			}



			/* Return the value object. */
			return peliculas; 


		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}
	
	//XXX creo que este es el metodo que funciona mal
	//	  en el codigo hace cosas raras por haber hardcodeado las categorias
	public List<PeliculaInfoVO> find(Connection connection, String titulo, String categoria)
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<PeliculaInfoVO> peliculas = new ArrayList<PeliculaInfoVO>();

		try {
			
			if(categoria.equals(" ") && (!titulo.equals("".trim()))){
				String queryString = "SELECT p.idPelicula,p.titulo,s.idSesion,s.fecha,s.hora,c.idCine,c.nombre FROM PELICULA as p, SESION as s, CINE as c " +
				"WHERE p.idPelicula = s.idPelicula AND s.idCine = c.idCine AND p.titulo LIKE ?";
				
				preparedStatement = connection.prepareStatement(queryString);
				
				int i=1;
				preparedStatement.setString(i++, "%"+titulo+"%");
				
				/* Execute query. */
				
				

			} else {
				if (titulo.equals("".trim()) && (!categoria.equals(" "))){
					String queryString = "SELECT p.idPelicula,p.titulo,s.idSesion,s.fecha,s.hora,c.idCine,c.nombre FROM PELICULA as p, SESION as s, CINE as c " +
					"WHERE p.idPelicula = s.idPelicula AND s.idCine = c.idCine AND p.clasificacion LIKE ?";
					
					preparedStatement = connection.prepareStatement(queryString);
					
					int i=1;
					preparedStatement.setString(i++, categoria);
					
			
				} else { 
					if(!categoria.equals(" ") && (!titulo.equals("".trim()))){
						String queryString = "SELECT p.idPelicula,p.titulo,s.idSesion,s.fecha,s.hora,c.idCine,c.nombre FROM PELICULA as p, SESION as s, CINE as c " +
						"WHERE p.idPelicula = s.idPelicula AND s.idCine = c.idCine AND p.clasificacion LIKE ? AND p.titulo LIKE ?";
						
						preparedStatement = connection.prepareStatement(queryString);
						
						int i=1;
						preparedStatement.setString(i++, categoria);
						preparedStatement.setString(i++, "%"+titulo+"%");
						
					}
				}
			}
			
			resultSet = preparedStatement.executeQuery();
			
			int i;
			while(resultSet.next()){
				i=1;
				long idPelicula = resultSet.getLong(i++);
				String tit = resultSet.getString(i++);
				long idSesion = resultSet.getLong(i++);
				Calendar fechaSesion = Calendar.getInstance();
				fechaSesion.setTime(resultSet.getTimestamp(i++));
				Time hora = resultSet.getTime(i++);
				long idCine = resultSet.getLong(i++);
				String nombreCine = resultSet.getString(i++);

				peliculas.add(new PeliculaInfoVO(idPelicula, tit, idSesion, fechaSesion, hora, idCine, nombreCine));
						
				
			}


			/* Return the value object. */
			return peliculas; 


		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}
	
	
	//XXX creo que este es el metodo que funciona mal
	//	  en el codigo hace cosas raras por haber hardcodeado las categorias
	public List<PeliculaInfoVO> find(Connection connection, String titulo, String categoria, Calendar fecha)
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<PeliculaInfoVO> peliculas = new ArrayList<PeliculaInfoVO>();

		try {
			Timestamp date = new Timestamp(fecha.getTime().getTime());

			if(categoria.equals(" ") && (!titulo.equals("".trim()))){
				String queryString = "SELECT p.idPelicula,p.titulo,s.idSesion,s.fecha,s.hora,c.idCine,c.nombre FROM PELICULA as p, SESION as s, CINE as c " +
				"WHERE p.idPelicula = s.idPelicula AND s.idCine = c.idCine AND s.fecha = ? AND p.titulo LIKE ?";
				
				preparedStatement = connection.prepareStatement(queryString);
				
				int i=1;
				preparedStatement.setTimestamp(i++, date);
				preparedStatement.setString(i++, "%"+titulo+"%");
				
				/* Execute query. */
				
				

			} else {
				if (titulo.equals("".trim()) && (!categoria.equals(" "))){
					String queryString = "SELECT p.idPelicula,p.titulo,s.idSesion,s.fecha,s.hora,c.idCine,c.nombre FROM PELICULA as p, SESION as s, CINE as c " +
					"WHERE p.idPelicula = s.idPelicula AND s.idCine = c.idCine AND s.fecha = ? AND p.clasificacion LIKE ?";
					
					preparedStatement = connection.prepareStatement(queryString);
					
					int i=1;
					preparedStatement.setTimestamp(i++, date);
					preparedStatement.setString(i++, categoria);
					
			
				} else { 
					if(!categoria.equals(" ") && (!titulo.equals("".trim()))){
						String queryString = "SELECT p.idPelicula,p.titulo,s.idSesion,s.fecha,s.hora,c.idCine,c.nombre FROM PELICULA as p, SESION as s, CINE as c " +
						"WHERE p.idPelicula = s.idPelicula AND s.idCine = c.idCine AND s.fecha = ? AND p.clasificacion LIKE ? AND p.titulo LIKE ?";
						
						preparedStatement = connection.prepareStatement(queryString);
						
						int i=1;
						preparedStatement.setTimestamp(i++, date);
						preparedStatement.setString(i++, categoria);
						preparedStatement.setString(i++, "%"+titulo+"%");
						
					}
				}
			}
			
			resultSet = preparedStatement.executeQuery();
			
			int i;
			while(resultSet.next()){
				i=1;
				long idPelicula = resultSet.getLong(i++);
				String tit = resultSet.getString(i++);
				long idSesion = resultSet.getLong(i++);
				Calendar fechaSesion = Calendar.getInstance();
				fechaSesion.setTime(resultSet.getTimestamp(i++));
				Time hora = resultSet.getTime(i++);
				long idCine = resultSet.getLong(i++);
				String nombreCine = resultSet.getString(i++);

				peliculas.add(new PeliculaInfoVO(idPelicula, tit, idSesion, fechaSesion, hora, idCine, nombreCine));
						
			}


			/* Return the value object. */
			return peliculas; 


		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeResultSet(resultSet);
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}



	public void update(Connection connection, PeliculaVO userPeliculaVO) 
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;

		try {

			/* Create "preparedStatement". */
			
			//XXX de donde sale Preferencia??
			String queryString = "UPDATE PELICULA" +
			" SET idPelicula = ?, titulo = ?, director = ?, clasificacion = ?, descripcion = ?, Preferencia = ?," +
			" WHERE titulo = ?";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, userPeliculaVO.getIdPelicula());
			preparedStatement.setString(i++, userPeliculaVO.getTitulo());
			preparedStatement.setString(i++, userPeliculaVO.getDirector());
			preparedStatement.setString(i++, userPeliculaVO.getClasificacion());
			preparedStatement.setString(i++, userPeliculaVO.getDescripcion());
			//preparedStatement.setInt(i++, adminPeliculaVO.getPreferencia());

			/* Execute query. */
			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new InstanceNotFoundException(
						userPeliculaVO.getTitulo(), 
						PeliculaVO.class.getName());
			}

			if (updatedRows > 1) {
				throw new SQLException("Duplicate row for login = '" + 
						userPeliculaVO.getTitulo() + "' in table 'PELICULA'");
			}        

		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}            

	}

	public void remove(Connection connection, String titulo) 
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "DELETE FROM PELICULA WHERE" +
			" titulo = ?";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setString(i++, titulo);

			/* Execute query. */
			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException(titulo,
						PeliculaVO.class.getName());
			}

		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}
	
	public void remove(Connection connection, Long idPelicula) 
	throws InstanceNotFoundException, InternalErrorException {

		PreparedStatement preparedStatement = null;

		try {

			/* Create "preparedStatement". */
			String queryString = "DELETE FROM PELICULA WHERE" +
			" idPelicula = ?";
			preparedStatement = connection.prepareStatement(queryString);

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, idPelicula);

			/* Execute query. */
			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException(idPelicula,
						PeliculaVO.class.getName());
			}

		} catch (SQLException e) {
			throw new InternalErrorException(e);    
		} finally {
			GeneralOperations.closeStatement(preparedStatement);
		}    

	}


}
