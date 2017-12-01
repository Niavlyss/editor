package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject{

    public boolean isClosed(Point pt, double distance) {
        for(GraphicsObject obj : m_objectList){
            if(obj.isClosed(pt,distance)){
                return true;
            }
        }
        return false;
    }

    public Group(){
        m_objectList = new Vector<>();
        m_ID = ID.getInstance().genererNextId();
    }

    public Group(String json) {
        Vector<String> separators = new Vector<String>();
        separators.add("objects");
        separators.add("groups");
        separators.add("}");
        m_objectList = JSON.parseItems(json, separators);
    }

    public void add(Object object) {
        addObject((GraphicsObject)object);
    }

    private void addObject(GraphicsObject object) {
        m_objectList.add(object);
    }




    public Group copy() {
        Group g = new Group();

        for (GraphicsObject o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);
            g.addObject(o.copy());
        }
        return g;
    }

    public int getID() {
        return m_ID;
    }

    public void move(Point delta) {
        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);

            element.move(delta);
        }
    }

    Point center(){
        return null;
    }






    public int size() {
        int size = 0;

        for (int i = 0; i < m_objectList.size(); i++) {
            size += m_objectList.get(i).size();
        }

        return size;
    }

    public String toJson() {
        String str = "{ type: group, objects : { ";

        for (int i = 0; i < m_objectList.size(); ++i) {
            if ( m_objectList.elementAt(i).isGraphicObject() == true) {
                GraphicsObject element = m_objectList.elementAt(i);

                str += element.toJson();
                if (i < m_objectList.size() - 1) {
                    str += ", ";
                }
            }
        }
        str += " }, groups : { ";

        for (int i = 0; i < m_objectList.size(); ++i) {
            if ( m_objectList.elementAt(i).isGraphicObject() == false) {
                Group element = (Group)m_objectList.elementAt(i);
                str += element.toJson();
            }
        }
        return str + " } }";
    }

    public String toString() {
        String str = "group[[";
        int countNbObjetSimple = 1;

        for (int i = 0; i < m_objectList.size(); ++i) {
            if ( m_objectList.elementAt(i).isGraphicObject() == true) {
                GraphicsObject element = m_objectList.elementAt(i);
                countNbObjetSimple++;

                str += element.toString();
                if (i < m_objectList.size() - 1 && countNbObjetSimple == compterObjetSimple()) {
                    str += ", ";
                }
            }
        }
        str += "],[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            if ( m_objectList.elementAt(i).isGraphicObject() == false) {
                Group element = (Group)m_objectList.elementAt(i);

                str += element.toString();
            }
        }
        return str + "]]";
    }

    @Override
    public boolean  isGraphicObject() {
        return false;
    }

    private int compterObjetSimple() {
        int nbObjetSimple = 0;
        for (int i = 0; i < m_objectList.size(); ++i) {
            if (m_objectList.elementAt(i).isGraphicObject()) {
                nbObjetSimple += 1;
            }
        }
        return nbObjetSimple;
    }

    private Vector<GraphicsObject> m_objectList;
    private int m_ID;
}
