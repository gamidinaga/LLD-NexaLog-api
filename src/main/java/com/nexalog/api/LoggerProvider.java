package com.nexalog.api;

/**
 * Provides logger instances to the NexaLog API.
 *
 * <p>Logging implementations provide this interface so that
 * {@link LoggerFactory} can discover them without depending
 * on their concrete implementation classes.
 *
 * @author Nagaraju Gamidi
 * @since 0.1.0
 */
public interface LoggerProvider {

    /**
     * Returns a logger associated with the specified name.
     *
     * @param name the name identifying the logger
     * @return a logger associated with the specified name
     * @throws IllegalArgumentException if the name is null or blank
     */
    Logger getLogger(String name);
}
