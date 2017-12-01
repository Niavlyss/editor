package org.ulco.test;

import junit.framework.TestCase;
import org.ulco.*;

public class ClosedTest extends TestCase {
    public void testIsClosed() throws Exception{
        Group group = new Group();
        Group group2 = new Group();

        Square square = new Square(new Point(1,1),5);
        Circle circle = new Circle(new Point(6,6),4);
        Rectangle rectangle = new Rectangle(new Point(-5,11), 3.2, 8);

        group.add(square);
        group.add(circle);

        group2.add(group);
        group2.add(rectangle);

        Point pt = new Point(6,6);
        assertTrue(group2.isClosed(pt,4));
    }

}
