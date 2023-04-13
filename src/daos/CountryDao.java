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

    public CountryDao(Connection connection)
    {
        this.connection = connection;
    }

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

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, country.getCode());
            stmt.setString(2, country.getName());
            stmt.setString(3, country.getContinent());
            stmt.setString(4, country.getRegion());
            stmt.setDouble(5, country.getSurfaceArea());
            stmt.setInt(6, country.getIndepYear());
            stmt.setInt(7, country.getPopulation());
            stmt.setDouble(8, country.getLifeExpectancy());
            stmt.setDouble(9, country.getGNP());
            stmt.setDouble(10, country.getGNPOld());
            stmt.setString(11, country.getLocalName());
            stmt.setString(12, country.getGovernmentForm());
            stmt.setString(13, country.getHeadOfState());
            stmt.setInt(14, country.getCapital());
            stmt.setString(15, country.getCode2());

            stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Boolean update(Country country){
        String sql = "UPDATE Country SET Name=?, Continent=?, Region=?, SurfaceArea=?, IndepYear=?, Population=?, LifeExpectancy=?, GNP=?, GNPOld=?, LocalName=?, GovernmentForm=?, HeadOfState=?, Capital=?, Code2=? WHERE Code=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, country.getName());
            stmt.setString(2, country.getContinent());
            stmt.setString(3, country.getRegion());
            stmt.setDouble(4, country.getSurfaceArea());
            stmt.setInt(5, country.getIndepYear());
            stmt.setInt(6, country.getPopulation());
            stmt.setDouble(7, country.getLifeExpectancy());
            stmt.setDouble(8, country.getGNP());
            stmt.setDouble(9, country.getGNPOld());
            stmt.setString(10, country.getLocalName());
            stmt.setString(11, country.getGovernmentForm());
            stmt.setString(12, country.getHeadOfState());
            stmt.setInt(13, country.getCapital());
            stmt.setString(14, country.getCode2());
            stmt.setString(15, country.getCode());
    
            stmt.executeUpdate();
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    

    public Country findById(String pk){
        String sql = "SELECT * FROM Country WHERE Code = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pk);
            ResultSet result = stmt.executeQuery();
    
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

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pk);
    
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
