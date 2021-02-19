package com.conference.my.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
  private static CommandContainer container;
  private static final Map<String, Command> commands = new TreeMap<>();
  private static final Logger LOGGER = LogManager.getLogger(CommandContainer.class);


  static {
    commands.put("noCommand", new NoCommand());
    commands.put("login", new LoginCommand());
    commands.put("logOut", new LogoutCommand());
    commands.put("loginPage", new LoginPageCommand());
    commands.put("eventDetails", new EventDetailsCommand());
    commands.put("register", new RegisterCommand());
    commands.put("newUser", new NewUserCommand());
    commands.put("settingsUser", new SettingsUserCommand());
    commands.put("updateUser", new UpdateUserCommand());

    commands.put("registerUserForEvent", new RegisterUserForEventCommand());
    commands.put("visitEvent", new VisitEventCommand());
    commands.put("navEvents", new NavEventsCommand());


    LOGGER.debug("Command container was successfully initialized");
  }

  private CommandContainer() {};

  public static CommandContainer getContainer() {
    if (container == null)
      container = new CommandContainer();
    return container;
  }

  public Command get(String commandName) {
    if (!commands.containsKey(commandName)) {
      LOGGER.trace("Command not found for name --> " + commandName);
      return commands.get("noCommand");
    }

    return commands.get(commandName);
  }

}
