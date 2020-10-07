// create new birthmark extractor.
// This sample registers 7-gram birthmark extractor.

import jp.cafebabe.pochi.birthmarks.kgram.KGramBasedExtractorBuilder

println("========= extractor ==========")
println(pochi.extractorNames())

// create 7-gram birthmark extractor.
sevenGram = new KGramBasedExtractorBuilder(7);
pochi.register(sevenGram)

println(pochi.extractorNames())
