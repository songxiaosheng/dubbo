package org.apache.dubbo.tracing.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.observation.ObservationRegistry;

import org.apache.dubbo.metrics.MetricsGlobalRegistry;
import org.apache.dubbo.rpc.model.ApplicationModel;

public class ObservationMeter {

    public static void addMeterRegistry(ObservationRegistry registry, ApplicationModel applicationModel) {
        MeterRegistry meterRegistry = MetricsGlobalRegistry.getCompositeRegistry(applicationModel);
        registry.observationConfig()
                .observationHandler(new io.micrometer.core.instrument.observation.DefaultMeterObservationHandler(
                        meterRegistry));
    }

}
