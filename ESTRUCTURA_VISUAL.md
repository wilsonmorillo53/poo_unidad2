# 📐 ESTRUCTURA VISUAL DEL PROYECTO MVC

## 🏗️ Árbol de Directorios Completo

```
poo_unidad2/
│
├── 📄 README.md                          # Documentación original
├── 📄 README_MVC.md                      # 📖 Guía MVC (LEER PRIMERO)
├── 📄 MVC_DOCUMENTATION.md               # 📖 Documentación detallada
├── 📄 RESUMEN_MVC.md                     # 📋 Resumen del proyecto
├── 📄 ESTRUCTURA_VISUAL.md               # Este archivo
│
├── 📁 src/                               # Código fuente
│   │
│   ├── 📁 poo/                           # 🚀 PUNTO DE ENTRADA
│   │   └── PruebaAudioVisual.java        # main() - Inicia la aplicación
│   │
│   ├── 📁 controller/                    # 🎛️ CONTROLLER LAYER (NUEVO)
│   │   └── ContenidoController.java      # Coordinador MVC central
│   │                                    # ~450 líneas
│   │                                    # Responsabilidades:
│   │                                    # - Maneja eventos del usuario
│   │                                    # - Invoca métodos del Service
│   │                                    # - Pasa datos a la View
│   │                                    # - Orquesta el flujo
│   │
│   ├── 📁 view/                          # 👁️ VIEW LAYER (NUEVO)
│   │   └── ConsoleView.java              # Interfaz de usuario en consola
│   │                                    # ~280 líneas
│   │                                    # Responsabilidades:
│   │                                    # - Mostrar menús
│   │                                    # - Solicitar entrada
│   │                                    # - Presentar información
│   │                                    # - Mostrar mensajes
│   │
│   ├── 📁 service/                       # 🔧 SERVICE LAYER (NUEVO)
│   │   └── ContenidoService.java         # Lógica de negocio
│   │                                    # ~310 líneas
│   │                                    # Responsabilidades:
│   │                                    # - CRUD de contenido
│   │                                    # - Búsquedas y filtrados
│   │                                    # - Gestión de actores
│   │                                    # - Gestión de temporadas
│   │                                    # - Estadísticas
│   │
│   └── 📁 uni1a/                         # 📦 MODEL LAYER (EXISTENTE)
│       │
│       ├── ContenidoAudiovisual.java     # Clase base abstracta
│       ├── Pelicula.java                 # Representación de películas
│       ├── SerieDeTV.java                # Representación de series
│       ├── Documental.java               # Representación de documentales
│       │
│       └── 📁 ups/
│           ├── 📁 expancion/             # Expansión de modelos
│           │   ├── VideoNeflix.java      # Contenido Netflix
│           │   └── VideoStriming.java    # Contenido de streaming
│           │
│           └── 📁 clases/
│               └── 📁 adicionales/       # Clases complementarias
│                   ├── Actor.java        # Representación de actores
│                   ├── Temporada.java    # Representación de temporadas
│                   └── Investigador.java # Investigadores de documentales
│
├── 📁 bin/                               # 🔧 CÓDIGO COMPILADO
│   │
│   ├── 📁 controller/
│   │   └── ContenidoController.class
│   │
│   ├── 📁 view/
│   │   └── ConsoleView.class
│   │
│   ├── 📁 service/
│   │   └── ContenidoService.class
│   │
│   ├── 📁 poo/
│   │   └── PruebaAudioVisual.class
│   │
│   └── 📁 uni1a/
│       ├── ContenidoAudiovisual.class
│       ├── Pelicula.class
│       ├── SerieDeTV.class
│       ├── Documental.class
│       └── [+ más clases compiladas]
│
└── .gitignore                            # Archivos a ignorar en Git
```

---

## 🔗 Relaciones entre Componentes

### Flujo de Dependencias

```
            ┌─────────────────────────┐
            │  PruebaAudioVisual      │
            │  (main)                 │
            │  - Punto de entrada     │
            └────────────┬────────────┘
                         │
                         │ crea
                         │
                         ▼
        ┌────────────────────────────────┐
        │  ContenidoController           │
        │  - Coordina MVC                │
        │  - Maneja menús                │
        └────────┬─────────────────┬─────┘
                 │                 │
        usa View │                 │ usa Service
                 │                 │
     ┌───────────▼──────┐   ┌──────▼──────────────┐
     │ ConsoleView      │   │ ContenidoService    │
     │ - Menús          │   │ - Lógica de negocio │
     │ - I/O            │   │ - CRUD              │
     │ - Presentación   │   │ - Búsquedas         │
     └──────────────────┘   └──────┬──────────────┘
                                   │
                            usa Model │
                                   │
                            ┌──────▼──────────────┐
                            │  Model Classes      │
                            │  - Películas        │
                            │  - Series           │
                            │  - Documentales     │
                            │  - Actores          │
                            │  - Temporadas       │
                            └─────────────────────┘
```

