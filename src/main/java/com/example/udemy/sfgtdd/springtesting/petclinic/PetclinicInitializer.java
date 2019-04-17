/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.udemy.sfgtdd.springtesting.petclinic;

import com.example.udemy.sfgtdd.springtesting.petclinic.config.MvcCoreConfig;
import com.example.udemy.sfgtdd.springtesting.petclinic.config.RootApplicationContextConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;


/**
 * In Servlet 3.0+ environments, this class replaces the traditional {@code web.xml}-based approach in order to configure the
 * {@link ServletContext} programmatically.
 * <p/>
 * Create the Spring "<strong>root</strong>" application context.<br/>
 * Register a {@link DispatcherServlet}  in the servlet context.<br/>
 * For both servlets, register a {@link CharacterEncodingFilter}.
 * <p/>
 *
 * @author Antoine Rey
 */
public class PetclinicInitializer extends AbstractDispatcherServletInitializer {

    /**
     * Spring profile used to choose the persistence layer implementation.
     * <p>
     * When using Spring jpa, use: jpa
     * When using Spring JDBC, use: jdbc
     * When using Spring Data JPA, use: spring-data-jpa
     * <p/>
     * <p>
     * You also may use the -Dspring.profiles.active=jdbc VM options to change
     * default jpa Spring profile.
     */
    private static final String SPRING_PROFILE = "jpa";

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
        rootAppContext.register(RootApplicationContextConfig.class);
        rootAppContext.getEnvironment().setDefaultProfiles(SPRING_PROFILE);
        return rootAppContext;
    }

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext webAppContext = new AnnotationConfigWebApplicationContext();
        webAppContext.register(MvcCoreConfig.class);
        return webAppContext;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        // Used to provide the ability to enter Chinese characters inside the Owner Form
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8", true);
        return new Filter[]{characterEncodingFilter};
    }

}