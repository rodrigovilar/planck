package br.ufpb.dce.planck.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Document {

    /**
     */
    @ManyToOne
    private Person person;

    /**
     */
    @NotNull
    private String type;

    /**
     */
    @NotNull
    private String title;

    /**
     */
    @NotNull
    private String summary;

    /**
     */
    @NotNull
    private String authors;

    /**
     */
    @NotNull
    private String path_to_file;

    /**
     */
    @NotNull
    private Integer yearr;
}
