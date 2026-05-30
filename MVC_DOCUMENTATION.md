# Implementación del Patrón MVC en el Sistema de Gestión Audiovisual

## 📋 Descripción del Patrón MVC

El patrón **MVC (Model-View-Controller)** es un arquitectura de software que separa la aplicación en tres componentes principales:

1. **Model (Modelo)**: Representa la lógica de datos y negocios
2. **View (Vista)**: Presenta los datos al usuario (interfaz)
3. **Controller (Controlador)**: Coordina las interacciones entre Model y View

---

## 🏗️ Estructura del Proyecto MVC

```
src/
├── uni1a/                          # 📦 MODEL - Dominio de datos
│   ├── ContenidoAudiovisual.java   # Clase base abstracta
│   ├── Pelicula.java
│   ├── SerieDeTV.java
│   ├── Documental.java
│   └── ups/clases/adicionales/
│       ├── Actor.java
│       ├── Temporada.java
│       └── Investigador.java
├── ups/expancion/                  # 📦 MODEL - Extensiones
│   ├── VideoNeflix.java
│   └── VideoStriming.java
├── service/                        # 🔧 BUSINESS LOGIC
│   └── ContenidoService.java       # Lógica de negocio y CRUD
├── controller/                     # 🎛️ CONTROLLER
│   └── ContenidoController.java    # Coordinador MVC
├── view/                           # 👁️ VIEW
│   └── ConsoleView.java            # Interfaz de consola
└── poo/
    └── PruebaAudioVisual.java      # 🚀 Main - Punto de entrada
```

---

## 🔍 Detalles de Cada Componente

### 1️⃣ **MODEL** - `uni1a.*, ups.expancion.*`

**Responsabilidades:**
- Representar la estructura de datos del dominio
- Definir las relaciones entre entidades
- Implementar la lógica de encapsulamiento (getters/setters)
- Mantener la integridad de los datos

**Clases:**
- `ContenidoAudiovisual`: Clase base abstracta
- `Pelicula`, `SerieDeTV`, `Documental`: Subclases de contenido
- `VideoNeflix`, `VideoStriming`: Extensiones especializadas
- `Actor`, `Temporada`, `Investigador`: Clases complementarias

```java
// Ejemplo: El modelo define la estructura
public abstract class ContenidoAudiovisual {
    private String titulo;
    private int duracionEnMinutos;
    private String genero;
    // ... getters y setters
}
```

---

### 2️⃣ **VIEW** - `view.ConsoleView`

**Responsabilidades:**
- Presentar información al usuario
- Solicitar entrada del usuario
- Mostrar mensajes de éxito, error y advertencia
- Mantener la interfaz limpia y organizada

**Funcionalidades:**
- Menús interactivos
- Formularios de entrada
- Listados formateados
- Sistemas de notificación

```java
// Ejemplo: La vista maneja la presentación
public class ConsoleView {
    public int mostrarMenuPrincipal() { /* ... */ }
    public String leerCadena(String mensaje) { /* ... */ }
    public void mostrarContenidos(List<ContenidoAudiovisual> contenidos) { /* ... */ }
}
```

---

### 3️⃣ **CONTROLLER** - `controller.ContenidoController`

**Responsabilidades:**
- Coordinar interacciones entre View y Service
- Procesar decisiones del usuario
- Invocar métodos del servicio según las acciones
- Pasar datos entre componentes

**Funcionalidades:**
- Manejo de menús
- Procesamiento de acciones CRUD
- Validación de entrada
- Orquestación de flujos

```java
// Ejemplo: El controlador coordina componentes
public class ContenidoController {
    private ContenidoService servicio;
    private ConsoleView vista;
    
    public void crearPelicula() {
        String titulo = vista.leerCadena("Título: ");
        servicio.crearPelicula(titulo, ...);
        vista.mostrarExito("Película creada");
    }
}
```

---

### 4️⃣ **SERVICE** - `service.ContenidoService` (Capa de Lógica de Negocio)

**Responsabilidades:**
- Implementar la lógica de negocio
- Realizar operaciones CRUD
- Gestionar búsquedas y filtrados
- Mantener la consistencia de datos

**Operaciones:**
- CRUD de Contenido (Crear, Leer, Actualizar, Eliminar)
- Búsquedas por título, género, tipo
- Gestión de actores
- Gestión de temporadas
- Estadísticas del sistema

