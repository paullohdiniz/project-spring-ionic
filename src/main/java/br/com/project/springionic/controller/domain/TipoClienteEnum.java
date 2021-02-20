package br.com.project.springionic.controller.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoClienteEnum {

    PESSOA_FISICA(1, "Pessoa Fisica"),
    PESSOA_JURIDICA(2, "Pessoa Juridica");

    private Integer cod;
    private String descricao;

    public static TipoClienteEnum toEnum(Integer cod){
        if(cod == null)
            return null;

        for(TipoClienteEnum tipo : TipoClienteEnum.values()){
            if(cod.equals(tipo.getCod())){
                return tipo;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + cod);
    }
}
