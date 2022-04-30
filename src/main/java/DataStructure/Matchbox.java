package DataStructure;
import java.util.Date;
import java.io.*;
import java.io.Serializable;

//Datastructure to represent Matchboxes in the Menace
public class Matchbox implements Serializable {

    private String layout;
    private int[] beadList;

    public Matchbox()
    {}

    public Matchbox(String layout,int[] beadList)
    {
        this.layout=layout;
        this.beadList=beadList;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public int[] getBeadList() {
        return beadList;
    }

    public void setBeadList(int[] beadList) {
        this.beadList = beadList;
    }
}
