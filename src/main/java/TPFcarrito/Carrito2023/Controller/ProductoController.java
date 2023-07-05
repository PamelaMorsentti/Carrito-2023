package TPFcarrito.Carrito2023.Controller;

import TPFcarrito.Carrito2023.Entity.Productos;
import TPFcarrito.Carrito2023.Service.IProdcuctosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ProductoController {
    //@Qualifier("IProdcuctosService")
    @Qualifier("productoService")
    @Autowired
    private final IProdcuctosService ProductoService;

    // traemos todos los productoos de la DB
    @GetMapping("/all")
    public List<Productos> getAllProductos(){
        return ProductoService.getAllProductos();
    }

    // consultamos un producto de la DB por ID
    @GetMapping("/getProducto/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Long id){
        Productos producto = ProductoService.getProductoById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            String message = "El producto seleccionado, ID = " + id + "no se encuentra en la base de datos";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    // agregamos un prodcuto a la DB
    @PostMapping("/addProducto")
    public ResponseEntity<String> addProducto (@RequestBody Productos productos){
        boolean producto = ProductoService.addProducto(productos);
        if (producto) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Producto agregado exitosamente");
        } else {
            //String message = "Producto no agregado, ya existe un producto con el ID " + productos.getId();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Producto no agregado, producto ID " + productos.getId() + "ya existe");
        }
    }

    // actualizamos un producto en base a su ID
    @PutMapping("/updateProducto/{id}")
    public ResponseEntity<String> updateProducto(@PathVariable Long id, @RequestBody Productos producto) {
        //Productos existingProducto = ProductoService.getProductoById(producto.getId());
        Productos existingProducto = ProductoService.getProductoById(id);
        if (existingProducto != null) {
            // Actualizar los campos del producto existente con los valores del producto recibido
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setCategoria(producto.getCategoria());
            existingProducto.setDescripcion(producto.getDescripcion());
            existingProducto.setPrecio(producto.getPrecio());
            existingProducto.setFoto(producto.getFoto());
            existingProducto.setTamaño(producto.getTamaño());
            existingProducto.setTipo(producto.getTipo());

            Productos updatedProducto = ProductoService.updateProducto(existingProducto);
            if (updatedProducto != null) {
                return ResponseEntity.status(HttpStatus.OK).body("Producto actualizado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el producto");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no existe en la base de datos");
        }
    }

/*
            boolean updated = ProductoService.addProducto(existingProducto);
            if (updated) {
                return ResponseEntity.status(HttpStatus.OK).body("Producto actualizado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el producto");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no existe en la base de datos");
        }
    }

 */
/*
    // borramos un producto seleccionado por id
    @DeleteMapping("/deleteProducto/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable("id") Long id) {
        Productos existingProducto = ProductoService.getProducto(id);
        if (existingProducto != null) {
            boolean deleted = ProductoService.deleteProducto(id);
            if (deleted) {
                return ResponseEntity.status(HttpStatus.OK).body("Producto eliminado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el producto");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no existe en la base de datos");
        }
    }
 */

    // borramos un producto por ID
    @DeleteMapping("/deleteProducto/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        boolean productoBorrado = ProductoService.deleteProducto(id);
        if (productoBorrado) {
            return ResponseEntity.ok("Producto borrado exitosamente");
        } else {
            String mensaje = "El producto con el ID " + id + " no existe";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }


    // Método para calcular el total de productos comprados o seleccionados
    @GetMapping("/calcularTotal")
    public ResponseEntity<?> calcularTotal() {
        List<Productos> productos = ProductoService.getProducto();
        int totalCantidad = 0;
        BigDecimal totalPrecio = BigDecimal.ZERO;

        for (Productos producto : productos) {
            // Aquí puedes incluir cualquier lógica adicional según tus requisitos
            totalCantidad += producto.getCantidad();
            totalPrecio = totalPrecio.add(producto.getPrecio().multiply(BigDecimal.valueOf(producto.getCantidad())));
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("totalCantidad", totalCantidad);
        resultado.put("totalPrecio", totalPrecio);

        return ResponseEntity.ok(resultado);
    }
}
