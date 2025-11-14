package DAO;

import BaseDatos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.InspeccionFitosanitaria;

/**
 * DAO para la entidad InspeccionFitosanitaria.
 * Gestiona operaciones CRUD sobre la tabla INSPECCION_FITOSANITARIA.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 */
public class InspeccionFitosanitariaDAO extends DAOBase<InspeccionFitosanitaria> {
	
	public InspeccionFitosanitariaDAO() {
		super();
	}
	
	public InspeccionFitosanitariaDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	public long crear(InspeccionFitosanitaria inspeccion) throws SQLException {
		String sql = "INSERT INTO INSPECCION_FITOSANITARIA (ID_CULTIVO, ID_ASISTENTE_TECNICO, FECHA_INSPECCION, ESTADO) " +
					 "VALUES (?, ?, SYSDATE, 'PENDIENTE')";
		Object[] params = {null, null};
		return executeInsertWithGeneratedKey(sql, params);
	}
	
	public InspeccionFitosanitaria obtenerPorId(long id) throws SQLException {
		String sql = "SELECT * FROM INSPECCION_FITOSANITARIA WHERE ID_INSPECCION = ?";
		Object[] params = {id};
		return findOneWithParams(sql, params, this::mapInspeccion);
	}
	
	public List<InspeccionFitosanitaria> obtenerTodos() throws SQLException {
		String sql = "SELECT * FROM INSPECCION_FITOSANITARIA";
		return findMany(sql, this::mapInspeccion);
	}
	
	public List<InspeccionFitosanitaria> obtenerPorEstado(String estado) throws SQLException {
		String sql = "SELECT * FROM INSPECCION_FITOSANITARIA WHERE ESTADO = ?";
		Object[] params = {estado};
		return findManyWithParams(sql, params, this::mapInspeccion);
	}
	
	public int actualizar(InspeccionFitosanitaria inspeccion) throws SQLException {
		String sql = "UPDATE INSPECCION_FITOSANITARIA SET ESTADO = ? WHERE ID_INSPECCION = ?";
		Object[] params = {"COMPLETADA", inspeccion.getId()};
		return executeUpdate(sql, params);
	}
	
	public int cambiarEstado(long id, String estado) throws SQLException {
		String sql = "UPDATE INSPECCION_FITOSANITARIA SET ESTADO = ? WHERE ID_INSPECCION = ?";
		Object[] params = {estado, id};
		return executeUpdate(sql, params);
	}
	
	public int eliminar(long id) throws SQLException {
		String sql = "DELETE FROM INSPECCION_FITOSANITARIA WHERE ID_INSPECCION = ?";
		Object[] params = {id};
		return executeUpdate(sql, params);
	}
	
	private InspeccionFitosanitaria mapInspeccion(ResultSet rs) throws SQLException {
		InspeccionFitosanitaria inspeccion = new InspeccionFitosanitaria();
		inspeccion.setId(rs.getString("ID_INSPECCION"));
		return inspeccion;
	}
}
