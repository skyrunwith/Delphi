package com.fzd.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.WebAppRootListener;

@Configuration
@ComponentScan(basePackages = {"com.fzd.model"},excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { EnableWebMvc.class }) })
//@Import(DataConfig.class)
public class RootConfig{

}
