package com.smpinheiro.agendaservico.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "agendamento")
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clinet_id", nullable = false)
	@JsonBackReference("cliente-agendamento")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "funcionario_id", nullable = false)
	@JsonBackReference("funcionario-agendamento")
	private Funcionario funcionario;
	
	private String descricaoServico;
	private LocalDateTime dataHora;
	
	@Value("ativo")
	private String status;
	@Value("false")
	private boolean concluido;
	
}
