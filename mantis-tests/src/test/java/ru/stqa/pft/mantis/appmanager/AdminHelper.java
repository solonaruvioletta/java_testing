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
    click(By.xpath("//a[contains(.,'управление')]"));
    click(By.xpath("//a[contains(.,'Управление пользователями')]"));
    List<WebElement> users = wd.findElements(By.xpath("//a[contains(@href,'manage_user_edit_page.php')]"));
    WebElement selectedUser = users.stream().filter((u) -> !(u.getText().equals(app.getProperty("web.adminLogin"))))
            .findAny().get();
    selectedUser.click();
  }

  public User userFromEditForm() {
    String name = wd.findElement(By.name("username")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    return new User().withUsername(name).withEmail(email);
  }

  public void resetUserPassword() {
    click(By.xpath("//input[@value='Сбросить пароль']"));
  }
}
