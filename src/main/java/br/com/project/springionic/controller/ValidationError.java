package br.com.project.springionic.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class ValidationError extends StandarError implements Serializable {

    private static final long serialVersionUID = 1L;

    List<FieldMessage> fieldMessageList = new ArrayList<>();

    public  ValidationError (Integer status, String msg, Long timeStamp){
        super(status,msg,timeStamp);
    }

    public void addError(String field, String msg){
        fieldMessageList.add(new FieldMessage(field, msg));
    }
}
