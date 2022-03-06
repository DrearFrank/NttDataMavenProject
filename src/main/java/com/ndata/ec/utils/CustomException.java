package com.ndata.ec.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Class for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
@Getter
@Setter
public class CustomException extends RuntimeException {

    private HttpStatus status;

    public CustomException(HttpStatus status, String mensaje) {
        super(mensaje);
        this.status = status;
    }
}
