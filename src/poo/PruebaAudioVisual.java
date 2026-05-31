
package poo;

import controller.ContenidoController;

/**
 * Clase Main - Punto de entrada del sistema de gestión audiovisual
 * Implementa el Patrón MVC (Model-View-Controller)
 * 
 * Estructura:
 * - MODEL (M): Clases en uni1a.*, ups.expancion.*
 * - VIEW (V): ConsoleView en package view
 * - CONTROLLER (C): ContenidoController en package controller
 * - SERVICE: ContenidoService en package service (capa de lógica de negocio)
 */
public class PruebaAudioVisual {
    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("   Sistema de Gestión Audiovisual - MVC    ");
        System.out.println("              Iniciando...                  ");
        System.out.println("==============================================\n");

        // Crear el controlador e iniciar la aplicación
        ContenidoController controlador = new ContenidoController();
        controlador.iniciar();
    }
}