### Matriz de Comunicación

```
                 View  Controller  Service  Model
View              -       ←         -        -
Controller        →       -         →        -
Service           -       ←         -        →
Model             -       -         ←        -

Leyenda:
→ = Invoca métodos
← = Devuelve resultados
- = No se comunica directamente
```

---

## 📊 Estadísticas del Proyecto

### Líneas de Código por Componente

```
ContenidoController.java  │████████████████████░░░░│ ~450 líneas (43%)
ContenidoService.java     │████████████░░░░░░░░░░░░│ ~310 líneas (30%)
ConsoleView.java          │████████░░░░░░░░░░░░░░░░│ ~280 líneas (27%)
────────────────────────────────────────────────────────────
TOTAL MVC NUEVO           │████████████████████████│ ~1040 líneas
```

### Distribución de Responsabilidades

```
VIEW (27%)
├── Menús: 15%
├── Entrada/Salida: 8%
└── Presentación: 4%

CONTROLLER (43%)
├── Manejo de Menús: 20%
├── Coordinación: 15%
├── Validaciones: 5%
└── Orquestación: 3%

SERVICE (30%)
├── CRUD Contenido: 12%
├── Búsqueda/Filtrado: 10%
├── Gestión Actores: 5%
└── Gestión Temporadas: 3%
```

---

## 🔄 Ciclo de Vida de una Operación

### Ejemplo: Crear una Película

```
1. Usuario en Consola
   └─→ "Selecciona opción: 1"

2. ConsoleView
   ├─→ mostrarMenuPrincipal()
   ├─→ mostrarMenuContenido()
   └─→ leerCadena("Título: ")
       leerEntero("Duración: ")
       leerCadena("Género: ")
       leerCadena("Estudio: ")

3. ContenidoController
   ├─→ crearPelicula()
   └─→ servicio.crearPelicula(titulo, duracion, genero, estudio)

4. ContenidoService
   ├─→ Validar datos
   ├─→ new Pelicula(...)
   └─→ contenidos.add(pelicula)

5. Model
   └─→ Pelicula instance created with data

6. Response back
   ContenidoService → ContenidoController → ConsoleView
   "✓ Película creada exitosamente"

7. Usuario Ve
   └─→ Mensaje de éxito
       Menú vuelve a mostrar
```

---

## 📋 Operaciones Disponibles

### 1. CRUD Contenido

```
Crear (Create)
├── Pelicula
├── SerieDeTV
├── Documental
├── VideoNeflix
└── VideoStriming

Leer (Read)
├── obtenerTodosLosContenidos()
├── obtenerContenidoPorId(id)
└── mostrarDetallesContenido()

Actualizar (Update)
├── actualizarTitulo()
├── actualizarDuracion()
└── actualizarGenero()

Eliminar (Delete)
└── eliminarContenido()
```

### 2. Búsqueda y Filtrado

```
Búsquedas
├── buscarPorTitulo(String)
├── buscarPorGenero(String)
├── buscarPorTipo(String)
└── obtenerContenidoPorId(int)

Resultados
└── List<ContenidoAudiovisual>
```

### 3. Gestión de Actores

```
Crear Actor
└── crearActor(nombre, apellido)

Listar Actores
├── obtenerTodosLosActores()
└── buscarActor(nombre, apellido)

Asociar a Películas
└── agregarActorAPelicula(idPelicula, actor)
```

### 4. Gestión de Temporadas

```
Crear Temporada
└── agregarTemporadaASerie(idSerie, num, episodios)

Ver Detalles
└── obtenerSerie(id)
```

---

## 💾 Estructura de Datos

### En Memoria (ContenidoService)

```
ContenidoService
├── List<ContenidoAudiovisual> contenidos
│   ├── Pelicula 1
│   ├── SerieDeTV 1
│   ├── Documental 1
│   ├── VideoNeflix 1
│   └── VideoStriming 1
│
└── List<Actor> actores
    ├── Actor 1
    ├── Actor 2
    └── Actor N
```

