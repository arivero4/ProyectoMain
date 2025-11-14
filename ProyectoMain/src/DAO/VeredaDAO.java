package DAO;

import BaseDatos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Vereda;

/**
 * DAO para la entidad Vereda.
 * Gestiona operaciones CRUD sobre la tabla VEREDA.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 */
public class VeredaDAO extends DAOBase<Vereda> {
	
	public VeredaDAO() {
		super();
	}
	
	public VeredaDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	public long crear(Vereda vereda) throws SQLException {
		String sql = "INSERT INTO VEREDA (ID_MUNICIPIO, NOMBRE, CODIGO) " +
					 "VALUES (?, ?, ?)";
		Object[] params = {null, vereda.getNombre(), vereda.getId()};
		return executeInsertWithGeneratedKey(sql, params);
	}
	
	public Vereda obtenerPorId(long id) throws SQLException {
		String sql = "SELECT * FROM VEREDA WHERE ID_VEREDA = ?";
		Object[] params = {id};
		return findOneWithParams(sql, params, this::mapVereda);
	}
	
	public List<Vereda> obtenerTodos() throws SQLException {
		String sql = "SELECT * FROM VEREDA";
		return findMany(sql, this::mapVereda);
	}
	
	public int actualizar(Vereda vereda) throws SQLException {
		String sql = "UPDATE VEREDA SET NOMBRE = ? WHERE ID_VEREDA = ?";
		Object[] params = {vereda.getNombre(), vereda.getId()};
		return executeUpdate(sql, params);
	}
	
	public int eliminar(long id) throws SQLException {
		String sql = "DELETE FROM VEREDA WHERE ID_VEREDA = ?";
		Object[] params = {id};
		return executeUpdate(sql, params);
	}
	
	private Vereda mapVereda(ResultSet rs) throws SQLException {
		Vereda vereda = new Vereda();
		vereda.setId(rs.getString("ID_VEREDA"));
		vereda.setNombre(rs.getString("NOMBRE"));
		return vereda;
	}
}
