package br.com.pluggedin.infra.components;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class SessionFactoryCreator implements ComponentFactory<SessionFactory> {

	private SessionFactory factory;

	@PostConstruct
	public void create() {
		factory = getAnnotationConfiguration().configure().buildSessionFactory();
	}

	public SessionFactory getInstance() {
		return factory;
	}

	@PreDestroy
	public void destroy() {
		factory.close();
	}

    protected AnnotationConfiguration getAnnotationConfiguration() {
        return new AnnotationConfiguration();
    }
}