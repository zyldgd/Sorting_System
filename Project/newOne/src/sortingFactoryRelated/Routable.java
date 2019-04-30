package sortingFactoryRelated;

import java.awt.*;
import java.util.Queue;

public interface Routable {
    public Queue<Direction> routeSearch(Point origination, Point destination);
}
