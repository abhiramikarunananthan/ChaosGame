package no.ntnu.idatt2003.chaosgame.transforms;

import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;

/**
 * Functional interface class for performing mathematical
 * transformations.
 *
 * @author 10052
 * @version 1.0
 */
@FunctionalInterface
public interface Transform2D {
    /**
     * The method for performing the mathematical
     * transformation on the specified {@link Vector2D}
     *
     * @param point The vector point which the transformation will
     *              be applied to
     * @return {@link Vector2D} as a result of the transformation
     */
    Vector2D transform(Vector2D point);

}
