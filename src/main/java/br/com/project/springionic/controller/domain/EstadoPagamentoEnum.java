package br.com.project.springionic.controller.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoPagamentoEnum {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private Integer cod;
    private String descricao;

    public static EstadoPagamentoEnum toEnum(Integer cod){
        if(cod == null)
            return null;

        for(EstadoPagamentoEnum tipo : EstadoPagamentoEnum.values()){
            if(cod.equals(tipo.getCod())){
                return tipo;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + cod);
    }
}
