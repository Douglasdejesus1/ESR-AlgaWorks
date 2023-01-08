package com.douglas.algafood.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.douglas.algafood.domain.exception.EntidadeEmUsoException;
import com.douglas.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.douglas.algafood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler  extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		
		
		/*Problem problem = Problem.builder()
				.status(status.value())
				.type("https://algafood.com.br/entidade-nao-encontrada")
				.title("Entidade não econtrada")
				.detail(detail)
				.build();*/
		return super.handleExceptionInternal(ex, problem, new HttpHeaders(), 
				HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException ex, WebRequest request) {
		return super.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
				HttpStatus.BAD_REQUEST, request);
		
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
		return super.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
				HttpStatus.CONFLICT, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if(body==null) {
		body = Problem.builder()
				.status(status.value())
				.title(status.getReasonPhrase())
				.build();
		}
		else if(body instanceof String){
			body = Problem.builder()
					.status(status.value())
					.title((String) body)
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	/*@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
		Problema problema = Problema.builder().dataHora(LocalDateTime.now()).mensagem("O tipo de mídia não é aceito").build();

		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(problema);
	} JA FAZ PARTE DA ResponseEntityExceptionHandler*/
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, 
			ProblemType problemType, String detail) {
		
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
		
		
	}
	

}
