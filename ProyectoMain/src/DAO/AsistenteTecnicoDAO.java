package DAO;

import BaseDatos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.AsistenteTecnico;

/**
 * DAO para la entidad AsistenteTecnico.
 * Gestiona operaciones CRUD sobre la tabla ASISTENTE_TECNICO en la base de datos.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 */
public class AsistenteTecnicoDAO extends DAOBase<AsistenteTecnico> {
	
	public AsistenteTecnicoDAO() {
		super();
	}
	
	public AsistenteTecnicoDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	public long crear(AsistenteTecnico asistente) throws SQLException {
		String sql = "INSERT INTO ASISTENTE_TECNICO (ID_USUARIO, ESPECIALIDAD, ACTIVO) " +
					 "VALUES (?, ?, 1)";
		
		Object[] params = {
			asistente.getId(),
			"GENERAL"
		};
		
		return executeInsertWithGeneratedKey(sql, params);
	}
	
	public AsistenteTecnico obtenerPorId(long id) throws SQLException {
		String sql = "SELECT * FROM ASISTENTE_TECNICO WHERE ID_ASISTENTE_TECNICO = ?";
		Object[] params = {id};
		
		return findOneWithParams(sql, params, this::mapAsistente);
	}
	
	public List<AsistenteTecnico> obtenerTodos() throws SQLException {
		String sql = "SELECT * FROM ASISTENTE_TECNICO WHERE ACTIVO = 1";
		return findMany(sql, this::mapAsistente);
	}
	
	public int actualizar(AsistenteTecnico asistente) throws SQLException {
		String sql = "UPDATE ASISTENTE_TECNICO SET ESPECIALIDAD = ? WHERE ID_ASISTENTE_TECNICO = ?";
		
		Object[] params = {
			"GENERAL",
			asistente.getId()
		};
		
		return executeUpdate(sql, params);
	}
	
	public int eliminarLogico(long id) throws SQLException {
		String sql = "UPDATE ASISTENTE_TECNICO SET ACTIVO = 0 WHERE ID_ASISTENTE_TECNICO = ?";
		Object[] params = {id};
		
		return executeUpdate(sql, params);
	}
	
	private AsistenteTecnico mapAsistente(ResultSet rs) throws SQLException {
		AsistenteTecnico asistente = new AsistenteTecnico();
		asistente.setId(rs.getString("ID_ASISTENTE_TECNICO"));
		return asistente;
	}
}
