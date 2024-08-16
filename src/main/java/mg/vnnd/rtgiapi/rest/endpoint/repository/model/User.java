package mg.vnnd.rtgiapi.rest.endpoint.repository.model;

import static jakarta.persistence.EnumType.STRING;
import static org.hibernate.type.SqlTypes.NAMED_ENUM;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mg.vnnd.rtgiapi.endpoint.rest.model.UserTypeEnum;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.security.core.GrantedAuthority;

@Table(name = "\"user\"")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id private String id;
  private String name;
  private String email;
  private LocalDate birthdate;
  private String encodedPassword;

  @JdbcTypeCode(NAMED_ENUM)
  @Enumerated(STRING)
  private UserTypeEnum type;

  public enum UserTypeEnum implements GrantedAuthority {
    COMMON,
    GREEN_REPRESENTATIVE;

    public static UserTypeEnum fromRest(mg.vnnd.rtgiapi.endpoint.rest.model.UserTypeEnum rest) {
      return switch (rest) {
        case COMMON -> COMMON;
        case GREEN_REPRESENTATIVE -> GREEN_REPRESENTATIVE;
      };
    }

    public mg.vnnd.rtgiapi.endpoint.rest.model.UserTypeEnum toRest() {
      return switch (this) {
        case GREEN_REPRESENTATIVE ->
            mg.vnnd.rtgiapi.endpoint.rest.model.UserTypeEnum.GREEN_REPRESENTATIVE;
        case COMMON -> mg.vnnd.rtgiapi.endpoint.rest.model.UserTypeEnum.COMMON;
      };
    }

    public String getRole() {
      return name();
    }

    @Override
    public String getAuthority() {
      return "ROLE_" + getRole();
    }
  }
}
