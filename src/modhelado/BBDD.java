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

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public class BBDD {


    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;

    private static Jedis getJedis() {
        return new Jedis(REDIS_HOST, REDIS_PORT);
    }

    public static List<Object> consultar(String consulta) {
        return Collections.emptyList();
    }

    /**
     * A�ade un nuevo usuario, verificando primero que el nombre no exista.
     * @param nombre Nombre de usuario único.
     * @param contrase�a Contraseña (se hashea antes de guardar).
     */
    public static void añadirUsuario(String nombreUsuario, String contraseña, String nombre,
                                     String apellido, String correo, String fechaNacimiento, boolean vetado) {

        try (Jedis jedis = getJedis()) {
            String claveUsuario = "usuario:" + nombreUsuario;

            // No se permiten usuarios repetidos
            if (jedis.exists(claveUsuario)) {
                System.err.println("Error: El usuario '" + nombre + "' ya existe. No se ha a�adido.");
                return;
            }

            Map<String, String> userData = new HashMap<>();


            String passwordHash = String.valueOf(contraseña.hashCode());

            //campos a a�adir al HSET(usuario, campos...)
            userData.put("nombre", nombre);
            userData.put("apellido", apellido);
            userData.put("fecha_nacimiento", fechaNacimiento);
            userData.put("vetado", String.valueOf(vetado) );
            userData.put("password_hash", passwordHash);
            userData.put("fecha_registro", String.valueOf(System.currentTimeMillis()));



            jedis.hset(claveUsuario, userData);
            System.out.println("Usuario '" + nombre + "' a�adido exitosamente.");

        } catch (Exception e) {
            System.out.println("Error durante el proceso de creaci�n de usuario");
        }
    }

    public static void iniciarSesion(String nombreUsuario, String contraseña) {
        try (Jedis jedis = getJedis()) {

            String claveUsuario = "usuario:" + nombreUsuario;
            String hashGuardado = jedis.hget(claveUsuario, "password_hash");

            if (hashGuardado == null) {
                System.err.println("Error de inicio de sesi�n: Usuario no encontrado.");
                return;
            }

            String hashIngresado = String.valueOf(contraseña.hashCode());
            if (hashIngresado.equals(hashGuardado)) {
                System.out.println("Inicio de sesi�n exitoso para: " + nombreUsuario);
            } else {
                System.err.println("Error de inicio de sesi�n: Contrase�a incorrecta.");
            }


        }catch (Exception e) {
            System.out.println("Error iniciando sesi�n");
        }
    }

    public static void guardarEvento(Evento evento) {
        long eventoId;

        try(Jedis jedis = getJedis())
        {
            // hacemos que redis gestione los ids de los eventos
            eventoId = jedis.incr("evento:next_id");
            String eventKey = "eveto:" + eventoId;

            Map <String, String> eventData = new HashMap<>();
            eventData.put("id", String.valueOf(eventoId));
            eventData.put("creador_nombre", evento.getCreador().getUsername());
            eventData.put("titulo", evento.getTitulo());
            eventData.put("fecha", evento.getFecha());
            eventData.put("aforo", String.valueOf(evento.getAforo()));
            eventData.put("lugar", evento.getFecha());

            jedis.hset(eventKey, eventData);

            String claveEventosCreados = "usuario:eventos_creados:" + evento.getCreador().getUsername();
            jedis.sadd(claveEventosCreados, String.valueOf(eventoId));


            String claveInteresesEvento = "evento:intereses:" + eventoId;
            String[] intereses = evento.getIntereses().stream()
                    .map(Interes::interes)
                    .toArray(String[]::new);

            if (intereses.length > 0 ) {
                jedis.sadd(claveInteresesEvento, intereses);
            }
        }

    }

    public static void guardarPublicacion(Publicacion publicacion) {

        String publicacionId = String.valueOf(publicacion.getID());
        try(Jedis jedis = getJedis())
        {
            // hacemos que redis gestione los ids de los eventos
            String publicacionKey = "publicacion:" + publicacionId;



            Map <String, String> publicacionData = new HashMap<>();
            publicacionData.put("creador_nombre", publicacion.getCreador().getUsername());
            publicacionData.put("contenido", publicacion.getContenido());
            publicacionData.put("fecha", publicacion.getFecha());
            publicacionData.put("likes", String.valueOf(publicacion.getUsuarioLikes()));


            jedis.hset(publicacionKey, publicacionData);

            String claveEventosCreados = "usuario:publicacion_creados:" + publicacion.getCreador().getUsername();
            jedis.sadd(claveEventosCreados, String.valueOf(publicacionId));


            String claveInteresesEvento = "publicacion:intereses:" + publicacionId;
            String[] intereses = publicacion.getIntereses().stream()
                    .map(Interes::interes)
                    .toArray(String[]::new);

            if (intereses.length > 0 ) {
                jedis.sadd(claveInteresesEvento, intereses);
            }
        }

    }

    public static Evento[] verEventos(ArrayList<Interes> intereses) {
        ArrayList<Evento> eventosEncontrados = new ArrayList<>();

        if (intereses == null || intereses.isEmpty()) {
            return eventosEncontrados.toArray(new Evento[0]);
        }


        try (Jedis jedis = getJedis()) {

            String[] clavesIntereses = intereses.stream()
                    .map(i -> "interes:" + i.interes())
                    .toArray(String[]::new);

            //evitar duplicados
            Set<String> idEventosUnicos = jedis.sunion(clavesIntereses);

            if (idEventosUnicos.isEmpty()) {
                return eventosEncontrados.toArray(new Evento[0]);
            }


            List<Response<Map<String, String>>> responses = new ArrayList<>();
            Pipeline pipeline = jedis.pipelined();


            for (String id : idEventosUnicos) {
                responses.add(pipeline.hgetAll("evento:" + id));
            }

            pipeline.sync();


            for (Response<Map<String, String>> response : responses) {
                Map<String, String> data = response.get();
                if (data != null && !data.isEmpty()) {

                    Evento evento = mapHashToEvento(data);
                    if (evento != null) {
                        eventosEncontrados.add(evento);
                    }
                }
            }
        } catch (Exception e) {

        }

        return eventosEncontrados.toArray(new Evento[eventosEncontrados.size()]);
    }

    private static Evento mapHashToEvento(Map<String, String> data) {
        Evento evento = null;
        try {

            String titulo = data.get("titulo");
            String fecha = data.get("fecha");
            Integer aforo = Integer.parseInt(data.get("aforo"));
            String lugar = data.get("lugar");
            String creadorUsername = data.get("creador_nombre");

            evento = new Evento(null, titulo, fecha, aforo, lugar, new ArrayList<>());

        } catch (Exception e) {
            System.out.println("Fallo al mapear Hash a Evento");
            return null;
        }
        return evento;
    }

}

