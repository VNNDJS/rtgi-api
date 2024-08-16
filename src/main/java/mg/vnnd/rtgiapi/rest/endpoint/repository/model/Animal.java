package mg.vnnd.rtgiapi.rest.endpoint.repository.model;

import static jakarta.persistence.EnumType.STRING;
import static org.hibernate.type.SqlTypes.NAMED_ENUM;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mg.vnnd.rtgiapi.endpoint.rest.model.AnimalGender;
import org.hibernate.annotations.JdbcTypeCode;

@Table(name = "\"animal\"")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
  @Id private String id;
  private String name;
  private boolean isEndemic;
  private String greenSpaceId;
  private int number;

  @JdbcTypeCode(NAMED_ENUM)
  @Enumerated(STRING)
  private AnimalGender gender;
}
