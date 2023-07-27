package com.github.hobbylabs.ldapsample.controller;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;

import javax.naming.CommunicationException;
import java.util.HashSet;
import java.util.Set;

public class DirContextPooledObjectFactory extends BaseKeyedPooledObjectFactory<Object, Object> {
    private static final Set<Class<? extends Throwable>> DEFAULT_NONTRANSIENT_EXCEPTIONS = new HashSet<Class<? extends Throwable>>();

    static {
        DEFAULT_NONTRANSIENT_EXCEPTIONS.add(CommunicationException.class);
    };

    @Override
    public Object create(Object o) throws Exception {
        return null;
    }

    @Override
    public PooledObject<Object> wrap(Object o) {
        return null;
    }
}
