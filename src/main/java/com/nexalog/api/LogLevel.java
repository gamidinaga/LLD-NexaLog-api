package com.nexalog.api;

/**
 * Defines the severity levels supported by NexaLog.
 *
 * <p>Levels are ordered from least severe to most severe:
 * {@link #TRACE}, {@link #DEBUG}, {@link #INFO},
 * {@link #WARN}, and {@link #ERROR}.
 *
 * @author Nagaraju Gamidi
 * @since 0.1.0
 */
public enum LogLevel {
    /**
     * Fine-grained diagnostic information useful for tracing
     * application execution.
     */
    TRACE(0),

    /**
     * Diagnostic information useful when investigating
     * application behavior.
     */
    DEBUG(1),

    /**
     * Information about normal application events.
     */
    INFO(2),

    /**
     * An unexpected situation that may require attention.
     */
    WARN(3),

    /**
     * A failure that prevents an operation from completing
     * as expected.
     */
    ERROR(4);

    private final int severity;

    /**
     * Creates a log level with its associated severity value.
     *
     * @param severity numeric severity used to compare levels
     */
    LogLevel(int severity) {
        this.severity = severity;
    }

    /**
     * Returns the numeric severity associated with this level.
     *
     * @return the severity value; higher values represent
     *         more severe levels
     */
    public int severity() {
        return severity;
    }
}
