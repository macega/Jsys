package br.sql.util;

import br.JavaApplicationJsys;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Juliano Alves Medina
 */
public class Mail {

    public Mail() {
        emailJava = new MailJava();
        map = new HashMap<>();
        map.clear();
    }

    private final MailJava emailJava;
    private final Map<String, String> map;

    private void setMailJava() {
        //configuracoes de envio
        emailJava.setSmtpHostMail(JavaApplicationJsys.INI.getString("EMAIL", "HOST"));
        emailJava.setSmtpPortMail(JavaApplicationJsys.INI.getString("EMAIL", "PORTA"));
        emailJava.setSmtpAuth(JavaApplicationJsys.INI.getString("EMAIL", "AUTH"));
        emailJava.setSmtpStarttls(JavaApplicationJsys.INI.getString("EMAIL", "STARTTLS"));
        emailJava.setUserMail(JavaApplicationJsys.INI.getString("EMAIL", "USER"));
        emailJava.setFromNameMail(JavaApplicationJsys.INI.getString("EMAIL", "NOME_EMAIL"));
        emailJava.setPassMail(JavaApplicationJsys.INI.getString("EMAIL", "SENHA"));
        emailJava.setCharsetMail("ISO-8859-1");
        emailJava.setTypeTextMail(MailJava.TYPE_TEXT_HTML);
        emailJava.setToMailsUsers(map);
    }

    /**
     * sete quantos destinatarios desejar
     *
     * @param nome
     * @param email
     */
    public void setDestinatario(String nome, String email) {
        nome = Normalizer.normalize(nome, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        for (String s : email.split(",")) {
            map.put(s.trim(), nome);
        }
    }

    /**
     *
     * @param titulo
     * @param textHTML
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public void senderMail(String titulo, String textHTML) throws UnsupportedEncodingException, MessagingException {
        senderMail(titulo, textHTML, null);
    }

    /**
     *
     * @param titulo
     * @param textHTML
     * @param files
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public void senderMail(String titulo, String textHTML, List<String> files) throws UnsupportedEncodingException, MessagingException {
        setMailJava();
        emailJava.setSubjectMail(titulo);
        emailJava.setBodyMail(textHTML);

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", emailJava.getSmtpHostMail());
        props.setProperty("mail.smtp.auth", emailJava.getSmtpAuth());
        props.setProperty("mail.smtp.starttls.enable", emailJava.getSmtpStarttls());
        props.setProperty("mail.smtp.port", emailJava.getSmtpPortMail());
        props.setProperty("mail.mime.charset", emailJava.getCharsetMail());

        //classe anonima que realiza a autenticação
        //do usuario no servidor de email.
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailJava.getUserMail(), emailJava.getPassMail());
            }
        };

        // Cria a sessao passando as propriedades e a autenticação
        Session session = Session.getDefaultInstance(props, auth);

        // Gera um log no console referente ao processo de envio
        //session.setDebug(true);
        //cria a mensagem setando o remetente e seus destinatários
        Message msg = new MimeMessage(session);
        //aqui seta o remetente
        msg.setFrom(new InternetAddress(
                emailJava.getUserMail(), emailJava.getFromNameMail())
        );
        boolean first = true;
        for (Map.Entry<String, String> m : emailJava.getToMailsUsers().entrySet()) {
            if (first) {
                //setamos o 1° destinatario
                msg.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(m.getKey(), m.getValue())
                );
                first = false;
            } else {
                //setamos os demais destinatarios
                msg.addRecipient(Message.RecipientType.CC,
                        new InternetAddress(m.getKey(), m.getValue())
                );
            }
        }

        // Adiciona um Assunto a Mensagem
        msg.setSubject(emailJava.getSubjectMail());

        // Cria o objeto que recebe o texto do corpo do email
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(emailJava.getBodyMail(), emailJava.getTypeTextMail());

        // Monta a mensagem SMTP  inserindo o conteudo, texto e anexos
        Multipart mps = new MimeMultipart();
        if (files != null) {
            emailJava.setFileMails(files);
            for (String file : emailJava.getFileMails()) {
                // Cria um novo objeto para cada arquivo, e anexa o arquivo
                MimeBodyPart attachFilePart = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(
                        file
                );
                attachFilePart.setDataHandler(new DataHandler(fds));
                attachFilePart.setFileName(fds.getName());

                // adiciona os anexos da mensagem
                mps.addBodyPart(attachFilePart);
            }
            //        for (int index = 0; index < emailJava.getFileMails().size(); index++) {
//
//            // Cria um novo objeto para cada arquivo, e anexa o arquivo
//            MimeBodyPart attachFilePart = new MimeBodyPart();
//            FileDataSource fds = new FileDataSource(
//                    emailJava.getFileMails().get(index)
//            );
//            attachFilePart.setDataHandler(new DataHandler(fds));
//            attachFilePart.setFileName(fds.getName());
//
//            //adiciona os anexos da mensagem
//            mps.addBodyPart(attachFilePart, index);
//        }
        }

        //adiciona o corpo texto da mensagem
        mps.addBodyPart(textPart);

        //adiciona a mensagem o conteúdo texto e anexo
        msg.setContent(mps);

        // Envia a Mensagem
        Transport.send(msg);
    }

//    public static boolean Email(Date dt, String html) {
//        setMailJava();
//        emailJava.setToMailsUsers(map);
//        if (html == null) {
//            return false;
//        } else {
//            final String username = JavaApplicationJsys.INI.getString("EMAIL", "USER");
//            final String password = JavaApplicationJsys.INI.getString("EMAIL", "SENHA");
//            Properties props = new Properties();
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.host", "smtp.gmail.com");
//            props.put("mail.smtp.port", "587");
//            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(username, password);
//                }
//            });
//            try {
//                Message message = new MimeMessage(session);
//                Multipart mp = new MimeMultipart();
//                message.setFrom(new InternetAddress(JavaApplicationJsys.INI.getString("EMAIL", "DESTINO")));
//                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(JavaApplicationJsys.INI.getString("EMAIL", "DESTINO")));
//                message.setSubject("Caixa dia: " + ManagerData.convertDate(dt, "dd/MM/yyyy"));
//                /* anexar arquivos
//                 * byte[] attachmentData = null;
//                 * MimeBodyPart attachment = new MimeBodyPart();
//                 * attachment.setFileName("manual.pdf");
//                 * attachment.setContent(attachmentData, "application/pdf");
//                 * mp.addBodyPart(attachment);*/
//
//                MimeBodyPart htmlPart = new MimeBodyPart();
//                htmlPart.setContent(html, "text/html");
//                mp.addBodyPart(htmlPart);
//                //message.setText(mensagen);
//                message.setContent(mp);
//                Transport.send(message);
//                return true;
//            } catch (MessagingException e) {
//                salvaLog.registraErro("SendMailTLS", "Email", e);
//                return false;
//            }
//        }
//    }
}
