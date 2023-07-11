package com.algaworks.algatransito.api.controller;

import java.util.List;

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

import com.algaworks.algatransito.api.model.openapi.controller.ProprietarioControllerOpenApi;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.service.RegistroProprietarioService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController implements ProprietarioControllerOpenApi{
	
	//@PersistenceContext
	//private EntityManager manager;
	//@Autowired não é necessário, pois é criado o construtor da classe com o @AllArgsConstructor assim não é necessário injeção, pois está fazendo via construtor
	private final ProprietarioRepository proprietarioRepository;
	
	private final RegistroProprietarioService registroProprietarioService;
	
	@GetMapping
	public List<Proprietario> listar(){
		//return proprietarioRepository.findByNomeContaining("a");
		return proprietarioRepository.findAll();
		//TypedQuery<Proprietario> query = manager.createQuery("from Proprietario", Proprietario.class);
		//return query.getResultList();
	}
	
	 @GetMapping("/{proprietarioId}")
	    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId) {
	        return proprietarioRepository.findById(proprietarioId)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }
	 
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	 public Proprietario adicionar(@Valid @RequestBody Proprietario proprietario) {
		 //return proprietarioRepository.save(proprietario);
		 return registroProprietarioService.salvar(proprietario);
	 } 
	 
	 @PutMapping("/{proprietarioId}")
	 public  ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId,@RequestBody @Valid Proprietario proprietario) {
		 if(!proprietarioRepository.existsById(proprietarioId)) {
			 return ResponseEntity.notFound().build();
		 }
		 proprietario.setId(proprietarioId);
		 Proprietario proprietarioAtualizado = proprietarioRepository.save(proprietario); 
		 return ResponseEntity.ok(proprietarioAtualizado);
	 }
	 
	@DeleteMapping("/{proprietarioId}")
	public ResponseEntity<Void> remover(@PathVariable Long proprietarioId) {
		 if(!proprietarioRepository.existsById(proprietarioId)) {
			 return ResponseEntity.notFound().build();
		 }
		 
		// proprietarioRepository.deleteById(proprietarioId); 
		 registroProprietarioService.excluir(proprietarioId);
		 return ResponseEntity.noContent().build();
	}

}
