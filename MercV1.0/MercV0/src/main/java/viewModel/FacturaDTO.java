/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

/**
 *
 * @author edson
 */
public class FacturaDTO {
    String nombre;
    int codigo;
    int preciov;
    int preciop;



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPreciov() {
        return preciov;
    }

    public void setPreciov(int preciov) {
        this.preciov = preciov;
    }

    public int getPreciop() {
        return preciop;
    }

    public void setPreciop(int preciop) {
        this.preciop = preciop;
    }
    
    
}
