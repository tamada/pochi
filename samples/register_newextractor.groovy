// create new birthmark extractor.
// This sample registers 7-gram birthmark extractor.

import jp.cafebabe.pochi.birthmarks.kgram.KGramBasedExtractorBuilder

// create 7-gram birthmark extractor.
sevenGram = new KGramBasedExtractorBuilder(7);
pochi.register(sevenGram)

println("========= extractor ==========")
println(pochi.extractorNames())
