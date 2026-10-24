package com.nexalog.api;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Discovers the logging provider available to NexaLog.
 *
 * <p>This package-private class separates provider discovery
 * from the public logger factory and allows discovery behavior
 * to be tested independently.
 *
 * @author Nagaraju Gamidi
 * @since 0.1.0
 */
 final class LoggerProviderLoader {

    /**
     * Prevents instantiation of this utility class.
     */
    private LoggerProviderLoader(){
        throw new AssertionError("Logger provider cannot be instantiated");
    }

    /**
     * Discovers the registered logging provider.
     *
     * @return the single discovered provider
     * @throws IllegalStateException if zero or multiple providers exist
     */
     static LoggerProvider loadProvider(){
          return loadProvider(ServiceLoader.load(LoggerProvider.class));
     }

    /**
     * Selects the only provider from the supplied iterable.
     *
     * @param providers the providers to inspect
     * @return the single discovered provider
     * @throws IllegalArgumentException if providers is null
     * @throws IllegalStateException if zero or multiple providers exist
     */

    static LoggerProvider loadProvider(Iterable<LoggerProvider> providers) {

        if(providers == null){
            throw new IllegalArgumentException("provider cannot be null");
        }

           Iterator<LoggerProvider> itarator =providers.iterator();
        if (!itarator.hasNext()) {
            throw new IllegalStateException("No NexaLog logging provider found. Add a provider implementation to the classpath.");
        }

        LoggerProvider provider = itarator.next();

        if(itarator.hasNext()){
            throw new IllegalStateException(
                    "Multiple NexaLog logging providers found. Register exactly one provider."
            );
        }
        return provider;
    }
}
