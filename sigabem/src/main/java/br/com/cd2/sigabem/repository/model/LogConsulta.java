package br.com.cd2.sigabem.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogConsulta {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull(message = "O peso não pode ser nulo")
    private Double peso;

    @NotEmpty(message = "O CEP destino não pode ser vazio")
    private String cepDestino;

    @NotEmpty(message = "O CEP origem não pode ser vazio")
    private String cepOrigem;

    @NotEmpty(message = "O Nome destinatario não pode ser vazio")
    private String nomeDestinatario;

    @NotNull(message = "O valor total do frete não pode ser nulo")
    private Double vlTotalFrete;

    @NotNull(message = "A data prevista para entrega não pode ser nula")
    private LocalDate dataPrevistaEntrega;

    @NotNull(message = "A data da consulta não pode ser nula")
    private LocalDate dataConsulta;
}
