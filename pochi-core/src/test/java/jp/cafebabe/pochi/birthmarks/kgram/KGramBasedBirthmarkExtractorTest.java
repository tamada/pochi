package jp.cafebabe.pochi.birthmarks.kgram;

import jp.cafebabe.birthmarks.config.ConfigurationBuilder;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.kunai.entries.ClassName;
import jp.cafebabe.kunai.source.DataSource;
import jp.cafebabe.kunai.source.factories.DefaultDataSourceFactory;
import jp.cafebabe.pochi.extractors.ExtractorBuilders;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class KGramBasedBirthmarkExtractorTest {
    public Birthmarks<KGram<Integer>> extract(String path, String type) throws Exception{
        URL location = getClass().getResource(path);
        ExtractorBuilder builder = new ExtractorBuilders().builder(new BirthmarkType(type));
        DataSource source = new DefaultDataSourceFactory().build(Paths.get(location.toURI()));
        Extractor extractor = builder.build(new ConfigurationBuilder().configuration());
        return extractor.extract(source);
    }

    @Test
    public void testBasic() throws Exception{
        Birthmarks<KGram<Integer>> birthmarks = extract("/test-resources/HelloWorld.class", "2-gram");

        assertThat(birthmarks.find(new ClassName("HelloWorld")).count(), is(1L));

        List<Birthmark<KGram<Integer>>> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<KGram<Integer>> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item));

        assertThat(elements.size(), is(5));
        assertThat(elements.get(0).toString(), is("25 183"));  // aload(19) invokespecial(b7)
        assertThat(elements.get(1).toString(), is("183 177")); // invokespecial(b7) return(b1)
        assertThat(elements.get(2).toString(), is("178 18"));  // getstatic(b2) ldc(12)
        assertThat(elements.get(3).toString(), is("18 182"));  // ldc(12) invokevirtual(b6)
        assertThat(elements.get(4).toString(), is("182 177")); // invokevirtual(b6) return(b1)
    }

    @Test
    public void testBasic2() throws Exception{
        Birthmarks<KGram<Integer>> birthmarks = extract("/test-resources/Fibonacci.class", "3-gram");

        assertThat(birthmarks.find(new ClassName("Fibonacci")).count(), is(1L));

        List<Birthmark<KGram<Integer>>> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<KGram<Integer>> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item));
        assertThat(elements.size(), is(40));

        // Constructor
        assertThat(elements.get(0), is(toKGram(Opcodes.ALOAD, Opcodes.INVOKESPECIAL, Opcodes.RETURN)));

        // run method
        assertThat(elements.get(1), is(toKGram(Opcodes.ALOAD, Opcodes.BIPUSH, Opcodes.INVOKEVIRTUAL)));
        assertThat(elements.get(2), is(toKGram(Opcodes.BIPUSH, Opcodes.INVOKEVIRTUAL, Opcodes.ASTORE)));
        assertThat(elements.get(3), is(toKGram(Opcodes.INVOKEVIRTUAL, Opcodes.ASTORE, Opcodes.ALOAD)));
        assertThat(elements.get(4), is(toKGram(Opcodes.ASTORE, Opcodes.ALOAD, Opcodes.INVOKEINTERFACE)));
        assertThat(elements.get(5), is(toKGram(Opcodes.ALOAD, Opcodes.INVOKEINTERFACE, Opcodes.ASTORE)));
        assertThat(elements.get(6), is(toKGram(Opcodes.INVOKEINTERFACE, Opcodes.ASTORE, Opcodes.ALOAD)));
        assertThat(elements.get(7), is(toKGram(Opcodes.ASTORE, Opcodes.ALOAD, Opcodes.INVOKEINTERFACE)));
        assertThat(elements.get(8), is(toKGram(Opcodes.ALOAD, Opcodes.INVOKEINTERFACE, Opcodes.IFEQ)));
        assertThat(elements.get(9), is(toKGram(Opcodes.INVOKEINTERFACE, Opcodes.IFEQ, Opcodes.ALOAD)));
        assertThat(elements.get(10), is(toKGram(Opcodes.IFEQ, Opcodes.ALOAD, Opcodes.INVOKEINTERFACE)));
        assertThat(elements.get(11), is(toKGram(Opcodes.ALOAD, Opcodes.INVOKEINTERFACE, Opcodes.CHECKCAST)));
        assertThat(elements.get(12), is(toKGram(Opcodes.INVOKEINTERFACE, Opcodes.CHECKCAST, Opcodes.ASTORE)));
        assertThat(elements.get(13), is(toKGram(Opcodes.CHECKCAST, Opcodes.ASTORE, Opcodes.GETSTATIC)));
        assertThat(elements.get(14), is(toKGram(Opcodes.ASTORE, Opcodes.GETSTATIC, Opcodes.ALOAD)));
        assertThat(elements.get(15), is(toKGram(Opcodes.GETSTATIC, Opcodes.ALOAD, Opcodes.INVOKEVIRTUAL)));
        assertThat(elements.get(16), is(toKGram(Opcodes.ALOAD, Opcodes.INVOKEVIRTUAL, Opcodes.GOTO)));
        assertThat(elements.get(17), is(toKGram(Opcodes.INVOKEVIRTUAL, Opcodes.GOTO, Opcodes.RETURN)));

        // stream method
        assertThat(elements.get(18), is(toKGram(Opcodes.ICONST_1, Opcodes.NEW, Opcodes.DUP)));
        assertThat(elements.get(19), is(toKGram(Opcodes.NEW, Opcodes.DUP, Opcodes.ACONST_NULL)));
        assertThat(elements.get(20), is(toKGram(Opcodes.DUP, Opcodes.ACONST_NULL, Opcodes.INVOKESPECIAL)));
        assertThat(elements.get(21), is(toKGram(Opcodes.ACONST_NULL, Opcodes.INVOKESPECIAL, Opcodes.INVOKESTATIC)));
        assertThat(elements.get(22), is(toKGram(Opcodes.INVOKESPECIAL, Opcodes.INVOKESTATIC, Opcodes.ARETURN)));

        // fibonacci method
        assertThat(elements.get(23), is(toKGram(Opcodes.ALOAD, Opcodes.INVOKEVIRTUAL, Opcodes.ILOAD)));
        assertThat(elements.get(24), is(toKGram(Opcodes.INVOKEVIRTUAL, Opcodes.ILOAD, Opcodes.I2L)));
        assertThat(elements.get(25), is(toKGram(Opcodes.ILOAD, Opcodes.I2L, Opcodes.INVOKEINTERFACE)));
        assertThat(elements.get(26), is(toKGram(Opcodes.I2L, Opcodes.INVOKEINTERFACE, Opcodes.INVOKEDYNAMIC)));
        assertThat(elements.get(27), is(toKGram(Opcodes.INVOKEINTERFACE, Opcodes.INVOKEDYNAMIC, Opcodes.INVOKEINTERFACE)));
        assertThat(elements.get(28), is(toKGram(Opcodes.INVOKEDYNAMIC, Opcodes.INVOKEINTERFACE, Opcodes.INVOKESTATIC)));
        assertThat(elements.get(29), is(toKGram(Opcodes.INVOKEINTERFACE, Opcodes.INVOKESTATIC, Opcodes.INVOKEINTERFACE)));
        assertThat(elements.get(30), is(toKGram(Opcodes.INVOKESTATIC, Opcodes.INVOKEINTERFACE, Opcodes.CHECKCAST)));
        assertThat(elements.get(31), is(toKGram(Opcodes.INVOKEINTERFACE, Opcodes.CHECKCAST, Opcodes.ARETURN)));

        // main method
        assertThat(elements.get(32), is(toKGram(Opcodes.NEW, Opcodes.DUP, Opcodes.INVOKESPECIAL)));
        assertThat(elements.get(33), is(toKGram(Opcodes.DUP, Opcodes.INVOKESPECIAL, Opcodes.ASTORE)));
        assertThat(elements.get(34), is(toKGram(Opcodes.INVOKESPECIAL, Opcodes.ASTORE, Opcodes.ALOAD)));
        assertThat(elements.get(35), is(toKGram(Opcodes.ASTORE, Opcodes.ALOAD, Opcodes.INVOKEVIRTUAL)));
        assertThat(elements.get(36), is(toKGram(Opcodes.ALOAD, Opcodes.INVOKEVIRTUAL, Opcodes.RETURN)));

        // inner method
        assertThat(elements.get(37), is(toKGram(Opcodes.NEW, Opcodes.DUP, Opcodes.ILOAD)));
        assertThat(elements.get(38), is(toKGram(Opcodes.DUP, Opcodes.ILOAD, Opcodes.INVOKESPECIAL)));
        assertThat(elements.get(39), is(toKGram(Opcodes.ILOAD, Opcodes.INVOKESPECIAL, Opcodes.ARETURN)));
    }

    private KGram<Integer> toKGram(int... values){
        return KGramBuilder.from(values);
    }

    @Test
    public void testBasic3() throws Exception{
        Birthmarks<KGram<Integer>> birthmarks = extract("/test-resources/MazeBuilder.class", "4-gram");

        assertThat(birthmarks.find(new ClassName("MazeBuilder")).count(), is(1L));

        List<Birthmark<KGram<Integer>>> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<KGram<Integer>> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item));

        // assertThat(elements.size(), is(6));
    }

    @Test
    public void testBasic4() throws Exception{
        Birthmarks<KGram<Integer>> birthmarks = extract("/test-resources/MyServer2.class", "5-gram");

        assertThat(birthmarks.find(new ClassName("MyServer2")).count(), is(1L));

        List<Birthmark<KGram<Integer>>> list = birthmarks.stream().collect(Collectors.toList());
        assertThat(list.size(), is(1));

        List<KGram<Integer>> elements = new ArrayList<>();
        list.get(0).forEach(item -> elements.add(item));

        // assertThat(elements.size(), is(3));
    }
}
