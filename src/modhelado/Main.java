package modhelado;

import modhelado.interes.DescripcionInteres;
import modhelado.interes.Interes;
import modhelado.tablon.evento.Evento;
import modhelado.usuario.Usuario;
import modhelado.usuario.conexion.Conexion;

import java.util.ArrayList;
import java.util.List;

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

        System.out.println("Intereses usuario1: "+ usuario1.getIntereses().toString());
        System.out.println("Intereses usuario2: "+ usuario2.getIntereses().toString());
        System.out.println("Intereses usuario3: "+ usuario3.getIntereses().toString());
        System.out.println("Intereses usuario4: "+ usuario4.getIntereses().toString());
        System.out.println("Intereses usuario5: "+ usuario5.getIntereses().toString());
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
        usuario4.cancelarConexion(usuario5);
        System.out.println("\n\n---------------------------------------------------------------");


        //Crear eventos
        System.out.println("-> Se crean eventos por parte de los usuarios:");
        usuario1.crearEvento("Museo del cine", "20-04-2026", 10, "Museo cine Málaga", "Ir a visitar el museo del cine de Málaga una tarde", List.of(cine()));
        usuario4.crearEvento("Concierto música clásica", "05-02-2026", 6, "Avenida Andalucía", "¿A quién no le gusta un concierto?", List.of(musica()));

        System.out.println("Eventos usuario1:\n" + usuario1.getEventos().toString());
        System.out.println("Eventos usuario4:\n" + usuario4.getEventos().toString());
        System.out.println("\n\n---------------------------------------------------------------");


        //Crear publicaciones
        System.out.println("-> Se crean publicaciones por parte de los usuarios:");
        usuario2.crearPublicacion("Nueva película de Marvel!!", "10-12-2025", List.of(cine()));
        usuario3.crearPublicacion("Nuevo lanzamiento de disco!", "14-12-2025", List.of(musica()));

        System.out.println("Publicaciones usuario2:\n" + usuario2.getPublicacionesCreadas().toString());
        System.out.println("Publicaciones usuario3:\n" + usuario3.getPublicacionesCreadas().toString());
        System.out.println("\n\n---------------------------------------------------------------");

        //Acceder a los tablones del usuario 1
        System.out.println("-> Tablones del usuario1:");
        usuario1.getTablonPublicacion().ver();
        usuario1.getTablonEventos().ver();

        System.out.println("-> Tablones del usuario2:");
        usuario2.getTablonPublicacion().ver();
        usuario2.getTablonEventos().ver();
        System.out.println("\n\n---------------------------------------------------------------");


        //Unirse a eventos
        System.out.println("-> Los usuarios se unen a eventos:");
        //usuario2.accederEvento();

        System.out.println("\n\n---------------------------------------------------------------");


        //Chat grupal de eventos
        System.out.println("-> Los usuarios participan en chats grupales de eventos:");

        System.out.println("\n\n---------------------------------------------------------------");


        //Chat privado
        System.out.println("-> Los usuarios participan en chats privados:");

        System.out.println("\n\n---------------------------------------------------------------");


        //Usuario vetado
    }
}