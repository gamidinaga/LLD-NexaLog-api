package com.nexalog.api;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests validation performed by LoggerFactory.
 *
 * @author Nagaraju Gamidi
 * @since 0.1.0
 */
 class LoggerFactoryTest {

    /**
     * Verifies that null, empty, and whitespace-only names are rejected.
     *
     * @param name the invalid logger name
     */
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings ={" ","   ","\t", "\n"})
    void shouldRejectNullOrBlankLoggerName (String name){
        assertThrows(IllegalArgumentException.class, () -> LoggerFactory.getLogger(name));
    }
}
