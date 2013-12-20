package br.ufpb.dce.planck.test;

import java.util.List;

import com.nanuvem.restest.TypedResource;

import br.ufpb.dce.planck.model.Person;

public class PersonResource extends TypedResource<Person>{

    private static String PERSONS = "http://localhost:8080/Planck/api/people";
    
    
    public PersonResource() {
            super(PERSONS);
    }

    @Override
    protected List<Person> toList(String jsonArray) {
            return (List<Person>) Person.fromJsonArrayToPeople(jsonArray);
            		
    }

    @Override
    protected Person toObject(String json) {
            return Person.fromJsonToPerson(json);
            		
    }

    @Override
    protected String toJson(Person t) {
            return t.toJson();
    }
	
	
}
