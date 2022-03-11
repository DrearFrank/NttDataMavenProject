package com.ndata.ec.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class Entity  for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuenta")
public class Cuenta implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Long idCuenta;

    @Column(name = "fe_creacion")
    Date fechaCreacion;

    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "tipo_cuenta")
    private String tipoCuenta;

    private String numero;
    @Column(name = "saldo_inicial")

    private Float saldoInicial;

    private String estado;

    @ManyToOne()
    @JoinColumn(name = "cliente_id", insertable = false, updatable = false)
    private Cliente cliente;

}
