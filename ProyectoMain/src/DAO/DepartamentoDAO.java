package DAO;

import BaseDatos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Departamento;

/**
 * DAO para la entidad Departamento.
 * Gestiona operaciones CRUD sobre la tabla DEPARTAMENTO.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 */
public class DepartamentoDAO extends DAOBase<Departamento> {
	
	public DepartamentoDAO() {
		super();
	}
	
	public DepartamentoDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	public long crear(Departamento depto) throws SQLException {
		String sql = "INSERT INTO DEPARTAMENTO (NOMBRE, CODIGO) VALUES (?, ?)";
		Object[] params = {depto.getNombre(), depto.getId()};
		return executeInsertWithGeneratedKey(sql, params);
	}
	
	public Departamento obtenerPorId(long id) throws SQLException {
		String sql = "SELECT * FROM DEPARTAMENTO WHERE ID_DEPARTAMENTO = ?";
		Object[] params = {id};
		return findOneWithParams(sql, params, this::mapDepartamento);
	}
	
	public List<Departamento> obtenerTodos() throws SQLException {
		String sql = "SELECT * FROM DEPARTAMENTO";
		return findMany(sql, this::mapDepartamento);
	}
	
	public int actualizar(Departamento depto) throws SQLException {
		String sql = "UPDATE DEPARTAMENTO SET NOMBRE = ? WHERE ID_DEPARTAMENTO = ?";
		Object[] params = {depto.getNombre(), depto.getId()};
		return executeUpdate(sql, params);
	}
	
	public int eliminar(long id) throws SQLException {
		String sql = "DELETE FROM DEPARTAMENTO WHERE ID_DEPARTAMENTO = ?";
		Object[] params = {id};
		return executeUpdate(sql, params);
	}
	
	private Departamento mapDepartamento(ResultSet rs) throws SQLException {
		Departamento depto = new Departamento();
		depto.setId(rs.getString("ID_DEPARTAMENTO"));
		depto.setNombre(rs.getString("NOMBRE"));
		return depto;
	}
}
