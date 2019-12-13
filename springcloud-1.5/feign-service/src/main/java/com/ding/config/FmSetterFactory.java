package com.ding.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import feign.Feign;
import feign.Target;
import feign.hystrix.SetterFactory;
import org.springframework.core.env.ConfigurableEnvironment;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @Auther: ding
 * @Date: 2019-12-13 22:44
 * @Description:
 */
public class FmSetterFactory implements SetterFactory {
    private static final String HYSTRIX_COMMAND_PREFIX = "hystrix.command";

    private ConfigurableEnvironment environment;

    public FmSetterFactory(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public HystrixCommand.Setter create(Target<?> target, Method method) {
        String groupKey = target.name();
        String commandKey = Feign.configKey(target.type(), method);


        return HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                .andCommandPropertiesDefaults(
                        initHystrixCommandPropertiesSetter(target));
    }

    private HystrixCommandProperties.Setter initHystrixCommandPropertiesSetter(Target<?> target) {
        HystrixCommandProperties.Setter setter = HystrixCommandProperties.Setter();
        Boolean circuitBreakerEnabled = getHystrixCommandBooleanPropValue(target.name(), "circuitBreaker.enabled");
        Optional.ofNullable(circuitBreakerEnabled).ifPresent(v -> setter.withCircuitBreakerEnabled(v));
        Integer circuitBreakerRequestVolumeThreshold = getHystrixCommandIntegerPropValue(target.name(), "circuitBreaker.requestVolumeThreshold");
        Optional.ofNullable(circuitBreakerRequestVolumeThreshold).ifPresent(v -> setter.withCircuitBreakerRequestVolumeThreshold(v));
        Integer circuitBreakerSleepWindowInMilliseconds = getHystrixCommandIntegerPropValue(target.name(), "circuitBreaker.sleepWindowInMilliseconds");
        Optional.ofNullable(circuitBreakerSleepWindowInMilliseconds).ifPresent(v -> setter.withCircuitBreakerSleepWindowInMilliseconds(v));
        Integer circuitBreakerErrorThresholdPercentage = getHystrixCommandIntegerPropValue(target.name(), "circuitBreaker.errorThresholdPercentage");
        Optional.ofNullable(circuitBreakerErrorThresholdPercentage).ifPresent(v -> setter.withCircuitBreakerErrorThresholdPercentage(v));
        Boolean circuitBreakerForceOpen = getHystrixCommandBooleanPropValue(target.name(), "circuitBreaker.forceOpen");
        Optional.ofNullable(circuitBreakerForceOpen).ifPresent(v -> setter.withCircuitBreakerForceOpen(v));
        Boolean circuitBreakerForceClosed = getHystrixCommandBooleanPropValue(target.name(), "circuitBreaker.forceClosed");
        Optional.ofNullable(circuitBreakerForceClosed).ifPresent(v -> setter.withCircuitBreakerForceClosed(v));
        String executionIsolationStrategy = getHystrixCommandStringPropValue(target.name(), "execution.isolation.strategy");
        Optional.ofNullable(executionIsolationStrategy).ifPresent(v -> {
            try {
                setter.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.valueOf(v));
            } catch (Exception e) {
            }
        });
        Integer executionTimeoutInMilliseconds = getHystrixCommandIntegerPropValue(target.name(), "execution.isolation.thread.timeoutInMilliseconds");
        Optional.ofNullable(executionTimeoutInMilliseconds).ifPresent(v -> setter.withExecutionTimeoutInMilliseconds(v));
        Boolean executionTimeoutEnabled = getHystrixCommandBooleanPropValue(target.name(), "execution.timeout.enabled");
        Optional.ofNullable(executionTimeoutEnabled).ifPresent(v -> setter.withExecutionTimeoutEnabled(v));

        Boolean executionIsolationThreadInterruptOnTimeout = getHystrixCommandBooleanPropValue(target.name(), "execution.isolation.thread.interruptOnTimeout");
        Optional.ofNullable(executionIsolationThreadInterruptOnTimeout).ifPresent(v -> setter.withExecutionIsolationThreadInterruptOnTimeout(v));
        Boolean executionIsolationThreadInterruptOnFutureCancel = getHystrixCommandBooleanPropValue(target.name(), "execution.isolation.thread.interruptOnFutureCancel");
        Optional.ofNullable(executionIsolationThreadInterruptOnFutureCancel).ifPresent(v -> setter.withExecutionIsolationThreadInterruptOnFutureCancel(v));
        Integer executionIsolationSemaphoreMaxConcurrentRequests = getHystrixCommandIntegerPropValue(target.name(), "execution.isolation.semaphore.maxConcurrentRequests");
        Optional.ofNullable(executionIsolationSemaphoreMaxConcurrentRequests).ifPresent(v -> setter.withExecutionIsolationSemaphoreMaxConcurrentRequests(v));
        Integer fallbackIsolationSemaphoreMaxConcurrentRequests = getHystrixCommandIntegerPropValue(target.name(), "fallback.isolation.semaphore.maxConcurrentRequests");
        Optional.ofNullable(fallbackIsolationSemaphoreMaxConcurrentRequests).ifPresent(v -> setter.withFallbackIsolationSemaphoreMaxConcurrentRequests(v));
        Boolean fallbackEnabled = getHystrixCommandBooleanPropValue(target.name(), "fallback.enabled");
        Optional.ofNullable(fallbackEnabled).ifPresent(v -> setter.withFallbackEnabled(v));
        Integer metricsRollingStatisticalWindowInMilliseconds = getHystrixCommandIntegerPropValue(target.name(), "metrics.rollingStats.timeInMilliseconds");
        Optional.ofNullable(metricsRollingStatisticalWindowInMilliseconds).ifPresent(v -> setter.withMetricsRollingStatisticalWindowInMilliseconds(v));
        Integer metricsRollingStatisticalWindowBuckets = getHystrixCommandIntegerPropValue(target.name(), "metrics.rollingStats.numBuckets");
        Optional.ofNullable(metricsRollingStatisticalWindowBuckets).ifPresent(v -> setter.withMetricsRollingStatisticalWindowBuckets(v));
        Boolean metricsRollingPercentileEnabled = getHystrixCommandBooleanPropValue(target.name(), "metrics.rollingPercentile.enabled");
        Optional.ofNullable(metricsRollingPercentileEnabled).ifPresent(v -> setter.withMetricsRollingPercentileEnabled(v));

        Integer metricsRollingPercentileWindowInMilliseconds = getHystrixCommandIntegerPropValue(target.name(), "metrics.rollingPercentile.timeInMilliseconds");
        Optional.ofNullable(metricsRollingPercentileWindowInMilliseconds).ifPresent(v -> setter.withMetricsRollingPercentileWindowInMilliseconds(v));
        Integer metricsRollingPercentileWindowBuckets = getHystrixCommandIntegerPropValue(target.name(), "metrics.rollingPercentile.numBuckets");
        Optional.ofNullable(metricsRollingPercentileWindowBuckets).ifPresent(v -> setter.withMetricsRollingPercentileWindowBuckets(v));

        Integer metricsRollingPercentileBucketSize = getHystrixCommandIntegerPropValue(target.name(), "metrics.rollingPercentile.bucketSize");
        Optional.ofNullable(metricsRollingPercentileBucketSize).ifPresent(v -> setter.withMetricsRollingPercentileBucketSize(v));

        Integer metricsHealthSnapshotIntervalInMilliseconds = getHystrixCommandIntegerPropValue(target.name(), "metrics.healthSnapshot.intervalInMilliseconds");
        Optional.ofNullable(metricsHealthSnapshotIntervalInMilliseconds).ifPresent(v -> setter.withMetricsHealthSnapshotIntervalInMilliseconds(v));

        Boolean requestCacheEnabled = getHystrixCommandBooleanPropValue(target.name(), "requestCache.enabled");
        Optional.ofNullable(requestCacheEnabled).ifPresent(v -> setter.withRequestCacheEnabled(v));

        Boolean requestLogEnabled = getHystrixCommandBooleanPropValue(target.name(), "requestLog.enabled");
        Optional.ofNullable(requestLogEnabled).ifPresent(v -> setter.withRequestLogEnabled(v));
        return setter;
    }


