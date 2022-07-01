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
public class usuarioDTO {
    String nit = null;
    String name = null;
    String correo = null;
    String clave = null;

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    

    public usuarioDTO(String nit, String name, String correo, String contraseña) {
        this.nit = nit;
        this.name =  name;
        this.correo = correo;
        this.clave = contraseña;
    }

    

    

    

    



    
    

    
}
