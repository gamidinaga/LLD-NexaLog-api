package com.nexalog.api;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoggerProviderLoaderTest {

    @Test
    void shouldRejectNullProviderCollection() {
        assertThrows(
                IllegalArgumentException.class,
                () -> LoggerProviderLoader.loadProvider(
                        (Iterable<LoggerProvider>) null
                )
        );
    }

    @Test
    void shouldRejectMissingProvider() {
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> LoggerProviderLoader.loadProvider(List.of())
        );

        assertTrue(exception.getMessage().contains("No NexaLog logging provider found. Add a provider implementation to the classpath."));
    }

    @Test
    void shouldReturnTheOnlyProvider() {
        LoggerProvider provider = new TestLoggerProvider();

        LoggerProvider result = LoggerProviderLoader.loadProvider(
                List.of(provider)
        );

        assertSame(provider, result);
    }

    @Test
    void shouldRejectMultipleProviders() {
        List<LoggerProvider> providers = List.of(
                new TestLoggerProvider(),
                new TestLoggerProvider()
        );

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> LoggerProviderLoader.loadProvider(providers)
        );

        assertEquals(
                true,
                exception.getMessage().contains("Multiple NexaLog logging providers")
        );
    }

    private static final class TestLoggerProvider
            implements LoggerProvider {

        @Override
        public Logger getLogger(String name) {
            throw new UnsupportedOperationException(
                    "Not required for provider discovery tests"
            );
        }
    }

}
