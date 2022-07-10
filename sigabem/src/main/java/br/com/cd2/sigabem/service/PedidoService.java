package br.com.cd2.sigabem.service;

import br.com.cd2.sigabem.controller.pedido.dto.PedidoRequest;
import br.com.cd2.sigabem.controller.pedido.dto.PedidoResponse;
import br.com.cd2.sigabem.domain.Endereco;
import br.com.cd2.sigabem.domain.Frete;
import br.com.cd2.sigabem.exception.BadRequestException;
import br.com.cd2.sigabem.mapper.LogConsultaMapper;
import br.com.cd2.sigabem.repository.LogConsultaRepository;
import br.com.cd2.sigabem.repository.model.LogConsulta;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class PedidoService {

    private LogConsultaRepository logConsultaRepository;
    private FreteService freteService;


    public Endereco pegarEndereco(String cep) {
        ResponseEntity<Endereco> endereco = null;
        try {
            endereco = new RestTemplate().exchange(
                    "https://viacep.com.br/ws/" + cep + "/json",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Endereco>() {
                    }
            );
        } catch (Exception e) {
            throw new BadRequestException("Cep Inválido");
        }

        if (endereco.getBody().getErro()) {
            throw new BadRequestException("Houve um erro no ViaCep");
        }

        return endereco.getBody();
    }

    public LogConsulta registraConsulta(PedidoResponse pedidoResponse, PedidoRequest dadosEnvio) {
        LogConsulta logConsulta = LogConsultaMapper.pedidoResponseToLogConsulta(pedidoResponse);
        logConsulta.setDataConsulta(LocalDate.now());
        logConsulta.setNomeDestinatario(dadosEnvio.getNomeDestinatario());
        logConsulta.setPeso(dadosEnvio.getPeso());
        return logConsultaRepository.save(logConsulta);
    }

    public PedidoResponse calcularFrete(PedidoRequest pedido) {
        Endereco edOrigem = pegarEndereco(pedido.getCepOrigem());
        Endereco edDestino = pegarEndereco(pedido.getCepDestino());

        Frete frete = freteService.calcularFrete(edOrigem, edDestino, pedido.getPeso());

        PedidoResponse response = PedidoResponse.builder()
                .vlTotalFrete(frete.getVlTotalEntrega())
                .dataPrevistaEntrega(frete.getPrevisaoEntrega())
                .cepOrigem(edOrigem.getCep())
                .cepDestino(edDestino.getCep())
                .build();

        registraConsulta(response, pedido);
        return response;
    }

}
