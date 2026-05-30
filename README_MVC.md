# 🎬 Sistema de Gestión de Contenido Audiovisual - Implementación MVC

## 📌 Resumen Ejecutivo

Este proyecto es un **Sistema de Gestión Audiovisual** que implementa el **Patrón MVC (Model-View-Controller)** en Java. El sistema permite gestionar películas, series de TV, documentales, y contenido de plataformas de streaming (Netflix, Amazon Prime, etc.).

### ✨ Características Principales

✅ **Interfaz de Consola Interactiva** con menús intuitivos
✅ **CRUD Completo** para contenido audiovisual
✅ **Gestión de Actores** y asociación con películas
✅ **Gestión de Temporadas** para series de TV
✅ **Búsqueda y Filtrado** avanzado por título, género y tipo
✅ **Estadísticas del Sistema** en tiempo real
✅ **Arquitectura MVC** bien estructurada y escalable

---

## 🏗️ Estructura del Proyecto

```
poo_unidad2/
├── src/
│   ├── poo/
│   │   └── PruebaAudioVisual.java        # 🚀 Punto de entrada (main)
│   ├── controller/
│   │   └── ContenidoController.java      # 🎛️ Controlador principal
│   ├── view/
│   │   └── ConsoleView.java              # 👁️ Interfaz de usuario
│   ├── service/
│   │   └── ContenidoService.java         # 🔧 Lógica de negocio
│   └── uni1a/                            # 📦 Modelos de datos
│       ├── ContenidoAudiovisual.java
│       ├── Pelicula.java
│       ├── SerieDeTV.java
│       ├── Documental.java
│       └── ups/clases/adicionales/
│           ├── Actor.java
│           ├── Temporada.java
│           └── Investigador.java
├── ups/expancion/
│   ├── VideoNeflix.java
│   └── VideoStriming.java
├── bin/                                  # Archivos compilados (.class)
├── README.md                             # Documentación original
├── MVC_DOCUMENTATION.md                  # 📖 Documentación detallada del MVC
└── .gitignore
```

---

## 🚀 Cómo Compilar y Ejecutar

### Requisitos Previos
- **Java JDK 8+** instalado
- Terminal o CMD con acceso a `javac` y `java`

### Paso 1: Compilar el Proyecto

```bash
cd poo_unidad2
mkdir -p bin
javac -sourcepath src -d bin src/poo/PruebaAudioVisual.java
```

### Paso 2: Ejecutar la Aplicación

```bash
java -cp bin poo.PruebaAudioVisual
```

### Resultado Esperado
```
╔════════════════════════════════════════════╗
║   Sistema de Gestión Audiovisual - MVC    ║
║              Iniciando...                  ║
╚════════════════════════════════════════════╝

╔════════════════════════════════════════════╗
║   SISTEMA DE GESTIÓN AUDIOVISUAL - MVC    ║
╚════════════════════════════════════════════╝

1. Gestionar Contenido
2. Gestionar Actores
3. Gestionar Temporadas (Series)
4. Búsqueda y Filtrado
5. Ver Estadísticas
6. Listar Todo el Contenido
0. Salir

Selecciona opción: 
```

---

## 📚 Funcionalidades Disponibles

### 1️⃣ Gestionar Contenido
- ✅ Crear Película
- ✅ Crear Serie de TV
- ✅ Crear Documental
- ✅ Crear Video Netflix
- ✅ Crear Video Streaming
- ✅ Actualizar propiedades (título, duración, género)
- ✅ Eliminar contenido

### 2️⃣ Gestionar Actores
- ✅ Crear nuevos actores
- ✅ Listar todos los actores registrados
- ✅ Asociar actores a películas específicas

### 3️⃣ Gestionar Temporadas
- ✅ Añadir temporadas a series de TV
- ✅ Ver detalles de temporadas
- ✅ Especificar número de episodios por temporada

### 4️⃣ Búsqueda y Filtrado
- ✅ Buscar por título (búsqueda parcial)
- ✅ Filtrar por género
- ✅ Filtrar por tipo de contenido (Película, Serie, Documental, etc.)
- ✅ Obtener detalles específicos por ID

### 5️⃣ Estadísticas
- ✅ Total de contenidos en el sistema
- ✅ Total de actores registrados
- ✅ Distribución por tipo de contenido
- ✅ Información en tiempo real

---

## 🎯 Ejemplo de Uso

### Escenario: Crear una Película

```
1. Selecciona "Gestionar Contenido" → "Crear Película"
2. Ingresa los datos:
   - Título: Avatar
   - Duración en minutos: 162
   - Género: Ciencia Ficción
   - Estudio productor: 20th Century Studios
3. ¡La película se ha creado exitosamente!
```

### Escenario: Buscar Contenido por Género

```
1. Selecciona "Búsqueda y Filtrado" → "Buscar por Género"
2. Ingresa el género: Acción
3. Sistema muestra todas las películas, series y documentales de acción
```

---

## 📖 Arquitectura MVC Detallada

### 🔄 Flujo de Datos

