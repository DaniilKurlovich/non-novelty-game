package game_package.tile;

import game_package.graphics.Sprite;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class TileManager {

    public static ArrayList<TileMap> tm;

    private String path;

    public TileManager() {
        tm = new ArrayList<TileMap>();
    }

    public TileManager(String path, String spritePath) {
        tm = new ArrayList<TileMap>();
        addTileMap(path, spritePath, 64, 64);
    }

    public TileManager(String path, String spritePath, int blockWidth, int blockHeight) {
        tm = new ArrayList<TileMap>();
        addTileMap(path, spritePath, blockWidth, blockHeight);
    }

    private void addTileMap(String path, String spritePath, int blockWidth, int blockHeight) {
        int width = 0;
        int height = 0;
        int tileWidth;
        int tileHeight;
        int tileColumns;
        int layers = 0;
        Sprite sprite;

        String[] data = new String[10];

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(path).toURI().toString());
//            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("tileset");
            Node node = list.item(0);
            Element eElement = (Element) node;

            tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));
            tileColumns =  Integer.parseInt(eElement.getAttribute("columns"));
            sprite = new Sprite(spritePath, tileWidth, tileHeight);
            list = doc.getElementsByTagName("layer");
            layers = list.getLength();

            for(int i = 0; i < layers; i++) {
                node = list.item(i);
                eElement = (Element) node;
                if(i <= 0) {
                    width = Integer.parseInt(eElement.getAttribute("width"));
                    height = Integer.parseInt(eElement.getAttribute("height"));
                    data[i] = eElement.getElementsByTagName("data").item(0).getTextContent();
                }
                else {
                    data[i] =  doc.getElementsByTagName("layer").item(1).getTextContent();
                }

                if(i >= 1) {
                    tm.add(new TileMapNorm(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                } else {
                    tm.add(new TileMapObj(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
                }
            }
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("ERROR - TILEMANAGER: can not read tilemap");
        }
    }

    public void build(float x, float y, int[][] item){
        int dx = (int)(x/64);
        int dy = (int)(y/64);
        HashMap<String, String> modifed = ((TileMapNorm)tm.get(1)).build(dx, dy, new Sprite("resource/tile/map_tile.png", 16, 16),
                item, 32);
        if (modifed != null){
            save_сhanges(modifed);
        }
    }

    private void save_сhanges(HashMap<String, String> changes){
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(path).toURI().toString());
            Node staff = doc.getElementsByTagName("layer").item(1);
            String data = staff.getTextContent();
            String[] lines = data.split("\n");
            List<String[]> characters = new ArrayList<>();

            for(String line:lines){
                if (!line.isEmpty()) {
                    characters.add(line.split(","));
                }
            }

            for (String change:changes.keySet()){
                String[] keys = change.split(",");
                characters.get(Integer.parseInt(keys[1]))[Integer.parseInt(keys[0])] = changes.get(change);
            }

            List<String> result_line = new ArrayList<>();
            for(String[] line:characters){
                if (line.length > 1)
                    result_line.add(String.join(",", line) + ",");
            }
            String result_data = String.join("\n", result_line);
            staff.setTextContent(result_data.substring(0, result_data.length() - 1) + "\n");

//          Запись в файл.
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path).toURI().toString());
            transformer.transform(source, result);
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR - TILEMANAGER: can not read tilemap");
        }
    }

    public void save(){
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(path).toURI().toString());
            Node staff = doc.getElementsByTagName("layer").item(1);
            String data = staff.getTextContent();
            String[] lines = data.split("\n");
            List<String[]> characters = new ArrayList<>();

            for(String line:lines){
                if (!line.isEmpty()) {
                    characters.add(line.split(","));
                }
            }

            List<String> result_line = new ArrayList<>();
            for(String[] line:characters){
                if (line.length > 1)
                    result_line.add(String.join(",", line) + ",");
            }
            String result_data = String.join("\n", result_line);
            staff.setTextContent(result_data.substring(0, result_data.length() - 1) + "\n");

//          Запись в файл.
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path).toURI().toString());
            transformer.transform(source, result);
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR - TILEMANAGER: can not read tilemap");
        }
    }

    public void destroy(float x, float y) {
        int dx = (int) (x / 64);
        int dy = (int) (y / 64);
        HashMap<String, String> modifed = ((TileMapNorm)tm.get(1)).destroy(dx, dy, 32);
        if (modifed != null){
            System.out.println(modifed);
            save_сhanges(modifed);
        }
    }

    public void render(Graphics2D g, int x, int y) {
        for(int i = 0; i < tm.size(); i++) {
            tm.get(i).render(g, x, y);
        }
    }
}