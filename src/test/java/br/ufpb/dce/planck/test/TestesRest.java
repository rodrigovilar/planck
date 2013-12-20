package br.ufpb.dce.planck.test;

import java.util.GregorianCalendar;

import br.ufpb.dce.planck.model.Person;

import com.nanuvem.restest.TypedResource;
import com.nanuvem.restest.TypedResourceTest;

public class TestesRest extends TypedResourceTest<Person> {

	@Override
	protected Person createObject() {
		Person person = new Person();
		person.setFullname("Anderson");
		person.setBirthday(new GregorianCalendar(1989, 11, 29));
		person.setPassword("12345678");
		person.setEmail("anderson@dce.ufpb.br");

		return person;
	}

	@Override
	protected TypedResource<Person> createTypedResource() {
		return new PersonResource();
	}

	@Override
	protected Person editObject(Person editedperson) {
		editedperson.setFullname("Alex");
		editedperson.setBirthday(new GregorianCalendar(1989, 11, 29));
		editedperson.setPassword("87654321");
		editedperson.setEmail("alex@dce.ufpb.br");

		return editedperson;
	}

	@Override
	protected Object getId(Person p) {
		return p.getId();
	}

}
