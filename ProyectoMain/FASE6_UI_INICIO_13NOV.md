# FASE 6: INTERFAZ GRÁFICA (UI) - INICIO
## Fecha: 13 de Noviembre de 2025

---

## 📋 RESUMEN EJECUTIVO

Se ha iniciado la implementación de la **Interfaz Gráfica de Usuario (UI)** del Sistema de Inspección Fitosanitaria. Se han creado los componentes base, utilidades y las ventanas principales de login y navegación.

**Estado:** ✅ Fundamentos Completados (40%)  
**Archivos Creados:** 10  
**Líneas de Código:** ~1,200  

---

## 📁 ESTRUCTURA DE PAQUETES CREADA

```
src/ui/
├── login/           # Autenticación
│   └── LoginFrame.java
├── main/            # Ventana principal
│   └── MainFrame.java
├── forms/           # Formularios CRUD (pendiente)
├── components/      # Componentes reutilizables
│   ├── BaseFrame.java
│   ├── BasePanel.java
│   └── BaseDialog.java
├── dialogs/         # Diálogos modales (pendiente)
└── utils/           # Utilidades UI
    ├── UIConstants.java
    ├── MessageUtil.java
    └── DocumentFilters.java
```

---

## ✅ COMPONENTES IMPLEMENTADOS

### **1. ui.utils.UIConstants.java** (130 líneas)
**Propósito:** Constantes centralizadas para toda la UI

**Características:**
- ✅ **Colores:** 13 colores definidos (primary, secondary, accent, error, warning, success, etc.)
- ✅ **Fuentes:** 7 fuentes estándar (title, subtitle, heading, body, caption, button)
- ✅ **Dimensiones:** Tamaños de ventanas (login, main, dialogs, forms)
- ✅ **Espaciado:** Padding y gaps (small, medium, large)
- ✅ **Mensajes:** Textos estándar (error, success, confirm, etc.)
- ✅ **Etiquetas:** Labels comunes (ID, Name, Email, etc.)
- ✅ **Botones:** Textos de botones (Save, Cancel, Delete, etc.)
- ✅ **Roles:** Roles de usuario (ADMIN, INSPECTOR, TECNICO, etc.)

**Paleta de Colores:**
```
PRIMARY:   #228B22 (Verde forestal)
SECONDARY: #2E7D32 (Verde oscuro)
ACCENT:    #4CAF50 (Verde claro)
ERROR:     #D32F2F (Rojo)
WARNING:   #F57C00 (Naranja)
SUCCESS:   #388E3C (Verde éxito)
INFO:      #0288D1 (Azul)
```

---

### **2. ui.components.BaseFrame.java** (85 líneas)
**Propósito:** Clase base abstracta para todas las ventanas principales

**Características:**
- ✅ Centrado automático en pantalla
- ✅ Configuración estándar (tamaño, cierre, redimensionable)
- ✅ Métodos abstractos: `initializeComponents()`, `setupLayout()`, `setupListeners()`
- ✅ Métodos públicos: `display()`, `close()`, `centerWindow()`
- ✅ Patrón Template Method

**Herencia:**
```
JFrame
  └── BaseFrame (abstract)
        ├── LoginFrame
        └── MainFrame
```

---

### **3. ui.components.BasePanel.java** (70 líneas)
**Propósito:** Clase base abstracta para todos los paneles

**Características:**
- ✅ Soporte para cualquier LayoutManager
- ✅ Color de fondo estandarizado
- ✅ Métodos abstractos: `initializeComponents()`, `setupLayout()`, `setupListeners()`
- ✅ Métodos opcionales: `clearFields()`, `validateData()`
- ✅ Reutilizable para formularios y paneles complejos

---

### **4. ui.components.BaseDialog.java** (115 líneas)
**Propósito:** Clase base abstracta para diálogos modales

**Características:**
- ✅ Soporte para diálogos modales y no modales
- ✅ Centrado respecto a ventana padre
- ✅ Control de aceptación/cancelación (`isAccepted()`)
- ✅ Métodos: `acceptDialog()`, `cancelDialog()`, `display()`
- ✅ Tamaños predefinidos (small, medium, large)

