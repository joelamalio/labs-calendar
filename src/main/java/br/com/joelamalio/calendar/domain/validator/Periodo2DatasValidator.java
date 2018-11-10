package br.com.joelamalio.calendar.domain.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.joelamalio.calendar.domain.Periodo;
import br.com.joelamalio.calendar.domain.validator.annotation.ValidPeriodo2Datas;

public class Periodo2DatasValidator implements ConstraintValidator<ValidPeriodo2Datas, Periodo> {

	@Override
	public boolean isValid(Periodo periodo, ConstraintValidatorContext constraint) {
		LocalDate date1 = periodo.getDataInicial();
		LocalDate date2 = periodo.getDataFinal();
		
		if (date1 != null && date2 != null 
				&& (date1.isEqual(date2) || date1.isBefore(date2))) {
			return true;
		}
		return false;
	}

}
