package io.github.baka4n.misty;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.JDB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.QuickIO;
import io.github.baka4n.misty.io.Entity;
import net.mamoe.mirai.contact.Group;

import java.util.HashMap;
import java.util.Map;

public class Database implements AutoCloseable {
    public static final Map<Long, Databases> databases = new HashMap<>();
    public final JDB db;
    public Database(String path, Group group) {
        db = QuickIO.db(Config.of(config -> {
            config.name(String.valueOf(group.getId()));
            config.path(path);
        }));
    }

    public <T extends Entity> Collection<T> collection(Class<T> type) {
        return db.collection(type);
    }
    @Override
    public void close() {
        db.close();
    }
}
