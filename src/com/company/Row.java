/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;
/**
 *
 * @author Zain.Ulabidin
 */
public class Row {
    private String osm_id;
    private String streetId;
    private String street;
    private String houseNumber;
    private double longtidue;
    private double lattitude;

    public Row (String osmId, String street_Id, String streetAddress, String Hnumber, double longi, double latti )
    {
        osm_id = osmId;
        streetId = street_Id;
        street = streetAddress;
        houseNumber = Hnumber;
        longtidue = longi;
        lattitude = latti;
    }

    public String GetOsmId ()
    {
        return osm_id;
    }

    public String GetStreetId ()
    {
        return streetId;
    }

    public String GetHouseNumber ()
    {
        return houseNumber;
    }

    public double GetLongtidue()
    {
        return longtidue;
    }

    public double GetLattitude ()
    {
        return lattitude;
    }
    public String GetStreet ()
    {
        return street;
    }

}
