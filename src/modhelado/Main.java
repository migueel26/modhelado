package modhelado;

import modhelado.chat.Chat;
import modhelado.interes.DescripcionInteres;
import modhelado.interes.Interes;
import modhelado.tablon.evento.Evento;
import modhelado.usuario.Usuario;
import modhelado.usuario.conexion.Conexion;

import java.util.*;

import static modhelado.interes.Ciencia.ciencia;
import static modhelado.interes.Cine.cine;
import static modhelado.interes.Deporte.deporte;
import static modhelado.interes.Literatura.literatura;
import static modhelado.interes.Musica.musica;
import static modhelado.interes.Tecnologia.tecnologia;

public class Main {
    public static void main(String[] args) {

        System.out.println("----------------- Wellcome to MyTriboo! -----------------");
        //Crear varios usuarios
        System.out.println("-> Se crean 5 usuarios:");
        Usuario usuario1 = new Usuario("mariaa1", "María", "López","maria@gmail.com","01-01-2005");
        Usuario usuario2 = new Usuario("pepedm", "Pepe", "García","pepe@gmail.com","01-07-1999");
        Usuario usuario3 = new Usuario("elena_r", "Elena", "Ruiz", "elena.ruiz@hotmail.com", "15-11-2001");
        Usuario usuario4 = new Usuario("pablo04", "Pablo", "Pérez", "pablo@yahoo.es", "28-05-1995");
        Usuario usuario5 = new Usuario("ana_sanz", "Ana", "Sánchez", "ana.sanz@gmail.com", "10-03-1988");
        System.out.println("Usuarios creados: "+ usuario1.getUsername()+", "+usuario2.getUsername()+", "+usuario3.getUsername()+", "+usuario4.getUsername()+", "+usuario5.getUsername());
        System.out.println("\n\n---------------------------------------------------------------");


        //Asociar intereses a los usuarios
        System.out.println("-> Se asocian intereses a los usuarios:");
        usuario1.addInteres(cine(), "Harry Potter");
        usuario1.addInteres(deporte(), "Baloncesto");
        usuario2.addInteres(cine(), "Titanic");
        usuario3.addInteres(tecnologia(), "Avances Inteligencia Artificial");
        usuario3.addInteres(ciencia(), "Física");
        usuario4.addInteres(deporte(), "Natación");
        usuario4.addInteres(ciencia(), "Biología");
        usuario5.addInteres(musica(), "Pop");
        usuario5.addInteres(cine(), "Cine español");
        usuario5.addInteres(literatura(), "Ciencia ficción");

        System.out.println("Intereses usuario1: "+ usuario1.verIntereses());
        System.out.println("Intereses usuario2: "+ usuario2.verIntereses());
        System.out.println("Intereses usuario3: "+ usuario3.verIntereses());
        System.out.println("Intereses usuario4: "+ usuario4.verIntereses());
        System.out.println("Intereses usuario5: "+ usuario5.verIntereses());
        System.out.println("\n\n---------------------------------------------------------------");


        //Crear conexiones
        System.out.println("-> Se crean conexiones entre usuarios:");
            //usuario1 envía conexión a usuario2
        System.out.println("Usuarion1 - Usuario2");
        System.out.println(usuario1.getUsername() + " envía solicitud a " + usuario2.getUsername());
        usuario1.enviarSolicitud(usuario2);
        System.out.println("Estado: " + usuario1.buscarConexion(usuario2).get().conexion());
        System.out.println(usuario2.getUsername() + " acepta la solicitud");
        usuario2.aceptarConexion(usuario1);
        System.out.println("Estado: " + usuario1.buscarConexion(usuario2).get().conexion());

        System.out.println("\nUsuario3 - Usuario1");
        System.out.println(usuario3.getUsername() + " envía solicitud a " + usuario1.getUsername());
        usuario3.enviarSolicitud(usuario1);
        System.out.println("Estado: " + usuario1.buscarConexion(usuario3).get().conexion());
        usuario1.bloquearConexion(usuario3);
        System.out.println("Estado: " + usuario1.buscarConexion(usuario3).get().conexion());

        System.out.println("\nUsuario4 - Usuario5");
        System.out.println(usuario4.getUsername() + " bloquea a " + usuario5.getUsername());
        usuario4.bloquearConexion(usuario5);
        System.out.println("Estado: " + usuario4.buscarConexion(usuario5).get().conexion());
        System.out.println(usuario4.getUsername() + " desbloquea a " + usuario5.getUsername());

        try{
            usuario5.cancelarConexion(usuario4);
        } catch (AssertionError e){
            System.out.println("---> ERROR: usuario 5 no puede desbloquear al que lo bloqueó (usuario4)");
        }
        System.out.println("Estado: " + usuario4.buscarConexion(usuario5).get().conexion());

        usuario4.cancelarConexion(usuario5);
        try {
            System.out.println("Estado: " + usuario4.buscarConexion(usuario5).orElseThrow().conexion());
        } catch (NoSuchElementException e){
            System.out.println("---> ERROR: no existe la conexión entre usuario4 y usuario5");
        }

        System.out.println("\n\n---------------------------------------------------------------");


        //Crear eventos
        System.out.println("-> Se crean eventos por parte de los usuarios:");
        usuario1.crearEvento("Museo del cine", "20-04-2026", 10, "Museo cine Málaga", "Ir a visitar el museo del cine de Málaga una tarde", List.of(cine()));
        usuario4.crearEvento("Concierto música clásica", "05-02-2026", 6, "Avenida Andalucía", "¿A quién no le gusta un concierto?", List.of(musica()));

        usuario1.verEventos();
        usuario4.verEventos();
        System.out.println("\n\n---------------------------------------------------------------");


        //Crear publicaciones
        System.out.println("-> Se crean publicaciones por parte de los usuarios:");
        usuario2.crearPublicacion("Nueva película de Marvel!!", "10-12-2025", List.of(cine()));
        usuario3.crearPublicacion("Nuevo lanzamiento de disco!", "14-12-2025", List.of(musica()));

        usuario2.verPublicaciones();
        usuario3.verPublicaciones();
        System.out.println("\n\n---------------------------------------------------------------");

        //Acceder a los tablones de los usuarios
        System.out.println("-> Tablones del usuario1:");
        usuario1.getTablonPublicacion().ver();
        usuario1.getTablonEventos().ver();

        System.out.println("-> Tablones del usuario2:");
        usuario2.getTablonPublicacion().ver();
        usuario2.getTablonEventos().ver();

        System.out.println("-> Tablones del usuario3:");
        usuario3.getTablonPublicacion().ver();
        usuario3.getTablonEventos().ver();

        System.out.println("-> Tablones del usuario4:");
        usuario4.getTablonPublicacion().ver();
        usuario4.getTablonEventos().ver();

        System.out.println("-> Tablones del usuario5:");
        usuario5.getTablonPublicacion().ver();
        usuario5.getTablonEventos().ver();
        System.out.println("\n\n---------------------------------------------------------------");


        //Unirse a eventos
        System.out.println("-> Los usuarios se unen a eventos:");
        // Se une al primero, por ejemplo
        usuario2.accederEvento(usuario2.getTablonEventos().getEventos().nextElement());

        System.out.println("\n\n---------------------------------------------------------------");


        //Chat grupal de eventos
        System.out.println("-> Los usuarios participan en chats grupales de eventos:");

        System.out.println("\n\n---------------------------------------------------------------");


        //Chat privado
        System.out.println("-> Los usuarios participan en chats privados:");
        Chat chat1_2 = usuario1.buscarConexion(usuario2).get().getChat();
        usuario1.enviarMensaje("HOLAAA", chat1_2);
        System.out.println(chat1_2);

        System.out.println("\n\n---------------------------------------------------------------");


        //Usuario vetado
        System.out.println("-> A un usuario vetado no se le permiten ciertas acciones en el sistema:");
        System.out.println("Vetamos al usuario4: ");
        usuario4.setVetado(true);
        usuario4.getTablonEventos().ver();
        usuario4.getTablonPublicacion().ver();
        System.out.println("El usuario vetado no puede ver eventos ni publicaciones de otros usuarios.");

        System.out.println("\n\n---------------------------------------------------------------");
    }
}