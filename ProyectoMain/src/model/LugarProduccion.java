package model;

import java.util.Collection;

/**
 * @version 2.1
 * @author Isabella Vargas
 * @author Ricardo Viancha
 * @author Iswar Corrales
 * @author Andres Rivero
 * 
 * Modela un sitio geográfico o administrativo donde se desarrollan actividades agrícolas bajo inspección fitosanitaria.
 * Representa la ubicación física donde se encuentran los lotes, cultivos y donde se realizan las inspecciones.
 * 
 * Esta clase es central para la organización espacial del sistema, vinculando productores, predios, asistentes técnicos
 * y lotes en un mismo contexto geográfico. Facilita la gestión integral de un lugar de producción y permite
 * el seguimiento completo de todas las actividades de inspección fitosanitaria realizadas en esa ubicación específica.
 */
public class LugarProduccion {

	/**
	 * Identificador único del lugar de producción, utilizado para referenciarlo en las relaciones con productores o lotes.
	 */
	private String id;

	/**
	 * Código interno o institucional que identifica el lugar de producción según normativas locales o registros oficiales (por ejemplo, un código ICA).
	 */
	private String codigoIca;

	private String nombre;

	private Productor productor;

	private Predio predio;

	private AsistenteTecnico asistenteTecnico;

	private Collection<Lote> lote;

	private Collection<InspeccionFitosanitaria> inspeccionFitosanitaria;

	/**
	 * onstructor que inicializa un objeto vacío de tipo LugarProduccion.
	 * 
	 *  
	 */
	public LugarProduccion() {

	}

	/**
	 * Asigna el identificador único del lugar de producción.
	 */
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * Devuelve el identificador actual del lugar.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Establece el código LCA (código local del área). Este código es clave en los registros y trazabilidad de la producción.
	 */
	public void setCodigoIca(String pCodigoIca) {
		this.codigoIca = pCodigoIca;
	}

	/**
	 * Retorna el código LCA asignado, útil para validaciones o reportes de registro.
	 */
	public String getCodigoIca() {
		return this.codigoIca;
	}

	public void setNombre(String pNombre) {
		this.nombre = pNombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Vincula un predio al lugar de producción.
	 * Parámetro: pPredio → objeto de tipo Predio.
	 */
	public void agregarPredio(Predio pPredio) {
		this.predio = pPredio;
	}

	/**
	 * Devuelve el predio asociado al lugar de producción.
	 */
	public Predio getPredio() {
		return this.predio;
	}

	/**
	 * Asocia un productor responsable del lugar.
	 * Parámetro: pProductor → objeto de tipo Productor.
	 */
	public void setProductor(Productor pProductor) {
		this.productor = pProductor;
	}

	/**
	 * Retorna el productor vinculado al lugar de producción.
	 */
	public Productor getProductor() {
		return this.productor;
	}

	public void setAsistenteTecnico(AsistenteTecnico pAsistenteTecnico) {
		this.asistenteTecnico = pAsistenteTecnico;
	}

	public AsistenteTecnico getAsistenteTecnico() {
		return this.asistenteTecnico;
	}

	public void agregarLote(Lote pLote) {
		this.lote.add(pLote);
	}

	public Lote getLote() {
		return (Lote) this.lote;
	}

	/**
	 * Registra una inspección fitosanitaria realizada por el asistente técnico en un lugar determinado. Permite llevar control sobre las inspecciones ejecutadas.
	 */
	public void agregarInspeccionFitosanitaria(InspeccionFitosanitaria pInspeccionFitosanitaria) {
		this.inspeccionFitosanitaria.add(pInspeccionFitosanitaria);
	}

	/**
	 * Retorna las inspeccion fitosanitaria  vinculados.
	 */
	public InspeccionFitosanitaria getInspeccionFitosanitaria() {
		return (InspeccionFitosanitaria) this.inspeccionFitosanitaria;
	}

}
