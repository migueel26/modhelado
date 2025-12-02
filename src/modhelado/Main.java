package modhelado;

import modhelado.usuario.Usuario;
import modhelado.usuario.conexion.Conexion;

public class Main {
    public static void main(String[] args) {
        //Crear varios usuarios
        Usuario usuario1 = new Usuario("mariaa1", "María", "López","maria@gmail.com","01-01-2005");
        Usuario usuario2 = new Usuario("pepedm", "Pepe", "García","pepe@gmail.com","01-07-1999");
        Usuario usuario3 = new Usuario("elena_r", "Elena", "Ruiz", "elena.ruiz@hotmail.com", "15-11-2001");
        Usuario usuario4 = new Usuario("pablo04", "Pablo", "Pérez", "pablo@yahoo.es", "28-05-1995");
        Usuario usuario5 = new Usuario("ana_sanz", "Ana", "Sánchez", "ana.sanz@gmail.com", "10-03-1988");
        System.out.println("Usuarios creados: "+ usuario1.getUsername()+", "+usuario2.getUsername()+", "+usuario3.getUsername()+", "+usuario4.getUsername()+", "+usuario5.getUsername());

        //Asociar intereses a los usuarios


        //Crear conexiones
            //usuario1 envía conexión a usuario2
        //Conexion conexion12 = new Conexion(usuario1,usuario2,"01-12-2025");


        //Crear eventos



        //Crear publicaciones


        /*
        //Acceder a los tablones del usuario 1
        System.out.println("Tablones del usuario1:");
        usuario1.tablonEventos.ver();
        usuario1.tablonPublicaciones.ver();
        */

        //Unirse a eventos


        //Chat grupal de eventos



        //Chat privado

    }
}