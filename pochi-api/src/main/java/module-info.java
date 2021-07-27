/**
 * <p>This module <code>jp.cafebabe.birthmarks</code> represents APIs for birthmarking.
 * </p><p>
 * The below flowchart shows birthmarking flow in the <strong>pochi</strong>.
 * </p><p>
 * <img alt="birthmarking flow in the pochi" src="https://tamada.github.io/pochi/images/birthmarking_process.svg">
 * </p><p>
 * The classes/interfaces in the flowchart are separated into <code>{@link jp.cafebabe.birthmarks.Task}</code>s and entities.
 * </p>
 *
 * <dl>
 *   <dt>{@link jp.cafebabe.birthmarks.Task}</dt>
 *   <dd>
 *     Every tasks have the implementation class of corresponding {@link jp.cafebabe.birthmarks.TaskBuilder}.
 *   </dd>
 *   <dd>
 *     <ul>
 *       <li>{@link jp.cafebabe.birthmarks.extractors.Extractor} extracts birthmarks from given <code>{@link jp.cafebabe.kunai.source.DataSource}</code>.
 *         The implementation classes of this interface are multiply defined in the module {@link jp.cafebabe.pochi}.
 *       </li>
 *       <li>{@link jp.cafebabe.birthmarks.pairs.PairMatcher}
 *         matches pair of two birthmarks from given birthmark list.
 *       </li>
 *       <li>{@link jp.cafebabe.birthmarks.comparators.Comparator}
 *         computes similarity between the given pair (two birthmarks), and generates {@link jp.cafebabe.birthmarks.comparators.Comparison}
 *       </li>
 *       <li>{@link jp.cafebabe.birthmarks.BirthmarkParser}
 *         parses csv formatted birthmark list and build {@link jp.cafebabe.birthmarks.entities.Birthmark} objects.
 *       </li>
 *     </ul>
 *   </dd> 
 *   <dt>{@link jp.cafebabe.birthmarks.entities Entities}</dt>
 *   <dd>
 *     <ul>
 *       <li>{@link jp.cafebabe.birthmarks.entities.Birthmark}
 *         shows the birthmark of the certain class file, which contains {@link jp.cafebabe.birthmarks.entities.Elements}.
 *       </li>
 *       <li>{@link jp.cafebabe.birthmarks.entities.Pair Pair&lt;Birthmark&gt;}
 *         is the pair of birthmarks for comparing them.
 *       </li>
 *       <li>{@link jp.cafebabe.birthmarks.comparators.Comparison}
 *         shows the comparison results of pair of birthmarks.
 *       </li>
 *     </ul>
 *    </dd>
 * </dl>
 *
 * @author Haruaki Tamada
 */
module jp.cafebabe.birthmarks {
   requires transitive jp.cafebabe.kunai;
   requires transitive io.vavr;
   requires java.logging;
   requires com.fasterxml.jackson.databind;

   exports jp.cafebabe.birthmarks;
   exports jp.cafebabe.birthmarks.comparators;
   exports jp.cafebabe.birthmarks.config;
   exports jp.cafebabe.birthmarks.entities;
   exports jp.cafebabe.birthmarks.entities.elements;
   exports jp.cafebabe.birthmarks.extractors;
   exports jp.cafebabe.birthmarks.pairs;

   uses jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
   uses jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
   uses jp.cafebabe.birthmarks.pairs.PairMatcherBuilder;
}
