package eprocument.common.user;

import ifmis.framework.common.dto.AbstractEntityDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class TokenDto extends AbstractEntityDto {

        private String token;
}
