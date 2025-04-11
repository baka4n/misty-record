package io.github.baka4n.misty.io;

import io.github.baka4n.misty.interfaces.ILevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
public class Level extends Entity implements ILevel<Level> {
    public long uid;
    public BigInteger qiTraining;//default = 1;练气一重
    public BigDecimal gold, wood, water, fire, earth;//金木水火土
    public BigInteger forgingBody;//default = 1;锻体一重
    public BigInteger heart, liver, spleen, lungs, kidneys;//心、肝、脾、肺、肾五脏
    public BigInteger smallIntestine, gall, gastric, largeIntestine, bladder, trifocals;
    //小肠、胆、胃、大肠、膀胱, 三焦 六腑

    public static Level DEFAULT() {
        return new Level()
                .qiTraining(BigInteger.ONE)
                .gold(BigDecimal.valueOf(Math.random()))
                .wood(BigDecimal.valueOf(Math.random()))
                .water(BigDecimal.valueOf(Math.random()))
                .fire(BigDecimal.valueOf(Math.random()))
                .earth(BigDecimal.valueOf(Math.random()))
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
                .gold(gold)
                .wood(wood)
                .water(water)
                .fire(fire)
                .earth(earth)
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
