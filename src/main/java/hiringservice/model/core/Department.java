package hiringservice.model.core;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/** Project description.
 * @author kevinngo
 */

@Entity
@Table(name = "department")
public class Department implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private int id;

  @Column(unique = true)
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
