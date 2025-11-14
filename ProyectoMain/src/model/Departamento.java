package model;

import java.util.Collection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Representa la división territorial principal donde se agrupan los municipios del país.
 * Contiene información básica de identificación geográfica y administrativa del departamento.
 * 
 * Esta clase es fundamental para la estructura geográfica del sistema de inspecciones fitosanitarias.
 * Permite organizar los municipios en unidades territoriales mayores, facilitando la gestión
 * administrativa de inspecciones y la generación de reportes por zonas geográficas.
 */
public class Departamento {

	/**
	 * Identificador único del departamento, utilizado para diferenciarlo de otros departamentos dentro del sistema.
	 */
	private String id;

	private String codigoDane;

	/**
	 * Nombre oficial del departamento según la división política administrativa.
	 */
	private String nombre;

	private Collection<Municipio> municipio;

	/**
	 * Constructor de la clase. Inicializa un objeto de tipo Departamento sin valores predefinidos.
	 * 
	 *  
	 */
	public Departamento() {

	}

	/**
	 * Asigna el identificador único del departamento.
	 */
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * Establece el nombre del departamento.
	 */
	public void setNombre(String pNombre) {
		this.nombre = pNombre;
	}

	/**
	 * Devuelve el identificador del departamento.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Retorna el nombre del departamento registrado.
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Asocia un municipio al departamento. Permite crear la relación 1–* entre departamento y municipios.
	 * Parámetro: pMunicipio → objeto de tipo Municipio.
	 */
	public void agregarMunicipio(Municipio pMunicipio) {
		this.municipio.add(pMunicipio);
	}

	/**
	 * Retorna los municipios vinculados al departamento.
	 */
	public Municipio getMunicipios() {
		return (Municipio) this.municipio;
	}

	/**
	 * etsbalece
	 */
	public void setCodigoDane(String pCodigoDane) {
		this.codigoDane = pCodigoDane;
	}

	public String getCodigoDane() {
		return this.codigoDane;
	}

}
