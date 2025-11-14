package model;

import java.util.Collection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Modela la unidad territorial más pequeña dentro de un municipio, representando divisiones geográficas locales.
 * Cada vereda pertenece a un municipio y sirve para ubicar con mayor precisión los predios agrícolas inspeccionados.
 * 
 * Esta clase es fundamental para la georeferenciación detallada en el sistema de inspecciones fitosanitarias.
 * Permite la localización precisa de predios y lugares de producción, facilitando la gestión territorial
 * a nivel local y el seguimiento de actividades de inspección en zonas geográficamente específicas.
 */
public class Vereda {

	/**
	 * Identificador único de la vereda dentro del sistema.
	 */
	private String id;

	private String codigoDane;

	/**
	 * Nombre de la vereda según la nomenclatura local.
	 */
	private String nombre;

	private Collection<Predio> predio;

	private Municipio municipio;

	/**
	 * Constructor de la clase. Inicializa una vereda sin valores definidos.
	 * 
	 *  
	 */
	public Vereda() {

	}

	/**
	 * Asigna el identificador único de la vereda.
	 */
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * Devuelve el identificador de la vereda.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Establece el nombre de la vereda.
	 */
	public void setNombre(String pNombre) {
		this.nombre = pNombre;
	}

	/**
	 * Retorna el nombre de la vereda.
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Define el municipio al que pertenece la vereda.
	 * Parámetro: municipio (Municipio).
	 */
	public void setMunicipio(Municipio pMunicipio) {
		this.municipio = pMunicipio;
	}

	/**
	 * Devuelve el municipio asociado.
	 */
	public Municipio getMunicipio() {
		return this.municipio;
	}

	/**
	 * Añade un nuevo predio al listado de predios que pertenecen a la vereda.
	 * Parámetro: predio (Predio) — instancia de un predio agrícola.
	 */
	public void agregarLugarProduccion(LugarProduccion pLugarProduccion) {
		// Este método necesita revisión de tipos según la estructura del modelo
	}

	/**
	 * Retorna la colección de predios vinculados a la vereda.
	 * Uso: empleado en reportes de campo e inspecciones fitosanitarias.
	 */
	public LugarProduccion getLugarProduccion() {
		return (LugarProduccion) this.predio;
	}

	public void setCodigoDane(String pCodigoDane) {
		this.codigoDane = pCodigoDane;
	}

	public String getCodigoDane() {
		return this.codigoDane;
	}

}
