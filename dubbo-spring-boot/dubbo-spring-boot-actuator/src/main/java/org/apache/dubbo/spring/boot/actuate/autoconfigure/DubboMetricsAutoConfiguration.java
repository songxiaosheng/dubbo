package org.apache.dubbo.spring.boot.actuate.autoconfigure;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.tomcat.TomcatMetrics;
import org.apache.catalina.Manager;
import org.apache.dubbo.metrics.prometheus.DubboMetricsBinder;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.metrics.web.tomcat.TomcatMetricsBinder;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
//@ConditionalOnWebApplication
//@ConditionalOnClass({  DubboMetricsBinder.class })
//@AutoConfigureAfter(CompositeMeterRegistryAutoConfiguration.class)
public class DubboMetricsAutoConfiguration {

	@Bean
	@ConditionalOnBean(MeterRegistry.class)
	@ConditionalOnMissingBean({DubboMetricsBinder.class })
	public DubboMetricsBinder dubboMetricsBinder(MeterRegistry meterRegistry) {
		return new DubboMetricsBinder(meterRegistry);
	}

}
