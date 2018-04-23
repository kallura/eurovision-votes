package org.eurovision.command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.util.Comparator.comparing;
import static org.eurovision.util.Utils.MAPPER;
import static org.eurovision.util.Utils.getCountryPointsFile;

import org.eurovision.model.CountryPoints;
import org.eurovision.model.Vote;

/**
 * @author Kseniia Orlenko
 * @version 4/21/18
 */
public class LoadCommand implements Command {

    public String getCode() {

        return "load";
    }

    public void run(String... args) throws IOException {

        if (args.length != 2) {
            throw new IllegalArgumentException("invalid number of arguments; "
                    + "expected 2 arg: <file> <year>; actual number is " + args.length +
                    " : [" + Arrays.toString(args) + "]");
        }
        _run(args[0], args[1]);
    }

    private void _run(String file, String year) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        // build aggregated votes from file
        Map<Vote, Integer> votes = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                Vote vote = MAPPER.readValue(line.trim(), Vote.class);
                votes.compute(vote, (key, val) -> val == null ? 1 : val + 1);
            }
        }

        // build country points index from aggregated votes
        Map<String, SortedSet<CountryPoints>> index = new HashMap<>();

        for (Map.Entry<Vote, Integer> entry : votes.entrySet()) {
            Vote vote = entry.getKey();
            index.computeIfAbsent(vote.getCountry(),
                    (key) -> new TreeSet<>(comparing(CountryPoints::getPoints))).
                    add(new CountryPoints(vote.getVotedFor(), entry.getValue()));
        }

        // persist country points index to FS
        MAPPER.writeValue(getCountryPointsFile(year), index);
    }
}
