package org.apache.dubbo.metrics.prometheus;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.common.metrics.collector.DefaultMetricsCollector;
import org.apache.dubbo.common.metrics.collector.MetricsCollector;
import org.apache.dubbo.common.metrics.model.sample.GaugeMetricSample;
import org.apache.dubbo.common.metrics.model.sample.MetricSample;
import org.apache.dubbo.metrics.AbstractMetricsReporter;
import org.apache.dubbo.metrics.collector.AggregateMetricsCollector;
import org.apache.dubbo.rpc.model.ApplicationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DubboMetricsBinder implements MeterBinder {

    private final Logger logger = LoggerFactory.getLogger(DubboMetricsBinder.class);

    private final MeterRegistry meterRegistry;

    protected final List<MetricsCollector> collectors = new ArrayList<>();


    public DubboMetricsBinder(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        initCollectors();
    }

    private void initCollectors() {
        ApplicationModel applicationModel = ApplicationModel.defaultModel();
        applicationModel.getBeanFactory().getOrRegisterBean(AggregateMetricsCollector.class);
        collectors.add(applicationModel.getBeanFactory().getBean(DefaultMetricsCollector.class));
        collectors.add(applicationModel.getBeanFactory().getBean(AggregateMetricsCollector.class));
    }

    @Override
    public void bindTo(MeterRegistry registry) {
        collectors.forEach(collector -> {
            List<MetricSample> samples = collector.collect();
            for (MetricSample sample : samples) {
                try {
                    switch (sample.getType()) {
                        case GAUGE:
                            GaugeMetricSample gaugeSample = (GaugeMetricSample) sample;
                            List<Tag> tags = new ArrayList<>();
                            gaugeSample.getTags().forEach((k, v) -> {
                                if (v == null) {
                                    v = "";
                                }

                                tags.add(Tag.of(k, v));
                            });

                            Gauge.builder(gaugeSample.getName(), gaugeSample.getSupplier())
                                .description(gaugeSample.getDescription()).tags(tags).register(registry);
                            break;
                        case COUNTER:
                        case TIMER:
                        case LONG_TASK_TIMER:
                        case DISTRIBUTION_SUMMARY:
                            // TODO
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    logger.error("error occurred when synchronize metrics collector.", e);
                }
            }
        });
    }

}
