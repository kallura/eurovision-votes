package org.eurovision.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eurovision.command.Command;

import java.io.File;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * @author Kseniia Orlenko
 * @version 4/21/18
 */
public class Utils {

    public final static ObjectMapper MAPPER = new ObjectMapper();

    private final static String COUNTRY_POINTS_FILENAME_FORMAT = "country-points-index-%s.json";

    static {
        MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    public static Map<String, Command> mapCommandsByCode(Command... commands) {
        return Stream.of(commands).collect(toMap(Command::getCode, identity()));
    }

    public static File getCountryPointsFile(String year) {
        return new File(format(COUNTRY_POINTS_FILENAME_FORMAT, year));
    }
}
