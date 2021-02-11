package br.com.project.springionic.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandarError {

    private Integer status;
    private String msg;
    private Long timeStamp;
}
