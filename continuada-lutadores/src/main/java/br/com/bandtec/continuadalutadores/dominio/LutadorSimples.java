package br.com.bandtec.continuadalutadores.dominio;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class LutadorSimples {
    @Size(min = 3, max = 12)
    @NotBlank
    private String nome;

    @Positive
    private Double forcaGolpe;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getForcaGolpe() {
        return forcaGolpe;
    }

    public void setForcaGolpe(Double forcaGolpe) {
        this.forcaGolpe = forcaGolpe;
    }
}
