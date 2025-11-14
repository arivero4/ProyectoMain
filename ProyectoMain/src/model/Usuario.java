package model;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Clase abstracta que representa a las personas que interactúan con el sistema 
 * (inspectores, técnicos, administradores o supervisores).
 * Cada usuario tiene un rol y una serie de permisos que determinan las acciones 
 * que puede ejecutar dentro del sistema de inspecciones fitosanitarias.
 * 
 * Esta clase proporciona la base para la autenticación, autorización y gestión 
 * de usuarios en el sistema. Los atributos principales incluyen datos personales, 
 * información de contacto y detalles de identificación.
 */
public abstract class Usuario {

	/**
	 * Identificador único del usuario dentro del sistema. Permite diferenciarlo de otros usuarios y es usado en los registros de auditoría.
	 */
	private String id;

	/**
	 * Documento de identidad del usuario, utilizado para validación y registro oficial.
	 */
	private String numeroIdentificacion;

	/**
	 * Define el rol asignado al usuario (por ejemplo: Inspector, Administrador, Técnico, Propietario). Este valor determina su nivel de acceso y permisos.
	 */
	private String rol;

	/**
	 * Nombre completo del usuario registrado.
	 */
	private String nombre;

	/**
	 * Número de teléfono o celular asociado al usuario, utilizado para comunicación directa.
	 */
	private String telefonoContacto;

	/**
	 * Dirección de correo electrónico usada para notificaciones o acceso al sistema.
	 */
	private String correoElectronico;

	/**
	 * Constructor de la clase. Inicializa un nuevo usuario con valores predeterminados o vacíos.
	 * 
	 *  
	 */
	public Usuario() {

	}

	/**
	 * Establece el identificador único del usuario.
	 */
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * Devuelve el identificador del usuario.
	 */
	public String getId() {
		return this.id;
	}

	public void setRol(String pRol) {
		this.rol = pRol;
	}

	public String getRol() {
		return this.rol;
	}

	/**
	 * Asigna el número de identificación oficial del usuario.
	 */
	public void setNumeroIdentificacion(String pNumeroIdentificacion) {
		this.numeroIdentificacion = pNumeroIdentificacion;
	}

	/**
	 * Retorna el número de identificación del usuario.
	 */
	public String getNumeroIdentificacion() {
		return this.numeroIdentificacion;
	}

	/**
	 * Define el nombre completo del usuario.
	 */
	public void setNombre(String pNombre) {
		this.nombre = pNombre;
	}

	/**
	 * Devuelve el nombre actual del usuario.
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Asigna el número de teléfono o celular del usuario.
	 */
	public void setTelefonoContacto(String pTelefonoContacto) {
		this.telefonoContacto = pTelefonoContacto;
	}

	/**
	 * Retorna el número de contacto registrado del usuario.
	 */
	public String getTelefonoContacto() {
		return this.telefonoContacto;
	}

	/**
	 * Asigna el correo electrónico del usuario.
	 */
	public void setCorreoElectronico(String pCorreoElectronico) {
		this.correoElectronico = pCorreoElectronico;
	}

	/**
	 * Devuelve el correo electrónico asociado al usuario.
	 */
	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

}
