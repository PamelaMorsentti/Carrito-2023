package TPFcarrito.Carrito2023.Service;
import TPFcarrito.Carrito2023.Entity.Productos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProdcuctosService {
    List<Productos> getAllProductos();
    Productos getProductoById(Long id);
    boolean addProducto(Productos producto);
    Productos updateProducto(Productos producto);
    boolean deleteProducto(Long id);
    Productos getProducto(Long id);

    List<Productos> getProducto();
}

