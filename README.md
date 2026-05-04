# Proyecto de Gestión Audiovisual (POO - Unidad 1)

## Objetivos y Propósito del Proyecto
El propósito principal de este proyecto es aplicar y demostrar los fundamentos de la Programación Orientada a Objetos (POO) utilizando Java. Se ha diseñado un sistema para gestionar un catálogo de contenido audiovisual simulando plataformas de streaming, aplicando conceptos clave como:
* **Herencia**: Creación de una jerarquía de clases a partir de una clase base abstracta (`ContenidoAudiovisual`).
* **Polimorfismo**: Manejo de distintas clases derivadas de manera unificada a través de arreglos.
* **Encapsulamiento**: Uso de modificadores de acceso restrictivos junto a *getters* y *setters*.
* **Relaciones entre Clases**: Implementación de fuertes asociaciones, agregaciones y composiciones entre distintos objetos del dominio.

## Clases y Funcionalidades Nuevas Añadidas
Además de las clases base originales (`Pelicula`, `SerieDeTV` y `Documental`), el sistema ha sido expandido significativamente con la creación de los siguientes módulos:

### Clases Complementarias (Paquete `uni1a.ups.clases.adicionales`)
* **`Actor`**: Modela a los actores que participan en los títulos. Relacionado por **agregación** con `Pelicula`, `VideoNeflix` y `VideoStriming`.
* **`Temporada`**: Modela las temporadas. Relacionado fuertemente por **composición** con `SerieDeTV` y `VideoStriming`.
* **`Investigador`**: Modela a los expertos de un documental. Relacionado por **agregación** con `Documental`.

### Clases de Expansión (Paquete `ups.expancion`)
* ** VideoNeflix**: Nueva subclase de `ContenidoAudiovisual`. Añade funcionalidades específicas como identificar la exclusividad (`esOriginal`) y calidad de transmisión (`resolucion`), integrando arreglos de actores.
* **`VideoStriming`**: Nueva subclase de `ContenidoAudiovisual`. Orientada a cualquier servicio on-demand en general, añadiendo `plataforma` y `visualizaciones`. Soporta relaciones complejas guardando tanto actores como temporadas.

## Instrucciones para Clonar y Ejecutar el Proyecto
Para ejecutar este proyecto de forma local, necesitas tener instalado **Java (JDK)**. Sigue estos pasos en tu terminal (Windows, Linux o macOS):

1. **Clonar el repositorio:**
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd poo_unidad1
   ```

2. **Compilar el proyecto:**
   Desde el directorio raíz del proyecto (`poo_unidad1`), asegúrate de crear el directorio de binarios y compilar los archivos:
   ```bash
   mkdir bin
   javac -sourcepath src -d bin src/poo/PruebaAudioVisual.java
   ```

3. **Ejecutar la clase principal:**
   Inicia la aplicación ejecutando la clase de pruebas:
   ```bash
   java -cp bin poo.PruebaAudioVisual
   ```
   *Esto imprimirá de forma dinámica por consola todos los detalles y metadatos del contenido creado, sus actores y temporadas.*

## Mejoras Adicionales Implementadas

* **Buenas Prácticas en el Código (`final`)**: Se optimizó la seguridad y legibilidad del código marcando con el modificador `final` todas las variables de instancia que albergan listas (Ej. `private final List<Actor> actores;`). Esto asegura que las estructuras de datos referenciadas no puedan ser sobrescritas erróneamente en tiempo de ejecución.
* **Prueba Funcional Integradora (`PruebaAudioVisual`)**: El archivo `main` fue refactorizado y convertido en un *Driver Program* completo. No solo levanta objetos vacíos, sino que recrea escenarios reales donde se instancian películas, se ligan a actores específicos, se configuran temporadas, y finalmente se somete todo el conjunto a un bucle polimórfico de tipo `ContenidoAudiovisual[]` para comprobar que todas las clases y dependencias funcionen al unísono sin arrojar excepciones.
* **Estructuración Modular (Paquetes)**: El proyecto fue fuertemente dividido en paquetes semánticos (`uni1a`, `ups.expancion`, `uni1a.ups.clases.adicionales`) garantizando un diseño limpio y escalable (Separation of Concerns).
