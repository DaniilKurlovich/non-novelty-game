package game_package.tile;

import game_package.graphics.Sprite;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TileManager {

    public static ArrayList<TileMap> tm;

    public TileManager(){
        tm = new ArrayList<TileMap>();
    }

    public TileManager(String path){
        tm = new ArrayList<TileMap>();
        addTileMap(path, 64, 64);
    }

    private void addTileMap(String path, int bWidth, int hWidth){
        String imagePath;

        int width;
        int height;
        int tileWidth;
        int tileHeight;
        int tileColumns;
        int layers = 0;
        Sprite sprite;

        String[] data = new String[10];

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = documentBuilder.parse("out/production/ProjectJava/resource/tile/map.xml");
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("tileset");
            Node root = list.item(0);
            System.out.println("Root loaded: " + root.toString());
            Element element = (Element) root;


            //imagePath = element.getAttribute("name");
            //tileWidth = Integer.parseInt(element.getAttribute("tilewidth"));
            //tileHeight = Integer.parseInt(element.getAttribute("tileheight"));
            //tileColumns = Integer.parseInt(element.getAttribute("columns"));
            //sprite = new Sprite("resource/tile/" + imagePath + ".png", tileWidth, tileHeight);

            list = doc.getElementsByTagName("layer");
            layers = list.getLength();

            for (int i=0; i< layers; i++){
                root = list.item(i);
                element = (Element) root;
                if (i <= 0){
                    width = Integer.parseInt(element.getAttribute("width"));
                    height = Integer.parseInt(element.getAttribute("height"));
                }

                data[i] = element.getElementsByTagName("data").item(0).getTextContent();
                System.out.println(data[i]);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void render(Graphics2D grphs){

    }
}
