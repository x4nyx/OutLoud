package fpmibsu.outloud.service.impl;

import fpmibsu.outloud.exception.PersistentException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServiceInvocationHandlerImpl implements InvocationHandler {

    private ServiceImpl service;

    public ServiceInvocationHandlerImpl(ServiceImpl service) {
        this.service = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
        try {
            Object result = method.invoke(service, arguments);
            service.transaction.commit();
            return result;
        } catch(PersistentException e) {
            rollback(method);
            throw e;
        } catch(InvocationTargetException e) {
            rollback(method);
            throw e.getCause();
        }
    }

    private void rollback(Method method) {
        try {
            service.transaction.rollback();
        } catch(PersistentException e) {
            e.printStackTrace();
        }
    }
}
