package io.github.baka4n.misty.interfaces;

import io.github.baka4n.misty.io.Level;

import java.math.BigInteger;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/11 17:16:48}
 */
public interface ILevel<T extends Level> {
    T self();

    default String levelStringQi() {
        BigInteger qiTraining = self().qiTraining;
        BigInteger[] divideAndRemainder = qiTraining.divideAndRemainder(BigInteger.TEN);

        BigInteger bigInteger = divideAndRemainder[0];
        if (bigInteger.compareTo(BigInteger.valueOf(8)) <= 0) {
            return "%s%s重".formatted(
                    bigInteger.equals(BigInteger.ZERO) ?
                            "练气": bigInteger.equals(BigInteger.ONE)
                            ? "筑基": bigInteger.equals(BigInteger.TWO)
                            ? "金丹": bigInteger.equals(BigInteger.valueOf(3))
                            ? "元婴": bigInteger.equals(BigInteger.valueOf(4))
                            ? "化神": bigInteger.equals(BigInteger.valueOf(5))
                            ? "炼虚": bigInteger.equals(BigInteger.valueOf(6))
                            ? "合体": bigInteger.equals(BigInteger.valueOf(7))
                            ? "大乘": "渡劫"
                    ,
                    divideAndRemainder[1]
            );
        } else {
            BigInteger bigIntegerXian = qiTraining.divide(BigInteger.valueOf(100));
            return bigIntegerXian.compareTo(BigInteger.valueOf(128)) > 0
                    ? "仙帝" :
                    bigIntegerXian.compareTo(BigInteger.valueOf(64)) > 0
                    ? "仙尊" :
                    bigIntegerXian.compareTo(BigInteger.valueOf(32)) > 0
                    ? "仙君" :
                    bigIntegerXian.compareTo(BigInteger.valueOf(16)) > 0
                    ? "大罗金仙" :
                    bigIntegerXian.compareTo(BigInteger.valueOf(8)) > 0
                    ? "金仙" :
                    bigIntegerXian.compareTo(BigInteger.valueOf(4)) > 0
                    ? "玄仙" :
                    bigIntegerXian.compareTo(BigInteger.valueOf(2)) > 0
                    ? "真仙" :
                    bigIntegerXian.compareTo(BigInteger.valueOf(1)) > 0
                    ? "天仙" : "地仙";
        }

    }
}
