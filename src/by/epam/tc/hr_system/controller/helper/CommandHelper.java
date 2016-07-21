package by.epam.tc.hr_system.controller.helper;

import java.util.HashMap;
import java.util.Map;

import by.epam.tc.hr_system.command.ICommand;

public class CommandHelper {
	private static final CommandHelper instance = new CommandHelper();
	private Map<CommandName, ICommand> commands = new HashMap<>();
	
	private CommandHelper(){
		
	}

}
