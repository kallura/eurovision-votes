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
import java.util.List;

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
            if (countryPoints.size() < 10) {
                throw new IllegalArgumentException("Can't get the results due to incorrect "
                        + "loaded a data set for the country : [" + country + "]"
                        + ". Please reload the data set.");
            }
            List<CountryPoints> winners = countryPoints.subList(0, 10);
            displayResults(winners);
        }

    }

    private void displayResults(List<CountryPoints> winners) {
        displayResult(winners.get(0).getCountry(), 1);
        displayResult(winners.get(1).getCountry(), 2);
        displayResult(winners.get(2).getCountry(), 3);
        displayResult(winners.get(3).getCountry(), 4);
        displayResult(winners.get(4).getCountry(), 5);
        displayResult(winners.get(5).getCountry(), 6);
        displayResult(winners.get(6).getCountry(), 7);
        displayResult(winners.get(7).getCountry(), 8);
        displayResult(winners.get(8).getCountry(), 10);
        displayResult(winners.get(9).getCountry(), 12);
    }

    private static void displayResult(String country, int points) {
        System.out.println(points + (points == 1 ? " point goes to " : " points go to ") + country);
    }
}
