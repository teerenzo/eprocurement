package eprocument.common.user;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class EmailDto {

    private final String to;
    private final String username;
    private final String emailTemplateName;
    private final String confirmationUrl;
    private final String activationCode;
    private final String subject;
}
