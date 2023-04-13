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
                insertCountry.setCode("USA");
                insertCountry.setName("United States");
                insertCountry.setContinent("North America");
                insertCountry.setRegion("Western");
                insertCountry.setSurfaceArea(9372610.0);
                insertCountry.setIndepYear(1776);
                insertCountry.setPopulation(328239523);
                insertCountry.setLifeExpectancy(78.9);
                insertCountry.setGNP(17420000000000.0);
                insertCountry.setGNPOld(16600000000000.0);
                insertCountry.setLocalName("United States");
                insertCountry.setGovernmentForm("Federal presidential constitutional republic");
                insertCountry.setHeadOfState("President Joe Biden");
                insertCountry.setCapital(166);
                insertCountry.setCode2("US");
                countryDao.insert(insertCountry);

                Country country = countryDao.findById("AGO");
                System.out.println("Country returned from findById AGO: " + country);

                boolean isDeleted = countryDao.delete("USA");

        if (isDeleted) {
            System.out.println("Country deleted successfully.");
        } else {
            System.out.println("Failed to delete country.");
        }
        
            //City Dao
            CityDao cityDao = new CityDao(connection);
            cityList = cityDao.findAll();


            // language dao
            LanguageDao languageDao = new LanguageDao(connection);
            languageList =languageDao.findAll();


            System.out.println("Cities Printed");
            for( City city: cityList){
                System.out.println(city);
            }

            System.out.println("Languages Printed");
            for( Language language: languageList){
                System.out.println(language);
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

            city = cityDao.findById(4087);

            System.out.print("City found with Id of 4087 "+city);

            // update
            city.setPopulation(10000);
            Boolean success = cityDao.update(city);
            System.out.print("City after update: "+ cityDao.findById(4087));



            /*
            ResultSet  resultSet = statement.executeQuery("SELECT * FROM Country");
            while(resultSet.next())
            {

                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString("name"));
            }
            resultSet.close();
            */
        }
        catch(SQLException ex)
        {
            System.out.println("Exception "+ ex.getMessage() + " In other words: Didn't work try again");
        }


    }
}
