package br.ufpb.dce.planck.model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import flexjson.JSONDeserializer;
import br.ufpb.dce.planck.util.JSON;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
    @DateTimeFormat(style = "S-")
    private Calendar birthday;

    /**
     */
    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Document> documents = new HashSet<Document>();
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Person other = (Person) obj;
        if (birthday == null) {
            if (other.birthday != null) return false;
        } else if (!equalsDate(birthday, other.birthday)) return false;
        if (email == null) {
            if (other.email != null) return false;
        } else if (!email.equals(other.email)) return false;
        if (fullname == null) {
            if (other.fullname != null) return false;
        } else if (!fullname.equals(other.fullname)) return false;
        if (password == null) {
            if (other.password != null) return false;
        } else if (!password.equals(other.password)) return false;
        return true;
    }

    public static boolean equalsDate(Calendar c1, Calendar c2) {
        c1.clear(Calendar.HOUR);
        c1.clear(Calendar.MINUTE);
        c1.clear(Calendar.MILLISECOND);
        c1.clear(Calendar.SECOND);
        c1.clear(Calendar.DAY_OF_WEEK);
        c1.clear(Calendar.WEEK_OF_YEAR);
        c1.clear(Calendar.WEEK_OF_MONTH);
        c1.clear(Calendar.DAY_OF_YEAR);
        c1.clear(Calendar.DAY_OF_WEEK_IN_MONTH);
        c1.clear(Calendar.AM_PM);
        c1.clear(Calendar.HOUR_OF_DAY);
        c1.clear(Calendar.DST_OFFSET);
        c2.clear(Calendar.HOUR);
        c2.clear(Calendar.MINUTE);
        c2.clear(Calendar.MILLISECOND);
        c2.clear(Calendar.SECOND);
        c2.clear(Calendar.DAY_OF_WEEK);
        c2.clear(Calendar.WEEK_OF_YEAR);
        c2.clear(Calendar.WEEK_OF_MONTH);
        c2.clear(Calendar.DAY_OF_YEAR);
        c2.clear(Calendar.DAY_OF_WEEK_IN_MONTH);
        c2.clear(Calendar.AM_PM);
        c2.clear(Calendar.HOUR_OF_DAY);
        c2.clear(Calendar.DST_OFFSET);
        if (c1.equals(c2)) {
            return true;
        }
        return false;
    }

    public String toJson() {
        ObjectNode noUsuario = personJson(this);
        return noUsuario.toString();
    }

    public static Collection<Person> fromJsonArrayToPeople(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory factory = objectMapper.getJsonFactory();
            ArrayNode personsJSON = (ArrayNode) objectMapper.readTree(factory.createJsonParser(json));
            List<Person> persons = new ArrayList<Person>();
            for (JsonNode personJSON : personsJSON) {
                Person person = fromJsonToPerson(personJSON);
                persons.add(person);
            }
            return persons;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static Person fromJsonToPerson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory factory = objectMapper.getJsonFactory();
        try {
            JsonNode personJSON = objectMapper.readTree(factory.createJsonParser(json));
            return fromJsonToPerson(personJSON);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static br.ufpb.dce.planck.model.Person fromJsonToPerson(JsonNode personJSON) {
        Person person = new Person();
        try {
            if (personJSON.has("id")) {
                person.setId(personJSON.get("id").asLong());
            }
            if (personJSON.has("fullname")) {
                person.setFullname(personJSON.get("fullname").asText());
            }
            if (personJSON.has("email")) {
                person.setEmail(personJSON.get("email").asText());
            }
            if (personJSON.has("password")) {
                person.setPassword(personJSON.get("password").asText());
            }
            person.setBirthday(JSON.extractCalendar(personJSON, "birthday"));
            if (personJSON.has("version")) {
                person.setVersion(personJSON.get("version").asInt());
            }
            return person;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonArray(Collection<Person> collection) {
        ArrayNode arrayOfPersons = JsonNodeFactory.instance.arrayNode();
        for (Person person : collection) {
            ObjectNode noPerson = personJson(person);
            arrayOfPersons.add(noPerson);
        }
        return arrayOfPersons.toString();
    }

    private static ObjectNode personJson(Person person) {
        ObjectNode noPerson = JsonNodeFactory.instance.objectNode();
        if (person.getId() != null) {
            noPerson.put("id", person.getId());
        }
        noPerson.put("fullname", person.getFullname());
        noPerson.put("email", person.getEmail());
        noPerson.put("password", person.getPassword());
        noPerson.put("birthday", JSON.generateString(person.getBirthday()));
        noPerson.put("version", person.getVersion());
        return noPerson;
    }

 
}
