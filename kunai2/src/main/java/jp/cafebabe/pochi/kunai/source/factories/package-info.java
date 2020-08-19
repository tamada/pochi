/**
 * This package includes classes related with data source factories.
 * 
 * Typical usage is:
 * <pre>
 * Path path = Paths.get("some/data/source/path");
 * DataSourceFactory factory = DefaltDataSourceFactory();
 * try(DataSource source = factory.build(path)){
 *     source.stream()
 *         .forEach(entry -&gt;
 *             // some operation
 *         );
 * }</pre>
 * 
 * @author Haruaki Tamada
 */
package jp.cafebabe.pochi.kunai.source.factories;
