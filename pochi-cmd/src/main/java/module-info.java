/**
 * This module is for wrapping the groovy command (<code>groovy</code> and <code>groovysh</code>).
 *
 *  @author Haruaki Tamada
 */
module jp.cafebabe.pochicmd {
   requires args4j;
   opens jp.cafebabe.pochicmd to args4j;
}
