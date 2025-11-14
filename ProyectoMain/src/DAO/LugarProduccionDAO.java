package DAO;

import BaseDatos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.LugarProduccion;

/**
 * DAO para la entidad LugarProduccion.
 * Gestiona operaciones CRUD sobre la tabla LUGAR_PRODUCCION.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 */
public class LugarProduccionDAO extends DAOBase<LugarProduccion> {
	
	public LugarProduccionDAO() {
		super();
	}
	
	public LugarProduccionDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	public long crear(LugarProduccion lugar) throws SQLException {
		String sql = "INSERT INTO LUGAR_PRODUCCION (ID_PREDIO, NOMBRE, TIPO_CULTIVO) " +
					 "VALUES (?, ?, ?)";
		Object[] params = {null, lugar.getId(), "GENERAL"};
		return executeInsertWithGeneratedKey(sql, params);
	}
	
	public LugarProduccion obtenerPorId(long id) throws SQLException {
		String sql = "SELECT * FROM LUGAR_PRODUCCION WHERE ID_LUGAR_PRODUCCION = ?";
		Object[] params = {id};
		return findOneWithParams(sql, params, this::mapLugar);
	}
	
	public List<LugarProduccion> obtenerTodos() throws SQLException {
		String sql = "SELECT * FROM LUGAR_PRODUCCION";
		return findMany(sql, this::mapLugar);
	}
	
	public int actualizar(LugarProduccion lugar) throws SQLException {
		String sql = "UPDATE LUGAR_PRODUCCION SET TIPO_CULTIVO = ? WHERE ID_LUGAR_PRODUCCION = ?";
		Object[] params = {"GENERAL", lugar.getId()};
		return executeUpdate(sql, params);
	}
	
	public int eliminar(long id) throws SQLException {
		String sql = "DELETE FROM LUGAR_PRODUCCION WHERE ID_LUGAR_PRODUCCION = ?";
		Object[] params = {id};
		return executeUpdate(sql, params);
	}
	
	private LugarProduccion mapLugar(ResultSet rs) throws SQLException {
		LugarProduccion lugar = new LugarProduccion();
		lugar.setId(rs.getString("ID_LUGAR_PRODUCCION"));
		return lugar;
	}
}