```java
// Ejemplo: El servicio encapsula la lógica
public class ContenidoService {
    private List<ContenidoAudiovisual> contenidos;
    
    public void crearPelicula(String titulo, ...) { /* lógica */ }
    public List<ContenidoAudiovisual> buscarPorGenero(String genero) { /* ... */ }
}
```

---

## 🔄 Flujo de la Aplicación

```
┌─────────────────────────────────────────────────────────────┐
│                   Punto de Entrada (main)                   │
│              PruebaAudioVisual.main()                        │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│              CONTROLLER: ContenidoController                │
│              - Inicializa la aplicación                     │
│              - Coordina usuario ↔ servicio                  │
└──────────┬──────────────────────────┬───────────────────────┘
           │                          │
           ▼                          ▼
  ┌─────────────────┐      ┌──────────────────────┐
  │ VIEW: Menús     │      │ SERVICE: Lógica      │
  │ - Mostrar       │◄────►│ - CRUD               │
  │ - Solicitar     │      │ - Búsquedas          │
  │   entrada       │      │ - Validaciones       │
  └─────────────────┘      └──────────┬───────────┘
                                      │
                                      ▼
                            ┌──────────────────┐
                            │ MODEL: Datos     │
                            │ - Películas      │
                            │ - Series         │
                            │ - Actores, etc.  │
                            └──────────────────┘
```

---

## 📊 Relaciones entre Componentes

### Dependencias Unidireccionales (Una dirección)

```
View ───────────────────► No depende del Controller
View ───────────────────► No depende del Model

Controller ─────────────► Usa View (muestra/solicita datos)
Controller ─────────────► Usa Service (lógica de negocio)

Service ────────────────► Usa Model (manipula datos)
```

### Comunicación

1. **Usuario → View**: Interacción con menús
2. **View → Controller**: Envía decisiones del usuario
3. **Controller → Service**: Solicita operaciones
4. **Service → Model**: Manipula datos
5. **Service → Controller**: Devuelve resultados
6. **Controller → View**: Muestra resultados

---

## ✅ Ventajas del Patrón MVC

| Ventaja | Descripción |
|---------|-------------|
| 📋 **Separación de Responsabilidades** | Cada componente tiene un único rol claro |
| 🔄 **Fácil Mantenimiento** | Cambios en un componente no afectan otros |
| 🧪 **Testabilidad** | Cada parte se puede probar independientemente |
| 📈 **Escalabilidad** | Fácil agregar nuevas funcionalidades |
| 🔌 **Reutilización** | Los componentes se pueden reutilizar |
| 🎨 **Flexibilidad** | Cambiar la interfaz sin tocar la lógica |

---

## 🚀 Cómo Ejecutar la Aplicación

### Compilar:
```bash
mkdir bin
javac -sourcepath src -d bin src/poo/PruebaAudioVisual.java
```

### Ejecutar:
```bash
java -cp bin poo.PruebaAudioVisual
```

### Operaciones Disponibles:

1. **Gestionar Contenido**
   - Crear película, serie, documental, videos
   - Actualizar propiedades
   - Eliminar contenido

2. **Gestionar Actores**
   - Crear actores
   - Listar actores
   - Asociar actores a películas

3. **Gestionar Temporadas**
   - Añadir temporadas a series
   - Ver detalles de temporadas

4. **Búsqueda y Filtrado**
   - Buscar por título
   - Filtrar por género
   - Buscar por tipo de contenido
   - Obtener detalles por ID

5. **Estadísticas**
   - Ver resumen del sistema
   - Distribución de contenidos

---

## 🔧 Extensiones Futuras

El patrón MVC facilita agregar nuevas capas:

```
┌─────────────────────────────────────────────┐
│  Posibles Extensiones:                      │
├─────────────────────────────────────────────┤
│ - View REST API (en lugar de ConsoleView)   │
│ - View Interfaz Gráfica (Swing/JavaFX)      │
│ - Database Layer (Persistencia)             │
│ - Authentication Layer (Seguridad)          │
│ - Cache Layer (Rendimiento)                 │
└─────────────────────────────────────────────┘
```

---

## 📝 Conclusión

La implementación del patrón MVC en este proyecto demuestra:

✅ **Arquitectura clara y organizada**
✅ **Separación de responsabilidades efectiva**
✅ **Facilidad para mantener y extender el código**
✅ **Mejor experiencia de usuario con menús interactivos**
✅ **Código altamente reutilizable y testeable**

El patrón MVC es fundamental en desarrollo de software profesional y este proyecto es un excelente ejemplo de su aplicación práctica en Java.
