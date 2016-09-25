package by.epam.tc.hr_system.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.hr_system.exception.CommandException;

/**
 * 
 * interface for all commands
 * 
 * @author Ivan Chernikau
 * 
 *
 */
public interface ICommand {
	/**
	 * method for execution particular logic in command
	 * @param request 
	 * @param response 
	 * @throws CommandException
	 */
	
	public void execute(HttpServletRequest request, HttpServletResponse response)  throws CommandException ;

}
