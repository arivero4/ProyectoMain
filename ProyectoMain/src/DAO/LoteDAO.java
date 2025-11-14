package DAO;

import BaseDatos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Lote;

/**
 * DAO para la entidad Lote.
 * Gestiona operaciones CRUD sobre la tabla LOTE.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 */
public class LoteDAO extends DAOBase<Lote> {
	
	public LoteDAO() {
		super();
	}
	
	public LoteDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	public long crear(Lote lote) throws SQLException {
		String sql = "INSERT INTO LOTE (ID_LUGAR_PRODUCCION, NUMERO_LOTE, AREA_HECTAREAS) " +
					 "VALUES (?, ?, ?)";
		Object[] params = {null, lote.getId(), null};
		return executeInsertWithGeneratedKey(sql, params);
	}
	
	public Lote obtenerPorId(long id) throws SQLException {
		String sql = "SELECT * FROM LOTE WHERE ID_LOTE = ?";
		Object[] params = {id};
		return findOneWithParams(sql, params, this::mapLote);
	}
	
	public List<Lote> obtenerTodos() throws SQLException {
		String sql = "SELECT * FROM LOTE";
		return findMany(sql, this::mapLote);
	}
	
	public int actualizar(Lote lote) throws SQLException {
		String sql = "UPDATE LOTE SET NUMERO_LOTE = ?, AREA_HECTAREAS = ? WHERE ID_LOTE = ?";
		Object[] params = {lote.getId(), null, lote.getId()};
		return executeUpdate(sql, params);
	}
	
	public int eliminar(long id) throws SQLException {
		String sql = "DELETE FROM LOTE WHERE ID_LOTE = ?";
		Object[] params = {id};
		return executeUpdate(sql, params);
	}
	
	private Lote mapLote(ResultSet rs) throws SQLException {
		Lote lote = new Lote();
		lote.setId(rs.getString("ID_LOTE"));
		return lote;
	}
}
