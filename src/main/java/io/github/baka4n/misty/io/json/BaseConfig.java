package io.github.baka4n.misty.io.json;

import net.mamoe.mirai.console.data.IntValue;
import net.mamoe.mirai.console.data.Value;
import net.mamoe.mirai.console.data.java.JavaAutoSavePluginConfig;
import org.jetbrains.annotations.NotNull;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/11 22:18:57}
 */
public class BaseConfig extends JavaAutoSavePluginConfig {
    public static final BaseConfig BASE_CONFIG = new BaseConfig();
    public BaseConfig() {
        super("BaseConfig");
    }

    public final Value<Integer> DEATH_COOLDOWN = value("death_cooldown", 120);
    public final Value<Integer> RESTART_COOLDOWN = value("restart_cooldown", 10);
}
