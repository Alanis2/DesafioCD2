package br.com.cd2.sigabem.mapper;

import br.com.cd2.sigabem.controller.pedido.dto.PedidoResponse;
import br.com.cd2.sigabem.repository.model.LogConsulta;

public class LogConsultaMapper {
    public static LogConsulta pedidoResponseToLogConsulta(PedidoResponse pedidoResponse){
        return LogConsulta.builder()
                .vlTotalFrete(pedidoResponse.getVlTotalFrete())
                .dataPrevistaEntrega(pedidoResponse.getDataPrevistaEntrega())
                .cepOrigem(pedidoResponse.getCepOrigem())
                .cepDestino(pedidoResponse.getCepDestino())
                .build();
    }
}
