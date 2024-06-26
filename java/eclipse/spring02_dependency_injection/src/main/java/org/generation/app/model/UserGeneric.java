package org.generation.app.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*
 *  @Component es una anotación genérica que se utiliza
 *  para indicar que la clase es componente de Spring
 *  y debe ser gestionada por el contenedor
 *  de inversión de control (IoC).
 *  
 *  @Component se aplica a una clase para que Spring
 *  la detecte automáticamente y la registre como un bean
 *  en el contexto de la aplicación.
 *  
 *  @Qualifer se usa para resolver ambiguedades cuando
 *  hay varios objetos(beans) del mismo tipo en
 *  el contexto de la aplicación y se necesita especificar
 *  el bean que se debe inyectar.
 *  
 *  Bean: Es un objeto administrado por el contenedor de Spring.
 * 
 */

@Qualifier("generic")
@Component
// @Entity
public class UserGeneric implements User {
	
	private String firstName;
	private String lastName;
	
	public UserGeneric() {
		
	}
	
	public UserGeneric(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String greeting() {
		return "Hola, soy un usuario general";
	}

}
