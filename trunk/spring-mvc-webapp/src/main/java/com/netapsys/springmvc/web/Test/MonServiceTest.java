package com.netapsys.springmvc.web.Test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import junit.framework.TestCase;

import com.netapsys.springmvc.web.Customer;

public class MonServiceTest  {

	public static void main(String[] args) {

		Customer custom = new Customer();

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(custom);

		if (constraintViolations.size() > 0) {
			System.out.println("Impossible de valider les donnees du bean : ");
			for (ConstraintViolation<Customer> contraintes : constraintViolations) {
				System.out.println(contraintes.getRootBeanClass().getSimpleName() + "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());
			}
		} else {
			System.out.println("Les donnees du bean sont valides");
		}
	}
}