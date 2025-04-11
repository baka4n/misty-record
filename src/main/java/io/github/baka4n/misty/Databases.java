package io.github.baka4n.misty;

import com.github.artbits.quickio.api.Collection;
import io.github.baka4n.misty.io.Economy;
import io.github.baka4n.misty.io.Info;
import io.github.baka4n.misty.io.Level;
import net.mamoe.mirai.contact.Group;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/11 20:15:34}
 */
public class Databases implements AutoCloseable {
    public final Database economy;
    public final Database level;
    public final Database info;
    public final Collections all;

    public Databases(Group group, String economy, String level, String info) {
        this.economy = new Database(economy, group);
        this.level = new Database(level, group);
        this.info = new Database(info, group);
        all = new Collections(this.economy, this.level, this.info);
    }

    @Override
    public void close() {
        economy.close();
        level.close();
        info.close();
    }

    public static class Collections {
        public final Collection<Economy> economy;
        public final Collection<Level> level;
        public final Collection<Info> info;

        public Collections(Database economy, Database level, Database info) {
            this.economy = economy.collection(Economy.class);
            this.level = level.collection(Level.class);
            this.info = info.collection(Info.class);
        }
    }
}