    private String getHystrixCommandStringPropValue(String key, String instanceProperty) {
        return getHystrixCommandStringPropValue(key, instanceProperty, null);
    }

    private Boolean getHystrixCommandBooleanPropValue(String key, String instanceProperty) {
        return getHystrixCommandBooleanPropValue(key, instanceProperty, null);
    }

    private Integer getHystrixCommandIntegerPropValue(String key, String instanceProperty) {
        return getHystrixCommandIntegerPropValue(key, instanceProperty, null);
    }

    private String getHystrixCommandStringPropValue(String key, String instanceProperty, String defaultValue) {
        return getHystrixCommandPropValue(key, instanceProperty, defaultValue, String.class);
    }

    private Boolean getHystrixCommandBooleanPropValue(String key, String instanceProperty, Boolean defaultValue) {
        return getHystrixCommandPropValue(key, instanceProperty, defaultValue, Boolean.class);
    }

    private Integer getHystrixCommandIntegerPropValue(String key, String instanceProperty, Integer defaultValue) {
        return getHystrixCommandPropValue(key, instanceProperty, defaultValue, Integer.class);
    }

    private <T> T getHystrixCommandPropValue(String key, String instanceProperty, T defaultValue, Class<T> type) {
        return getPropValue(HYSTRIX_COMMAND_PREFIX, key, instanceProperty, defaultValue, type);
    }


    private <T> T getPropValue(String propertyPrefix, String key, String instanceProperty, T defaultValue, Class<T> type) {
        String propName = new StringBuilder()
                .append(propertyPrefix)
                .append(".")
                .append(key)
                .append(".")
                .append(instanceProperty)
                .toString();
        T value =  environment.getProperty(propName, type);
        if(value==null){
            value = defaultValue;
        }
        return value;
    }
}
