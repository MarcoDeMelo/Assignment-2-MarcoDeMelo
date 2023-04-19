package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Country;

public class CountryDao implements Dao<Country, String> {
    Connection connection;

    public CountryDao(Connection connection){this.connection = connection;}

    public List<Country> findAll() {
        List<Country> countrys = new ArrayList<Country>();
        try (Statement statement = connection.createStatement())
        {
            ResultSet result = statement.executeQuery("SELECT * FROM country");
            while(result.next())
            {
                Country country = new Country();
                country.setCode(result.getString("Code"));
                country.setName(result.getString("Name"));
                country.setContinent(result.getString("Continent"));
                country.setRegion(result.getString("Region"));
                country.setSurfaceArea(result.getDouble("SurfaceArea"));
                country.setIndepYear(result.getInt("IndepYear"));
                country.setPopulation(result.getInt("Population"));
                country.setLifeExpectancy(result.getDouble("LifeExpectancy"));
                country.setGNP(result.getDouble("GNP"));
                country.setGNPOld(result.getDouble("GNPOld"));
                country.setLocalName(result.getString("LocalName"));
                country.setGovernmentForm(result.getString("GovernmentForm"));
                country.setHeadOfState(result.getString("HeadOfState"));
                country.setCapital(result.getInt("Capital"));
                country.setCode2(result.getString("Code2"));
                countrys.add(country);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countrys;
    }

    public void insert(Country country){
        String sql = "INSERT INTO Country VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, country.getCode());
            ps.setString(2, country.getName());
            ps.setString(3, country.getContinent());
            ps.setString(4, country.getRegion());
            ps.setDouble(5, country.getSurfaceArea());
            ps.setInt(6, country.getIndepYear());
            ps.setInt(7, country.getPopulation());
            ps.setDouble(8, country.getLifeExpectancy());
            ps.setDouble(9, country.getGNP());
            ps.setDouble(10, country.getGNPOld());
            ps.setString(11, country.getLocalName());
            ps.setString(12, country.getGovernmentForm());
            ps.setString(13, country.getHeadOfState());
            ps.setInt(14, country.getCapital());
            ps.setString(15, country.getCode2());

            ps.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Boolean update(Country country){
        String sql = "UPDATE Country SET Name=?, Continent=?, Region=?, SurfaceArea=?, IndepYear=?, Population=?, LifeExpectancy=?, GNP=?, GNPOld=?, LocalName=?, GovernmentForm=?, HeadOfState=?, Capital=?, Code2=? WHERE Code=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, country.getName());
            ps.setString(2, country.getContinent());
            ps.setString(3, country.getRegion());
            ps.setDouble(4, country.getSurfaceArea());
            ps.setInt(5, country.getIndepYear());
            ps.setInt(6, country.getPopulation());
            ps.setDouble(7, country.getLifeExpectancy());
            ps.setDouble(8, country.getGNP());
            ps.setDouble(9, country.getGNPOld());
            ps.setString(10, country.getLocalName());
            ps.setString(11, country.getGovernmentForm());
            ps.setString(12, country.getHeadOfState());
            ps.setInt(13, country.getCapital());
            ps.setString(14, country.getCode2());
            ps.setString(15, country.getCode());
    
            ps.executeUpdate();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    

    public Country findById(String pk){
        String sql = "SELECT * FROM Country WHERE Code = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, pk);
            ResultSet result = ps.executeQuery();
    
            if (result.next()) {
                Country country = new Country();
                country.setCode(result.getString("Code"));
                country.setName(result.getString("Name"));
                country.setContinent(result.getString("Continent"));
                country.setRegion(result.getString("Region"));
                country.setSurfaceArea(result.getDouble("SurfaceArea"));
                country.setIndepYear(result.getInt("IndepYear"));
                country.setPopulation(result.getInt("Population"));
                country.setLifeExpectancy(result.getDouble("LifeExpectancy"));
                country.setGNP(result.getDouble("GNP"));
                country.setGNPOld(result.getDouble("GNPOld"));
                country.setLocalName(result.getString("LocalName"));
                country.setGovernmentForm(result.getString("GovernmentForm"));
                country.setHeadOfState(result.getString("HeadOfState"));
                country.setCapital(result.getInt("Capital"));
                country.setCode2(result.getString("Code2"));
                return country;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Country findByCountryName(String name){
        String sql = "SELECT * FROM Country WHERE Name = ?";
    
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet result = ps.executeQuery();
    
            if (result.next()) {
                Country country = new Country();
                country.setCode(result.getString("Code"));
                country.setName(result.getString("Name"));
                country.setContinent(result.getString("Continent"));
                country.setRegion(result.getString("Region"));
                country.setSurfaceArea(result.getDouble("SurfaceArea"));
                country.setIndepYear(result.getInt("IndepYear"));
                country.setPopulation(result.getInt("Population"));
                country.setLifeExpectancy(result.getDouble("LifeExpectancy"));
                country.setGNP(result.getDouble("GNP"));
                country.setGNPOld(result.getDouble("GNPOld"));
                country.setLocalName(result.getString("LocalName"));
                country.setGovernmentForm(result.getString("GovernmentForm"));
                country.setHeadOfState(result.getString("HeadOfState"));
                country.setCapital(result.getInt("Capital"));
                country.setCode2(result.getString("Code2"));
                return country;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }    

    public Country findByRegion(String region){
        String sql = "SELECT * FROM Country WHERE Region = ?";
    
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, region);
            ResultSet result = ps.executeQuery();
    
            if (result.next()) {
                Country country = new Country();
                country.setCode(result.getString("Code"));
                country.setName(result.getString("Name"));
                country.setContinent(result.getString("Continent"));
                country.setRegion(result.getString("Region"));
                country.setSurfaceArea(result.getDouble("SurfaceArea"));
                country.setIndepYear(result.getInt("IndepYear"));
                country.setPopulation(result.getInt("Population"));
                country.setLifeExpectancy(result.getDouble("LifeExpectancy"));
                country.setGNP(result.getDouble("GNP"));
                country.setGNPOld(result.getDouble("GNPOld"));
                country.setLocalName(result.getString("LocalName"));
                country.setGovernmentForm(result.getString("GovernmentForm"));
                country.setHeadOfState(result.getString("HeadOfState"));
                country.setCapital(result.getInt("Capital"));
                country.setCode2(result.getString("Code2"));
                return country;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean delete(String pk){
        String sql = "DELETE FROM Country WHERE Code = ?";

        Boolean success = false;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, pk);
    
            
            success  = true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return success;
    }
    
}
