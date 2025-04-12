package io.github.baka4n.misty.io;

import io.github.baka4n.misty.interfaces.ILevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
public class Level extends Entity implements ILevel<Level> {
    public long uid;
    public BigDecimal
            goldSpiritualPower,
            woodSpiritualPower,
            waterSpiritualPower,
            fireSpiritualPower,
            dirtSpiritualPower;//灵力储量
    public BigDecimal
            windSpiritualPower,
            thunderSpiritualPower,
            mountainsSpiritualPower,
            riversSpiritualPower,
            heavenSpiritualPower,
            yinSpiritualPower,
            yangSpiritualPower
    ;//衍生灵力储量
    public BigInteger qiTraining;//default = 1;练气一重
    public BigDecimal gold, wood, water, fire, dirt;//金木水火土
    public BigInteger forgingBody;//default = 1;锻体一重
    public BigInteger heart, liver, spleen, lungs, kidneys;//心、肝、脾、肺、肾五脏
    public BigInteger smallIntestine, gall, gastric, largeIntestine, bladder, trifocals;
    //小肠、胆、胃、大肠、膀胱, 三焦 六腑

    public static final Random random = new Random();

    public static Level DEFAULT() {
        return new Level()
                .qiTraining(BigInteger.ONE)
                .goldSpiritualPower(BigDecimal.ZERO)
                .woodSpiritualPower(BigDecimal.ZERO)
                .waterSpiritualPower(BigDecimal.ZERO)
                .fireSpiritualPower(BigDecimal.ZERO)
                .dirtSpiritualPower(BigDecimal.ZERO)
                .windSpiritualPower(BigDecimal.ZERO)
                .thunderSpiritualPower(BigDecimal.ZERO)
                .mountainsSpiritualPower(BigDecimal.ZERO)
                .riversSpiritualPower(BigDecimal.ZERO)
                .heavenSpiritualPower(BigDecimal.ZERO)
                .yinSpiritualPower(BigDecimal.ZERO)
                .yangSpiritualPower(BigDecimal.ZERO)
                .gold(BigDecimal.valueOf(random.nextInt(0, 11)))
                .wood(BigDecimal.valueOf(random.nextInt(0, 11)))
                .water(BigDecimal.valueOf(random.nextInt(0, 11)))
                .fire(BigDecimal.valueOf(random.nextInt(0, 11)))
                .dirt(BigDecimal.valueOf(random.nextInt(0, 11)))
                .forgingBody(BigInteger.ONE)
                .heart(BigInteger.ZERO)
                .liver(BigInteger.ZERO)
                .spleen(BigInteger.ZERO)
                .lungs(BigInteger.ZERO)
                .kidneys(BigInteger.ZERO)
                .smallIntestine(BigInteger.ZERO)
                .gall(BigInteger.ZERO)
                .gastric(BigInteger.ZERO)
                .largeIntestine(BigInteger.ZERO)
                .bladder(BigInteger.ZERO)
                .trifocals(BigInteger.ZERO)
                ;
    }

    @Override
    public Level copy() {
        return new Level()
                .qiTraining(qiTraining)
                .goldSpiritualPower(goldSpiritualPower)
                .waterSpiritualPower(waterSpiritualPower)
                .fireSpiritualPower(fireSpiritualPower)
                .dirtSpiritualPower(dirtSpiritualPower)
                .windSpiritualPower(windSpiritualPower)
                .thunderSpiritualPower(thunderSpiritualPower)
                .mountainsSpiritualPower(mountainsSpiritualPower)
                .riversSpiritualPower(riversSpiritualPower)
                .heavenSpiritualPower(heavenSpiritualPower)
                .yinSpiritualPower(yinSpiritualPower)
                .yangSpiritualPower(yangSpiritualPower)
                .gold(gold)
                .wood(wood)
                .water(water)
                .fire(fire)
                .dirt(dirt)
                .forgingBody(forgingBody)
                .heart(heart)
                .liver(liver)
                .spleen(spleen)
                .lungs(lungs)
                .kidneys(kidneys)
                .smallIntestine(smallIntestine)
                .gall(gall)
                .gastric(gastric)
                .largeIntestine(largeIntestine)
                .bladder(bladder)
                .trifocals(trifocals)
                ;
    }

    @Override
    public Level self() {
        return this;
    }
}
