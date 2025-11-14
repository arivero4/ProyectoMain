package model;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Modela la unidad física de producción agrícola dentro del sistema de inspecciones fitosanitarias.
 * Representa el terreno o finca donde se desarrollan cultivos, se realizan inspecciones y se registran condiciones fitosanitarias.
 * 
 * Esta clase es fundamental para la estructura de propiedades del sistema, vinculando propietarios con sus terrenos específicos.
 * Facilita la gestión territorial de los predios y permite el seguimiento de inspecciones y resultados técnicos
 * asociados a cada unidad de producción individual.
 */
public class Predio {

	/**
	 * Identificador único del predio dentro del sistema. Permite distinguirlo de otros registros y es usado como clave principal en las relaciones con otras clases.
	 */
	private String id;

	/**
	 * Código alfanumérico que identifica oficialmente el predio ante las entidades catastrales. Es fundamental para la gestión territorial y la verificación de la propiedad.
	 */
	private String numeroPredial;

	/**
	 * Ubicación física o dirección específica del predio. Puede incluir nombres de veredas, coordenadas o vías de acceso.
	 */
	private String direccion;

	/**
	 * Tamaño total del predio en hectáreas o metros cuadrados. Permite calcular la extensión destinada a los cultivos y controlar las áreas inspeccionadas.
	 */
	private float area;

	private Propietario propietario;

	private LugarProduccion lugarProduccion;

	private Vereda vereda;

	/**
	 * 
	 * Constructor de la clase. Inicializa un nuevo objeto Predio con valores vacíos o predeterminados.
	 * 
	 *  
	 */
	public Predio() {

	}

	/**
	 * Retorna el identificador único del predio.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Asigna el valor del identificador único del predio.
	 */
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * Establece el código catastral del predio.
	 */
	public void setNumeroPredial(String pNumeroPredial) {
		this.numeroPredial = pNumeroPredial;
	}

	/**
	 * Devuelve el código catastral asociado al predio.
	 */
	public String getNumeroPredial() {
		return this.numeroPredial;
	}

	/**
	 * Permite modificar la dirección registrada del predio.
	 */
	public void setDireccion(String pDireccion) {
		this.direccion = pDireccion;
	}

	/**
	 * Retorna la dirección o ubicación textual del predio.
	 */
	public String getDireccion() {
		return this.direccion;
	}

	/**
	 * Establece o actualiza el valor del área del predio.
	 */
	public void setArea(float pArea) {
		this.area = pArea;
	}

	/**
	 * Devuelve el área total del predio.
	 */
	public float getArea() {
		return this.area;
	}

	/**
	 * Asocia el predio con su propietario.
	 * Parámetro: propietario (Propietario).
	 */
	public void setPropietario(Propietario pPropietario) {
		this.propietario = pPropietario;
	}

	/**
	 * Retorna el propietario del predio.
	 */
	public Propietario getPropietario() {
		return this.propietario;
	}

	/**
	 * Añade un nuevo lugar de producción dentro del predio.
	 * Parámetro: lugarProduccion (LugarProduccion).
	 */
	public void setLugarProduccion(LugarProduccion pLugarProduccion) {
		this.lugarProduccion = pLugarProduccion;
	}

	/**
	 * Retorna la lista de lugares de producción vinculados al predio.
	 * Uso: permite visualizar o procesar la información agrícola de la finca.
	 */
	public LugarProduccion getLugaresProduccion() {
		return this.lugarProduccion;
	}

	public void setVereda(Vereda pVereda) {
		this.vereda = pVereda;
	}

	public Vereda getVereda() {
		return this.vereda;
	}

}
