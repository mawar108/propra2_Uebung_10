package bestellung;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class BestellungenService {

  Queue<Bestellung> offeneBestellungen =new LinkedList<>();

  @Value("${lieferung.endpoint}")
  private String endpoint;

  private final RestTemplate rest;

  private final BestellungRepository repository;

  public BestellungenService(BestellungRepository repository) {
    this.rest = new RestTemplate();
    this.repository = repository;
  }

  public Collection<Bestellung> alleBestellungen() {
    return repository.findAll();
  }

  public void neueBestellungFuer(String kunde) {
    Bestellung bestellung = new Bestellung(kunde);
    offeneBestellungen.add(bestellung);
    repository.save(bestellung);

    // Hier muss das andere SCS asynchron informiert werden
    // Achten Sie darauf, dass die Nachricht an das andere System niemals verloren geht
    
    // Probieren Sie aus, das andere System vorher zu deaktivieren. 
    // Wenn es reaktiviert wird, mussen die fehlenden Nachrichten übertragen werden.

  }

  @Scheduled(fixedDelay = 1000)
  public void lieferSystem(){
    try{
      while(!offeneBestellungen.isEmpty()){
        MultiValueMap<String,Long> map= new LinkedMultiValueMap<String, Long>();
        map.add("id", offeneBestellungen.peek().getId());
        HttpEntity<MultiValueMap<String,Long>> request= new HttpEntity<>(map);
        ResponseEntity<String> responseEntity= rest.postForEntity(endpoint, request, String.class);
        offeneBestellungen.remove();
      }
    }
    catch(RestClientException e){
      e.printStackTrace();
      System.out.println("Post fehlgeschlagen");
    }
  }



}
