package ua.nure.moisieiev.summaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.moisieiev.summaryTask4.web.command.crewCommands.*;
import ua.nure.moisieiev.summaryTask4.web.command.flightCommands.*;
import ua.nure.moisieiev.summaryTask4.web.command.requestCommands.RequestAddCommand;
import ua.nure.moisieiev.summaryTask4.web.command.requestCommands.RequestChangeStatusCommand;
import ua.nure.moisieiev.summaryTask4.web.command.requestCommands.RequestEditCommand;
import ua.nure.moisieiev.summaryTask4.web.command.requestCommands.RequestListCommand;
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
        commands.put("noCommand", new NoCommand());
        commands.put("flightList", new FlightListCommand());
        commands.put("staffList", new StaffListCommand());
        commands.put("crewList", new CrewListCommand());
        commands.put("editFlight", new FlightEditCommand());
        commands.put("searchFlights", new FlightSearchCommand());
        commands.put("selectionFlights", new FlightSelectionCommand());
        commands.put("requestList", new RequestListCommand());
        //commands.put("viewSettings", new ViewSettingsCommand());
        //commands.put("noCommand", new NoCommand());

        // dispatcher commands
        commands.put("deleteCrew", new CrewDeleteCommand());
        commands.put("addCrew", new CrewAddStaffCommand());
        commands.put("saveCrew", new СrewSaveCommand());
        commands.put("editCrew", new CrewEditCommand());
        commands.put("saveFlightStatus", new FlightSaveFlightStatusCommand());
        commands.put("addRequest", new RequestAddCommand());


        // admin commands
        commands.put("deleteFlight", new FlightDeleteCommand());
        commands.put("saveFlight", new FlightSaveCommand());
        commands.put("deleteStaff", new StaffDeleteCommand());
        commands.put("editStaff", new StaffEditCommand());
        commands.put("saveStaff", new StaffSaveCommand());
        commands.put("editRequest", new RequestEditCommand());
        commands.put("saveRequestStatus", new RequestChangeStatusCommand());

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
