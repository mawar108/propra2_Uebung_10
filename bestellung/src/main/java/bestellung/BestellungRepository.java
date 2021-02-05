package bestellung;

import static java.util.stream.Collectors.toList;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BestellungRepository {

  private static final AtomicLong nextid = new AtomicLong(0);

  public static Long getId() {
    return nextid.addAndGet((long) (Math.random() * 6 + 1));
  }

  private Map<Long, Bestellung> db = new HashMap<>();

  public void save(Bestellung... bestellungen) {
    Arrays.stream(bestellungen).forEach(b -> db.put(b.getId(), b));
  }

  public Bestellung findById(Long id) {
    return db.get(id);
  }

  public Long maxId() {
    return nextid.get();
  }

  public List<Bestellung> findBestellungenByIdGreaterThan(Long id) {
    return db.values().stream()
        .filter(b -> b.getId() >= id)
        .collect(toList());
  }

  public Collection<Bestellung> findAll() {
    return db.values().stream().sorted().collect(toList());
  }

}
