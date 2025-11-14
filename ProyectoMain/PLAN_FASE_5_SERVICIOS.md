# PLAN FASE 5: SERVICIOS Y LÓGICA DE NEGOCIO

## 📋 RESUMEN EJECUTIVO
**Estado Actual**: Base de Datos, Conexión y DAOs completados y verificados ✅
**Objetivo Phase 5**: Crear capa de Servicios con validaciones, reglas de negocio y transacciones
**Duración Estimada**: 8-10 horas de desarrollo
**Criticidad**: ALTA - Base para GUI y Testing

---

## 🏗️ ARQUITECTURA DE SERVICIOS

### Nivel 1: Servicio Base Genérico
```
ServiceBase<T> (Clase genérica)
├── CRUD Operations (Crear, Leer, Actualizar, Eliminar)
├── Validations (Reglas de negocio)
├── Transactions (Control transaccional)
├── Logging (Auditoría de operaciones)
└── Error Handling (Manejo de excepciones)
```

### Nivel 2: Servicios de Usuarios (4 clases)
```
UsuarioService
├── Validación de credenciales
├── Cambio de contraseña
├── Gestión de roles
└── Control de acceso

ProductorService
├── Validación de datos de productor
├── Gestión de predios asociados
├── Cálculo de superficies
└── Reportes de producción

AsistenteTecnicoService
├── Validación de especialidades
├── Gestión de inspecciones asignadas
├── Generación de reportes técnicos
└── Control de evaluaciones

PropietarioService
├── Validación de propietario
├── Gestión de propiedades
├── Control de acceso a información
└── Reportes de propiedades
```

### Nivel 3: Servicios de Negocio (5 clases)
```
CultivoService
├── Crear/actualizar cultivos
├── Validar tipos de cultivo
├── Calcular áreas de cultivo
├── Validar fechas de siembra/cosecha
└── Controlar rotación de cultivos

InspeccionFitosanitariaService
├── Crear inspecciones
├── Validar datos técnicos
├── Calcular índices de infestación
├── Generar alertas automáticas
└── Control de plagas detectadas

PlagaService
├── Gestión de plagas
├── Validar severidad
├── Historial de plagas por lote
└── Alertas epidemiológicas

LoteService
├── Crear/actualizar lotes
├── Validar combinación predio-área
├── Gestionar plagas en lote
└── Histórico de inspecciones

ResultadoTecnicoService
├── Generar resultados de inspecciones
├── Validar datos técnicos
├── Cálculos de recomendaciones
└── Control de medidas aplicadas
```

### Nivel 4: Servicios Auxiliares (3 clases)
```
ValidationService
├── Validación de datos comunes
├── Reglas de negocio transversales
├── Formatos y patrones
└── Mensajes de error

ReportService
├── Generar reportes PDF
├── Reportes SQL
├── Exportación de datos
└── Estadísticas

AlertService
├── Crear alertas automáticas
├── Envío de notificaciones
├── Control de umbrales
└── Historial de alertas
```

---

## 📁 ESTRUCTURA DE CARPETAS

```
src/
├── BaseDatos/          (✅ COMPLETADO - 7 clases)
├── DAO/                (✅ COMPLETADO - 16 clases)
├── model/              (✅ COMPLETADO - 14 clases)
├── service/            (🔄 NUEVA - 12 clases)
│   ├── base/
│   │   └── ServiceBase.java
│   ├── usuario/
│   │   ├── UsuarioService.java
│   │   ├── ProductorService.java
│   │   ├── AsistenteTecnicoService.java
│   │   └── PropietarioService.java
│   ├── negocio/
│   │   ├── CultivoService.java
│   │   ├── InspeccionFitosanitariaService.java
│   │   ├── PlagaService.java
│   │   ├── LoteService.java
│   │   └── ResultadoTecnicoService.java
│   ├── utilidad/
│   │   ├── ValidationService.java
│   │   ├── ReportService.java
│   │   └── AlertService.java
│   └── exceptions/
│       ├── ServiceException.java
│       ├── ValidationException.java
│       └── BusinessRuleException.java
├── gui/                (⏳ FASE 6)
├── test/               (⏳ FASE 8)
└── util/               (⏳ UTILITIES)
```

---

## 🔧 IMPLEMENTACIÓN PASO A PASO

### PASO 1: Crear Excepciones Personalizadas (5 min)
```java
// ServiceException - Excepción base
// ValidationException - Validaciones fallidas
// BusinessRuleException - Reglas de negocio violadas
```
**Archivos**: 3 clases en `service/exceptions/`

