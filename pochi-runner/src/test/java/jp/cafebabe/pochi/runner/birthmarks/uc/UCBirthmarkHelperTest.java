package jp.cafebabe.pochi.runner.birthmarks.uc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Type;

import jp.cafebabe.pochi.birthmarks.config.ConfigurationBuilder;
import jp.cafebabe.pochi.birthmarks.entities.Element;
import jp.cafebabe.pochi.birthmarks.entities.Elements;

public class UCBirthmarkHelperTest {
    private UCBirthmarkHelper helper;

    @Before
    public void setUp(){
        helper = new UCBirthmarkHelper(new ConfigurationBuilder().configuration());
    }

    @Test
    public void testEachMethod(){
        assertThat(helper.normalize("java.lang.String"), is("java.lang.String"));
        assertThat(helper.normalize("java/lang/String"), is("java.lang.String"));
        assertThat(helper.normalize("Ljava/lang/String;"), is("java.lang.String"));

        assertThat(helper.stripType(Type.getType("Ljava/lang/String;")), is(Type.getType("Ljava/lang/String;")));
        assertThat(helper.stripType(Type.getType("[Ljava/lang/String;")), is(Type.getType("Ljava/lang/String;")));
        assertThat(helper.stripType(Type.getType("[[Ljava/lang/String;")), is(Type.getType("Ljava/lang/String;")));
    }

    @Test
    public void testBasic(){
        helper.add("java.lang.String");
        helper.add("Ljava/lang/Integer;");
        helper.add("java/lang/System");
        Type signature1 = Type.getMethodType("([[Ljava/io/InputStream;[[I)Ljava/io/BufferedReader;");
        helper.add(signature1.getReturnType());
        helper.addAll(signature1.getArgumentTypes());
        Type signature2 = Type.getMethodType("(Ljava/io/OutputStream;Ljava/lang/Double;)Ljava/io/PrintWriter;");
        helper.add(signature2.getReturnType());
        helper.addAll(signature2.getArgumentTypes());

        String[] nullStrings = null;
        helper.addAll(nullStrings);

        Type[] nullTypes = null;
        helper.addAll(nullTypes);

        Elements elements = helper.build();
        List<Element> list = new ArrayList<>();
        elements.forEach(item -> list.add(item));

        assertThat(list.size(), is(8));
        assertThat(list.get(0), is(new Element("java.io.BufferedReader")));
        assertThat(list.get(1), is(new Element("java.io.InputStream")));
        assertThat(list.get(2), is(new Element("java.io.OutputStream")));
        assertThat(list.get(3), is(new Element("java.io.PrintWriter")));
        assertThat(list.get(4), is(new Element("java.lang.Double")));
        assertThat(list.get(5), is(new Element("java.lang.Integer")));
        assertThat(list.get(6), is(new Element("java.lang.String")));
        assertThat(list.get(7), is(new Element("java.lang.System")));
    }
}
