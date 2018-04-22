package org.eurovision.command;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * @author Kseniia Orlenko
 * @version 4/21/18
 */
public class CommandTest {

    private final LoadCommand loadCommand = new LoadCommand();
    private final ResultsCommand resultsCommand = new ResultsCommand();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private PrintStream sysOut;

    @Before
    public void setUpStreams() {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void runPositiveFlow() {
        try {
            loadCommand.run("input.txt", "2018");
            resultsCommand.run("Netherlands", "2018");

            assertTrue(outContent.toString().contains("Netherlands 2018 voting results:\n"
                    + "1 point goes to Belgium\n"
                    + "2 points go to Malta"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void runResultsCountryNotExist() {
        try {
            loadCommand.run("input.txt", "2018");
            resultsCommand.run("USA", "2018");

            assertTrue(outContent.toString().contains("USA 2018 voting results:\n"
                    + "-- NONE --"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void runLoadFileNotFound() throws Exception {
        loadCommand.run("notExist.txt", "2018");
    }

    @Test(expected = RuntimeException.class)
    public void runResultsFileNotExist() throws Exception {
        resultsCommand.run("USA", "2021");
    }

    @Test(expected = IllegalArgumentException.class)
    public void runLoadIncorrectArguments() throws Exception {
        loadCommand.run("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void runResultsIncorrectArguments() throws Exception {
        resultsCommand.run("");
    }

    @After
    public void restoreStreams() {
        System.setOut(sysOut);
    }
}