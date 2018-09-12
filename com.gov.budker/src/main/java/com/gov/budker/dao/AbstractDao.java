package com.gov.budker.dao;

import java.io.Serializable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;

public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    public void persist(T entity) {
        getSession().persist(entity);
    }

    public PK saveOrUpdate(T entity) {
        PK id = _getIdValue(entity);

        if (id == null) {
            return create(entity);
        }
        getSession().update(entity);
        return id;

    }


    public void delete(T entity) {
        getSession().delete(entity);
    }

    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }

    public PK create(T entity) {
        return (PK) getSession().save(entity);
    }

    private static final Map<Class, Field> keyRegistry = new HashMap<>();

    private PK _getIdValue(T entity) {
        Field field = keyRegistry.get(entity.getClass());

        if (field == null) {
            for (Field f : entity.getClass().getDeclaredFields()) {

                Annotation a = f.getAnnotation(Id.class);

                a = a == null ? f.getAnnotation(EmbeddedId.class) : a;

                if (a != null) {
                    field = f;
                    break;
                }
            }
        }

        if (field == null) {

            return null;
        }

        try {
            field.setAccessible(true);
            return (PK) field.get(entity);
        } catch (Exception e) {
            return null;
        }
    }


}