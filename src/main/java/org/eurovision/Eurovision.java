package org.eurovision;

import java.util.Map;

import static java.util.Arrays.copyOfRange;
import static org.eurovision.util.Utils.mapCommandsByCode;

import org.eurovision.command.Command;
import org.eurovision.command.LoadCommand;
import org.eurovision.command.ResultsCommand;

/**
 * @author Kseniia Orlenko
 * @version 4/21/18
 */
public class Eurovision {

    private static final Map<String, Command> commandByCode = mapCommandsByCode(
            new LoadCommand(),
            new ResultsCommand());

    public static void main(String[] args) {
        try {
            _main(args);
        } catch (Exception e) {
            System.err.println("ERR: " + e.getMessage());
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    private static void _main(String[] args) throws Exception {

        if (args.length < 3) {
            throw new IllegalArgumentException("Invalid number of arguments; "
                    + "at least 3 arguments are expected: <command> <arg1> <arg2> [...<argN>]");
        }

        final String commandCode = args[0];

        Command command = commandByCode.get(commandCode);
        if (command == null) {
            throw new IllegalArgumentException(
                    commandCode + " command to found; valid commands: " + commandByCode.keySet());
        }

        String[] commandArgs = copyOfRange(args, 1, args.length);

        command.run(commandArgs);
    }
}
