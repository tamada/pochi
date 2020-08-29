// prints pochi information.

println("========== birthmark extractor names ==========")
println(pochi.extractorNames())

println("========== birthmark comparator names ==========")
println(pochi.comparatorNames())

println("========== pair matcher names ==========")
println(pochi.matcherNames())

config = pochi.config()
println("========== system library rules ==========")
println(config.rules())

println("========== properties ==========")
config.propertyStream()
    .each(item -> println(item))
