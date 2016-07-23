birthmarks{
    birthmark{
      {
        "name": "uc",
        "extractor": "com.github.ebis.birthmarks.impl.UCExtractor",
        "comparator": "com.github.ebis.birthmarks.impl.JaccardIndexComparator"
      },
      {
        "name": "k-gram",
        "extractor": "com.github.ebis.birthmarks.impl.KGramExtractor",
        "comparator": "com.github.ebis.birthmarks.impl.JaccardIndexComparator"
      },
      {
        "name": "wsp",
        "extractor": "com.github.ebis.birthmarks.impl.WSPExtractor",
        "comparator": "com.github.ebis.birthmarks.impl.WSPComparator"
      }
    }
  },
  "well-known-classes": [
    { "type": "prefix", "pattern": "java." },
    { "type": "prefix", "pattern": "javax." },
    { "type": "prefix", "pattern": "org.omg." },
    { "type": "prefix", "pattern": "org.ietf." },
    { "type": "prefix", "pattern": "org.w3c." },
    { "type": "prefix", "pattern": "org.xml.sax." },
    { "type": "prefix", "pattern": "org.apache." },
  ],
}
