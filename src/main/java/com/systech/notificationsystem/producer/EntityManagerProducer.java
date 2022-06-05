package com.systech.notificationsystem.producer;

import com.systech.notificationsystem.util.Constants;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@RequestScoped
public class EntityManagerProducer {

    @PersistenceContext(unitName = Constants.EM_NAME)
    private EntityManager em;

    @Produces
    public EntityManager getEntityManager() {
        return em;
    }

}
