package bestellung;

import java.util.Objects;
import org.springframework.util.Assert;

public class Bestellung  implements Comparable<Bestellung>{
  private final Long id;
  private final String kunde;
  private boolean verschickt;

  public Bestellung(String kunde) {
    Assert.notNull(kunde, "Name muss vorhanden sein");
    this.id = BestellungRepository.getId();
    this.kunde = kunde;
  }

  public void verschickt() {
    this.verschickt = true;
  }

  public Long getId() {
    return id;
  }

  public String getKunde() {
    return kunde;
  }

  public boolean wurdeVerschickt() {
    return verschickt;
  }

  @Override
  public String toString() {
    return "Bestellung{" +
        "id=" + id +
        ", kunde='" + kunde + '\'' +
        ", verschickt=" + verschickt +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Bestellung that = (Bestellung) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public int compareTo(Bestellung o) {
    return id.compareTo(o.id);
  }
}