---

### **5. ui.utils.MessageUtil.java** (180 líneas)
**Propósito:** Utilidad para mensajes de diálogo estandarizados

**Características:**
- ✅ **Métodos de Error:** `showError()` (2 sobrecarga)
- ✅ **Métodos de Advertencia:** `showWarning()` (2 sobrecarga)
- ✅ **Métodos de Información:** `showInfo()` (2 sobrecarga)
- ✅ **Métodos de Éxito:** `showSuccess()`
- ✅ **Métodos de Confirmación:** `showConfirm()` (2 sobrecarga)
- ✅ **Atajos especializados:**
  - `showDeleteConfirm()` - Confirmar eliminación
  - `showSaveSuccess()` - Éxito al guardar
  - `showUpdateSuccess()` - Éxito al actualizar
  - `showDeleteSuccess()` - Éxito al eliminar
  - `showRequiredFields()` - Campos requeridos vacíos
  - `showInvalidData()` - Datos inválidos

**Ejemplo de uso:**
```java
MessageUtil.showError(this, "Error al conectar con la base de datos");
MessageUtil.showSaveSuccess(this);
boolean confirm = MessageUtil.showDeleteConfirm(this);
```

---

### **6. ui.utils.DocumentFilters.java** (200 líneas)
**Propósito:** Filtros para validación de entrada en campos de texto

**Características:**
- ✅ **IntegerFilter:** Solo números enteros (con longitud máxima opcional)
- ✅ **DecimalFilter:** Solo números decimales (con longitud máxima opcional)
- ✅ **LengthFilter:** Limita longitud máxima de texto
- ✅ **AlphaFilter:** Solo letras y espacios (soporte de acentos españoles)

**Ejemplo de uso:**
```java
// Campo de texto para cédula (solo números, máximo 10 dígitos)
JTextField cedulaField = new JTextField();
((AbstractDocument) cedulaField.getDocument()).setDocumentFilter(
    new DocumentFilters.IntegerFilter(10)
);

// Campo de texto para área (decimales)
JTextField areaField = new JTextField();
((AbstractDocument) areaField.getDocument()).setDocumentFilter(
    new DocumentFilters.DecimalFilter(10)
);
```

---

### **7. ui.login.LoginFrame.java** (305 líneas)
**Propósito:** Ventana de inicio de sesión

**Características:**
- ✅ **Autenticación:** Por correo electrónico
- ✅ **Validaciones:**
  - Campos vacíos
  - Formato de email (regex)
  - Usuario existe en BD
- ✅ **Diseño:**
  - Header con título y subtítulo
  - Formulario centrado (email, password)
  - Botones estilizados (Login, Limpiar)
- ✅ **Navegación:**
  - Enter en email → foco a password
  - Enter en password → ejecutar login
- ✅ **Integración:**
  - Usa `UsuarioService` para autenticación
  - Abre `MainFrame` al autenticar
  - Método `main()` para ejecutar aplicación

**Tamaño:** 400 x 500 px  
**Look & Feel:** System default

**Flujo de autenticación:**
```
1. Usuario ingresa email
2. Usuario ingresa contraseña
3. Click en "Iniciar Sesión" o Enter
4. Validar campos vacíos
5. Validar formato email
6. Buscar usuario en BD (UsuarioService.obtenerPorEmail)
7. Si existe → Abrir MainFrame
8. Si no existe → Mostrar error
```

**Pendiente:**
- ⏳ Implementar hash de contraseñas (TODO en código)
- ⏳ Gestión de sesiones
- ⏳ Recuperación de contraseña

---

### **8. ui.main.MainFrame.java** (355 líneas)
**Propósito:** Ventana principal del sistema

**Características:**
- ✅ **Menú Principal:** 5 menús (Inspecciones, Predios, Usuarios, Reportes, Sistema)
- ✅ **ToolBar:** Botones de acceso rápido
- ✅ **Status Bar:** Muestra usuario actual y mensajes
- ✅ **Panel de Bienvenida:** Saludo personalizado con nombre y rol
- ✅ **Navegación por Roles:** Estructura preparada

