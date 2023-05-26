package model.item;

import controller.Position;

public class Teleport extends Item {

    public static final String NAME = "TELEPORT";
    public Position currentPosition;
    public Position destinPosition;

    public Teleport() {
        super(NAME);
        this.description = "Use to teleport to location";
        this.pick = false;

        this.image = getImage("teleport");
    }

    public Teleport(Position current, Position destin) {
        super(NAME);

        this.currentPosition = current;
        this.destinPosition = destin;

        this.description = "Use to teleport to location";
        this.pick = false;

        this.image = getImage("teleport");
    }

}
