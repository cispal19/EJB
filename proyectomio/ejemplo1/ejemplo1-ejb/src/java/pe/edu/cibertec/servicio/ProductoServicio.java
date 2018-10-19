package pe.edu.cibertec.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import pe.edu.cibertec.dominio.Producto;

@Singleton
@LocalBean
public class ProductoServicio {

    private ConcurrentMap<Integer, Producto> datos = new ConcurrentHashMap<>();

    private AtomicInteger semilla;

    @PostConstruct
    public void init() {
        semilla = new AtomicInteger(1);
        agregar(new Producto(0, "Pelota", "para jugar"));
        agregar(new Producto(0, "Jabón", "para la limpieza"));
        agregar(new Producto(0, "Parlantes", "La música que si suena"));
        
        
    }
    
    public Producto agregar(Producto producto){
    producto.setId(semilla.getAndIncrement());
    datos.put(producto.getId(),producto);
    return producto;
    }

   public Producto obtenerById(int id){
   return datos.get(id);
   }
   
   public List<Producto> obtenerTodos(){
   return new ArrayList<>(datos.values());
   }
    
    
}
