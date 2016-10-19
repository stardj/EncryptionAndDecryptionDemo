package org.stardj.User;

/**
 * Created by stardj on 16/7/22.
 */
public class iMovies {

    private int ID;
    private String Name;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }
    public iMovies(int ID,String name){
        this.setID(ID);
        this.setName(name);
    }
}
