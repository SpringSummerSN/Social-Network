package spring.summer.socialnetwork.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.dto.UserDTO;
import spring.summer.socialnetwork.exceptions.EmailExistsException;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.models.VerificationToken;
import spring.summer.socialnetwork.repositories.UserRepository;

@Service
public class RegisterService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private JavaMailSender javaMailSender;

    private TokenService tokenService;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
        this.tokenService = tokenService;
    }


    private void register_validate(UserDTO userDTO) throws EmailExistsException {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            System.out.println("Test");
            throw new EmailExistsException();
        }

    }


    public String register(UserDTO userDTO) throws EmailExistsException, MessagingException {
        register_validate(userDTO);
        var user = User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .surname(userDTO.getSurname())
                .name(userDTO.getName())
                .build();


        userRepository.save(user);

        VerificationToken verificationToken = tokenService.createTokenforUser(user);
        String confirmation_text =
                "<!doctype html>\n" +
                        "<html>\n" +
                        "  <head>\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "    <title>Simple Transactional Email</title>\n" +
                        "    <style>\n" +
                        "@media only screen and (max-width: 620px) {\n" +
                        "  table.body h1 {\n" +
                        "    font-size: 28px !important;\n" +
                        "    margin-bottom: 10px !important;\n" +
                        "  }\n" +
                        "\n" +
                        "  table.body p,\n" +
                        "table.body ul,\n" +
                        "table.body ol,\n" +
                        "table.body td,\n" +
                        "table.body span,\n" +
                        "table.body a {\n" +
                        "    font-size: 16px !important;\n" +
                        "  }\n" +
                        "\n" +
                        "  table.body .wrapper,\n" +
                        "table.body .article {\n" +
                        "    padding: 10px !important;\n" +
                        "  }\n" +
                        "\n" +
                        "  table.body .content {\n" +
                        "    padding: 0 !important;\n" +
                        "  }\n" +
                        "\n" +
                        "  table.body .container {\n" +
                        "    padding: 0 !important;\n" +
                        "    width: 100% !important;\n" +
                        "  }\n" +
                        "\n" +
                        "  table.body .main {\n" +
                        "    border-left-width: 0 !important;\n" +
                        "    border-radius: 0 !important;\n" +
                        "    border-right-width: 0 !important;\n" +
                        "  }\n" +
                        "\n" +
                        "  table.body .btn table {\n" +
                        "    width: 100% !important;\n" +
                        "  }\n" +
                        "\n" +
                        "  table.body .btn a {\n" +
                        "    width: 100% !important;\n" +
                        "  }\n" +
                        "\n" +
                        "  table.body .img-responsive {\n" +
                        "    height: auto !important;\n" +
                        "    max-width: 100% !important;\n" +
                        "    width: auto !important;\n" +
                        "  }\n" +
                        "}\n" +
                        "@media all {\n" +
                        "  .ExternalClass {\n" +
                        "    width: 100%;\n" +
                        "  }\n" +
                        "\n" +
                        "  .ExternalClass,\n" +
                        ".ExternalClass p,\n" +
                        ".ExternalClass span,\n" +
                        ".ExternalClass font,\n" +
                        ".ExternalClass td,\n" +
                        ".ExternalClass div {\n" +
                        "    line-height: 100%;\n" +
                        "  }\n" +
                        "\n" +
                        "  .apple-link a {\n" +
                        "    color: inherit !important;\n" +
                        "    font-family: inherit !important;\n" +
                        "    font-size: inherit !important;\n" +
                        "    font-weight: inherit !important;\n" +
                        "    line-height: inherit !important;\n" +
                        "    text-decoration: none !important;\n" +
                        "  }\n" +
                        "\n" +
                        "  #MessageViewBody a {\n" +
                        "    color: inherit;\n" +
                        "    text-decoration: none;\n" +
                        "    font-size: inherit;\n" +
                        "    font-family: inherit;\n" +
                        "    font-weight: inherit;\n" +
                        "    line-height: inherit;\n" +
                        "  }\n" +
                        "\n" +
                        "  .btn-primary table td:hover {\n" +
                        "    background-color: #34495e !important;\n" +
                        "  }\n" +
                        "\n" +
                        "  .btn-primary a:hover {\n" +
                        "    background-color: #34495e !important;\n" +
                        "    border-color: #34495e !important;\n" +
                        "  }\n" +
                        "}\n" +
                        "</style>\n" +
                        "  </head>\n" +
                        "  <body style=\"background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\">\n" +
                        "\n" +
                        "            <!-- START CENTERED WHITE CONTAINER -->\n" +
                        "            <table role=\"presentation\" class=\"main\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background: #ffffff; border-radius: 3px; width: 100%;\" width=\"100%\">\n" +
                        "\n" +
                        "              <!-- START MAIN CONTENT AREA -->\n" +
                        "              <tr>\n" +
                        "                <td class=\"wrapper\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;\" valign=\"top\">\n" +
                        "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\" width=\"100%\">\n" +
                        "                    <tr>\n" +
                        "                      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\" valign=\"top\">\n" +
                        "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;\">" +
                        "Hi, " + userDTO.getName() +
                        "</p>\n" +
                        "                        <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; margin-bottom: 15px;\">" +
                        "To confirm your account, click here</p>\n" +
                        "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; box-sizing: border-box; width: 100%;\" width=\"100%\">\n" +
                        "                          <tbody>\n" +
                        "                            <tr>\n" +
                        "                              <td align=\"left\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;\" valign=\"top\">\n" +
                        "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;\">\n" +
                        "                                  <tbody>\n" +
                        "                                    <tr>\n" +
                        "                                      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; border-radius: 5px; text-align: center; background-color: #3498db;\" valign=\"top\" align=\"center\" bgcolor=\"#3498db\"> " +
                        "<a href="
                        + "http://localhost:8080/registrationConfirm?token="
                        + verificationToken.getToken()
                        + " target=\"_blank\" style=\"border: solid 1px #3498db; border-radius: 5px; box-sizing: border-box; cursor: pointer; display: inline-block; font-size: 14px; font-weight: bold; margin: 0; padding: 12px 25px; text-decoration: none; text-transform: capitalize; background-color: #3498db; border-color: #3498db; color: #ffffff;\">" +
                        "Confirm email</a> </td>\n" +
                        "                                    </tr>\n" +
                        "                                  </tbody>\n" +
                        "                                </table>\n" +
                        "                              </td>\n" +
                        "                            </tr>\n" +
                        "                          </tbody>\n" +
                        "                        </table>\n" +
                        "                      </td>\n" +
                        "                    </tr>\n" +
                        "                  </table>\n" +
                        "                </td>\n" +
                        "              </tr>\n" +
                        "\n" +
                        "            <!-- END MAIN CONTENT AREA -->\n" +
                        "            </table>\n" +
                        "            <!-- END CENTERED WHITE CONTAINER -->\n" +
                        "\n" +
                        "          </div>\n" +
                        "        </td>\n" +
                        "        <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\" valign=\"top\">&nbsp;</td>\n" +
                        "      </tr>\n" +
                        "    </table>\n" +
                        "  </body>\n" +
                        "</html>";


        send_confirm_email(userDTO.getEmail(), confirmation_text);

        return "Registration succeed";

    }

    private void send_confirm_email(String to, String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setTo(to);
        helper.setSubject("Complete registration!");
        message.setContent(text, "text/html");

        javaMailSender.send(message);

    }


}
