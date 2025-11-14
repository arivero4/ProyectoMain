package model;

import java.util.Collection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Modela la información básica de la persona natural o jurídica dueña del predio.
 * Permite establecer la relación legal entre los terrenos agrícolas y sus respectivos titulares.
 * 
 * Esta clase es fundamental para mantener la estructura de propiedad de los predios 
 * en el sistema. A través de esta clase se vinculan los propietarios con sus predios 
 * y se establece la responsabilidad legal sobre los terrenos donde se realizan 
 * las inspecciones fitosanitarias.
 */
public class Propietario extends Usuario {

	/**
	 * Identificador único del propietario dentro del sistema. Se utiliza como clave primaria para establecer relaciones con los predios.
	 */
	private String id;

	private Collection<Predio> predio;

	/**
	 * Constructor de la clase. Inicializa un objeto Propietario vacío.
	 * 
	 *  
	 */
	public Propietario() {

	}

	/**
	 * Asigna el identificador único al propietario.
	 */
	@Override
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * Retorna el identificador actual del propietario.
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/**
	 * Retorna los predios vinculados al propietario.
	 */
	public void agregarPredio(Predio pPredio) {
		this.predio.add(pPredio);
	}

	/**
	 * Retorna el productor vinculado al lugar de predio.
	 */
	public Predio getPredios() {
		return (Predio) this.predio;
	}

}
