package sample;

import java.io.*;
import java.util.ArrayList;

public class Sauvegarde {

    private String nameTop;
    private int scoreTop;

    private ArrayList<Integer> listint;
    private ArrayList<String> listname;
    private ArrayList<String> listComplete;

    private File myFile;

    public Sauvegarde(){
        try
        {
            myFile = new File("scores.txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }



            BufferedReader reader = new BufferedReader(new FileReader(myFile));

            String readLine = "";
            listint = new ArrayList<Integer>();
            listname = new ArrayList<String>();
            listComplete = new ArrayList<String>();

            int i=1;
            while ((readLine = reader.readLine()) != null) {
                String[] tokens = readLine.split(":");
                listComplete.add(readLine);
                listint.add(Integer.parseInt(tokens[1]));
                listname.add(tokens[0]);
                i++;
            }
            reader.close();

            findTopPlayer();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findTopPlayer(){
        int max=0;
        String name = null;
        for (int i=0; i<listint.size();i++){
            if (listint.get(i)>max){
                max=listint.get(i);
                name = listname.get(i);
            }
        }
        nameTop=name;
        scoreTop=max;
    }

    public String getNameTop() {
        return nameTop;
    }

    public int getScoreTop() {
        return scoreTop;
    }

    public void writeFile(String pseudo, int score) {
        try{

            FileWriter writer = new FileWriter(myFile.getName(), false);
            String text = pseudo + " :"+ Integer.toString(score);//+"\n"

            //Pour faire un classement
            if (listint.size()!=0){
                int i =0;
                while (score < listint.get(i)){
                    i++;
                }
                listComplete.add(i, text);
                text="";
                for (String line : listComplete){
                    text = text + line + "\n";
                }
            }
            writer.write(text);
            writer.close();
        }catch (IOException e){}
    }

    public ArrayList<String> getListComplete() {
        return listComplete;
    }
}
