package io.github.baka4n.misty.io;

import com.github.artbits.quickio.api.Collection;
import io.github.baka4n.misty.io.json.BaseConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/11 20:10:12}
 */
@Getter
@Setter
@Accessors(chain = true, fluent = true)
public class Info extends Entity {
    public static final int DEFAULT_COOLDOWN = 120;//second
    public long uid;
    public boolean death;//死亡冷却
    public LocalDateTime deathCooldown;
    public LocalDateTime restartCooldown;

    public boolean isDeath(Collection<Info> collection) {
        if (deathCooldown != null && deathCooldown.plusSeconds(BaseConfig.BASE_CONFIG.DEATH_COOLDOWN.get()).isBefore(LocalDateTime.now())) {
            deathCooldown(null).death(false);
            collection.save(this);
        }
        return death();
    }

    public static Info DEFAULT() {
        return new Info().death(false);
    }

    @Override
    public Info copy() {
        return new Info()
                .deathCooldown(deathCooldown)
                .restartCooldown(restartCooldown)
                .death(death);
    }
}