**Estructura de Menús:**

**1. Menú Inspecciones:**
- Nueva Inspección
- Listar Inspecciones
- Buscar Inspección

**2. Menú Predios y Lotes:**
- Gestión de Predios
- Gestión de Lotes
- Gestión de Cultivos

**3. Menú Usuarios:**
- Gestión de Productores
- Gestión de Asistentes Técnicos
- Gestión de Propietarios

**4. Menú Reportes:**
- Reporte de Inspecciones
- Reporte de Predios
- Reporte de Plagas
- Exportar a CSV

**5. Menú Sistema:**
- Mi Perfil
- Configuración
- Ayuda
- Acerca de
- Cerrar Sesión
- Salir

**ToolBar:**
- Nueva Inspección
- Listar Inspecciones
- Reportes

**Métodos Públicos:**
- `updateStatus(String message)` - Actualiza mensaje en status bar
- `setContent(JPanel panel)` - Cambia contenido del panel principal

**Tamaño:** 1280 x 720 px  
**Estado Actual:** Skeleton implementado (opciones de menú muestran "En Desarrollo")

---

## 🎨 PATRONES DE DISEÑO IMPLEMENTADOS

### **1. Template Method**
```java
// BaseFrame, BasePanel, BaseDialog
abstract class BaseFrame {
    public BaseFrame() {
        initializeComponents();  // Template
        setupLayout();           // Template
        setupListeners();        // Template
    }
    
    protected abstract void initializeComponents();
    protected abstract void setupLayout();
    protected void setupListeners() { }  // Hook method
}
```

### **2. Factory Method**
```java
// Creación de componentes estilizados
private JButton createStyledButton(String text, Color bg) {
    JButton button = new JButton(text);
    button.setBackground(bg);
    // ... configuración estándar
    return button;
}
```

### **3. Singleton (Indirecto)**
```java
// UIConstants como clase de utilidad
public final class UIConstants {
    private UIConstants() { 
        throw new AssertionError();
    }
    // Solo constantes estáticas
}
```

---

## 🔧 TECNOLOGÍAS UTILIZADAS

| Tecnología | Versión | Uso |
|------------|---------|-----|
| Java Swing | JDK 8+ | Framework UI |
| GridBagLayout | Nativo | Layout complejo |
| BorderLayout | Nativo | Layout principal |
| BoxLayout | Nativo | Layout vertical |
| FlowLayout | Nativo | Layout de botones |

---

## 📊 ESTADO DE IMPLEMENTACIÓN

### **Completado (40%):**
- ✅ Estructura de paquetes (7 packages)
- ✅ Componentes base (3 clases)
- ✅ Utilidades UI (3 clases)
- ✅ Sistema de Login (1 ventana)
- ✅ Ventana Principal (1 ventana)

### **Pendiente (60%):**
- ⏳ Formularios CRUD (12+ formularios)
- ⏳ Diálogos especializados (5+ diálogos)
- ⏳ Tablas de listado (JTable)
- ⏳ Paneles de búsqueda
- ⏳ Reportes y exportación
- ⏳ Gestión de permisos por rol
- ⏳ Validaciones avanzadas

---

## 🚀 PRÓXIMOS PASOS

### **Prioridad Alta (P0):**
1. **Implementar Formulario de Inspecciones**
   - InspeccionFitosanitariaForm (crear/editar)
   - InspeccionFitosanitariaListPanel (listar con JTable)
   - InspeccionFitosanitariaSearchDialog (búsqueda)

2. **Implementar Formulario de Productores**
   - ProductorForm (crear/editar)
   - ProductorListPanel (listar)

### **Prioridad Media (P1):**
3. **Implementar Formulario de Predios**
   - PredioForm (crear/editar con selección de Propietario)
   - PredioListPanel (listar)

4. **Implementar Formulario de Lotes**
   - LoteForm (crear/editar con selección de Predio)
   - LoteListPanel (listar)

### **Prioridad Baja (P2):**
5. **Implementar Sistema de Reportes**
   - ReportDialog (generación de reportes)
   - ExportCSVDialog (exportación)

