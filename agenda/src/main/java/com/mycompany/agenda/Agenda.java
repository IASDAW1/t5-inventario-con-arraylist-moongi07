/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.agenda;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Andrea
 */
public class Agenda {

    public static void main(String[] args) {
        Lista clientes = new Lista();
        /*Cargamos la lista de clientes: */
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("clientes.dat"))) {
            clientes = (Lista) in.readObject();
        } catch (IOException ex) {
            System.out.println("lista vacía");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        /*Gestión de clientes: */
        int opcion;
        do {
            System.out.println("__________________");
            System.out.println("1. Añadir cliente");
            System.out.println("2. Modificar datos cliente");
            System.out.println("3. Baja de cliente");
            System.out.println("4. Listar clientes");
            System.out.println("5. Salir");
            System.out.print("\nIntroducir opción: ");
            opcion = new Scanner(System.in).nextInt();
            System.out.println("");
            switch (opcion) {
                case 1 -> { //Añadir un cliente nuevo
                    System.out.print("dni: ");
                    String dni = new Scanner(System.in).next();
                    System.out.print("nombre: ");
                    String nombre = new Scanner(System.in).nextLine();
                    System.out.print("fecha de nacimiento (dd-mm-aaaa): ");
                    String fNac = new Scanner(System.in).next();
                    System.out.print("saldo: ");
                    int saldo = new Scanner(System.in).nextInt();
                    Cliente nuevo = new Cliente(dni, nombre, fNac, saldo);
                    clientes.insertarFinal(nuevo);
                }
                case 2 -> { //modificar
                    System.out.print("dni persona a modificar: ");
                    String dni = new Scanner(System.in).next();
                    Cliente claveBusqueda = new Cliente(dni);

                    int indice = clientes.buscar(claveBusqueda);

                    if (indice != -1) {//cliente encontrado

                        System.out.print("nombre nuevo: ");
                        String nombre = new Scanner(System.in).nextLine();

                        System.out.print("fecha de nacimiento nuevo (dd-mm-aaaa): ");
                        String fNac = new Scanner(System.in).next();
                        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate fechaNacimiento = LocalDate.parse(fNac, formato);

                        System.out.print("saldo nuevo: ");
                        int saldo = new Scanner(System.in).nextInt();

                        Cliente cn = (Cliente) clientes.get(indice);
                        cn.setNombre(nombre);
                        cn.setFechaNacimiento(fechaNacimiento);
                        cn.setSaldo(saldo);

                    }//if
                    else
                    {
                        System.out.println("Cliente no encontrado");
                    }
         
                }//case
                case 3 -> { //baja
                    System.out.print("dni persona a modificar: ");
                    String dni = new Scanner(System.in).next();
                    Cliente claveBusqueda = new Cliente(dni);

                    int indice = clientes.buscar(claveBusqueda);

                    if (indice != -1) {//cliente encontrado

                        clientes.eliminar(indice);

                    } else {
                        System.out.println("Cliente no encontrado");
                    }
                }//CASO 3
                case 4 -> {
                  
                    Collections.sort(clientes.tabla, new ComparaNombres());
                    //Collections.sort(clientes.tabla, new Lista());
                    System.out.println(clientes);
                    System.out.println("______________________");
                    System.out.println("Saldo máximo: "+  saldoMax(clientes));
                    System.out.println("______________________");
                    System.out.println("Saldo mínimo: "+  saldoMin(clientes));
                    System.out.println("______________________");
                    System.out.println("Saldo medio: "+  saldoMedio(clientes));
                    

                }

            }
        } while (opcion != 5);
        /*Al salir guardamos los datos: */
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("clientes.dat"))) {
            out.writeObject(clientes);
           
           // out.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
/**
    * Método para determinar el saldo máximo de una lista clientes
    *
    * @param l que recibe la lista de objetos de clientes
    * @return el valor double del valor máximo de saldo
    */  
public static double saldoMax(Lista l){ 
    
    double max;
    if (!l.tabla.isEmpty()){ //inicializo al primer cliente si existe
        max= ((Cliente)l.tabla.get(0)).getSaldo();
    }
    else 
    {
        System.out.println("No hay clientes");
        max=-1.0;
    }
    
    for (int i=1;i<l.tabla.size();i++ ){
        Cliente c= (Cliente) l.tabla.get(i);
        if (c.getSaldo()>max) max = c.getSaldo();
    }//for
    return max;
}


/**
    * Método para determinar el saldo mínimo de una lista clientes
    *
    * @param l que recibe la lista de objetos de clientes
    * @return el valor double del valor mínimo de saldo
    */  
public  static double saldoMin(Lista l){ 
    
    double min;
    if (!l.tabla.isEmpty()){ //inicializo al primer cliente si existe
        min= ((Cliente)l.tabla.get(0)).getSaldo();
    }
    else 
    {
        System.out.println("No hay clientes");
        min=-1.0;
    }
    
    for (int i=1;i<l.tabla.size();i++ ){
        Cliente c= (Cliente) l.tabla.get(i);
        if (c.getSaldo()<min) min= c.getSaldo();
    }//for
    return min;

}

/**
    * Método para determinar el saldo medio de una lista clientes
    *
    * @param l que recibe la lista de objetos de clientes
    * @return el valor double del valor medio de saldo
    */  
public static double saldoMedio(Lista l){ 
    
    double med;
    if (!l.tabla.isEmpty()){ //inicializo al primer cliente si existe
        med= ((Cliente)l.tabla.get(0)).getSaldo();
    }
    else 
    {
        System.out.println("No hay clientes");
        med=-1.0;
    }
    
    for (int i=1;i<l.tabla.size();i++ ){
        Cliente c= (Cliente) l.tabla.get(i);
          med+=c.getSaldo();
    }//for
    return med/l.tabla.size();

}


    }

