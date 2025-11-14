package DAO;

import BaseDatos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Cultivo;

/**
 * DAO para la entidad Cultivo.
 * Gestiona operaciones CRUD sobre la tabla CULTIVO.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 */
public class CultivoDAO extends DAOBase<Cultivo> {
	
	public CultivoDAO() {
		super();
	}
	
	public CultivoDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	public long crear(Cultivo cultivo) throws SQLException {
		String sql = "INSERT INTO CULTIVO (ID_LOTE, TIPO_CULTIVO, FECHA_SIEMBRA, ESTADO) " +
					 "VALUES (?, ?, SYSDATE, 'ACTIVO')";
		Object[] params = {null, cultivo.getId()};
		return executeInsertWithGeneratedKey(sql, params);
	}
	
	public Cultivo obtenerPorId(long id) throws SQLException {
		String sql = "SELECT * FROM CULTIVO WHERE ID_CULTIVO = ?";
		Object[] params = {id};
		return findOneWithParams(sql, params, this::mapCultivo);
	}
	
	public List<Cultivo> obtenerTodos() throws SQLException {
		String sql = "SELECT * FROM CULTIVO WHERE ESTADO = 'ACTIVO'";
		return findMany(sql, this::mapCultivo);
	}
	
	public int actualizar(Cultivo cultivo) throws SQLException {
		String sql = "UPDATE CULTIVO SET TIPO_CULTIVO = ? WHERE ID_CULTIVO = ?";
		Object[] params = {cultivo.getId(), cultivo.getId()};
		return executeUpdate(sql, params);
	}
	
	public int cambiarEstado(long id, String estado) throws SQLException {
		String sql = "UPDATE CULTIVO SET ESTADO = ? WHERE ID_CULTIVO = ?";
		Object[] params = {estado, id};
		return executeUpdate(sql, params);
	}
	
	public int eliminar(long id) throws SQLException {
		String sql = "DELETE FROM CULTIVO WHERE ID_CULTIVO = ?";
		Object[] params = {id};
		return executeUpdate(sql, params);
	}
	
	private Cultivo mapCultivo(ResultSet rs) throws SQLException {
		Cultivo cultivo = new Cultivo();
		cultivo.setId(rs.getString("ID_CULTIVO"));
		return cultivo;
	}
}
