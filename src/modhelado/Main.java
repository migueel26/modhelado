package modhelado;

import modhelado.interes.Cine;
import modhelado.interes.DescripcionInteres;
import modhelado.interes.Interes;
import modhelado.tablon.evento.Evento;
import modhelado.usuario.Usuario;
import modhelado.usuario.conexion.Conexion;

import java.util.ArrayList;
import java.util.List;

import static modhelado.interes.Cine.cine;
import static modhelado.interes.Deporte.deporte;
import static modhelado.interes.Parque.parque;

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
        usuario1.addInteres(new DescripcionInteres("Harry Potter", usuario1, cine()));
        usuario1.addInteres(new DescripcionInteres("Baloncesto", usuario1, deporte()));
        usuario2.addInteres(new DescripcionInteres("Titanic", usuario2, cine()));

        //Crear conexiones
            //usuario1 envía conexión a usuario2
        usuario1.crearConexion(usuario2);
        //usuario2.aceptarConexion(usuario1);

        usuario3.crearConexion(usuario1);
        //usuario1.bloquea(usuario3);

        //Crear eventos
        usuario1.crearEvento(new Evento(usuario1, "Museo del cine", "20-04-2026", 10, "Museo cine Málaga", List.of(cine())));
        usuario4.crearEvento(new Evento(usuario4, "Taller de cerámica", "05-02-2026", 6, "Avenida Andalucía", List.of(parque())));

        //Crear publicaciones
        usuario2.crearPublicacion("Nueva película de Marvel!!", "10-12-2025", List.of(cine()));
        usuario3.crearPublicacion("Descubrimiento científico", "14-12-2025", List.of(parque()));


        //Acceder a los tablones del usuario 1
        System.out.println("Tablones del usuario1:");
        usuario1.getTablonPublicacion().ver();
        usuario1.getTablonEventos().ver();

        System.out.println("Tablones del usuario2:");
        usuario2.getTablonPublicacion().ver();
        usuario2.getTablonEventos().ver();


        //Unirse a eventos
        //usuario2.accederEvento();



        //Chat grupal de eventos



        //Chat privado

    }
}