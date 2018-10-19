/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.dominio.dto.mbd;

import java.io.Serializable;

/**
 *
 * @author JAdv-MJ
 */
public class ProductoEmailDto implements Serializable{
    
    
    private int productoid;
    private String correo;

    public int getProductoid() {
        return productoid;
    }

    public void setProductoid(int productoid) {
        this.productoid = productoid;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
}