### PASO 2: Crear ServiceBase Genérico (20 min)
```java
public abstract class ServiceBase<T, D extends DAOBase<T>> {
    protected D dao;
    protected static final Logger LOGGER = ...
    
    public T create(T entity) { ... }
    public T update(T entity) { ... }
    public T getById(String id) { ... }
    public List<T> getAll() { ... }
    public void delete(String id) { ... }
    
    protected abstract void validate(T entity);
    protected abstract void validateOnCreate(T entity);
    protected abstract void validateOnUpdate(T entity);
}
```
**Archivo**: 1 clase en `service/base/`

### PASO 3: Implementar Servicios de Usuarios (45 min)
- UsuarioService: Contraseñas, roles, permisos
- ProductorService: Validar datos agrícolas
- AsistenteTecnicoService: Especialidades, inspecciones
- PropietarioService: Acceso a propiedades
**Archivos**: 4 clases en `service/usuario/`

### PASO 4: Implementar Servicios de Negocio (60 min)
- CultivoService: Validar tipos, fechas, rotación
- InspeccionFitosanitariaService: Cálculos, alertas
- PlagaService: Historial, severidad
- LoteService: Validaciones de área y localización
- ResultadoTecnicoService: Generación de resultados
**Archivos**: 5 clases en `service/negocio/`

### PASO 5: Implementar Servicios Auxiliares (30 min)
- ValidationService: Reglas comunes
- ReportService: Generación de reportes
- AlertService: Alertas automáticas
**Archivos**: 3 clases en `service/utilidad/`

### PASO 6: Documentación y Testing (15 min)
- Javadoc completo
- Ejemplos de uso
- Casos de prueba básicos

---

## ✅ CRITERIOS DE ÉXITO

1. **12 clases de servicio** creadas y compiladas ✅
2. **3 excepciones personalizadas** implementadas ✅
3. **100% de métodos CRUD** funcionales en ServiceBase ✅
4. **Validaciones de negocio** en cada servicio ✅
5. **Logging profesional** en todas las operaciones ✅
6. **0 errores de compilación** ✅
7. **Documentación Javadoc** completa ✅
8. **Integración con DAO** verificada ✅

---

## 📊 TABLA DE RESPONSABILIDADES

| Servicio | DAO Asociado | Validaciones | Lógica Especial |
|----------|--------------|--------------|-----------------|
| UsuarioService | UsuarioDAO | Email único, Pass min 8 | Hash de contraseña |
| ProductorService | ProductorDAO | Cédula válida, Teléfono | Cálculo de área total |
| AsistenteTecnicoService | AsistenteTecnicoDAO | Licencia válida | Control de inspecciones |
| PropietarioService | PropietarioDAO | Documento válido | Acceso a propiedades |
| CultivoService | CultivoDAO | Tipo válido, Fechas | Validar rotación |
| InspeccionFitosanitariaService | InspeccionFitosanitariaDAO | Datos técnicos | Calcular índices |
| PlagaService | PlagaDAO | Nombre válido | Historial + alertas |
| LoteService | LoteDAO | Area + Predio | Validar localización |
| ResultadoTecnicoService | ResultadoTecnicoDAO | Datos técnicos | Recomendaciones |
| ValidationService | Ninguno | Patrones globales | Reutilizable |
| ReportService | Múltiples | - | Generación PDF/SQL |
| AlertService | AlertDAO | - | Sistema de alertas |

---

## 🎯 PRÓXIMOS PASOS

1. ✅ Crear carpeta `service/`
2. ✅ Implementar excepciones personalizadas
3. ✅ Crear ServiceBase genérico
4. ✅ Implementar servicios de usuario
5. ✅ Implementar servicios de negocio
6. ✅ Implementar servicios auxiliares
7. ✅ Validar compilación
8. ✅ Crear documentación
9. ⏳ FASE 6: GUI (Swing/JavaFX)
10. ⏳ FASE 7: Validaciones y Reglas
11. ⏳ FASE 8: Testing y Logging

---

## 📝 NOTAS IMPORTANTES

- **Transacciones**: Usar connection.setAutoCommit(false) cuando sea necesario
- **Validaciones**: Separar en métodos reutilizables
- **Logging**: Usar Logger profesional (java.util.logging)
- **Excepciones**: Siempre capturar y registrar
- **Parámetros**: Validar null en entrada de métodos
- **Sincronización**: Usar synchronized donde sea necesario para seguridad en hilos

---

**Autor**: GitHub Copilot  
**Fecha**: 13 de Noviembre de 2025  
**Versión**: 1.0
