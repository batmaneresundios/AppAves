package modelo;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.Date;


public class RegistroAves implements Serializable {
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Exclude

    private String id;

    private String aveVisualizada, lugarAvistamiento;
    private int numeroVistas;
    private Date fecha;
    public RegistroAves(String aveVisualizada, int numeroVistas, String lugarAvistamiento, Date fecha) {
        this.aveVisualizada = aveVisualizada;
        this.numeroVistas = numeroVistas;
        this.lugarAvistamiento = lugarAvistamiento;
        this.fecha = fecha;
    }
    public RegistroAves(){

    }

    public String getAveVisualizada() {
        return aveVisualizada;
    }

    public void setAveVisualizada(String aveVisualizada) {
        this.aveVisualizada = aveVisualizada;
    }

    public int getNumeroVistas() {
        return numeroVistas;
    }

    public void setNumeroVistas(int numeroVistas) {
        this.numeroVistas = numeroVistas;
    }

    public String getLugarAvistamiento() {
        return lugarAvistamiento;
    }

    public void setLugarAvistamiento(String lugarAvistamiento) {
        this.lugarAvistamiento = lugarAvistamiento;
    }
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) { this.fecha = fecha; }

}
