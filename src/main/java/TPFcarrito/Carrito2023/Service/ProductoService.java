package TPFcarrito.Carrito2023.Service;

import TPFcarrito.Carrito2023.Entity.Productos;
import TPFcarrito.Carrito2023.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProdcuctosService{
    @Autowired
    public ProductoRepository productoRepository;

    @Override
    public List<Productos> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Productos getProductoById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public boolean addProducto(Productos producto) {
        if (productoRepository.existsById(producto.getId())){
            return false;
        } else {
            productoRepository.save(producto);
            return true;
        }
    }

    @Override
    public Productos updateProducto(Productos producto) {
        //Productos existingProducto = getProductoById(Long.valueOf("id"));
        Productos existingProducto = getProductoById(producto.getId());
        if (existingProducto != null) {
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setCategoria(producto.getCategoria());
            existingProducto.setDescripcion(producto.getDescripcion());
            existingProducto.setPrecio(producto.getPrecio());
            existingProducto.setFoto(producto.getFoto());
            existingProducto.setTamaño(producto.getTamaño());
            existingProducto.setTipo(producto.getTipo());
            return productoRepository.save(existingProducto);
        } else {
            throw new RuntimeException("El producto no existe en la base de datos");
        }
        /*
        existingProducto.setNombre(producto.getNombre());
        existingProducto.setPrecio(producto.getPrecio());
        existingProducto.setCategoria(producto.getCategoria());
        existingProducto.setFoto(producto.getFoto());
        existingProducto.setTamaño(producto.getTamaño());
        existingProducto.setTipo(producto.getTipo());
        existingProducto.setDescripcion(producto.getDescripcion());
        existingProducto.setTotalPedido(producto.getTotalPedido());
        // Actualizar otras propiedades si es necesario
        return productoRepository.save(existingProducto);

         */
    }

/*
    @Override
    public boolean deleteProducto(Long id) {
        Productos producto = getProductoById(id);
        productoRepository.delete(producto);
        return false;
    }

 */
    /*
    @Override
    public boolean deleteProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("El producto no existe en la base de datos");
        }
    }

     */

    @Override
    public boolean deleteProducto(Long id) {
        try {
            if (productoRepository.existsById(id)) {
                productoRepository.deleteById(id);
                return true;
            } else {
                throw new RuntimeException("El producto no existe en la base de datos");
            }
        } catch (Exception e) {
            // Registra la excepción para su análisis posterior
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar el producto");
        }
    }

    @Override
    public Productos getProducto(Long id) {
        return null;
    }

    @Override
    public List<Productos> getProducto() {
        return null;
    }
}
