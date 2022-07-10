package br.com.cd2.sigabem.domain;

import lombok.Data;

@Data
public class Endereco {
    private String cep;
    private String uf;
    private String ddd;
    private Boolean erro = Boolean.FALSE;

}
