package br.ufpb.dce.planck;
import br.ufpb.dce.planck.model.Person;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = Person.class)
@Controller
@RequestMapping("/people")
public class PersonController {
}
