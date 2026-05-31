
package poo;

import controller.ContenidoController;


public class PruebaAudioVisual {
    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("   Sistema de Gestion Audiovisual - MVC    ");
        System.out.println("              Iniciando...                  ");
        System.out.println("==============================================\n");

        // Crear el controlador e iniciar la aplicación
        ContenidoController controlador = new ContenidoController();
        controlador.iniciar();
    }
}