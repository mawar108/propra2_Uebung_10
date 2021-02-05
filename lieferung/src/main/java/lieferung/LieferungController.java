package lieferung;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LieferungController {



  private final LieferungService lieferungen;

  public LieferungController(LieferungService lieferungen) {
    this.lieferungen = lieferungen;
  }

  @PostMapping("/liefern")
  public String liefern(Long id) {
    Assert.notNull(id,"Id für die Lieferung muss vorhanden sein");
    lieferungen.anfrage(id);
    String text = "Lieferung für " + id + " ist in Arbeit";
    System.out.println(text);
    return text;
  }

  @GetMapping("/count")
  public int count() {
    return lieferungen.anzahl();
  }


}
