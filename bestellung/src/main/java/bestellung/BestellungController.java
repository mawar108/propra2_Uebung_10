package bestellung;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BestellungController {



  private final BestellungenService bestellungen;

  public BestellungController(BestellungenService bestellungen) {
    this.bestellungen = bestellungen;
  }

  @GetMapping("/")
  public String index(Model m) {
    m.addAttribute("bestellungen", bestellungen.alleBestellungen());
    return "liste";
  }

  @PostMapping("/")
  public String addBestellung(String kunde) {
    bestellungen.neueBestellungFuer(kunde);
    return "redirect:/";
  }

}
