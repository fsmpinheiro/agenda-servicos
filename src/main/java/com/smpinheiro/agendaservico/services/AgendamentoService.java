package com.smpinheiro.agendaservico.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smpinheiro.agendaservico.models.Agendamento;
import com.smpinheiro.agendaservico.models.Cliente;
import com.smpinheiro.agendaservico.models.Funcionario;
import com.smpinheiro.agendaservico.repositories.AgendamentoRepository;
import com.smpinheiro.agendaservico.repositories.ClienteRepository;
import com.smpinheiro.agendaservico.repositories.FuncionarioRepository;

import jakarta.transaction.Transactional;

@Service
public class AgendamentoService {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	
	@Transactional
	public Agendamento createAgendamento (Agendamento novoAgendam) {
		
		Agendamento agendamento = new Agendamento();
		
		if(novoAgendam.getDataHora().isAfter(LocalDateTime.now().plusSeconds(5)) ) {
			throw new IllegalArgumentException("Agendamento deve ser no futuro");
		} else {
			agendamento.setDataHora(novoAgendam.getDataHora() );
		}
		
		
		Optional<Funcionario> funcionarOpt = funcionarioRepository.findByNome(novoAgendam.getFuncionario().getNome() );
		if(!funcionarOpt.isPresent() ) {
			throw new IllegalArgumentException("Funcionario não encontrado");
		} else {
			novoAgendam.setFuncionario(funcionarOpt.get() );
		}		
		
		
		LocalDateTime dataHoraInicio = agendamento.getDataHora();
		Long prazoServico = Long.parseLong( agendamento.getFuncionario().getPeriodo() );
		LocalDateTime dataHoraFim = agendamento.getDataHora().plusMinutes(prazoServico);
		
		
		Agendamento agendado = agendamentoRepository.findByFuncionarioBetweenDataHora(agendamento.getFuncionario().getNome(), dataHoraInicio, dataHoraFim);
		if(Objects.nonNull(agendado)) {
			throw new IllegalArgumentException("Horário já reservado");
		
		} 
			
		
		Optional<Cliente> clienteOpt = clienteRepository.findByTelefone(novoAgendam.getCliente().getTelefone() );
		if(!clienteOpt.isPresent() ) {
			agendamento.setCliente(clienteService.createCliente(novoAgendam.getCliente()) );
		} else {
			agendamento.setCliente(clienteOpt.get() );
		}
					
		return agendamentoRepository.save(agendamento);
	}
	
	@Transactional
	public void editAgendamento ( ) {
		
	}
	
	@Transactional
	public void deleteAgendamentoFuncionario (Agendamento agendamento) {
		
		Optional<Funcionario> funcionarOpt = funcionarioRepository.findByNome(agendamento.getFuncionario().getNome());
		if(!funcionarOpt.isPresent()) {
			throw new IllegalArgumentException("Funcionario não encontrado");
		}
		Object funcionario = funcionarOpt.get();
		
		Agendamento agendado = getAgendamento(funcionario, agendamento.getDataHora());
		
		agendamentoRepository.delete(agendado);
	}
	
	@Transactional
	public void deleteAgendamentoCliente (Agendamento agendamento) {
		
		Optional<Cliente> clienteOpt = clienteRepository.findByTelefone(agendamento.getCliente().getTelefone());
		if(!clienteOpt.isPresent()) {
			throw new IllegalArgumentException("Cliente não encontrado");
		}
		Object cliente = clienteOpt.get();
		
		Agendamento agendado = getAgendamento(cliente, agendamento.getDataHora());
		
		agendamentoRepository.delete(agendado);
	}

	public List<Agendamento> listAgendamentos ( ) {
		
		return null;
	}
	
	
	
	private Agendamento getAgendamento (Object entidade, LocalDateTime dataHora ) {
		Agendamento agendado = new Agendamento();
		LocalDateTime dataHoraInicio = dataHora.minusSeconds(2); 
		LocalDateTime dataHoraFim    = dataHora.plusSeconds(2);
		
		if(entidade.getClass().equals(Funcionario.class) ) {
			Funcionario funcionarEnt = (Funcionario) entidade;
			agendado = agendamentoRepository.findByFuncionarioBetweenDataHora(funcionarEnt.getNome(), dataHoraInicio, dataHoraFim);
		}
		
		if(entidade.getClass().equals(Cliente.class) ) {
			Cliente clienteEnt = (Cliente) entidade;
			agendado = agendamentoRepository.findByClienteBetweenDataHora(clienteEnt.getTelefone(), dataHoraInicio, dataHoraFim);
		}
		
		if(Objects.nonNull(agendado)) {
			throw new RuntimeException("Agendamento não encontrado");
		} 
		
		return agendado;
	}
}
