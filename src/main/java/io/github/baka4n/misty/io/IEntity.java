package io.github.baka4n.misty.io;

public interface IEntity {
    <T extends Entity> T copy();
}
