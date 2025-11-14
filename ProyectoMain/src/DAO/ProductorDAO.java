package DAO;

import BaseDatos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Productor;

/**
 * DAO para la entidad Productor.
 * Gestiona operaciones CRUD sobre la tabla PRODUCTOR en la base de datos.
 * 
 * Proporciona métodos para crear, leer, actualizar y eliminar productores del sistema.
 * Un productor es un usuario responsable de actividades agrícolas en lugares de producción.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 */
public class ProductorDAO extends DAOBase<Productor> {
	
	/**
	 * Constructor por defecto.
	 */
	public ProductorDAO() {
		super();
	}
	
	/**
	 * Constructor con inyección de conexión.
	 * 
	 * @param dbConnection Conexión a usar
	 */
	public ProductorDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	/**
	 * Crea un nuevo productor en la base de datos.
	 * 
	 * @param productor Productor a crear
	 * @return ID del productor creado
	 * @throws SQLException Si hay error en la operación
	 */
	public long crear(Productor productor) throws SQLException {
		String sql = "INSERT INTO PRODUCTOR (ID_USUARIO, ID_LUGAR_PRODUCCION) " +
					 "VALUES (?, ?)";
		
		Object[] params = {
			productor.getId(),
			productor.getLugaresProduccion() != null ? productor.getLugaresProduccion().getId() : null
		};
		
		return executeInsertWithGeneratedKey(sql, params);
	}
	
	/**
	 * Busca un productor por su ID.
	 * 
	 * @param id ID del productor a buscar
	 * @return Productor encontrado, o null si no existe
	 * @throws SQLException Si hay error en la operación
	 */
	public Productor obtenerPorId(long id) throws SQLException {
		String sql = "SELECT * FROM PRODUCTOR WHERE ID_PRODUCTOR = ?";
		Object[] params = {id};
		
		return findOneWithParams(sql, params, this::mapProductor);
	}
	
	/**
	 * Obtiene todos los productores activos del sistema.
	 * 
	 * @return Lista de productores activos
	 * @throws SQLException Si hay error en la operación
	 */
	public List<Productor> obtenerTodos() throws SQLException {
		String sql = "SELECT * FROM PRODUCTOR";
		return findMany(sql, this::mapProductor);
	}
	
	/**
	 * Actualiza los datos de un productor existente.
	 * 
	 * @param productor Productor con datos a actualizar
	 * @return Número de filas afectadas
	 * @throws SQLException Si hay error en la operación
	 */
	public int actualizar(Productor productor) throws SQLException {
		String sql = "UPDATE PRODUCTOR SET ID_LUGAR_PRODUCCION = ? WHERE ID_PRODUCTOR = ?";
		
		Object[] params = {
			productor.getLugaresProduccion() != null ? productor.getLugaresProduccion().getId() : null,
			productor.getId()
		};
		
		return executeUpdate(sql, params);
	}
	
	/**
	 * Elimina un productor de la base de datos.
	 * 
	 * @param id ID del productor a eliminar
	 * @return Número de filas afectadas
	 * @throws SQLException Si hay error en la operación
	 */
	public int eliminar(long id) throws SQLException {
		String sql = "DELETE FROM PRODUCTOR WHERE ID_PRODUCTOR = ?";
		Object[] params = {id};
		
		return executeUpdate(sql, params);
	}
	
	/**
	 * Mapea una fila del ResultSet a un objeto Productor.
	 * 
	 * @param rs ResultSet con la fila a mapear
	 * @return Productor mapeado
	 * @throws SQLException Si hay error al acceder a los campos
	 */
	private Productor mapProductor(ResultSet rs) throws SQLException {
		Productor productor = new Productor();
		productor.setId(rs.getString("ID_PRODUCTOR"));
		// Nota: ID_USUARIO y ID_LUGAR_PRODUCCION se cargarían mediante joins si fuera necesario
		return productor;
	}
}
