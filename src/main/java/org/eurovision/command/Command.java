package org.eurovision.command;

/**
 * @author Kseniia Orlenko
 * @version 4/21/18
 */
public interface Command {

    String getCode();

    void run(String... args) throws Exception;
}
