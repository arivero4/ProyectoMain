package DAO;

import BaseDatos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.ResultadoTecnico;

/**
 * DAO para la entidad ResultadoTecnico.
 * Gestiona operaciones CRUD sobre la tabla RESULTADO_TECNICO.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 */
public class ResultadoTecnicoDAO extends DAOBase<ResultadoTecnico> {
	
	public ResultadoTecnicoDAO() {
		super();
	}
	
	public ResultadoTecnicoDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	public long crear(ResultadoTecnico resultado) throws SQLException {
		String sql = "INSERT INTO RESULTADO_TECNICO (ID_INSPECCION, ID_PLAGA, NIVEL_INFESTACION, RECOMENDACIONES) " +
					 "VALUES (?, ?, ?, ?)";
		Object[] params = {null, null, "BAJO", resultado.getId()};
		return executeInsertWithGeneratedKey(sql, params);
	}
	
	public ResultadoTecnico obtenerPorId(long id) throws SQLException {
		String sql = "SELECT * FROM RESULTADO_TECNICO WHERE ID_RESULTADO = ?";
		Object[] params = {id};
		return findOneWithParams(sql, params, this::mapResultado);
	}
	
	public List<ResultadoTecnico> obtenerTodos() throws SQLException {
		String sql = "SELECT * FROM RESULTADO_TECNICO";
		return findMany(sql, this::mapResultado);
	}
	
	public int actualizar(ResultadoTecnico resultado) throws SQLException {
		String sql = "UPDATE RESULTADO_TECNICO SET NIVEL_INFESTACION = ?, RECOMENDACIONES = ? " +
					 "WHERE ID_RESULTADO = ?";
		Object[] params = {"BAJO", resultado.getId(), resultado.getId()};
		return executeUpdate(sql, params);
	}
	
	public int eliminar(long id) throws SQLException {
		String sql = "DELETE FROM RESULTADO_TECNICO WHERE ID_RESULTADO = ?";
		Object[] params = {id};
		return executeUpdate(sql, params);
	}
	
	private ResultadoTecnico mapResultado(ResultSet rs) throws SQLException {
		ResultadoTecnico resultado = new ResultadoTecnico();
		resultado.setId(rs.getString("ID_RESULTADO"));
		return resultado;
	}
}
