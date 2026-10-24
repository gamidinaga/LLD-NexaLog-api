package com.nexalog.api;

/**
 * Defines the public contract for logging application events.
 *
 * <p>Applications use this interface without depending on
 * the underlying logging implementation or destination.
 * <p>Parameterized methods accept a message template and
 * arguments. The implementation is responsible for formatting
 * the message when appropriate.
 * @author Nagaraju Gamidi
 * @since 0.1.0
 */
public interface Logger {

    /**
     * Returns whether TRACE-level logging is enabled.
     *
     * @return {@code true} if TRACE messages are enabled
     */
    boolean isTraceEnabled();

    /**
     * Returns whether DEBUG-level logging is enabled.
     *
     * @return {@code true} if DEBUG messages are enabled
     */
    boolean isDebugEnabled();

    /**
     * Returns whether INFO-level logging is enabled.
     *
     * @return {@code true} if INFO messages are enabled
     */
    boolean isInfoEnabled();

    /**
     * Returns whether WARN-level logging is enabled.
     *
     * @return {@code true} if WARN messages are enabled
     */
    boolean isWarnEnabled();

    /**
     * Returns whether ERROR-level logging is enabled.
     *
     * @return {@code true} if ERROR messages are enabled
     */
    boolean isErrorEnabled();

    /**
     * Logs a trace-level diagnostic message.
     *
     * @param message the message to log
     */
    void trace(String message);

    /**
     * Logs a parameterized trace-level diagnostic message.
     *
     * @param template the message template containing placeholders
     * @param arguments values to substitute into the template
     */
    void trace(String template, Object... arguments);

    /**
     * Logs a debug-level diagnostic message.
     *
     * @param message the message to log
     */
    void debug(String message);

    /**
     * Logs a parameterized debug-level diagnostic message.
     *
     * @param template the message template containing placeholders
     * @param arguments values to substitute into the template
     */
    void debug(String template, Object... arguments);

    /**
     * Logs an informational message about normal application events.
     *
     * @param message the message to log
     */
    void info(String message);

    /**
     * Logs a parameterized informational message.
     *
     * @param template the message template containing placeholders
     * @param arguments values to substitute into the template
     */
    void info(String template, Object... arguments);

    /**
     * Logs a warning about an unexpected situation.
     *
     * @param message the message to log
     */
    void warn(String message);

    /**
     * Logs a warning message with an associated exception.
     *
     * @param message the warning message
     * @param cause the associated exception, or {@code null}
     */
    void warn(String message, Throwable cause);

    /**
     * Logs a parameterized warning message.
     *
     * @param template the message template containing placeholders
     * @param arguments values to substitute into the template
     */
    void warn(String template, Object... arguments);

    /**
     * Logs a parameterized warning message with an associated exception.
     *
     * @param template the message template containing placeholders
     * @param cause the associated exception, or {@code null}
     * @param arguments values to substitute into the template
     */
    void warn(String template, Throwable cause, Object... arguments);

    /**
     * Logs an error message about a failed operation.
     *
     * @param message the message to log
     */
    void error(String message);

    /**
     * Logs an error message with an associated exception.
     *
     * @param message the error message
     * @param cause the associated exception, or {@code null}
     */
    void error(String message, Throwable cause);

    /**
     * Logs a parameterized error message.
     *
     * @param template the message template containing placeholders
     * @param arguments values to substitute into the template
     */
    void error(String template, Object... arguments);

    /**
     * Logs a parameterized error message with an associated exception.
     *
     * @param template the message template containing placeholders
     * @param cause the associated exception, or {@code null}
     * @param arguments values to substitute into the template
     */
    void error(String template, Throwable cause, Object... arguments);

}

