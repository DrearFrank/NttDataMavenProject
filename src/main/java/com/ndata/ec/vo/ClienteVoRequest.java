package com.ndata.ec.vo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * Class for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
@Getter
@Setter
public class ClienteVoRequest implements Serializable {

    /**
     *
     */

    private Integer idCliente;
    private String nombre;
    private String genero;
    private String Edad;
    private String identificacion;
    private String telefono;
    private String clave;
    private String estado;
}
