package no.ntnu.idatt2003.chaosgame.components;

import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;

/**
 * Functional interface observer class for observing
 * changes to {@link ChaosGame}. The observer meant to be used
 * for each iteration step.
 *
 * @author 10052
 * @version 1.0
 */
@FunctionalInterface
public interface ChaosGameObserver {

    /**
     * The method which is called when a change
     * occurs in the {@link ChaosGame} class, with
     * the new updated vector point.
     *
     * @param point The new vector point
     */
    void update(Vector2D point);

}
