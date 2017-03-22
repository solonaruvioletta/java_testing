package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by user on 21.03.2017.
 */

public class ResetPasswordTests extends TestBase{

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }


  @Test
  public void testResetPassword() throws IOException, MessagingException {

    app.admin().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.admin().selectUser();
    User user = app.admin().userFromEditForm();
    app.admin().resetUserPassword();


    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);


    long now = System.currentTimeMillis();
    String newPassword = "new_password" + now;


    String confirmationLink = findConfirmationLink(mailMessages, User.getEmail());
    app.registration().finish(confirmationLink, newPassword);

    assertTrue(app.newSession().login(User.getUsername(), newPassword));
  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
