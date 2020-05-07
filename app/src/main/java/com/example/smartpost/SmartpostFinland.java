package com.example.smartpost;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SmartpostFinland extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList<Smartpost> smartpostData = new ArrayList();
    private ArrayList<String> nameListForSpinner = new ArrayList();
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        readXML(v);
        populateSpinner();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Smartpost smartpost = (Smartpost) parent.getSelectedItem();
                displaySmartpostData(smartpost);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void readXML (View v) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "http://iseteenindus.smartpost.ee/api/?request=destinations&country=FI&type=APT";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            System.out.println("Root element " + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getDocumentElement().getElementsByTagName("item");

            for (int i = 0;  i < nList.getLength(); i++) {
                Node node = nList.item(i);
                System.out.println("Element is this " + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String city = element.getElementsByTagName("city").item(0).getTextContent();
                    String address = element.getElementsByTagName("address").item(0).getTextContent();
                    String availability = element.getElementsByTagName("availability").item(0).getTextContent();
                    Smartpost smartpost = new Smartpost(name, city, address, availability);
                    smartpostData.add(smartpost);
                    System.out.print("Name: ");
                    System.out.println(element.getElementsByTagName("name").item(0).getTextContent());
                    System.out.print("City: ");
                    System.out.println(element.getElementsByTagName("city").item(0).getTextContent());
                    System.out.print("Address: ");
                    System.out.println(element.getElementsByTagName("address").item(0).getTextContent());
                    System.out.print("Availability: ");
                    System.out.println(element.getElementsByTagName("availability").item(0).getTextContent());

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            System.out.println("##########DONE###########");
        }

    }

    public void populateSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<Smartpost> dataAdapter = new ArrayAdapter<Smartpost>(this,
                android.R.layout.simple_spinner_item, smartpostData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void getSelectedSmartpost(View v){
        Smartpost smartpost = (Smartpost) spinner.getSelectedItem();
        displaySmartpostData(smartpost);
    }

    private void displaySmartpostData(Smartpost smartpost) {
        String name = smartpost.getName();
        String address = smartpost.getAddress();
        String availability = smartpost.getAvailability();
        String smartpostData = "Smartpost: " + name + "\nAddress: " + address + "\nAvailability " + availability;
        Toast.makeText(this, smartpostData, Toast.LENGTH_LONG).show();
    }


}
