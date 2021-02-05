package bestellung;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class BestellungenService {


  @Value("${lieferung.endpoint}")
  private String endpoint;


  private final BestellungRepository repository;

  public BestellungenService(BestellungRepository repository) {
    this.repository = repository;
  }

  public Collection<Bestellung> alleBestellungen() {
    return repository.findAll();
  }

  public void neueBestellungFuer(String kunde) {
    Bestellung bestellung = new Bestellung(kunde);
    repository.save(bestellung);

    // Hier muss das andere SCS asynchron informiert werden
    // Achten Sie darauf, dass die Nachricht an das andere System niemals verloren geht
    
    // Probieren Sie aus, das andere System vorher zu deaktivieren. 
    // Wenn es reaktiviert wird, mussen die fehlenden Nachrichten übertragen werden.

  }




}
