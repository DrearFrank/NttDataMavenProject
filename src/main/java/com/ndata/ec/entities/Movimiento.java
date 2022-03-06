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
 * Class VO for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movimiento")
public class Movimiento implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movimiento")
	private Long idMovimiento;
	
	@ManyToOne()
	@JoinColumn(name = "cuenta_id", insertable = false, updatable = false)
	private Cuenta cuenta;

	@Column(name = "cuenta_id")
	private Long cuentaId;
	
	@Column(name = "tipo_movimiento")
	private String tipoMovimiento;
	
	private float valor;
	private float saldo;
	
	@Column(name = "usuario_creacion")
	String usuarioCreacion;
	
	@Column(name = "fe_creacion")
	Date feCreacion;
	
	@Column(name = "ip_creacion")
	String ipCreacion;	

}
