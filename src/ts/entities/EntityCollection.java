package ts.entities;

import ts.Point;
import java.util.HashMap;
import java.util.ArrayList;

public class EntityCollection {

    public ArrayList<Entity> container = new ArrayList<Entity>();

    public void add(Entity entity)
    {
        container.add(entity);
    }

}
