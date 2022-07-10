package br.com.cd2.sigabem.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ParametroDesconto {
    private Double desconto;
    private LocalDate dias;
}
