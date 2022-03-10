package com.starking.TedI.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.starking.TedI.model.Pessoa;
import com.starking.TedI.repositories.PessoaRepository;
import com.starking.TedI.service.PessoaService;

@RestController
@RequestMapping(value ="/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<Pessoa>  findAll(){
		 return this.pessoaRepository.findAll();
	}
		
	@PostMapping
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody Pessoa pessoa){
		Pessoa pessoaNova = this.pessoaService.salvar(pessoa);
		if(pessoaNova != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(pessoaNova);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		this.pessoaService.deletar(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		BeanUtils.copyProperties(id, pessoa, "id");
		this.pessoaService.salvar(pessoa);
	}
	

}
