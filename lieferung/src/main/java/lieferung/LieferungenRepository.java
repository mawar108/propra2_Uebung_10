package lieferung;

import static java.util.stream.Collectors.toList;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class LieferungenRepository {

  private Set<Long> ausgeliefert = new HashSet<>();


  public List<Long> findLieferungenGreaterThan(Long id) {
    return ausgeliefert.stream()
        .filter(b -> b >= id)
        .collect(toList());
  }

  public void save(Long id) {
    ausgeliefert.add(id);
  }


  public int count() {
    return ausgeliefert.size();
  }
}
