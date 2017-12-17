/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

/**
 *
 * @author couli
 */
public class Usuario implements java.io.Serializable {
 
    private String usuario = new String("");
    private String pass = new String("");
    private String mensaje = new String("");
 
    public String getMensaje() {
        return mensaje;
    }
 
 
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
 
 
    public String getUsuario() {
        return usuario;
    }
 
 
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
 
 
    public String getPass() {
        return pass;
    }
 
 
    public void setPass(String pass) {
        this.pass = pass;
    }
 
 
    public Usuario()    {
 
    }
 
}
