package br.com.cd2.sigabem.service;

import br.com.cd2.sigabem.domain.Endereco;
import br.com.cd2.sigabem.domain.Frete;
import br.com.cd2.sigabem.domain.ParametroDesconto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class FreteService {

    @Value("${frete.parametros.DDD.desconto}")
    private Double DESCONTO_DDD;
    @Value("${frete.parametros.DDD.dias}")
    private Long DIAS_DDD;
    @Value("${frete.parametros.UF.desconto}")
    private Double DESCONTO_UF;
    @Value("${frete.parametros.UF.dias}")
    private Long DIAS_UF;

    public Frete calcularFrete(Endereco edOrigem, Endereco edDestino, Double peso) {
        ParametroDesconto desconto = calcularDiasDesconto(edOrigem, edDestino);

        Double vlTotal = calcularVlTotal(desconto.getDesconto(), peso);

        return Frete.builder()
                .previsaoEntrega(desconto.getDias())
                .vlTotalEntrega(vlTotal)
                .build();
    }

    public Double calcularVlTotal(Double desconto, Double peso) {
        return peso -= peso * desconto;
    }

    public ParametroDesconto calcularDiasDesconto(Endereco edOrigem, Endereco edDestino) {
        ParametroDesconto paramDes = new ParametroDesconto();
        paramDes.setDesconto(0d);
        paramDes.setDias(pegarDias(10L));

        if (edOrigem.getUf().equals(edDestino.getUf())) {
            paramDes.setDesconto(DESCONTO_UF);
            paramDes.setDias(pegarDias(DIAS_UF));
        }

        if (edOrigem.getDdd().equals(edDestino.getDdd())) {
            paramDes.setDesconto(DESCONTO_DDD);
            paramDes.setDias(pegarDias(DIAS_DDD));
        }

        return paramDes;
    }

    public LocalDate pegarDias(Long dias) {
        return LocalDate.now().plusDays(dias);
    }

}
