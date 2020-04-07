package ua.nure.moisieiev.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.web.command.crewCommands.CrewDeleteCommand;
import ua.nure.moisieiev.summaryTask4.web.command.crewCommands.CrewListCommand;
import ua.nure.moisieiev.summaryTask4.web.command.flightCommands.FlightEditCommand;
import ua.nure.moisieiev.summaryTask4.web.command.flightCommands.FlightListCommand;
import ua.nure.moisieiev.summaryTask4.web.command.flightCommands.FlightDeleteCommand;
import ua.nure.moisieiev.summaryTask4.web.command.flightCommands.FlightSaveCommand;
import ua.nure.moisieiev.summaryTask4.web.command.staffCommands.StaffDeleteCommand;
import ua.nure.moisieiev.summaryTask4.web.command.staffCommands.StaffEditCommand;
import ua.nure.moisieiev.summaryTask4.web.command.staffCommands.StaffListCommand;
import ua.nure.moisieiev.summaryTask4.web.command.staffCommands.StaffSaveCommand;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("flightList", new FlightListCommand());
        commands.put("staffList", new StaffListCommand());
        commands.put("crewList", new CrewListCommand());
        //commands.put("viewSettings", new ViewSettingsCommand());
        //commands.put("noCommand", new NoCommand());

        // dispatcher commands
        commands.put("deleteCrew", new CrewDeleteCommand());
       // commands.put("editCrew", new CrewEditCommand());


        // admin commands
        commands.put("deleteFlight", new FlightDeleteCommand());
        commands.put("editFlight", new FlightEditCommand());
        commands.put("saveFlight", new FlightSaveCommand());
        commands.put("deleteStaff", new StaffDeleteCommand());
        commands.put("editStaff", new StaffEditCommand());
        commands.put("saveStaff", new StaffSaveCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}
