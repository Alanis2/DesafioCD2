package br.com.cd2.sigabem.controller.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PedidoRequest {

    private Double peso;
    private String cepOrigem;
    private String cepDestino;
    private String nomeDestinatario;
}
