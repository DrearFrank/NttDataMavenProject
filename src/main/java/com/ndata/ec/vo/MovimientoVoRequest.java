package com.ndata.ec.vo;

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
public class MovimientoVoRequest {

    private Long idMovimiento;
    private Long cuentaId;
    private float valor;
    private String usuarioCreacion;
    private String feCreacion;
    private String ipCreacion;
}
