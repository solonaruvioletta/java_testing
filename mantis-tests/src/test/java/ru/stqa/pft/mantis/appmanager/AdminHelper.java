package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.User;
import java.util.List;

/**
 * Created by user on 21.03.2017.
 */

public class AdminHelper extends HelperBase {

  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Войти']"));
  }

  public void selectUser() {
    //click(By.xpath("//button[@id='menu-toggler']"));
    //click(By.linkText("['управление']"));
    //click(By.linkText("Управление пользователями"));
   // wd.findElement(By.cssSelector("input[type = 'submit']")).click();   //By.xpath(".//*[@id='resetUserPassword-form']/fieldset/input[2]")).click();
    wd.findElement(By.xpath(".//div[@id='sidebar']/ul/li[6]/a/i")).click();
    wd.findElement(By.xpath(".//*[@id='main-container']/div[2]/div[2]/div/ul/li[2]/a")).click();

    List<WebElement> users = wd.findElements(By.xpath("//a[contains(@href,'manage_user_edit_page.php')]"));
    WebElement user = users.stream().filter((u) -> !(u.getText().equals(app.getProperty("web.adminLogin"))))
            .findAny().get();

  }

  public User userFromEditForm() {
    String name = wd.findElement(By.name("Пользователь")).getAttribute("value");
    String email = wd.findElement(By.name("E-mail")).getAttribute("value");
    return new User().withUsername(name).withEmail(email);
  }

  public void resetUserPassword() {
    click(By.xpath("//input[@value='Сбросить пароль']"));
  }
}
