package org.eurovision;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author Kseniia Orlenko
 * @version 4/21/18
 */
public class EurovisionTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private PrintStream sysOut;

    @Before
    public void setUpStreams() {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void mainPositiveFlow() {
        try {
            String[] loadArgs = {"load", "input.txt", "2019"};
            Eurovision.main(loadArgs);
            String[] resultArgs = {"results", "Netherlands", "2019"};
            Eurovision.main(resultArgs);

            assertTrue(outContent.toString().contains("Netherlands 2019 voting results:\n"
                    + "1 point goes to Belgium\n"
                    + "2 points go to Germany\n"
                    + "3 points go to Malta\n"
                    + "4 points go to Ukraine\n"
                    + "5 points go to USA\n"
                    + "6 points go to Poland\n"
                    + "7 points go to Turkey\n"
                    + "8 points go to France\n"
                    + "10 points go to Australia\n"
                    + "12 points go to Spain"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @After
    public void restoreStreams() {
        System.setOut(sysOut);
    }
}