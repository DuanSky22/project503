package com.iscas.project503.main;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.iscas.project503.config.AppConfiguration;

public class Main {
	public static void main(String[] args) throws Exception {
		Server server = new Server(getPort());
		server.setHandler(getServletContextHandler(getContext()));
		server.start();
		server.join();
	}

	private static int getPort() throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(Main.class.getClassLoader()
				.getResourceAsStream("application.properties")));
		return Integer.valueOf(prop.getProperty("server.port", "9090"));
	}

	private static ServletContextHandler getServletContextHandler(
			WebApplicationContext context) {
		ServletContextHandler contextHandler = new ServletContextHandler();
		contextHandler.setErrorHandler(null);
		DispatcherServlet dispatcher = new DispatcherServlet();
		dispatcher.setApplicationContext(context);
		contextHandler.addServlet(new ServletHolder(dispatcher), "/*");
		contextHandler.addEventListener(new ContextLoaderListener(context));
		return contextHandler;
	}

	private static WebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(AppConfiguration.class.getCanonicalName());
		//context.getEnvironment().setActiveProfiles("prod");
		return context;
	}
}
