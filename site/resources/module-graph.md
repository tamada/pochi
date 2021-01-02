```mermaid
graph TD
  birthmarks(jp.cafebabe.birthmarks)
  pochi(jp.cafebabe.pochi)
  kunai(jp.cafebabe.kunai)
  pochicmd(jp.cafebabe.pochicmd)
  logging(java.logging)
  vavr(io.vavr)
  asm(org.objectweb.asm)
  args4j(args4j)
  zipfs(jdk.zipfs)
  jackson(com.fasterxml.jackson.databind)

  pochicmd -- opens --> args4j
  pochi --> logging
  pochi --> birthmarks
  birthmarks --> logging
  birthmarks --> jackson
  birthmarks --transitive--> vavr 
  birthmarks --transitive--> kunai
  kunai --transitive--> asm
  kunai --> zipfs
```

