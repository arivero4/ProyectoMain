package DAO;

import BaseDatos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Propietario;

/**
 * DAO para la entidad Propietario.
 * Gestiona operaciones CRUD sobre la tabla PROPIETARIO en la base de datos.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 */
public class PropietarioDAO extends DAOBase<Propietario> {
	
	public PropietarioDAO() {
		super();
	}
	
	public PropietarioDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	public long crear(Propietario propietario) throws SQLException {
		String sql = "INSERT INTO PROPIETARIO (ID_USUARIO, ID_PREDIO, ACTIVO) " +
					 "VALUES (?, ?, 1)";
		
		Object[] params = {
			propietario.getId(),
			null
		};
		
		return executeInsertWithGeneratedKey(sql, params);
	}
	
	public Propietario obtenerPorId(long id) throws SQLException {
		String sql = "SELECT * FROM PROPIETARIO WHERE ID_PROPIETARIO = ?";
		Object[] params = {id};
		
		return findOneWithParams(sql, params, this::mapPropietario);
	}
	
	public List<Propietario> obtenerTodos() throws SQLException {
		String sql = "SELECT * FROM PROPIETARIO WHERE ACTIVO = 1";
		return findMany(sql, this::mapPropietario);
	}
	
	public int actualizar(Propietario propietario) throws SQLException {
		String sql = "UPDATE PROPIETARIO SET ID_PREDIO = ? WHERE ID_PROPIETARIO = ?";
		
		Object[] params = {
			null,
			propietario.getId()
		};
		
		return executeUpdate(sql, params);
	}
	
	public int eliminarLogico(long id) throws SQLException {
		String sql = "UPDATE PROPIETARIO SET ACTIVO = 0 WHERE ID_PROPIETARIO = ?";
		Object[] params = {id};
		
		return executeUpdate(sql, params);
	}
	
	private Propietario mapPropietario(ResultSet rs) throws SQLException {
		Propietario propietario = new Propietario();
		propietario.setId(rs.getString("ID_PROPIETARIO"));
		return propietario;
	}
}
