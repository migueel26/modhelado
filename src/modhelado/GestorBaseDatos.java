package modhelado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modhelado.interes.Interes;
import modhelado.tablon.evento.Evento;
import modhelado.usuario.Usuario;
import redis.clients.jedis.Jedis;

public class GestorBaseDatos {
	
	
	private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;

    private static Jedis getJedis() {
        return new Jedis(REDIS_HOST, REDIS_PORT);
    }
	
    public static void guardar(Object object) {

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
    
    
    public static void añadirEvento(Usuario usuario, Evento evento) {
    	
    }
    
    public static void guardarEvento(Evento evento) {
    	long eventoId;
    	
    	try(Jedis jedis = getJedis())
    	{
    		// hacemos que redis gestione los ids de los eventos
    		eventoId = jedis.incr("evento:next_id");
    		String claveEvento = "eveto:" + eventoId;
    		
    		Map <String, String> eventData = new HashMap<>();
    		eventData.put("id", String.valueOf(eventoId));
    		eventData.put("creador_nombre", evento.getCreador().getUsername());
    		eventData.put("id", String.valueOf(eventoId));
    		eventData.put("id", String.valueOf(eventoId));
    		
    	}
    	
    }
    
    public static ArrayList<Evento> verEventos(ArrayList<Interes> intereses) {
    	return new ArrayList<Evento>();
    }
    
    
}

