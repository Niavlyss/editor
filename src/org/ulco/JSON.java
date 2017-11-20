package org.ulco;

import java.lang.reflect.Constructor;

public class JSON {
    static public GraphicsObject parse(String json) {
        GraphicsObject o = null;
        String str = json.replaceAll("\\s+", "");
        String type = str.substring(str.indexOf("type") + 5, str.indexOf(","));

        // On passe le premier caractère en majuscule
        type = type.substring(0, 1).toUpperCase() + type.substring(1);

        // on cree un objet Class
        Class classe;
        try {
            // on récupère le type de l'objet que l'on souhaite instancier
            classe = Class.forName("org.ulco."+ type);

            // on crée les paramètres du constucteur
            Class[] parametres = new Class[]{String.class};

            // on crée le constructeur
            Constructor ct = classe.getConstructor(parametres);

            // on crée notre nouvel objet
            o = (GraphicsObject)ct.newInstance(new String[]{str});
        } catch (Exception e) {
            e.printStackTrace();
        }



        return o;
    }

    static public Group parseGroup(String json) {
        return new Group(json);
    }

    static public Layer parseLayer(String json) {
        return new Layer(json);
    }

    static public Document parseDocument(String json) {
        return new Document(json);
    }
}
