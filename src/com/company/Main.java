package com.company;


import org.apache.jena.base.Sys;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.system.StreamRDFWriter;
import org.apache.jena.vocabulary.VCARD;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        String inputFileName = args[0];
        String oututFileName = args[1];
        ArrayList<Row> dataSet = ReadFile(inputFileName);
        OutputStream stream = new FileOutputStream(oututFileName);
        Model model = ModelFactory.createDefaultModel();

        Property OSM_ID =
                model.createProperty(Constants.DummyURL,
                        "OSM_ID");

        Property Street =
                model.createProperty("http://dbpedia.org/ontology#",
                        "Street");
        Property StreetID =
                model.createProperty(Constants.DummyURL,
                        "StreetID");

        Property HouseID =
                model.createProperty("http://dbpedia.org/ontology#",
                        "House");

        Property Lat = model.createProperty("http://www.w3.org/2003/01/geo/wgs84_pos#", "lat");
        Property Lang = model.createProperty("http://www.w3.org/2003/01/geo/wgs84_pos#", "long");


        for (Row row : dataSet) {
            model.createResource(Constants.OSMID_URI + row.GetOsmId())
                    .addProperty(OSM_ID, row.GetOsmId())
                    .addProperty(Street, row.GetStreet())
                    .addProperty(StreetID, row.GetStreetId())
                    .addProperty(HouseID, row.GetHouseNumber())
                    .addProperty(Lat, Double.toString(row.GetLattitude()))
                    .addProperty(Lang, Double.toString(row.GetLongtidue()));


            // write your code here
        }
        model.setNsPrefix("dummy", Constants.DummyURL);
        model.setNsPrefix("dbpedia", "http://dbpedia.org/ontology#");
        model.setNsPrefix("W3-Geo", "http://www.w3.org/2003/01/geo/wgs84_pos#");

        model.write(stream, "TURTLE");
        stream.close();
    }
    public static ArrayList<Row> ReadFile (String inputFileName) {
        ArrayList list = new ArrayList<Row>();
        try {
            File file = new File(inputFileName);
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {

                String line = scan.nextLine();
                if (line.contains("osm_id")) {
                    continue;
                }
                String[] splitted = line.split("\t");
                String osm_id = splitted[0];
                String streetId = splitted[1];
                String street = splitted[2];
                String houseNumber = splitted[3];
                double langtitude = Double.parseDouble(splitted[4]);
                double lattitude = Double.parseDouble(splitted[5]);

                Row set = new Row(osm_id, streetId, street, houseNumber, langtitude, lattitude);
                list.add(set);
            }
            scan.close();


        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

}
