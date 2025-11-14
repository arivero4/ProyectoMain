package BaseDatos;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Clase factory que proporciona conexiones específicas según el rol del usuario.
 * Implementa el patrón Factory para crear instancias de conexión apropiadas.
 * 
 * Soporta diferentes roles: admin, productor, asistente_tecnico, propietario.
 * Cada rol puede tener una conexión con permisos específicos si es necesario.
 */
public class DBConnectionFactory {

	/**
	 * Obtiene una conexión configurada según el rol del usuario.
	 * 
	 * @param rol - Rol del usuario (admin, productor, asistente_tecnico, propietario)
	 * @return DBConnection - Conexión configurada para el rol especificado
	 * @throws IllegalArgumentException - Si el rol no es válido
	 */
	public static DBConnection getConnectionByRole(String rol) {
		if (rol == null || rol.trim().isEmpty()) {
			throw new IllegalArgumentException("El rol no puede estar vacío");
		}

		switch (rol.toLowerCase().trim()) {
		case "admin":
			return AdministradorConnection.getInstance();
		case "productor":
			return ProductorConnection.getInstance();
		case "asistente_tecnico":
			return AsistenteTecnicoConnection.getInstance();
		case "propietario":
			return PropietarioConnection.getInstance();
		default:
			throw new IllegalArgumentException("Rol no válido: " + rol + 
				". Roles válidos: admin, productor, asistente_tecnico, propietario");
		}
	}

	/**
	 * Obtiene la conexión por defecto (conexión general del sistema).
	 * 
	 * @return DBConnection - Conexión única del sistema
	 */
	public static DBConnection getDefaultConnection() {
		return Conexion.getInstance();
	}
}