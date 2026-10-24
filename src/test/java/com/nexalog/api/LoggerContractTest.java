package com.nexalog.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the public logging contract and Java overload resolution.
 *
 * @author Nagaraju Gamidi
 * @since 0.1.0
 */
public class LoggerContractTest {



    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    void shouldRejectBlankNullAndEmptyLoggerNames(String name){
        assertThrows(
                IllegalArgumentException.class,
                ()-> validateLoggerName(name)
                );
    }

    public static void validateLoggerName(String name){
           if(name == null || name.isBlank()){
               throw new IllegalArgumentException("Logger name cannot be null or blank");
           }
    }

    /**
     * Verifies that a normal argument uses the parameterized overload.
     */
    @Test
    void shouldSelectParameterizedOverloadMethod(){
        RecordingLogger logger = new RecordingLogger();
        logger.info("Payment {}", "PAY-101");
        assertEquals("info-parameterized", logger.lastMethod);
        assertEquals("Payment {}", logger.lastTemplate);
        assertArrayEquals(new Object[]{"PAY-101"}, logger.lastArguments);
    }

    /**
     * Verifies that an exception passed as the second argument
     * selects the exception overload.
     */
    @Test
    void shouldSelectExceptionOverloadWhenSecondArgumentIsThrowable() {
        RecordingLogger logger = new RecordingLogger();
        Throwable cause = new RuntimeException("Payment failed");

        logger.error("Payment failed", cause);

        assertEquals("error-with-cause", logger.lastMethod);
        assertSame(cause, logger.lastCause);
    }

    /**
     * Verifies that the explicit-cause overload accepts both
     * an exception and separate formatting arguments.
     */
    @Test
    void shouldSeparateCauseFromFormattingArguments() {
        RecordingLogger logger = new RecordingLogger();
        Throwable cause = new RuntimeException("Network failure");

        logger.error("Payment {} failed", cause, "PAY-101");

        assertEquals("error-parameterized-with-cause", logger.lastMethod);
        assertEquals("Payment {} failed", logger.lastTemplate);
        assertSame(cause, logger.lastCause);
        assertArrayEquals(new Object[]{"PAY-101"}, logger.lastArguments);
    }


    /**
     * A minimal logger that records which public method was called.
     */
    private static final class RecordingLogger implements Logger {

        private String lastMethod;
        private String lastTemplate;
        private Throwable lastCause;
        private Object[] lastArguments;

        @Override
        public boolean isTraceEnabled() {
            return true;
        }

        @Override
        public boolean isDebugEnabled() {
            return true;
        }

        @Override
        public boolean isInfoEnabled() {
            return true;
        }

        @Override
        public boolean isWarnEnabled() {
            return true;
        }

        @Override
        public boolean isErrorEnabled() {
            return true;
        }

        @Override
        public void trace(String message) {
            lastMethod = "trace";
            lastTemplate = message;
        }

        @Override
        public void trace(String template, Object... arguments) {
            record("trace-parameterized", template, arguments);
        }

        @Override
        public void debug(String message) {
            lastMethod = "debug";
            lastTemplate = message;
        }

        @Override
        public void debug(String template, Object... arguments) {
            record("debug-parameterized", template, arguments);
        }

        @Override
        public void info(String message) {
            lastMethod = "info";
            lastTemplate = message;
        }

        @Override
        public void info(String template, Object... arguments) {
            record("info-parameterized", template, arguments);
        }

        @Override
        public void warn(String message) {
            lastMethod = "warn";
            lastTemplate = message;
        }

        @Override
        public void warn(String message, Throwable cause) {
            recordWithCause("warn-with-cause", message, cause);
        }

        @Override
        public void warn(String template, Object... arguments) {
            record("warn-parameterized", template, arguments);
        }

        @Override
        public void warn(
                String template,
                Throwable cause,
                Object... arguments) {
            recordWithCauseAndArguments(
                    "warn-parameterized-with-cause",
                    template,
                    cause,
                    arguments
            );
        }

        @Override
        public void error(String message) {
            lastMethod = "error";
            lastTemplate = message;
        }

        @Override
        public void error(String message, Throwable cause) {
            recordWithCause("error-with-cause", message, cause);
        }

        @Override
        public void error(String template, Object... arguments) {
            record("error-parameterized", template, arguments);
        }

        @Override
        public void error(
                String template,
                Throwable cause,
                Object... arguments) {
            recordWithCauseAndArguments(
                    "error-parameterized-with-cause",
                    template,
                    cause,
                    arguments
            );
        }

        private void record(
                String method,
                String template,
                Object... arguments) {
            lastMethod = method;
            lastTemplate = template;
            lastArguments = arguments;
            lastCause = null;
        }

        private void recordWithCause(
                String method,
                String message,
                Throwable cause) {
            lastMethod = method;
            lastTemplate = message;
            lastCause = cause;
            lastArguments = new Object[0];
        }

        private void recordWithCauseAndArguments(
                String method,
                String template,
                Throwable cause,
                Object... arguments) {
            lastMethod = method;
            lastTemplate = template;
            lastCause = cause;
            lastArguments = arguments;
        }
    }
}