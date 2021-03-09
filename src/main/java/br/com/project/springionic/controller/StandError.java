package br.com.project.springionic.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer status;
    private String msg;
    private Long timeStamp;
}
