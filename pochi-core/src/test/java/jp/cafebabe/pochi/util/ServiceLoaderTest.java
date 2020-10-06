package jp.cafebabe.pochi.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.vavr.control.Try;
import jp.cafebabe.pochi.util.spi.NoDefaultConstructorService;
import jp.cafebabe.pochi.util.spi.NoDescriptionService;
import jp.cafebabe.pochi.util.spi.NoImplementationService;
import jp.cafebabe.pochi.util.spi.Service;
import org.junit.Test;

public class ServiceLoaderTest {
    @Test
    public void testLoadSuccess(){
        ServiceLoaderBuilder builder = new ServiceLoaderBuilder();
        ServiceLoader<Service> loader = builder.build(Service.class);

        List<Service> list = loader.instantiate().collect(Collectors.toList());
        assertThat(list.size(), is(3));

        assertThat(list.get(0).getClass().getName(), is("jp.cafebabe.pochi.util.spi.ServiceImpl1"));
        assertThat(list.get(0).name(), is("service1"));

        assertThat(list.get(1).getClass().getName(), is("jp.cafebabe.pochi.util.spi.ServiceImpl2"));
        assertThat(list.get(1).name(), is("service2"));

        assertThat(list.get(2).getClass().getName(), is("jp.cafebabe.pochi.util.spi.ServiceImpl3"));
        assertThat(list.get(2).name(), is("service3"));
    }

    @Test
    public void testLoadFailedWithNoImplementation(){
        ServiceLoaderBuilder builder = new ServiceLoaderBuilder();
        ServiceLoader<NoImplementationService> loader = builder.build(NoImplementationService.class);
        List<Class<NoImplementationService>> list = loader.stream().collect(Collectors.toList());

        assertThat(list.size(), is(0));
    }

    @Test
    public void testLoadFailedInNoDescription(){
        ServiceLoaderBuilder builder = new ServiceLoaderBuilder();
        ServiceLoader<NoDescriptionService> loader = builder.build(NoDescriptionService.class);
        List<Class<NoDescriptionService>> list = loader.stream().collect(Collectors.toList());

        assertThat(list.size(), is(0));
    }

    @Test
    public void testInstantiateWithFunction(){
        ServiceLoaderBuilder builder = new ServiceLoaderBuilder();
        ServiceLoader<NoDefaultConstructorService> loader = builder.build(NoDefaultConstructorService.class);
        List<NoDefaultConstructorService> list = loader
                .instantiate(clazz -> instantiate(clazz, "string"))
                .collect(Collectors.toList());

        assertThat(list.size(), is(2));
        assertThat(list.get(0).getClass().getName(), is("jp.cafebabe.pochi.util.spi.NoDefaultConstructorServiceImpl1"));
        assertThat(list.get(0).type(), is("service2-1"));
        assertThat(list.get(0).string(), is("string"));

        assertThat(list.get(1).getClass().getName(), is("jp.cafebabe.pochi.util.spi.NoDefaultConstructorServiceImpl2"));
        assertThat(list.get(1).type(), is("service2-2"));
        assertThat(list.get(1).string(), is("string"));
    }

    private <T> Optional<T> instantiate(Class<T> clazz, String argument){
        return Try.of(() -> {
            Constructor<T> constructor = clazz.getConstructor(String.class);
            return constructor.newInstance(argument);
        }).toJavaOptional();
    }
}
