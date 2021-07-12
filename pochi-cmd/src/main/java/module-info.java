/**
 * This module is for wrapping the groovy command (<code>groovy</code> and <code>groovysh</code>).
 *
 *  @author Haruaki Tamada
 */
module jp.cafebabe.pochicmd {
   requires info.picocli;
   opens jp.cafebabe.pochicmd to info.picocli;
}