```
┌──────────────┐
│   Usuario    │
└──────┬───────┘
       │ (interacción)
       ▼
┌──────────────────────────────┐
│  VIEW: ConsoleView           │
│  - Menús                     │
│  - Entrada de datos          │
│  - Presentación              │
└──────┬───────────────────────┘
       │ (decisión del usuario)
       ▼
┌──────────────────────────────┐
│ CONTROLLER: ContenidoController
│  - Procesa decisiones        │
│  - Coordina componentes      │
│  - Flujo de la app           │
└──────┬───────────────────────┘
       │ (solicita operación)
       ▼
┌──────────────────────────────┐
│ SERVICE: ContenidoService    │
│  - Lógica de negocio         │
│  - CRUD                      │
│  - Búsquedas                 │
└──────┬───────────────────────┘
       │ (manipula datos)
       ▼
┌──────────────────────────────┐
│  MODEL: Clases de Dominio    │
│  - ContenidoAudiovisual      │
│  - Pelicula, SerieDeTV, etc. │
│  - Actor, Temporada, etc.    │
└──────────────────────────────┘
```

### 📋 Descripción de Componentes

#### **MODEL** (uni1a, ups.expancion)
Representa la estructura de datos del dominio:
- Clases de entidades
- Relaciones (agregación, composición)
- Getters y setters

#### **VIEW** (view.ConsoleView)
Interfaz de usuario:
- Menús interactivos
- Solicitud de entrada
- Presentación de datos
- Mensajes al usuario

#### **CONTROLLER** (controller.ContenidoController)
Coordinador central:
- Maneja eventos del usuario
- Invoca métodos del servicio
- Pasa datos a la vista
- Orquesta el flujo de la aplicación

#### **SERVICE** (service.ContenidoService)
Lógica de negocio:
- Operaciones CRUD
- Búsquedas y filtrados
- Validaciones
- Gestión de datos

---

## 🔐 Principios SOLID Aplicados

| Principio | Descripción | Implementación |
|-----------|-------------|-----------------|
| **S**ingle Responsibility | Cada clase tiene una única responsabilidad | View solo muestra, Service solo opera datos |
| **O**pen/Closed | Abierto para extensión, cerrado para modificación | Se pueden agregar nuevas vistas sin cambiar lógica |
| **L**iskov Substitution | Las subclases pueden reemplazar sus padres | Las clases heredan de ContenidoAudiovisual |
| **I**nterface Segregation | Interfaces específicas y pequeñas | Métodos bien definidos en cada clase |
| **D**ependency Inversion | Depender de abstracciones, no implementaciones | Controller usa interfaces de View y Service |

---

## 💡 Ventajas del Patrón MVC

✅ **Separación de Responsabilidades** - Cada componente tiene un rol claro
✅ **Mantenibilidad** - Cambios en un componente no afectan otros
✅ **Escalabilidad** - Fácil agregar nuevas funcionalidades
✅ **Testabilidad** - Componentes se pueden probar independientemente
✅ **Reutilización** - Los componentes se pueden reutilizar en otros proyectos
✅ **Flexibilidad** - Se pueden cambiar vistas sin tocar lógica de negocio

---

## 🛠️ Posibles Extensiones Futuras

```
┌─────────────────────────────────────────┐
│ Extensiones Recomendadas:               │
├─────────────────────────────────────────┤
│ 1. Database Layer                       │
│    - Persistencia en base de datos      │
│    - MySQL, PostgreSQL, SQLite          │
│                                         │
│ 2. GUI Desktop (JavaFX/Swing)           │
│    - Interfaz gráfica visual            │
│    - Windows, Mac, Linux                │
│                                         │
│ 3. REST API                             │
│    - Spring Boot                        │
│    - Endpoints HTTP                     │
│                                         │
│ 4. Web Interface (JSP/Thymeleaf)        │
│    - Aplicación web                     │
│    - Navegador web                      │
│                                         │
│ 5. Authentication & Authorization       │
│    - Login de usuarios                  │
│    - Roles y permisos                   │
│                                         │
│ 6. Notifications                        │
│    - Email notifications                │
│    - SMS alerts                         │
└─────────────────────────────────────────┘
```

---

## 📋 Documentación Adicional

Para información más detallada sobre la implementación del patrón MVC, consulta [MVC_DOCUMENTATION.md](MVC_DOCUMENTATION.md)

---

## 👨‍💻 Autor

Proyecto de Programación Orientada a Objetos (POO) - Unidad 2
Implementación de Patrón MVC en Java

---

## 📄 Licencia

Este proyecto es de uso educativo. Libre para estudiar, modificar y distribuir.

---

## ❓ Preguntas Frecuentes

**P: ¿Qué es el patrón MVC?**
R: Es una arquitectura que separa la aplicación en tres componentes: Modelo (datos), Vista (presentación) y Controlador (coordinación).

**P: ¿Por qué usar MVC?**
R: Permite organizar el código de forma clara, facilita el mantenimiento, pruebas y permite cambiar fácilmente la interfaz sin afectar la lógica de negocio.

**P: ¿Puedo reemplazar la vista de consola con una GUI?**
R: Sí, gracias a la separación de responsabilidades, puedes crear una nueva clase View con interfaz gráfica sin cambiar el Controlador ni el Service.

**P: ¿Cómo agrego un nuevo tipo de contenido?**
R: 
1. Crea una nueva clase que herede de `ContenidoAudiovisual`
2. Implementa el método `mostrarDetalles()`
3. Agrega un método de creación en `ContenidoService`
4. Agrega la opción en el menú del `ContenidoController`

---

¡Espero que disfrutes explorando este proyecto MVC! 🚀
