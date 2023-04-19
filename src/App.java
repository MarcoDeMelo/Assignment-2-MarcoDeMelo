import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import daos.CityDao;
import daos.CountryDao;
import daos.Database;
import daos.LanguageDao;
import entities.City;
import entities.Language;
import entities.Country;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        List<Language> languageList;
        List<City> cityList;
        List<Country> countryList;

       

        try ( Connection connection = Database.getDatabaseConnection();
              Statement statement = connection.createStatement();)
        {

            CountryDao countryDao = new CountryDao(connection);
            countryList = countryDao.findAll();

            System.out.println("Printing country: ");
                for (Country country : countryList)
                {
                    System.out.println(country);
                }

                Country insertCountry = new Country();
                insertCountry.setCode("123");
                insertCountry.setName("Fake Country");
                insertCountry.setContinent("Fake Continent");
                insertCountry.setRegion("Western");
                insertCountry.setSurfaceArea(9372610.0);
                insertCountry.setIndepYear(1776);
                insertCountry.setPopulation(328239523);
                insertCountry.setLifeExpectancy(78.9);
                insertCountry.setGNP(17420000000000.0);
                insertCountry.setGNPOld(16600000000000.0);
                insertCountry.setLocalName("Fake state");
                insertCountry.setGovernmentForm("Federal presidential constitutional republic");
                insertCountry.setHeadOfState("President Joe Biden");
                insertCountry.setCapital(166);
                insertCountry.setCode2("12");
                countryDao.insert(insertCountry);

                boolean isDeleted = countryDao.delete("123");
                if(isDeleted == true)
                {
                    System.out.println("FAKE COUNTRY DELETED :)");

                }
                else 
                {
                    System.out.println("FAKE COUNTRY DIDN'T GET DELETED :( ");
                }

                Country country = countryDao.findById("AGO");
                System.out.println("Country returned from findById AGO: " + country);

                

        


        // call the findByCountryName method with the desired country name
        Country countryName = countryDao.findByCountryName("United States");
    
        // print out the country information
        if (countryName != null) {
            System.out.println(countryName.getName() + " has a population of " + countryName.getPopulation() + " and is in this region" + countryName.getRegion());
        } else {
            System.out.println("Country not found.");
        }

        Country myCountry = countryDao.findByRegion("South America");
        if(myCountry != null)
        {
            for (Country countryRegion : countryList)
                {
                    System.out.println(countryRegion.getName() + " is in South America");
                }
        } else 
        {
            System.out.println("Region not found.");
        }
    

            //City Dao
            CityDao cityDao = new CityDao(connection);
            cityList = cityDao.findAll();
            for (City city : cityList) {
                System.out.println(city);
            }
             // print the details of each city By CountryCode
            List<City> cityCountry = cityDao.findByCountry("USA");
            for (City cityByCountry : cityCountry) {
                System.out.println(cityByCountry);
            }

            System.out.println("Cities Printed");
            for( City city: cityList){
                System.out.println(city);
            }
    
            // print the details of each city Population over 1 mil
            List<City> cities = cityDao.findByPopOverMillion();

            for (City city1Mil : cities) {
                System.out.println(city1Mil.toString());
            }
       
            String cityName = "Tokyo";
            City cityname = cityDao.findByName(cityName);
        
            // Check if the city was found and print its information
            if (cityname != null) {
                System.out.println("City found: " + cityname.toString());
            } else {
                System.out.println("City not found.");
            }

             // Insert
             City insertCity = new City();


             insertCity.setCountryCode("CAN");
             insertCity.setDistrict("Kings");
             insertCity.setName("Kingston");
             insertCity.setPopulation(323526);
 
             cityDao.insert(insertCity);
 
             //findById
 
             City city = new City();
 
             city = cityDao.findById(25);
 
             System.out.print("City found with Id of 25 "+ city);
 
             // update
             city.setPopulation(10000);
             Boolean successCity = cityDao.update(city);
             if(successCity == true)
             {
                 System.out.print("City after update: "+ cityDao.findById(25));
             }
             else 
             {
                 System.out.print("City didn't update");
             }


            // language dao
            LanguageDao languageDao = new LanguageDao(connection);
            languageList = languageDao.findAll();


            

            System.out.println("Languages Printed");
            for( Language language: languageList){
                System.out.println(language);
            }

           
            
        }
        catch(SQLException ex)
        {
            System.out.println("Exception "+ ex.getMessage() + ", there's a error in your code ");
        }


    }
}
