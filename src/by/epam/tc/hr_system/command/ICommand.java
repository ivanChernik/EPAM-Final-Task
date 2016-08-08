package by.epam.tc.hr_system.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.tc.hr_system.exception.CommandException;

public interface ICommand {
	public void execute(HttpServletRequest request, HttpServletResponse response)  throws CommandException ;

}
