package com.adriano.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.adriano.cursomc.services.exception.ObjectNotFoundException;

/*Então agora que vai receber esta excecao vai ser o a camada de recursos. Vai capturar a excecao e mandar um Json apropriado
 * para a resposta http do meu recursos.
 * para isto vamos criar Handler. Um obj especial que vai interceptar, caso haja uma excecao.
 * O Handler que vai lançar a resposta http adequada no caso 404. Vai ser o manupulador e dexecoes do meus recursos
 * 
 * Para gerar um erro bonitinho com json informando codigo http, a mensagem de erro, emque instante ocorreu um erro,
 * vou criar um objeto auxiliar no mesmo pacote StandardError(erro padrao)
 * */

@ControllerAdvice
public class ResourcesExceptionHandler {
	
	//Este método é padrao
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	} 
	
}
