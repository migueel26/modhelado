package modhelado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modhelado.interes.Interes;
import modhelado.tablon.evento.Evento;
import modhelado.tablon.publicacion.Publicacion;



public class GestorBaseDatos {
    private static List<Publicacion> publicaciones = new ArrayList<>();
    private static List<Evento> eventos = new ArrayList<>();
	
    public static List<Object> consultar(String consulta) {
        return Collections.emptyList();
    }
    
    public static void guardar(Object object) {

    }

    public static void guardarEvento(Evento evento){
        if(!eventos.contains(evento)) eventos.add(evento);
    }

    public static List<Evento> consultarEventos(List<Interes> intereses){
        List<Evento> eventosUsuario = new ArrayList<>();
        for (Evento evento : eventos) {
            if (evento.matchIntereses(intereses)) eventosUsuario.add(evento);
        }
        return eventosUsuario;
    }

    public static void guardarPublicacion(Publicacion publicacion){
        if(!publicaciones.contains(publicacion)) publicaciones.add(publicacion);
    }

    public static List<Publicacion> consultarPublicaiones(List<Interes> intereses){
        List<Publicacion> publicacionesUsuario = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            if (publicacion.matchIntereses(intereses)) publicacionesUsuario.add(publicacion);
        }
        return publicacionesUsuario;
    }
    
}

