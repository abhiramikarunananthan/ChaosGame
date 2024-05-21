package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;

@FunctionalInterface
public interface ChaosGameObserver {

    void update(Vector2D point);

}
