package br.com.joelamalio.calendar.domain.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.joelamalio.calendar.domain.validator.Periodo2DatasValidator;

@Documented
@Constraint(validatedBy = { Periodo2DatasValidator.class })
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPeriodo2Datas {

	String message() default "A Data Inicial deve ser menor ou igual a Data Final";

	Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
