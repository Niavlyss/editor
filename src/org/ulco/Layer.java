package org.ulco;

import java.util.Vector;

public class Layer implements Parsable{
    public Layer() {
        m_list = new Vector<GraphicsObject>();
        m_ID = ID.getInstance().genererNextId();
    }

    public Layer(String json) {
        Vector<String> separators = new Vector<String>();
        separators.add("objects");
        separators.add("groups");
        separators.add("}");
        m_list = JSON.parseItems(json, separators);
    }

    public void add(GraphicsObject o) {
        m_list.add(o);
    }

    public GraphicsObject get(int index) {
        return m_list.elementAt(index);
    }

    public int getObjectNumber() {
        return m_list.size();
    }

    public int getID() {
        return m_ID;
    }

    public Vector<GraphicsObject> getObjectList() {
        return m_list;
    }



    public String toJson() {
        String str = "{ type: layer, objects : { ";

        for (int i = 0; i < m_list.size(); ++i) {
            GraphicsObject element = m_list.elementAt(i);

            str += element.toJson();
            if (i < m_list.size() - 1) {
                str += ", ";
            }
        }
        return str + " } }";
    }

    private Vector<GraphicsObject> m_list;
    private int m_ID;
}
