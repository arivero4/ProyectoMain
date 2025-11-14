package BaseDatos;

import java.sql.Connection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Interfaz que define el contrato para la gestión de conexiones a base de datos.
 * Permite implementar diferentes estrategias de conexión según el rol o tipo de usuario.
 * 
 * Proporciona métodos para obtener la conexión activa y la cadena de conexión.
 */
public interface DBConnection {

	/**
	 * Obtiene la conexión activa a la base de datos.
	 * 
	 * @return Connection - Objeto de conexión
	 */
	Connection getConnection();

	/**
	 * Obtiene la cadena de conexión JDBC.
	 * 
	 * @return String - Cadena de conexión formateada
	 */
	String getConnectionString();
}

