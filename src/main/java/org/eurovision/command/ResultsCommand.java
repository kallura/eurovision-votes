package org.eurovision.command;

import static org.eurovision.util.Utils.MAPPER;
import static org.eurovision.util.Utils.getCountryPointsFile;

import com.fasterxml.jackson.core.type.TypeReference;
import org.eurovision.model.CountryPoints;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Kseniia Orlenko
 * @version 4/21/18
 */
public class ResultsCommand implements Command {

    @Override
    public String getCode() {
        return "results";
    }

    @Override
    public void run(String... args) throws Exception {

        if (args.length != 2) {
            throw new IllegalArgumentException("invalid number of arguments; "
                    + "expected 2 arg: <country> <year>; actual number is " + args.length
                    + " : [" + Arrays.toString(args) + "]");
        }

        _run(args[0], args[1]);
    }

    private void _run(String country, String year) throws IOException {

        File indexFile = getCountryPointsFile(year);

        if (!indexFile.isFile()) {
            throw new RuntimeException("results file not found: " + indexFile.getPath()
                    + "; please load votes for " + year + " year.");
        }

        TypeReference<HashMap<String, ArrayList<CountryPoints>>> typeRef =
                new TypeReference<HashMap<String, ArrayList<CountryPoints>>>() {
                };

        HashMap<String, ArrayList<CountryPoints>> index = MAPPER.readValue(indexFile, typeRef);

        System.out.println(country + " " + year + " voting results:");

        ArrayList<CountryPoints> countryPoints = index.get(country);

        if (countryPoints == null) {
            System.out.println("-- NONE --");
        } else {
            for (CountryPoints cp : countryPoints) {
                System.out.println(formatCountryPoints(cp));
            }
        }

    }

    private static String formatCountryPoints(CountryPoints cp) {
        int points = cp.getPoints();
        return points + (points == 1 ? " point goes to " : " points go to ") + cp.getCountry();
    }
}
