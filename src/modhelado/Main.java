package modhelado;

import modhelado.interes.DescripcionInteres;
import modhelado.interes.Interes;
import modhelado.tablon.evento.Evento;
import modhelado.usuario.Usuario;
import modhelado.usuario.conexion.Conexion;

import java.util.ArrayList;
import java.util.List;

import static modhelado.interes.Cine.cine;
import static modhelado.interes.Deporte.deporte;
import static modhelado.interes.Musica.musica;

public class Main {

    public static void main(String[] args) {
        //Crear varios usuarios
        System.out.println("-> Se crean 5 usuarios:");
        Usuario usuario1 = new Usuario("mariaa1", "María", "López","maria@gmail.com","01-01-2005");
        Usuario usuario2 = new Usuario("pepedm", "Pepe", "García","pepe@gmail.com","01-07-1999");
        Usuario usuario3 = new Usuario("elena_r", "Elena", "Ruiz", "elena.ruiz@hotmail.com", "15-11-2001");
        Usuario usuario4 = new Usuario("pablo04", "Pablo", "Pérez", "pablo@yahoo.es", "28-05-1995");
        Usuario usuario5 = new Usuario("ana_sanz", "Ana", "Sánchez", "ana.sanz@gmail.com", "10-03-1988");
        System.out.println("Usuarios creados: "+ usuario1.getUsername()+", "+usuario2.getUsername()+", "+usuario3.getUsername()+", "+usuario4.getUsername()+", "+usuario5.getUsername());
        System.out.println("---------------------------------------------------------------\n\n");


        //Asociar intereses a los usuarios
        System.out.println("-> Se asocian intereses a los usuarios:");
        usuario1.addInteres(cine(), "Harry Potter");
        usuario1.addInteres(deporte(), "Baloncesto");
        usuario2.addInteres(cine(), "Titanic");

        System.out.println("Intereses usuario1: "+ usuario1.getIntereses().toString());
        System.out.println("Intereses usuario2: "+ usuario2.getIntereses().toString());
        System.out.println("---------------------------------------------------------------\n\n");


        //Crear conexiones
        System.out.println("-> Se crean conexiones entre usuarios:");
            //usuario1 envía conexión a usuario2
        usuario1.enviarSolicitud(usuario2);
        usuario2.aceptarConexion(usuario1);

        usuario3.enviarSolicitud(usuario1);
        usuario1.bloquearConexion(usuario3);
        System.out.println("---------------------------------------------------------------\n\n");


        //Crear eventos
        System.out.println("-> Se crean eventos por parte de los usuarios:");
        usuario1.crearEvento("Museo del cine", "20-04-2026", 10, "Museo cine Málaga", List.of(cine()));
        usuario4.crearEvento("Concierto música clásica", "05-02-2026", 6, "Avenida Andalucía", List.of(musica()));
        System.out.println("---------------------------------------------------------------\n\n");


        //Crear publicaciones
        System.out.println("-> Se crean publicaciones por parte de los usuarios:");
        usuario2.crearPublicacion("Nueva película de Marvel!!", "10-12-2025", List.of(cine()));
        usuario3.crearPublicacion("Nuevo lanzamiento de disco!", "14-12-2025", List.of(musica()));
        System.out.println("---------------------------------------------------------------\n\n");


        //Acceder a los tablones del usuario 1
        System.out.println("-> Tablones del usuario1:");
        usuario1.getTablonPublicacion().ver();
        usuario1.getTablonEventos().ver();

        System.out.println("-> Tablones del usuario2:");
        usuario2.getTablonPublicacion().ver();
        usuario2.getTablonEventos().ver();
        System.out.println("---------------------------------------------------------------\n\n");


        //Unirse a eventos
        System.out.println("-> Los usuarios se unen a eventos:");
        //usuario2.accederEvento();

        System.out.println("---------------------------------------------------------------\n\n");


        //Chat grupal de eventos
        System.out.println("-> Los usuarios participan en chats grupales de eventos:");

        System.out.println("---------------------------------------------------------------\n\n");


        //Chat privado
        System.out.println("-> Los usuarios participan en chats privados:");

        System.out.println("---------------------------------------------------------------\n\n");

    }
}