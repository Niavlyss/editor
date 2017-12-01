package org.ulco;

public class ID {
    private int ID = 0;
    private static ID INSTANCE = null;
    private ID() {}
    public static ID getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ID();
        }
        return INSTANCE;
    }
    public int genererNextId() {
        return ++ID;
    }
    public int getID() {
        return ID;
    }
}