
package DataStructure;
import Layouts.GenerateLayouts;

import java.util.*;

//Datastructure to represent Matchboxes in the Menace
public class StoreData{

    public static void main(String[] args){

        HashMap<String,String> layout=new HashMap<>();
        HashMap<String,int[]> layoutMap=new HashMap<>();
        GenerateLayouts g=new GenerateLayouts();
        String[] layouts={".........", "OX.......", "OXOX.....", "OXOXOX..."};
         int beadcount=4; // Initial value of beads of each color when no game is played
        for(String s:layouts)
        {
            layout=new HashMap<>();
            layout= g.getMenaceLayouts(s);  // Generating all the possible Layouts for a particular board layout
            HashSet<String> set=new HashSet<>();
            for(String l:layout.values())
            {
               String[] arr=l.split("#");
               set.add(arr[0]);
            }

            for(String str:set)
            {
                int[] beads=new int[9];
                for(int i=0;i<9;i++)
                {
                    if(str.charAt(i)=='.')
                    {
                        beads[i]=beadcount;
                    }
                }
                Matchbox box=new Matchbox(str,beads);
                layoutMap.put(box.getLayout(),box.getBeadList());
            }
            beadcount--;
        }
        try {
            FileUtility.writeToFile("DataBackup.txt", layoutMap);
            HashMap<String,int[]> readmap= FileUtility.readFromFile("DataBackup.txt");
            System.out.println(readmap.size());

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
