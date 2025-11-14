package DAO;

import BaseDatos.DBConnection;
import BaseDatos.DBConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta base para todos los DAOs (Data Access Objects).
 * Proporciona métodos genéricos para operaciones CRUD y gestión de base de datos.
 * 
 * Esta clase implementa el patrón Template Method, permitiendo a las subclases
 * definir consultas específicas mientras reutilizan métodos comunes de acceso a datos.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * @param <T> Tipo de entidad que gestiona este DAO
 */
public abstract class DAOBase<T> {
	
	/**
	 * Conexión a la base de datos.
	 */
	protected DBConnection dbConnection;
	
	/**
	 * Constructor del DAO base.
	 * Inicializa la conexión a base de datos usando la conexión por defecto.
	 */
	public DAOBase() {
		this.dbConnection = DBConnectionFactory.getDefaultConnection();
	}
	
	/**
	 * Constructor del DAO base con conexión específica.
	 * Permite inyectar una conexión particular (útil para pruebas).
	 * 
	 * @param dbConnection Conexión a la base de datos
	 */
	public DAOBase(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	/**
	 * Obtiene la conexión actual a la base de datos.
	 * 
	 * @return Objeto Connection activo
	 * @throws SQLException Si hay error al obtener la conexión
	 */
	protected Connection getConnection() throws SQLException {
		Connection conn = dbConnection.getConnection();
		if (conn == null || conn.isClosed()) {
			throw new SQLException("No hay conexión disponible a la base de datos");
		}
		return conn;
	}
	
	/**
	 * Ejecuta una consulta SELECT que retorna un único registro.
	 * 
	 * @param sql Consulta SQL a ejecutar
	 * @param mapResultSet Implementación de la interfaz para mapear ResultSet a objeto
	 * @return El objeto T mapeado desde el ResultSet, o null si no hay resultados
	 * @throws SQLException Si hay error en la ejecución de la consulta
	 */
	protected T findOne(String sql, ResultSetMapper<T> mapResultSet) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return mapResultSet.map(rs);
			}
			return null;
		} finally {
			closeResources(rs, pstmt, conn);
		}
	}
	
	/**
	 * Ejecuta una consulta SELECT que retorna múltiples registros.
	 * 
	 * @param sql Consulta SQL a ejecutar
	 * @param mapResultSet Implementación de la interfaz para mapear ResultSet a objeto
	 * @return Lista de objetos T mapeados desde el ResultSet
	 * @throws SQLException Si hay error en la ejecución de la consulta
	 */
	protected List<T> findMany(String sql, ResultSetMapper<T> mapResultSet) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<T> resultados = new ArrayList<>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				resultados.add(mapResultSet.map(rs));
			}
			return resultados;
		} finally {
			closeResources(rs, pstmt, conn);
		}
	}
	
	/**
	 * Ejecuta una consulta SELECT con parámetros que retorna un único registro.
	 * 
	 * @param sql Consulta SQL a ejecutar
	 * @param params Parámetros para la consulta preparada
	 * @param mapResultSet Implementación de la interfaz para mapear ResultSet a objeto
	 * @return El objeto T mapeado desde el ResultSet, o null si no hay resultados
	 * @throws SQLException Si hay error en la ejecución de la consulta
	 */
	protected T findOneWithParams(String sql, Object[] params, ResultSetMapper<T> mapResultSet) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return mapResultSet.map(rs);
			}
			return null;
		} finally {
			closeResources(rs, pstmt, conn);
		}
	}
	
	/**
	 * Ejecuta una consulta SELECT con parámetros que retorna múltiples registros.
	 * 
	 * @param sql Consulta SQL a ejecutar
	 * @param params Parámetros para la consulta preparada
	 * @param mapResultSet Implementación de la interfaz para mapear ResultSet a objeto
	 * @return Lista de objetos T mapeados desde el ResultSet
	 * @throws SQLException Si hay error en la ejecución de la consulta
	 */
	protected List<T> findManyWithParams(String sql, Object[] params, ResultSetMapper<T> mapResultSet) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<T> resultados = new ArrayList<>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				resultados.add(mapResultSet.map(rs));
			}
			return resultados;
		} finally {
			closeResources(rs, pstmt, conn);
		}
	}
	
	/**
	 * Ejecuta una operación INSERT, UPDATE o DELETE con parámetros.
	 * 
	 * @param sql Consulta SQL a ejecutar (INSERT, UPDATE o DELETE)
	 * @param params Parámetros para la consulta preparada
	 * @return Número de filas afectadas
	 * @throws SQLException Si hay error en la ejecución de la consulta
	 */
	protected int executeUpdate(String sql, Object[] params) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setParameters(pstmt, params);
			return pstmt.executeUpdate();
		} finally {
			closeResources(null, pstmt, conn);
		}
	}
	
	/**
	 * Ejecuta una operación INSERT con retorno de ID generado automáticamente.
	 * 
	 * @param sql Consulta SQL INSERT a ejecutar
	 * @param params Parámetros para la consulta preparada
	 * @return ID generado automáticamente por la base de datos
	 * @throws SQLException Si hay error en la ejecución de la consulta
	 */
	protected long executeInsertWithGeneratedKey(String sql, Object[] params) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameters(pstmt, params);
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getLong(1);
			}
			throw new SQLException("No se generó ID automático");
		} finally {
			closeResources(rs, pstmt, conn);
		}
	}
	
	/**
	 * Asigna parámetros a una consulta preparada.
	 * 
	 * @param pstmt Prepared statement a parametrizar
	 * @param params Array de parámetros
	 * @throws SQLException Si hay error al asignar parámetros
	 */
	protected void setParameters(PreparedStatement pstmt, Object[] params) throws SQLException {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				Object param = params[i];
				if (param instanceof String) {
					pstmt.setString(i + 1, (String) param);
				} else if (param instanceof Integer) {
					pstmt.setInt(i + 1, (Integer) param);
				} else if (param instanceof Long) {
					pstmt.setLong(i + 1, (Long) param);
				} else if (param instanceof Double) {
					pstmt.setDouble(i + 1, (Double) param);
				} else if (param instanceof java.sql.Date) {
					pstmt.setDate(i + 1, (java.sql.Date) param);
				} else if (param instanceof java.sql.Timestamp) {
					pstmt.setTimestamp(i + 1, (java.sql.Timestamp) param);
				} else if (param instanceof Boolean) {
					pstmt.setInt(i + 1, ((Boolean) param) ? 1 : 0);
				} else {
					pstmt.setObject(i + 1, param);
				}
			}
		}
	}
	
	/**
	 * Cierra los recursos de base de datos (ResultSet, PreparedStatement, Connection).
	 * Implementa el patrón try-with-resources de forma manual para compatibilidad.
	 * 
	 * @param rs ResultSet a cerrar (puede ser null)
	 * @param pstmt PreparedStatement a cerrar (puede ser null)
	 * @param conn Connection a cerrar (puede ser null)
	 */
	protected void closeResources(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.err.println("Error al cerrar ResultSet: " + e.getMessage());
			}
		}
		
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Error al cerrar PreparedStatement: " + e.getMessage());
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("Error al cerrar Connection: " + e.getMessage());
			}
		}
	}
	
	/**
	 * Interfaz funcional para mapear un ResultSet a un objeto de tipo T.
	 * Permite que las subclases definan cómo mapear los campos del resultado.
	 * 
	 * @param <T> Tipo de objeto a mapear
	 */
	@FunctionalInterface
	public interface ResultSetMapper<T> {
		/**
		 * Mapea una fila del ResultSet a un objeto de tipo T.
		 * 
		 * @param rs ResultSet con la fila actual
		 * @return Objeto T mapeado
		 * @throws SQLException Si hay error al acceder a los campos del ResultSet
		 */
		T map(ResultSet rs) throws SQLException;
	}
}
