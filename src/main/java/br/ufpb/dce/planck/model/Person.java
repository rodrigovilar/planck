package br.ufpb.dce.planck.model;

import java.util.Calendar;
import java.util.Collection;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.json.RooJson;


@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Person {

    /**
     */
    @NotNull
    private String fullname;

    /**
     */
    @NotNull
    @Column(unique = true)
    private String email;

    @Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Calendar birthdate;

    /**
     */
    @NotNull
    @Size(min = 6, max = 20)
    private String password;
    
    public String toJson() {
		ObjectNode noUsuario = personJson(this);
		return noUsuario.toString();
	}

	public static String toJsonArray(
			Collection<br.ufpb.dce.planck.model.Person> collection) {
		ArrayNode arrayOfPersons = JsonNodeFactory.instance.arrayNode();
		for (Person person : collection) {
			ObjectNode noPerson = personJson(person);
			arrayOfPersons.add(noPerson);
		}
		return arrayOfPersons.toString();
	}
    
    private static ObjectNode personJson (br.ufpb.dce.planck.model.Person person){
    	
    	ObjectNode noPerson = JsonNodeFactory.instance.objectNode();
    	noPerson.put("id", person.getId());
    	noPerson.put("fullname", person.getFullname());
    	noPerson.put("email", person.getEmail());
    	noPerson.put("password", person.getPassword());
    	StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(person.getBirthdate().get(Calendar.DATE) + "/");
		strBuilder.append(getMonthName((person.getBirthdate().get(Calendar.MONTH))) + "/");
		strBuilder.append(person.getBirthdate().get(Calendar.YEAR));
		noPerson.put("birthday", strBuilder.toString());
    	
		return noPerson;
    	
    }
    
    public static br.ufpb.dce.planck.model.Person fromJsonToPerson (String json){
    	ObjectMapper objectMapper = new ObjectMapper();
    	JsonFactory factory = objectMapper.getJsonFactory();
    	
    	try {
    		JsonNode personJSON = objectMapper.readTree(factory.createJsonParser(json));
    		Person person = new Person();
    		
    		if(personJSON.has("id")){
    			person.setId(personJSON.get("id").asLong());
      		}
    		if(personJSON.has("fullname")){
    			person.setFullname(personJSON.get("fullname").asText());
    		}
    		if(personJSON.has("email")){
    			person.setEmail(personJSON.get("email").asText());
    		}
    		if(personJSON.has("password")){
    			person.setPassword(personJSON.get("password").asText());
    		}
    		if(personJSON.has("birthdate")){
    			String date_str = personJSON.get("birthdate").asText();
    			String[] date = date_str.split("/");
    			Calendar c = Calendar.getInstance();
    			c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[0]));
    			c.set(Calendar.MONTH, getMonthNumber(date[1]));
				c.set(Calendar.YEAR, Integer.parseInt(date[2]));
				person.setBirthdate(c);
     		}
    		
    		return person;
    		
    	}catch (Exception e){
    		throw new RuntimeException(e);
    	}
    	
    }     
    
    public static String getMonthName(int month) {
		return new java.text.DateFormatSymbols().getMonths()[month];
	}

	public static int getMonthNumber(String month) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(new java.text.SimpleDateFormat("MMM",
					java.util.Locale.ENGLISH).parse(month));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cal.get(Calendar.MONTH);
	}
    
    
    
}


