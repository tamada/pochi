package com.github.ebis.nasubi;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.github.ebis.nasubi.spi.NoDefaultConstructorService;
import com.github.ebis.nasubi.spi.NoDescriptionService;
import com.github.ebis.nasubi.spi.NoImplementationService;
import com.github.ebis.nasubi.spi.Service;

public class ServiceLoaderTest {
    @Test
    public void testLoadSuccess(){
        ServiceLoaderBuilder builder = new ServiceLoaderBuilder();
        ServiceLoader<Service> loader = builder.build(Service.class);

        List<Service> list = loader.instantiate().collect(Collectors.toList());
        assertThat(list.size(), is(3));

        assertThat(list.get(0).getClass().getName(), is("com.github.ebis.nasubi.spi.ServiceImpl1"));
        assertThat(list.get(0).name(), is("service1"));

        assertThat(list.get(1).getClass().getName(), is("com.github.ebis.nasubi.spi.ServiceImpl2"));
        assertThat(list.get(1).name(), is("service2"));

        assertThat(list.get(2).getClass().getName(), is("com.github.ebis.nasubi.spi.ServiceImpl3"));
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
        assertThat(list.get(0).getClass().getName(), is("com.github.ebis.nasubi.spi.NoDefaultConstructorServiceImpl1"));
        assertThat(list.get(0).type(), is("service2-1"));
        assertThat(list.get(0).string(), is("string"));

        assertThat(list.get(1).getClass().getName(), is("com.github.ebis.nasubi.spi.NoDefaultConstructorServiceImpl2"));
        assertThat(list.get(1).type(), is("service2-2"));
        assertThat(list.get(1).string(), is("string"));
    }

    private <T> T instantiate(Class<T> clazz, String argument){
        return Exceptions.map(clazz, argument, (c, a) -> {
            Constructor<T> constructor = clazz.getConstructor(String.class);
            return constructor.newInstance(argument);
        }).get();
    }
}
