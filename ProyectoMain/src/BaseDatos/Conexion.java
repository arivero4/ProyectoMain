package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Clase singleton que gestiona la conexión a la base de datos Oracle.
 * Implementa el patrón Singleton para garantizar una única instancia de conexión.
 * 
 * Proporciona una conexión única y reutilizable a través del método getInstance().
 * Implementa la interfaz DBConnection para permitir polimorfismo en la gestión de conexiones.
 */
public final class Conexion implements DBConnection {

    private static Conexion instance; // Singleton - instancia única
    private Connection connection;
    private static final Logger LOGGER = Logger.getLogger(Conexion.class.getName());
    
    // Parámetros de conexión Oracle
    private final String username = "EJEMPLOICA";
    private final String password = "EJEMPLOICA";
    private final String host = "192.168.2.100";
    private final String port = "1521";
    private final String service = "XE";

    /**
     * Constructor privado para el patrón Singleton.
     * Carga el driver JDBC y establece la conexión a Oracle.
     * 
     * @throws RuntimeException si hay error en la conexión
     */
    private Conexion() {
        try {
            // Cargar driver JDBC de Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Establecer conexión
            connection = DriverManager.getConnection(getConnectionString(), username, password);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error: Driver de Oracle no encontrado", e);
            throw new RuntimeException("Error: ojdbc.jar no está en el classpath.", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error conectando a la base de datos Oracle", e);
            throw new RuntimeException("Error conectando a la base de datos: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene la instancia única de conexión (Singleton).
     * Si no existe, la crea.
     * 
     * @return Conexion - Instancia única de la clase
     */
    public static synchronized Conexion getInstance() {
        if (instance == null) {
            instance = new Conexion();
        }
        return instance;
    }

    /**
     * Obtiene la conexión actual a la base de datos.
     * 
     * @return Connection - Objeto de conexión activo
     */
    @Override
    public Connection getConnection() {
        return connection;
    }

    /**
     * Construye la cadena de conexión JDBC para Oracle.
     * 
     * @return String - Cadena de conexión formateada
     */
    @Override
    public String getConnectionString() {
        return String.format("jdbc:oracle:thin:@%s:%s/%s", this.host, this.port, this.service);
    }

    /**
     * Valida si la conexión está activa.
     * 
     * @return boolean - true si la conexión está abierta, false en caso contrario
     */
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error al verificar la conexión", e);
            return false;
        }
    }

    /**
     * Cierra la conexión con la base de datos.
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                LOGGER.info("Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error al cerrar la conexión", e);
        }
    }
}
