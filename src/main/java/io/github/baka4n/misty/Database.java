package io.github.baka4n.misty;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.JDB;
import com.github.artbits.quickio.core.Config;
import com.github.artbits.quickio.core.QuickIO;
import io.github.baka4n.misty.io.Economy;
import io.github.baka4n.misty.io.Entity;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Database implements AutoCloseable {
    public static final Map<Long, Database> groupDatabase = new HashMap<>();
    public static final Map<Long, Database> groupLevelDatabase = new HashMap<>();
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

    public <T extends Entity> T setOrCreate(Class<T> type, Predicate<T> setter, Consumer<T> setterConsumer, T defaultT) {
        Collection<T> collection = collection(type);
        T one = collection.findOne(setter);
        if (one != null) {
            setterConsumer.accept(one);
            save(type, one);
            return one;
        } else {
            setterConsumer.accept(defaultT);
            save(type, defaultT);
            return defaultT;
        }

    }

    public <T extends Entity> void save(Class<T> type, T t) {
        collection(type).save(t);
    }


    @Override
    public void close() {
        db.close();
    }
}