### Relaciones Internas

```
Película ←→ Actores (Agregación)
├── pelicula.actores: List<Actor>
└── actor.pelicula: Pelicula

Serie ←→ Temporadas (Composición)
├── serie.temporadas: List<Temporada>
└── temporada.serie: SerieDeTV

Documental ←→ Investigadores (Agregación)
├── documental.investigadores: List<Investigador>
└── investigador.documental: Documental
```

---

## 🎯 Flujos Principales de Menú

### Flujo 1: Gestión de Contenido

```
┌─ Menú Principal
│   ├─→ "1. Gestionar Contenido"
│   │   ├─→ "1. Crear Película"
│   │   ├─→ "2. Crear Serie"
│   │   ├─→ "3. Crear Documental"
│   │   ├─→ "4. Crear Video Netflix"
│   │   ├─→ "5. Crear Video Streaming"
│   │   ├─→ "6. Actualizar Contenido"
│   │   ├─→ "7. Eliminar Contenido"
│   │   └─→ "0. Volver"
│   │
│   ├─ Vuelve al Menú Principal
```

### Flujo 2: Búsqueda

```
┌─ Menú Principal
│   ├─→ "4. Búsqueda y Filtrado"
│   │   ├─→ "1. Buscar por Título"
│   │   ├─→ "2. Buscar por Género"
│   │   ├─→ "3. Buscar por Tipo"
│   │   ├─→ "4. Obtener por ID"
│   │   └─→ "0. Volver"
│   │
│   ├─ Muestra resultados
│   ├─ Vuelve al Menú
```

---

## ✅ Ventajas Arquitectónicas

```
┌────────────────────────────────────┐
│  Patrón MVC - Beneficios Clave     │
├────────────────────────────────────┤
│                                    │
│  ✅ Separación de Responsabilidades│
│  ├─ View: Solo presentación        │
│  ├─ Controller: Solo coordinación  │
│  ├─ Service: Solo lógica           │
│  └─ Model: Solo datos              │
│                                    │
│  ✅ Facilidad de Mantenimiento     │
│  ├─ Cambios localizados            │
│  ├─ Menos efectos secundarios      │
│  └─ Código predecible              │
│                                    │
│  ✅ Escalabilidad                  │
│  ├─ Agregar nuevas vistas fácil    │
│  ├─ Reutilizar lógica              │
│  └─ Extender funcionalidad         │
│                                    │
│  ✅ Testabilidad                   │
│  ├─ Tests unitarios claros         │
│  ├─ Mock objects fácil             │
│  └─ Componentes independientes     │
│                                    │
│  ✅ Flexibilidad                   │
│  ├─ Cambiar View sin tocar lógica  │
│  ├─ Agregar BD sin cambiar UI      │
│  └─ Múltiples interfaces posibles  │
│                                    │
└────────────────────────────────────┘
```

---

## 🚀 Próximos Pasos Sugeridos

### Fase 1: Mejorar la UI Actual
- [ ] Usar colores en la consola
- [ ] Agregar animaciones ASCII
- [ ] Mejorar formateo de tablas

### Fase 2: Persistencia
- [ ] Agregar SQLite
- [ ] Guardar en archivos JSON
- [ ] Implementar DAO pattern

### Fase 3: Interfaz Gráfica
- [ ] Crear GUI con JavaFX
- [ ] Reemplazar ConsoleView sin cambiar Controller
- [ ] Agregar iconos y temas

### Fase 4: Web
- [ ] Crear REST API con Spring Boot
- [ ] Frontend web con HTML/CSS/JS
- [ ] Deploy en servidor

### Fase 5: Mobile
- [ ] App Android/iOS
- [ ] Sincronización en la nube
- [ ] Notificaciones

---

## 📚 Archivos de Documentación

| Archivo | Propósito |
|---------|-----------|
| README.md | Documentación original |
| README_MVC.md | **Guía principal (COMIENZA AQUÍ)** |
| MVC_DOCUMENTATION.md | Documentación técnica detallada |
| RESUMEN_MVC.md | Resumen de cambios realizados |
| ESTRUCTURA_VISUAL.md | Este archivo (diagrama visual) |

---

**Proyecto MVC completado exitosamente** ✅

*Todo está listo para usar, extender y mejorar*
