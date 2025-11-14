package DAO;

import BaseDatos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Plaga;

/**
 * DAO para la entidad Plaga.
 * Gestiona operaciones CRUD sobre la tabla PLAGA.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 */
public class PlagaDAO extends DAOBase<Plaga> {
	
	public PlagaDAO() {
		super();
	}
	
	public PlagaDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	public long crear(Plaga plaga) throws SQLException {
		String sql = "INSERT INTO PLAGA (NOMBRE_COMUN, NOMBRE_CIENTIFICO, DESCRIPCION, NIVEL_PELIGROSIDAD) " +
					 "VALUES (?, ?, ?, ?)";
		Object[] params = {plaga.getNombreComun(), plaga.getNombreCientifico(), plaga.getDescripcion(), "MEDIA"};
		return executeInsertWithGeneratedKey(sql, params);
	}
	
	public Plaga obtenerPorId(long id) throws SQLException {
		String sql = "SELECT * FROM PLAGA WHERE ID_PLAGA = ?";
		Object[] params = {id};
		return findOneWithParams(sql, params, this::mapPlaga);
	}
	
	public List<Plaga> obtenerTodos() throws SQLException {
		String sql = "SELECT * FROM PLAGA";
		return findMany(sql, this::mapPlaga);
	}
	
	public int actualizar(Plaga plaga) throws SQLException {
		String sql = "UPDATE PLAGA SET NOMBRE_COMUN = ?, DESCRIPCION = ? WHERE ID_PLAGA = ?";
		Object[] params = {plaga.getNombreComun(), plaga.getDescripcion(), plaga.getId()};
		return executeUpdate(sql, params);
	}
	
	public int eliminar(long id) throws SQLException {
		String sql = "DELETE FROM PLAGA WHERE ID_PLAGA = ?";
		Object[] params = {id};
		return executeUpdate(sql, params);
	}
	
	private Plaga mapPlaga(ResultSet rs) throws SQLException {
		Plaga plaga = new Plaga();
		plaga.setId(rs.getString("ID_PLAGA"));
		return plaga;
	}
}
