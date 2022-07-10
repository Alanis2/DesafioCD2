package br.com.cd2.sigabem.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Frete {
    private Double vlTotalEntrega;
    private LocalDate previsaoEntrega;
}
