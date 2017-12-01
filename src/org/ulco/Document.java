package org.ulco;

import java.util.Iterator;
import java.util.Vector;

public class Document implements Parsable{
    public Document() {
        m_layers = new Vector<Layer>();
    }

    public Document(String json) {
        Vector<String> separatorList = new Vector<String>();
        separatorList.add("layers");
        separatorList.add("}");
        m_layers=JSON.parseItems(json, separatorList);
    }


    public Layer createLayer() {
        Layer layer = new Layer();

        m_layers.add(layer);
        return layer;
    }

    public Vector<Layer> getLayer() {
        return m_layers;
    }

    public int getLayerNumber() {
        return m_layers.size();
    }

    public int getObjectNumber() {
        int size = 0;

        for (int i = 0; i < m_layers.size(); ++i) {
            size += m_layers.elementAt(i).getObjectNumber();
        }
        return size;
    }



    public String toJson() {
        String str = "{ type: document, layers: { ";

        for (int i = 0; i < m_layers.size(); ++i) {
            Layer element = m_layers.elementAt(i);

            str += element.toJson();
            if (i < m_layers.size() - 1) {
                str += ", ";
            }
        }
        return str + " } }";
    }

    private Vector<Layer> m_layers;
}
