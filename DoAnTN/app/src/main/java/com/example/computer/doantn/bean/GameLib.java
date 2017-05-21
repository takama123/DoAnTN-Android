package com.example.computer.doantn.bean;

import android.content.Context;
import android.os.Environment;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class GameLib {

    private ArrayList<GameLevelBean> getAllListGame(Context context, String className) {
        String fileName = className + ".xml";
        String nameNode = "Lesson";
        ArrayList<GameLevelBean> listGame = new ArrayList<>();
        NodeList allNodeList = getAllNoteList(context, fileName, nameNode, 0);

        for(int i=0;i<allNodeList.getLength();i++) // duyệt từ node đầu tiên cho tới node cuối cùng
        {
            Node node=allNodeList.item(i);// mỗi lần duyệt thì lấy ra 1 node
            if(node instanceof Element) // kiểm tra xem node đó có phải là Element hay không, vì ta dựa vào element để lấy dữ liệu bên trong
            {
                Element lessonXml=(Element) node;
                int id = Integer.parseInt(lessonXml.getAttribute("idLesson"));
                String titleLesson = lessonXml.getAttribute("titleLesson");
                String maxNumber = lessonXml.getAttribute("maxNumber");
                String typeMath = lessonXml.getAttribute("typeMath");
                String isRemember = lessonXml.getAttribute("isRemember");
                GameLevelBean lesson = new GameLevelBean(id, titleLesson, maxNumber, isRemember, typeMath);
                listGame.add(lesson);
            }
        }
        return listGame;
    }

    public int getMaxLevel(Context context, String className){
        String fileName = className + "-setting.xml";
        int maxLevel = 1;
        String nameNode = "level";
        NodeList allNodeList = getAllNoteList(context, fileName, nameNode, 1);
        for(int i=0;i<allNodeList.getLength();i++) // duyệt từ node đầu tiên cho tới node cuối cùng
        {
            Node node=allNodeList.item(i);// mỗi lần duyệt thì lấy ra 1 node
            if(node instanceof Element) // kiểm tra xem node đó có phải là Element hay không, vì ta dựa vào element để lấy dữ liệu bên trong
            {
                Element employee=(Element) node;
                String titleLesson = employee.getAttribute("maxLevel");
                maxLevel = Integer.parseInt(titleLesson);
            }
        }
        return maxLevel;
    }

    public void wirteFile(String lop, int i) {

    }

    private NodeList getAllNoteList(Context context, String fileNameXml, String nameNode, int typePath) {
        NodeList listNode = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = null;
            InputStream is = null;
            FileInputStream fileInputStream = null;
            if(typePath == 0) {
                is = context.getAssets().open(fileNameXml);
                doc = dBuilder.parse(is);
            }else{
                File file = new File(Environment.getExternalStorageDirectory() + "/Nguyen/" + fileNameXml);
                fileInputStream = new FileInputStream(file);
                doc = dBuilder.parse(fileInputStream);
            }


            Element element=doc.getDocumentElement();
            element.normalize();
            listNode = doc.getElementsByTagName(nameNode);// lấy toàn bộ node con của Root
        } catch (Exception e) {e.printStackTrace();}
        return listNode;
    }


    public ArrayList<GameLevelBean> getListGameOpenOfLevel(String nameClass, Context context){
        ArrayList<GameLevelBean> listLevelOpen = new ArrayList<>();
        ArrayList<GameLevelBean> listAllLevel = this.getAllListGame(context, nameClass);
        int levelOpen = this.getMaxLevel(context, nameClass);
        for (int i = 0; i < levelOpen; i++) {
            listLevelOpen.add(listAllLevel.get(i));
        }
        return listLevelOpen;
    }

    public void writeXml(String nameClass, String level) throws IOException {
        String fileName = nameClass + "-setting.xml";
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Nguyen");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, fileName);
            if (!gpxfile.exists()) {
                gpxfile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(gpxfile);
            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(fos, "UTF-8");
            serializer.startDocument(null, Boolean.valueOf(true));
            serializer.startTag(null, "setting");
            serializer.startTag(null, "level");
            serializer.attribute("","maxLevel" , level);
            serializer.endTag(null, "level");
            serializer.endDocument();
            serializer.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creatSettingFile() throws IOException {
        writeXml("lop1", "1");
        writeXml("lop2", "1");
        writeXml("lop3", "1");
    }
}
