package io.github.baka4n.misty.io;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.function.Consumer;

@SuppressWarnings("ALL")
@Getter @Setter
@Accessors(chain = true, fluent = true)
public class Economy extends Entity {
    public boolean isGroup;
    public long uid;
    public BigDecimal mistyCoin;
    public BigDecimal lowerSpiritStone;
    public BigDecimal midSpiritStone;
    public BigDecimal topSpiritStone;
    public BigDecimal bestSpiritStone;


    public static final Economy DEFAULT = new Economy()
            .mistyCoin(new BigDecimal(1000000))
            .lowerSpiritStone(BigDecimal.ZERO)
            .midSpiritStone(BigDecimal.ZERO)
            .topSpiritStone(BigDecimal.ZERO)
            .bestSpiritStone(BigDecimal.ZERO);

    public static Economy of(Consumer<Economy> c) {
        Economy e = new Economy();
        c.accept(e);
        return e;
    }

    @Override
    public Entity copy() {
        return new Economy()
                .isGroup(isGroup)
                .uid(uid)
                .mistyCoin(mistyCoin)
                .lowerSpiritStone(lowerSpiritStone)
                .midSpiritStone(midSpiritStone)
                .topSpiritStone(topSpiritStone)
                .bestSpiritStone(bestSpiritStone);
    }
}
