package com.smpinheiro.agendaservico.repositories;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smpinheiro.agendaservico.models.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID>{

	
	@Query("SELECT a FROM Agendamento a WHERE a.funcionario.nome = :nome AND a.dataHora BETWEEN :inicio AND :fim ")
	Agendamento findByFuncionarioBetweenDataHora( 
			@Param("nome") String nomeFuncionario, @Param("inicio") LocalDateTime dataHoraInicio, @Param("fim") LocalDateTime dataHoraFim );

	
	@Query("SELECT a FROM Agendamento a WHERE a.cliente.telefone = :telefone AND a.dataHora BETWEEN :inicio AND :fim ")
	Agendamento findByClienteBetweenDataHora(
			@Param("telefone") String telefoneCliente, @Param("inicio") LocalDateTime dataHoraInicio, @Param("fim") LocalDateTime dataHoraFim );

}
