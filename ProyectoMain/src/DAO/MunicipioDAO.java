package DAO;

import BaseDatos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Municipio;

/**
 * DAO para la entidad Municipio.
 * Gestiona operaciones CRUD sobre la tabla MUNICIPIO.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 */
public class MunicipioDAO extends DAOBase<Municipio> {
	
	public MunicipioDAO() {
		super();
	}
	
	public MunicipioDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	public long crear(Municipio municipio) throws SQLException {
		String sql = "INSERT INTO MUNICIPIO (ID_DEPARTAMENTO, NOMBRE, CODIGO) " +
					 "VALUES (?, ?, ?)";
		Object[] params = {null, municipio.getNombre(), municipio.getId()};
		return executeInsertWithGeneratedKey(sql, params);
	}
	
	public Municipio obtenerPorId(long id) throws SQLException {
		String sql = "SELECT * FROM MUNICIPIO WHERE ID_MUNICIPIO = ?";
		Object[] params = {id};
		return findOneWithParams(sql, params, this::mapMunicipio);
	}
	
	public List<Municipio> obtenerTodos() throws SQLException {
		String sql = "SELECT * FROM MUNICIPIO";
		return findMany(sql, this::mapMunicipio);
	}
	
	public int actualizar(Municipio municipio) throws SQLException {
		String sql = "UPDATE MUNICIPIO SET NOMBRE = ? WHERE ID_MUNICIPIO = ?";
		Object[] params = {municipio.getNombre(), municipio.getId()};
		return executeUpdate(sql, params);
	}
	
	public int eliminar(long id) throws SQLException {
		String sql = "DELETE FROM MUNICIPIO WHERE ID_MUNICIPIO = ?";
		Object[] params = {id};
		return executeUpdate(sql, params);
	}
	
	private Municipio mapMunicipio(ResultSet rs) throws SQLException {
		Municipio municipio = new Municipio();
		municipio.setId(rs.getString("ID_MUNICIPIO"));
		municipio.setNombre(rs.getString("NOMBRE"));
		return municipio;
	}
}
