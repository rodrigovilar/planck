package br.ufpb.dce.planck.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
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

    /**
     */
    @NotNull
    @Size(min = 6, max = 20)
    private String password;
}
