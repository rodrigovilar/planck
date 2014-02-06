package br.ufpb.dce.planck.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
@RooWebJson(jsonObject = Document.class)
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
