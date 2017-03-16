package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by user on 16.03.2017.
 */
public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration() {
    app.registration().start("user1", "user1@localhost.localdomain");

  }
}
