package com.example.evaluacion_3;

import java.io.Serializable;

public class MedicamentoModel implements Serializable {
    // creamos las variables necesarias para el medicamento

    private String nombre, fechaVencimiento, presentacion, descripcion;
    private int id, cantidad, miligramos;

    // Definimos los Getters
    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public int getCantidad(){
        return cantidad;
    }

    public String getFechaVencimiento(){
        return fechaVencimiento;
    }

    public int getMiligramos(){
        return miligramos;
    }

    public String getPresentacion(){
        return presentacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Ahora definimos los Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setMiligramos(int miligramos) {
        this.miligramos = miligramos;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
