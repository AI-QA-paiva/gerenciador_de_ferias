package com.applications.gerenciadordeferias.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

//@AllArgsConstructor
//@Data
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer status;
    private String msg;
    private Long timeStamp;

    //construtor

    public StandardError(Integer status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }

    //Getter

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

}
