package com.nexalog.api;

import java.util.Objects;
import java.util.ServiceLoader;

/**
 * Provides access to NexaLog logger instances.
 *
 * <p>Applications use this factory to obtain a logger without
 * depending on its underlying implementation.
 *
 * @author Nagaraju Gamidi
 * @since 0.1.0
 */
public final class LoggerFactory {
    //private static final LoggerProvider PROVIDER = loadProvider();

    /**
     * Acts as Cache using Java initialization mechanism
     * Provider object gets created Lazily and cached in memory when @INSTANCE called .
     */
    private static final class ProviderHolder{
        private static final LoggerProvider INSTANCE =LoggerProviderLoader.loadProvider();
    }

    /**
     * Private constructor to prevent Instantiation for this class
     */
    private LoggerFactory(){
          throw new AssertionError("Logger factory cannot be instantiated");
    }

    /**
     * Returns a logger associated with the specified name.
     *
     * @param name the name identifying the logger
     * @return a logger associated with the specified name
     * @throws IllegalArgumentException if the name is null or blank
     * @throws IllegalStateException if no logging provider is available
     */
    public static Logger getLogger(String name){
        if(name == null|| name.isBlank()){
            throw new IllegalArgumentException("Logger name cannot be null or blank");
        }
        //return PROVIDER.getLogger(name);
        //return LoggerProviderLoader.loadProvider().getLogger(name);
        return Objects.requireNonNull(ProviderHolder.INSTANCE.getLogger(name),"LoggerProvider returned a null logger");
    }

    /**
     * Discovers the logging providers registered with NexaLog.
     *
     * @return the single discovered logging provider
     * @throws IllegalStateException if no provider or multiple
     * providers are registered
     */
    /* *private static LoggerProvider loadProvider() {
        var providers = ServiceLoader.load(LoggerProvider.class)
                            .stream()
                            .map(ServiceLoader.Provider::get)
                            .toList();

         if(providers.isEmpty()){
             throw new IllegalStateException(
                     "No NexaLog logging provider found. "
                             + "Add a provider implementation to the classpath."
             );
         }

         if(providers.size() > 1){
             throw new IllegalStateException("More than one NexaLog logging provider found.");
         }

         return providers.getFirst();
    } */
}
