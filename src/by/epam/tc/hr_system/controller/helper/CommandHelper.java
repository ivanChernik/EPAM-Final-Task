package by.epam.tc.hr_system.controller.helper;

import java.util.HashMap;
import java.util.Map;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.command.impl.RegistrationCommand;

public class CommandHelper {
	private static final CommandHelper instance = new CommandHelper();
	private Map<CommandName, ICommand> commands = new HashMap<>();
	
	private CommandHelper(){
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
	}
	
	public ICommand getCommand(String name) {
		name = name.replace('-', '_');
		
		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		ICommand command = commands.get(commandName);

		return command;
	}
	
	public static CommandHelper getInstance() {
		return instance;
	}

}
