package model;

import java.util.Collection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Modela la subdivisión administrativa de un departamento, representando los municipios del país.
 * Cada municipio pertenece a un departamento y puede contener varias veredas como unidades territoriales menores.
 * 
 * Esta clase forma parte de la estructura territorial multinivel del sistema de inspecciones fitosanitarias.
 * Permite organizar geográficamente las veredas y predios, facilitando la gestión administrativa
 * y la generación de reportes por entidades municipales.
 */
public class Municipio {

	/**
	 * Identificador único del municipio dentro del sistema.
	 */
	private String id;

	private String codigoDane;

	/**
	 * Nombre oficial del municipio.
	 */
	private String nombre;

	private Collection<Vereda> vereda;

	private Departamento departamento;

	/**
	 * Constructor de la clase. Crea una instancia vacía de un municipio.
	 * 
	 *  
	 *  
	 */
	public Municipio() {

	}

	/**
	 * Asigna el identificador del municipio
	 */
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * Devuelve el identificador del municipio.
	 */
	public String getId() {
		return this.id;
	}

	public void setCodigoDane(String pCodigoDane) {
		this.codigoDane = pCodigoDane;
	}

	public String getCodigoDane() {
		return this.codigoDane;
	}

	/**
	 * Establece el nombre del municipio.
	 */
	public void setNombre(String pNombre) {
		this.nombre = pNombre;
	}

	/**
	 * Retorna el nombre del municipio.
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Asocia el municipio con el departamento al cual pertenece.
	 * Parámetro: departamento (Departamento) — objeto que representa el departamento.
	 */
	public void setDepartamento(Departamento pDepartamento) {
		this.departamento = pDepartamento;
	}

	/**
	 * Devuelve la referencia al departamento asociado.
	 */
	public Departamento getDepartamento() {
		return this.departamento;
	}

	/**
	 * Añade una nueva vereda al municipio, manteniendo la relación jerárquica.
	 * Parámetro: vereda (Vereda) — instancia que representa la vereda a vincular.
	 */
	public void agregarVereda(Vereda pVereda) {
		this.vereda.add(pVereda);
	}

	/**
	 * Retorna la lista completa de veredas asociadas al municipio.
	 * Uso: facilita consultas geográficas o listados de inspección.
	 */
	public Vereda getVeredas() {
		return (Vereda) this.vereda;
	}

}
