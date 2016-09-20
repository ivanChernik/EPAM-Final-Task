package by.epam.tc.hr_system.controller.helper;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import by.epam.tc.hr_system.command.ICommand;
import by.epam.tc.hr_system.command.impl.AddEducationToResumeCommand;
import by.epam.tc.hr_system.command.impl.AddPreviousPositionToResumeCommand;
import by.epam.tc.hr_system.command.impl.ApplyForJobCommand;
import by.epam.tc.hr_system.command.impl.AuthorizationCommand;
import by.epam.tc.hr_system.command.impl.ChangeLocalCommand;
import by.epam.tc.hr_system.command.impl.ChangeResponceStatusCommand;
import by.epam.tc.hr_system.command.impl.CheckAffiliationVacancyToHRCommand;
import by.epam.tc.hr_system.command.impl.CreateResumeCommand;
import by.epam.tc.hr_system.command.impl.CreateVacancyCommand;
import by.epam.tc.hr_system.command.impl.DeleteResumeCommand;
import by.epam.tc.hr_system.command.impl.DeleteVacancyCommand;
import by.epam.tc.hr_system.command.impl.LogOutCommand;
import by.epam.tc.hr_system.command.impl.RegistrationCommand;
import by.epam.tc.hr_system.command.impl.SearchResumeCommand;
import by.epam.tc.hr_system.command.impl.SearchVacancyCommand;
import by.epam.tc.hr_system.command.impl.ShowApplicantResponceCommand;
import by.epam.tc.hr_system.command.impl.ShowHRVacancyCommand;
import by.epam.tc.hr_system.command.impl.ShowResponceForVacancy;
import by.epam.tc.hr_system.command.impl.ShowResumeCommand;
import by.epam.tc.hr_system.command.impl.ShowTopVacancies;
import by.epam.tc.hr_system.command.impl.ShowVacancyCommand;
import by.epam.tc.hr_system.command.impl.ShowVacancyToUpdateCommand;
import by.epam.tc.hr_system.command.impl.UpdateProfileCommand;
import by.epam.tc.hr_system.command.impl.UpdateVacancyCommand;
import by.epam.tc.hr_system.exception.CommandException;

public class CommandHelper {
	private static final CommandHelper instance = new CommandHelper();
	private Map<CommandName, ICommand> commands = new HashMap<>();
	private static final Logger log = Logger.getLogger(CommandHelper.class);
	
	private CommandHelper(){
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.CHANGE_LOCAL, new ChangeLocalCommand());
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.CREATE_VACANCY, new CreateVacancyCommand());
		commands.put(CommandName.CREATE_RESUME, new CreateResumeCommand());
		commands.put(CommandName.SHOW_TOP_VACANCIES, new ShowTopVacancies());
		commands.put(CommandName.SHOW_VACANCY, new ShowVacancyCommand());
		commands.put(CommandName.RESPONCE_TO_VACANCY, new ApplyForJobCommand());
		commands.put(CommandName.SHOW_RESPONCE, new ShowApplicantResponceCommand());
		commands.put(CommandName.LOG_OUT, new LogOutCommand());
		commands.put(CommandName.SHOW_RESUME, new ShowResumeCommand());
		commands.put(CommandName.SHOW_HR_VACANCY, new ShowHRVacancyCommand());
		commands.put(CommandName.SHOW_RESPONCE_TO_VACANCY, new ShowResponceForVacancy());
		commands.put(CommandName.CHANGE_RESPONCE_STATUS, new ChangeResponceStatusCommand());
		commands.put(CommandName.DELETE_VACANCY, new DeleteVacancyCommand());
		commands.put(CommandName.SHOW_VACANCY_TO_UPDATE, new ShowVacancyToUpdateCommand());
		commands.put(CommandName.CHECK_AFFILIATION_VACANCY_TO_HR, new CheckAffiliationVacancyToHRCommand());
		commands.put(CommandName.UPDATE_VACANCY, new UpdateVacancyCommand());
		commands.put(CommandName.UPDATE_PROFILE, new UpdateProfileCommand());
		commands.put(CommandName.ADD_EDUCATION, new AddEducationToResumeCommand());
		commands.put(CommandName.ADD_PREVIOUS_POSITION, new AddPreviousPositionToResumeCommand());
		commands.put(CommandName.SEARCH_VACANCY, new SearchVacancyCommand());
		commands.put(CommandName.SEARCH_RESUME, new SearchResumeCommand());
		commands.put(CommandName.DELETE_RESUME, new DeleteResumeCommand());
	}
	
	public ICommand getCommand(String name) throws CommandException {
		
		if(name == null || name.isEmpty()){
			log.error("Error in command name");
			throw new CommandException("Error in command name"); 
		}
		
		name = name.replace('-', '_');
		
		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		ICommand command = commands.get(commandName);

		return command;
	}
	
	public static CommandHelper getInstance() {
		return instance;
	}

}
