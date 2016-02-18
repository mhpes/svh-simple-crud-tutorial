package entities;

import java.io.Serializable;

/**
 * Created by Edu on 18/02/2016.
 */
public class Example implements Serializable{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    public int getTelf() {
        return telf;
    }

    public void setTelf(int telf) {
        this.telf = telf;
    }

    int telf;

    Example(){}

    public Example(String name, int telf){
        setName(name);
        setTelf(telf);
    }
}
