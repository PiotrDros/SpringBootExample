package com.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import java.util.concurrent.TimeUnit;

import net.ttddyy.dsproxy.listener.logging.CommonsLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

@Component
public class DatasourceProxyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            return ProxyDataSourceBuilder.create((DataSource) bean)
                    // logQueryBySlf4j(), logQueryByJUL(), logQueryToSysOut()
                    .logQueryByCommons(CommonsLogLevel.INFO).countQuery()
                    // also by sl4j, jul, system out
                    .logSlowQueryByCommons(10, TimeUnit.MINUTES).countQuery().build();
        }
        return bean;
    }

}