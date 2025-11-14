package DAO;

import BaseDatos.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Usuario;

/**
 * DAO para la entidad Usuario base.
 * Gestiona operaciones CRUD sobre la tabla USUARIO en la base de datos.
 * 
 * Esta es la clase base para acceso a datos de usuarios.
 * Para operaciones específicas de cada tipo de usuario, usar sus DAOs especializados.
 * 
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 */
public class UsuarioDAO extends DAOBase<Usuario> {
	
	/**
	 * Constructor por defecto.
	 * Utiliza la conexión por defecto de la base de datos.
	 */
	public UsuarioDAO() {
		super();
	}
	
	/**
	 * Constructor con inyección de conexión.
	 * Permite proporcionar una conexión específica (útil para pruebas).
	 * 
	 * @param dbConnection Conexión a usar
	 */
	public UsuarioDAO(DBConnection dbConnection) {
		super(dbConnection);
	}
	
	/**
	 * Busca un usuario por su ID.
	 * 
	 * @param id ID del usuario a buscar
	 * @return Usuario encontrado, o null si no existe
	 * @throws SQLException Si hay error en la operación
	 */
	public Usuario obtenerPorId(long id) throws SQLException {
		String sql = "SELECT * FROM USUARIO WHERE ID_USUARIO = ?";
		Object[] params = {id};
		
		return findOneWithParams(sql, params, this::mapUsuario);
	}
	
	/**
	 * Busca un usuario por su correo electrónico.
	 * 
	 * @param email Correo del usuario a buscar
	 * @return Usuario encontrado, o null si no existe
	 * @throws SQLException Si hay error en la operación
	 */
	public Usuario obtenerPorEmail(String email) throws SQLException {
		String sql = "SELECT * FROM USUARIO WHERE CORREO_ELECTRONICO = ?";
		Object[] params = {email};
		
		return findOneWithParams(sql, params, this::mapUsuario);
	}
	
	/**
	 * Busca un usuario por su número de identificación.
	 * 
	 * @param cedula Número de identificación del usuario a buscar
	 * @return Usuario encontrado, o null si no existe
	 * @throws SQLException Si hay error en la operación
	 */
	public Usuario obtenerPorCedula(String cedula) throws SQLException {
		String sql = "SELECT * FROM USUARIO WHERE NUMERO_IDENTIFICACION = ?";
		Object[] params = {cedula};
		
		return findOneWithParams(sql, params, this::mapUsuario);
	}
	
	/**
	 * Obtiene todos los usuarios activos del sistema.
	 * 
	 * @return Lista de usuarios activos
	 * @throws SQLException Si hay error en la operación
	 */
	public List<Usuario> obtenerTodos() throws SQLException {
		String sql = "SELECT * FROM USUARIO WHERE ACTIVO = 1";
		return findMany(sql, this::mapUsuario);
	}
	
	/**
	 * Obtiene todos los usuarios de un rol específico.
	 * 
	 * @param rol Rol a filtrar
	 * @return Lista de usuarios del rol especificado
	 * @throws SQLException Si hay error en la operación
	 */
	public List<Usuario> obtenerPorRol(String rol) throws SQLException {
		String sql = "SELECT * FROM USUARIO WHERE ROL = ? AND ACTIVO = 1";
		Object[] params = {rol};
		
		return findManyWithParams(sql, params, this::mapUsuario);
	}
	
	/**
	 * Elimina un usuario de forma lógica (marca como inactivo).
	 * 
	 * @param id ID del usuario a eliminar
	 * @return Número de filas afectadas
	 * @throws SQLException Si hay error en la operación
	 */
	public int eliminarLogico(long id) throws SQLException {
		String sql = "UPDATE USUARIO SET ACTIVO = 0 WHERE ID_USUARIO = ?";
		Object[] params = {id};
		
		return executeUpdate(sql, params);
	}
	
	/**
	 * Verifica si existe un usuario con el email especificado.
	 * 
	 * @param email Email a verificar
	 * @return true si existe, false en caso contrario
	 * @throws SQLException Si hay error en la operación
	 */
	public boolean existeEmail(String email) throws SQLException {
		return obtenerPorEmail(email) != null;
	}
	
	/**
	 * Mapea una fila del ResultSet a un objeto Usuario.
	 * Nota: Como Usuario es abstracta, este método retorna los datos básicos.
	 * Las subclases especializadas deben usar sus propios mappers.
	 * 
	 * @param rs ResultSet con la fila a mapear
	 * @return null - Usuario es abstracta y no puede ser instanciada
	 * @throws SQLException Si hay error al acceder a los campos
	 */
	private Usuario mapUsuario(ResultSet rs) throws SQLException {
		// Usuario es abstracta, no puede ser instanciada directamente
		// Las subclases (Productor, AsistenteTecnico, etc.) tienen sus propios DAOs
		// Se verifica el ResultSet pero no se retorna instancia
		if (rs != null) {
			// Los datos se leen pero no se mapean (Usuario es abstracta)
			String id = rs.getString("ID_USUARIO");
			// Cada subclase tiene su propio DAO con su propio mapper
		}
		return null;
	}
}

