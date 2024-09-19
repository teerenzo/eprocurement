package eprocument.core.service.mail;
import eprocument.common.user.EmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final SpringTemplateEngine templateEngine;
    private final JavaMailSender mailSender;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.routing.exchangeName}")
    private String exchange;

    @Value("${rabbitmq.routing.keyName}")
    private String routingKey;

    @Async
    public void sendEmail(String to, String username, String emailTemplateName, String confirmationUrl,
                          String activationCode, String subject) throws MessagingException
    {
        String templateName;
        if (emailTemplateName == null) {
            templateName = "activate_account";
        } else {
            templateName = emailTemplateName;
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED,
                "UTF-8");

        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activation_code", activationCode);

        Context context = new Context(Locale.US, properties);

        helper.setFrom("teerenzo.co@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        String template = templateEngine.process(templateName, context);
        helper.setText(template, true);

        mailSender.send(mimeMessage);

    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void processEmailMessage(EmailDto emailDetailDTO) throws MessagingException {
        System.out.println("Received message: " + emailDetailDTO);
        sendEmail(emailDetailDTO.getTo(), emailDetailDTO.getUsername(), emailDetailDTO.getEmailTemplateName(),
                emailDetailDTO.getConfirmationUrl(), emailDetailDTO.getActivationCode(), emailDetailDTO.getSubject());
    }

    public void sendEmailToRabbit(String to, String username, String emailTemplateName, String confirmationUrl,
                          String activationCode, String subject)
    {

        EmailDto emailDetailDTO = EmailDto.builder()
                .to(to)
                .username(username)
                .emailTemplateName(emailTemplateName)
                .confirmationUrl(confirmationUrl)
                .activationCode(activationCode)
                .subject(subject)
                .build();

        rabbitTemplate.convertAndSend(exchange,routingKey,emailDetailDTO);



    }
}
