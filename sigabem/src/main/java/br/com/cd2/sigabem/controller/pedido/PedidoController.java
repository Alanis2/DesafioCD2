package br.com.cd2.sigabem.controller.pedido;

import br.com.cd2.sigabem.controller.pedido.dto.PedidoRequest;
import br.com.cd2.sigabem.controller.pedido.dto.PedidoResponse;
import br.com.cd2.sigabem.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedido")
@AllArgsConstructor
public class PedidoController {

    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponse> calcularFrete(@RequestBody @Valid PedidoRequest pedidoRequest) {
        return ResponseEntity.ok().body(pedidoService.calcularFrete(pedidoRequest));
    }
}
