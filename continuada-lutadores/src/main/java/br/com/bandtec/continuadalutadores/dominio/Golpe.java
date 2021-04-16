package br.com.bandtec.continuadalutadores.dominio;

import javax.validation.constraints.Positive;

public class Golpe {
    @Positive
    private int idLutadorBate;
    @Positive
    private int idLutadorApanha;

    public int getIdLutadorBate() {
        return idLutadorBate;
    }

    public void setIdLutadorBate(int idLutadorBate) {
        this.idLutadorBate = idLutadorBate;
    }

    public int getIdLutadorApanha() {
        return idLutadorApanha;
    }

    public void setIdLutadorApanha(int idLutadorApanha) {
        this.idLutadorApanha = idLutadorApanha;
    }
}
