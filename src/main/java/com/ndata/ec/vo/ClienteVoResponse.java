package com.ndata.ec.vo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteVoResponse implements Serializable {

    private String nombre;
    private String direccion;
    private String telefono;
    private String clave;
    private String estado;
}
