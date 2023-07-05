package TPFcarrito.Carrito2023.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
@SQLDelete(sql = "UPDATE productos SET enable = false WHERE id = ?")
@Where(clause = "enable = true")

public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    private Long id;

    @NotNull
    @NotBlank (message = "Este campo es requerido")
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    @NotNull
    @NotBlank
    @Column(name = "categoria", nullable = false, length = 255)
    private String categoria;
    @NotNull
    @NotBlank
    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;
    @NotNull
    @DecimalMin(value = "0.1")
    @Column(name = "precio", nullable = false)
    private BigDecimal precio;
    @Column(name = "foto", nullable = false)
    private String foto;
    @NotNull
    @Column(name = "tamaño", nullable = false)
    private BigDecimal tamaño;
    @NotNull
    @NotBlank
    @Column(name = "tipo", nullable = false, length = 255)
    private String tipo;
    @Column(name = "enable")
    private boolean enabled = true;
    @Column(name = "Pedido")
    private BigDecimal TotalPedido;

    public int getCantidad() {
        return 0;
    }
}

