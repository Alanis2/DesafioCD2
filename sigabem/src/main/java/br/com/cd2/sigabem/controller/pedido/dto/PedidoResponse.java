package br.com.cd2.sigabem.controller.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponse {

    private Double vlTotalFrete;
    private LocalDate dataPrevistaEntrega;
    private String cepOrigem;
    private String cepDestino;
}