6. **Implementar Gestión de Permisos**
   - Habilitar/deshabilitar menús según rol
   - Validar acceso a funcionalidades

---

## 📝 NOTAS TÉCNICAS

### **Decisiones de Diseño:**

1. **Uso de Swing sobre JavaFX:**
   - Mayor compatibilidad con JDK 8
   - Menor curva de aprendizaje
   - Bibliotecas más estables

2. **Patrón MVC:**
   - Model: Clases en package `model`
   - View: Clases en package `ui`
   - Controller: Clases en package `service`

3. **Validación en Capas:**
   - Capa UI: Validaciones de formato (DocumentFilters)
   - Capa Service: Validaciones de negocio (ServiceBase)
   - Capa DAO: Validaciones de integridad

4. **Gestión de Errores:**
   - Try-catch en eventos de UI
   - Mensajes user-friendly (MessageUtil)
   - Log de errores en servicios (Logger)

### **Convenciones de Código:**

```java
// Nombres de componentes
private JTextField emailField;      // Campos de texto
private JButton loginButton;        // Botones
private JPanel mainPanel;           // Paneles
private JLabel titleLabel;          // Labels

// Métodos de eventos
private void login() { }            // Acción principal
private void clearFields() { }      // Acción secundaria
private void showNotImplemented() { } // Placeholder

// Métodos de configuración
protected void initializeComponents() { }
protected void setupLayout() { }
protected void setupListeners() { }
```

---

## 🐛 PROBLEMAS CONOCIDOS

### **Advertencias de Importación:**
- ✅ Import no usado en BaseFrame (línea 7): `ui.utils.UIConstants`
- ✅ Import no usado en MainFrame (líneas 5-6): `ActionEvent`, `ActionListener`

**Solución:** Limpiar imports no utilizados o agregar uso de constantes.

### **Funcionalidad Pendiente:**
- ⚠️ LoginFrame: Hash de contraseñas (línea 258)
- ⚠️ MainFrame: Opciones de menú muestran "En Desarrollo"
- ⚠️ MainFrame: Cerrar sesión sale de la aplicación en lugar de regresar a Login

---

## 📈 MÉTRICAS

| Métrica | Valor |
|---------|-------|
| Archivos creados | 10 |
| Líneas de código (aprox.) | 1,200 |
| Clases creadas | 10 |
| Métodos públicos | 45+ |
| Constantes definidas | 60+ |
| Tiempo estimado | 4 horas |

---

## ✅ CHECKLIST DE COMPLETITUD

- [x] Estructura de paquetes creada
- [x] Clases base implementadas
- [x] Constantes UI definidas
- [x] Utilidades de mensajes
- [x] Filtros de documento
- [x] Sistema de login funcional
- [x] Ventana principal funcional
- [ ] Formularios CRUD
- [ ] Diálogos especializados
- [ ] Tablas de listado
- [ ] Sistema de reportes
- [ ] Permisos por rol

---

## 🎯 CONCLUSIÓN

Se ha completado exitosamente el **40% de la Fase 6 (UI)**, estableciendo una base sólida y escalable para el desarrollo de formularios CRUD. 

**Fundamentos implementados:**
- ✅ Arquitectura de componentes (BaseFrame, BasePanel, BaseDialog)
- ✅ Sistema de constantes centralizado
- ✅ Utilidades de mensajería y validación
- ✅ Login funcional con autenticación
- ✅ Ventana principal con menús estructurados

**Siguiente Sprint:**
- 🎯 Implementar formularios CRUD para Inspecciones
- 🎯 Implementar listados con JTable
- 🎯 Integrar servicios con UI

**Estado del Proyecto:**
- **Fases Completadas:** 5/8 (62.5%)
- **Fase Actual:** FASE 6 - UI (40%)
- **Próxima Fase:** Continuar FASE 6

---

**Fecha de Actualización:** 13 de Noviembre de 2025  
**Autor:** Equipo de Desarrollo (Isabella Vargas, Ricardo Viancha, Iswar Corrales, Andres Rivero)
