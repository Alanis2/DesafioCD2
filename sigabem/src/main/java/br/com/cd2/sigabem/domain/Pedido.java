package br.com.cd2.sigabem.domain;

import lombok.Data;
import java.time.LocalDate;


@Data
public class Pedido {
    private Double peso;
    private String cepOrigem;
    private String cepDestino;
    private String nomeDestinatario;
    private Double vlTotalFrete;
    private LocalDate dataPrevistaEntrega;
    private LocalDate dataConsulta;
}
