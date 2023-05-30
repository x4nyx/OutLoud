package fpmibsu.outloud.service.impl;

import fpmibsu.outloud.exception.PersistentException;
import fpmibsu.outloud.dao.Transaction;
import fpmibsu.outloud.dao.TransactionFactory;
import fpmibsu.outloud.service.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactoryImpl implements ServiceFactory {

    private static final Map<Class<? extends Service>, Class<? extends ServiceImpl>> SERVICES = new ConcurrentHashMap<>();

    static {
        SERVICES.put(AlbumService.class, AlbumServiceImpl.class);
        SERVICES.put(GenreService.class, GenreServiceImpl.class);
        SERVICES.put(GroupService.class, GroupServiceImpl.class);
        SERVICES.put(PostService.class, PostServiceImpl.class);
        SERVICES.put(ReportService.class, ReportServiceImpl.class);
        SERVICES.put(TrackService.class, TrackServiceImpl.class);
        SERVICES.put(UserService.class, UserServiceImpl.class);
    }

    private TransactionFactory factory;

    public ServiceFactoryImpl(TransactionFactory factory) throws PersistentException {
        this.factory = factory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Type extends Service> Type getService(Class<Type> key) throws PersistentException {
        Class<? extends ServiceImpl> value = SERVICES.get(key);
        if(value != null) {
            try {
                ClassLoader classLoader = value.getClassLoader();
                Class<?>[] interfaces = {key};
                Transaction transaction = factory.createTransaction();
                ServiceImpl service = value.newInstance();
                service.setTransaction(transaction);
                InvocationHandler handler = new ServiceInvocationHandlerImpl(service);
                return (Type) Proxy.newProxyInstance(classLoader, interfaces, handler);
            } catch(PersistentException e) {
                throw e;
            } catch(InstantiationException | IllegalAccessException e) {
                throw new PersistentException(e);
            }
        }
        return null;
    }

    @Override
    public void close() {
        factory.close();
    }
}
