package DAO;

import BaseDatos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Predio;

/**
 * DAO para la entidad Predio.
 * Gestiona operaciones CRUD sobre la tabla PREDIO.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 */
public class PredioDAO extends DAOBase<Predio> {
	
	public PredioDAO() {
		super();
	}
	
	public PredioDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	public long crear(Predio predio) throws SQLException {
		String sql = "INSERT INTO PREDIO (ID_PROPIETARIO, ID_VEREDA, NUMERO_PREDIAL, DIRECCION, AREA_HECTAREAS) " +
					 "VALUES (?, ?, ?, ?, ?)";
		Object[] params = {null, null, predio.getNumeroPredial(), predio.getDireccion(), predio.getArea()};
		return executeInsertWithGeneratedKey(sql, params);
	}
	
	public Predio obtenerPorId(long id) throws SQLException {
		String sql = "SELECT * FROM PREDIO WHERE ID_PREDIO = ?";
		Object[] params = {id};
		return findOneWithParams(sql, params, this::mapPredio);
	}
	
	public List<Predio> obtenerTodos() throws SQLException {
		String sql = "SELECT * FROM PREDIO";
		return findMany(sql, this::mapPredio);
	}
	
	public int actualizar(Predio predio) throws SQLException {
		String sql = "UPDATE PREDIO SET NUMERO_PREDIAL = ?, DIRECCION = ?, AREA_HECTAREAS = ? WHERE ID_PREDIO = ?";
		Object[] params = {predio.getNumeroPredial(), predio.getDireccion(), predio.getArea(), predio.getId()};
		return executeUpdate(sql, params);
	}
	
	public int eliminar(long id) throws SQLException {
		String sql = "DELETE FROM PREDIO WHERE ID_PREDIO = ?";
		Object[] params = {id};
		return executeUpdate(sql, params);
	}
	
	private Predio mapPredio(ResultSet rs) throws SQLException {
		Predio predio = new Predio();
		predio.setId(rs.getString("ID_PREDIO"));
		predio.setNumeroPredial(rs.getString("NUMERO_PREDIAL"));
		predio.setDireccion(rs.getString("DIRECCION"));
		predio.setArea(rs.getFloat("AREA_HECTAREAS"));
		return predio;
	}
}
