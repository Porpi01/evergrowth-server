package EverGrowth.com.EverGrowthserver.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String nombre;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String apellido1;

    @Size(min = 3, max = 255)
    private String apellido2;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "\\d{9}", message = "El número de teléfono debe tener 9 dígitos")
    private String telefono;

    @NotNull
    @Size(min = 5, max = 255)
    private String direccion;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 255)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @NotBlank
    @Size(min = 64, max = 64)
    @Pattern(regexp = "^[a-fA-F0-9]+$", message = "Password must be hexadecimal")
    private String password = "dedb63fbd1f3c4bce46a6e29be0700dda4fe2eec46c79b79fa0c5704d96e308d";

    private Boolean rol = false;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PedidoEntity> pedidos;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ValoracionEntity> valoracion;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<CarritoEntity> carrito; 


    public UsuarioEntity() {
        pedidos = new ArrayList<>();
        valoracion = new ArrayList<>();
        carrito = new ArrayList<>();
    }



    public UsuarioEntity(Long id, String nombre, String apellido1, String apellido2, String email, String telefono, String direccion,
            String username,
            String password, Boolean rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public UsuarioEntity(String nombre, String apellido1, String apellido2, String email, String telefono, String direccion,
            String username,String password, Boolean rol) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public UsuarioEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String getapellido1() {
        return apellido1;
    }

    public void setapellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getapellido2() {
        return apellido2;
    }

    public void setapellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getdireccion() {
        return direccion;
    }

    public void setdireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getrol() {
        return rol;
    }

    public void setrol(Boolean rol) {
        this.rol = rol;
    }

    public int getPedidos() {
        return pedidos.size();
    }

    public int getValoracion() {
        return valoracion.size();
    }

    public int getCarritos() {
        return carrito.size();
    }



}
