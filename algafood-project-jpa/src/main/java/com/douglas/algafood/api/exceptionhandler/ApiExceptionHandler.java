package com.douglas.algafood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.douglas.algafood.core.validation.ValidacaoException;
import com.douglas.algafood.domain.exception.EntidadeEmUsoException;
import com.douglas.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.douglas.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

@ControllerAdvice
public class ApiExceptionHandler  extends ResponseEntityExceptionHandler{
	private static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. "
			+ "Tente novamente e se o problema pesistir, entre em contato com o administrador do sistema";
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		}
		else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo da requisão está inválido. Verifique erro de sintaxe";
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	

	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		
		String detail = String.format("A propriedade '%s' não está disponível para modificações.",path);
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}


	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
		String detail = String.format("A propriedade '%s' recebeu o valor '%s'"
				+ ", que é de um tipo inválido. Corrija e informe um tipo compátivel com o tipo %s.",path,ex.getValue(),ex.getTargetType().getSimpleName());
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	@Autowired
	private MessageSource messageSource;
	
	/*@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String detail = String.format("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");

		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		
		BindingResult bindingResult = ex.getBindingResult();
		/*List<Problem.Field> problemFields = bindingResult.getFieldErrors().stream()
				.map(fieldError -> {
					String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
					
					return Problem.Field.builder()
						.name(fieldError.getField())
						.urserMessage(message)
						.build();
				})
				.collect(Collectors.toList());*/
		
		/*List<Problem.Object> problemFields = bindingResult.getAllErrors().stream()
				.map(objectError -> {
					String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
					
					String name = objectError.getObjectName();
					if(objectError instanceof FieldError) {
						name = ((FieldError) objectError).getField();
					}
					
					return Problem.Object.builder()
						.name(name)
						.urserMessage(message)
						.build();
				})
				.collect(Collectors.toList());
						
				
			
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.objects(problemFields)
				.build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}*/
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
			return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
		}

		@ExceptionHandler({ ValidacaoException.class })
		public ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, WebRequest request) {
			return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
					request);
		} 
	
	
	private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		        
		    ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		    String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
		    
			List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
					.map(objectError -> {
						String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
						
						String name = objectError.getObjectName();
						if(objectError instanceof FieldError) {
							name = ((FieldError) objectError).getField();
						}
						
						return Problem.Object.builder()
							.name(name)
							.urserMessage(message)
							.build();
					})
					
		            .collect(Collectors.toList());
		    
		    Problem problem = createProblemBuilder(status, problemType, detail)
		        .userMessage(detail)
		        .objects(problemObjects)
		        .build();
		    
		    return handleExceptionInternal(ex, problem, headers, status, request);
		}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
	        HttpStatus status, WebRequest request) {
	    
	    if (ex instanceof TypeMismatchException) {
	        return handleMethodArgumentTypeMismatch(
	                (MethodArgumentTypeMismatchException) ex, headers, status, request);
	    }

	    return super.handleTypeMismatch(ex, headers, status, request);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
	    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;		
	    ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
	    String detail = "Ocorreu um erro interno inesperado no sistema. "
	            + "Tente novamente e se o problema persistir, entre em contato "
	            + "com o administrador do sistema.";

	    ex.printStackTrace();
	    
	    Problem problem = createProblemBuilder(status, problemType, detail)
	    		.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
	    		.build();

	    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}  


	
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String detail = String.format("O recurso %s, que você tento acessar, é inxistente.", ex.getRequestURL());
		
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();

		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	
	
	
	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
			
		String detail = String.format("O parâmetro de URL '%S' recebeu um valor '%s' "
				+ ", que é um tipo invalido. Corrija e informe um valor compatível "
				+ "com o tipo %s.",ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		
		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		
		
		
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
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String detail = ex.getMessage();

		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();

		return super.handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();

		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();

		return super.handleExceptionInternal(ex, problem, new HttpHeaders(), 
				HttpStatus.CONFLICT, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if(body==null) {
		body = Problem.builder()
				.status(status.value())
				.title(status.getReasonPhrase())
				.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
				.build();
		}
		else if(body instanceof String){
			body = Problem.builder()
					.status(status.value())
					.title((String) body)
					.userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
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
				.timestamp(LocalDateTime.now())
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
				
				
		
		
	}
	private String joinPath(List<Reference> references) {
		return references.stream()
			.map(ref -> ref.getFieldName())
			.collect(Collectors.joining("."));
	}
	
	

}
