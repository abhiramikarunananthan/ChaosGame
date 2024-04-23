package no.ntnu.idatt2003.chaosgame.transforms;

import no.ntnu.idatt2003.chaosgame.tensors.Vector2D;

public interface Transform2D {
    Vector2D transform(Vector2D point);

}
