package TPFcarrito.Carrito2023.Repository;

import TPFcarrito.Carrito2023.Entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Productos, Long> {
    boolean existsById(Long id);
    void deleteById(Long id);
}
