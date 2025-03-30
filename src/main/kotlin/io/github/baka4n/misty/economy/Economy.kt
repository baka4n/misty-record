package io.github.baka4n.misty.economy

import net.mamoe.mirai.contact.Group
import java.math.BigDecimal
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.name

data class Economy(
    var mistyCoin: BigDecimal,
    var lowerSpiritStone: BigDecimal,
    var midSpiritStone: BigDecimal,
    var topSpiritStone: BigDecimal,
    var bestSpiritStone: BigDecimal,
) {
    fun copy(economy: Economy): Economy {
        mistyCoin = economy.mistyCoin
        lowerSpiritStone = economy.lowerSpiritStone
        midSpiritStone = economy.midSpiritStone
        bestSpiritStone = economy.bestSpiritStone
        topSpiritStone = economy.topSpiritStone
        return this
    }
}

val dataHome: Path = Paths.get("").toAbsolutePath().resolve("misty")

val gue: Map<Long, Map<Long, Economy>> = HashMap()// group -> user --> money

public fun load() {
    for (path in Files.list(dataHome)) {
        val grouopId = path.name.toLong()
    }

}

public fun create(group: Group) {
    val groupDir = dataHome.resolve(group.id.toString());
    val moneyData: Path = groupDir.resolve("money.json")

}

public fun save() {

}