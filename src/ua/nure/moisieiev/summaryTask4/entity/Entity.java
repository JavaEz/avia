package ua.nure.moisieiev.summaryTask4.entity;
/**
 * Root of all entities which have identifier field.
 *
 * @author S.Moisieiev
 */

import java.io.Serializable;

public abstract class Entity implements Serializable {



    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
