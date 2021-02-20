package br.com.project.springionic.controller.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class PagamentoComCartao extends Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer numeroParcelas;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataPagamento;

    public PagamentoComCartao(Integer id, EstadoPagamentoEnum estado, Pedido pedido, Integer numeroParcelas, Date dataPagamento) {
        super(id, estado, pedido);
        this.numeroParcelas = numeroParcelas;
        this.dataPagamento = dataPagamento;
    }

}
