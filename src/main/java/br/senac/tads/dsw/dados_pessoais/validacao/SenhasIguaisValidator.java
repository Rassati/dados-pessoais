package br.senac.tads.dsw.dados_pessoais.validacao;

import br.senac.tads.dsw.dados_pessoais.Pessoa;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class SenhasIguaisValidator
	implements ConstraintValidator<SenhasIguais, Pessoa> {

	private String mensagem;

	@Override
	public void initialize(SenhasIguais annotation) {
		// Lê a mensagem configurada na anotação @SenhasIguais
		this.mensagem = annotation.message();
	}

	@Override
	public boolean isValid(Pessoa pessoa, ConstraintValidatorContext context) {
		boolean resultado = pessoa.getSenha() != null && pessoa.getSenha().equals(pessoa.getSenhaRepeticao());
		if (!resultado) {
			//Associa o erro ao campo "senha" em vez de a classe toda
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(mensagem).addPropertyNode("senha").addConstraintViolation();
		}
	return resultado;
	}

}
