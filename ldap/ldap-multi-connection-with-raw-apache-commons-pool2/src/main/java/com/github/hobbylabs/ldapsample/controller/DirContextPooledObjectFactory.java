package com.github.hobbylabs.ldapsample.controller;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import java.lang.reflect.Proxy;

public class DirContextPooledObjectFactory extends BaseKeyedPooledObjectFactory<Object, Object> {



    @Override
    public Object create(Object o) throws Exception {
        //final DirContextType contextType = (DirContextType) key;
        return Proxy.newProxyInstance(DirContextProxy.class.getClassLoader(), new Class<?>[] {
                        LdapUtils.getActualTargetClass(readOnlyContext), DirContextProxy.class, FailureAwareContext.class },
                new org.springframework.ldap.pool2.factory.DirContextPooledObjectFactory.FailureAwareContextProxy(readOnlyContext));
    }

    @Override
    public PooledObject<Object> wrap(Object o) {
        return null;
    }
}
