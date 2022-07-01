/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

/**
 *
 * @author HOME
 */
public class ProductoDTO {
    String nombre; 
    int cantidad;
    String code;
    int precio;
    int preciop;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getPreciop() {
        return preciop;
    }

    public void setPreciop(int preciop) {
        this.preciop = preciop;
    }

    @Override
    public boolean equals (Object p) {
    return (this.nombre.equals(((ProductoDTO)p).nombre));
  }
    
}
