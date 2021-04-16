package br.com.bandtec.continuadalutadores.dominio;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class LutadorSimples {
    @Size(min = 3, max = 10)
    @NotBlank
    private String nome;

    @Positive
    private Double forcaGolpe;
}
