package com.ndata.ec.vo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class MovimientoVoResponse implements Serializable {

    private String fecha;
    private String cliente;
    private String numero;
    private String tipo;
    private float saldoInicial;
    private String estado;
    private float movimiento;
    private float saldoDisponible;

}
