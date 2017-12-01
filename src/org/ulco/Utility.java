package org.ulco;

import java.util.Vector;

public class Utility {
    public static GraphicsObjects select(Point p, double distance, Layer l){
        GraphicsObjects objectList = new GraphicsObjects();
        Vector<GraphicsObject> graphicsObjList = l.getObjectList();
        for(GraphicsObject obj : graphicsObjList){
            if(obj.isClosed(p,distance)){
                objectList.add(obj);
            }
        }
        return objectList;
    }


    public static GraphicsObjects select(Point p,double distance, Document doc){
        GraphicsObjects objectList = new GraphicsObjects();
        Vector<Layer> layerList = doc.getLayer();
        for(Layer l : layerList){
            objectList.addAll(Utility.select(p,distance,l));
        }
        return objectList;
    }
}
