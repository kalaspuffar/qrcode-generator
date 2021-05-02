package org.ea;

public enum Quality {
    LEVEL_L(0),
    LEVEL_M(1),
    LEVEL_Q(2),
    LEVEL_H(3);

    final int placement;

    Quality(int placement) {
        this.placement = placement;
    }

    public int getPlacement() {
        return placement;
    }
}
