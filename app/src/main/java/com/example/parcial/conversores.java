package com.example.parcial;

public class conversores {

    String [][] etiquetas = {
            {"pie cuadrado","vara cuadrada", "yarda cuadrada","metro cuadrado","tareas","manzanas","hectareas"}

    };
    Double [][] valores = {
            {1.0, 0.13223088, 0.111111, 0.092903, 0.00014775, 0.00001319, 9.2903e-6},


    };
    String[] obtenerConversor(int posicion){
        return etiquetas[posicion];
    }
    double convertir(int tipo, int de, int a, double cantidad){
        return valores[tipo][a] / valores[tipo][de] * cantidad;
    }
}
