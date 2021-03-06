package emailservice;

import java.net.MalformedURLException;
import java.net.URL;
import model.entidade.Colaborador;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import util.ValidatorUtil;

public class EmailUtils {

    private static final String HOSTNAME = "smtp.gmail.com";
    private static final String USERNAME = "Teste";
    private static final String PASSWORD = "#Wt5y3w88";
    private static final String EMAILORIGEM = "sghsuporte@gmail.com";

    private static Email conectaEmail() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(HOSTNAME);
        email.setSmtpPort(587);
        email.setAuthentication(EMAILORIGEM, PASSWORD);
        email.setTLS(true);
        email.setFrom(EMAILORIGEM);
        return email;
    }

    public static void enviaEmail(Mensagem mensagem) throws EmailException {
        if (ValidatorUtil.validarEmail(mensagem.getDestino())) {
            Email email = new SimpleEmail();
            email = conectaEmail();
            email.setSubject(mensagem.getTitulo());
            email.setMsg(mensagem.getMensagem());
            email.addTo(mensagem.getDestino());
            String resposta = email.send();
            System.out.println("Resposta " + resposta);
        }

    }

    public static void enviarHtmlEmail(Mensagem mensagem) throws EmailException, MalformedURLException {
        if (ValidatorUtil.validarEmail(mensagem.getDestino())) {
            ImageHtmlEmail htmlEmail = new ImageHtmlEmail();
            htmlEmail.setHostName(HOSTNAME);
            htmlEmail.setSmtpPort(587);
            htmlEmail.setAuthentication(EMAILORIGEM, PASSWORD);
            htmlEmail.setTLS(true);
            htmlEmail.setFrom(EMAILORIGEM);
            htmlEmail.setSubject(mensagem.getTitulo());
            htmlEmail.setMsg(mensagem.getMensagem());
            htmlEmail.addTo(mensagem.getDestino());
            String htmlEmailTemplate = ".... <img src=\"http://niechwiedza.pl/upload/avatar_user/135941068574bdd35043c74328996329174c58c4f5sgh-logo.png\">....";
            URL url = new URL("http://www.google.com");
            htmlEmail.setDataSourceResolver(new DataSourceUrlResolver(url));
            htmlEmail.setHtmlMsg(htmlEmailTemplate);
            htmlEmail.setTextMsg("n??o suporta html");
            System.out.println(htmlEmail.send());
        }
    }

    public static void enviaEmailRecuperacaoSenha(Colaborador colaborador, String senhaGerada) throws EmailException {
        if (colaborador != null) {
            ImageHtmlEmail htmlEmail = new ImageHtmlEmail();
            htmlEmail.setHostName(HOSTNAME);
            htmlEmail.setSmtpPort(587);
            htmlEmail.setAuthentication(EMAILORIGEM, PASSWORD);
            htmlEmail.setTLS(true);
            htmlEmail.setFrom(EMAILORIGEM);

            Mensagem mensagem = new Mensagem();
            mensagem.setTitulo("Recupera????o de Senha");
            mensagem.setDestino(colaborador.getEmailComercial());
            htmlEmail.setSubject(mensagem.getTitulo());
            htmlEmail.addTo(mensagem.getDestino());
            
            String htmlEmailTemplate = "<html>"
                    + "Ol?? Sr.(a) " + colaborador.getPessoa().getNome() + ",<br/>"
                    + "Recebemos uma solicita????o de recupera????o de senha. Por medidades de seguran??a, geramos uma nova senha para voc??.<br/>"
                    + "Sua nova senha ??: <strong>" + senhaGerada + "</strong>.<br/><br/>"
                    + "Lembre-se: voc?? pode mudar sua senha, na se????o 'Meu Perfil'.<br/><br/>"
                    + "Qualquer d??vida, voc?? pode nos contatar no telefone abaixo ou atrav??s deste e-mail. Estamos ?? sua disposi????o!<br/>"
                    + "Abra??os e tenha um bom dia!<br/><br/>"
                    + "Atenciosamente, <br/>"
                    + "<strong>Equipe de Suporte SGH</strong><br/>"
                    + "Blumenau - Santa Catarina - Brasil<br/>"
                    + "Rua Ant??nio da Veiga, 1254, Victor Konder<br/>"
                    + "Telefone: +55 47 3329-2323<br/>"
                    + "<strong><em>SGH - Sistema de Gest??o de Hor??rios</em></strong>";
            
//            URL url = new URL("http://www.google.com");
//            htmlEmail.setDataSourceResolver(new DataSourceUrlResolver(url));
            htmlEmail.setHtmlMsg(htmlEmailTemplate);
            System.out.println(htmlEmail.send());
        }
    }
}
