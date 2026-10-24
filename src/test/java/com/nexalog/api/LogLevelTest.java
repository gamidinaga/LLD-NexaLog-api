package com.nexalog.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the severity ordering of NexaLog log levels.
 *
 * @author Nagaraju Gamidi
 * @since 0.1.0
 */
public class LogLevelTest {


    /**
     * Verifies the numeric severity assigned to each log level.
     *
     * @param level the log level being tested
     * @param expectedSeverity the expected numeric severity
     */
    @ParameterizedTest
    @CsvSource({
            "TRACE,0",
            "DEBUG, 1",
            "INFO, 2",
            "WARN, 3",
            "ERROR, 4"
    })
    void shouldAssignExpectedSeverity(LogLevel level,
                                      int expectedSeverity){
        assertEquals(expectedSeverity, level.severity());
    }
}
