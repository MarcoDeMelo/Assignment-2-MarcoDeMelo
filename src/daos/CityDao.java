package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entities.City;

public class CityDao implements Dao<City, Integer> {
    Connection connection;

    public CityDao(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();

        try(Statement statement = connection.createStatement())
        {
            ResultSet result = statement.executeQuery("SELECT * FROM city");
            while(result.next())
            {
                City city = new City();
                city.setCountryCode(result.getString("CountryCode"));
                city.setDistrict(result.getString("District"));
                city.setID(result.getInt("ID"));
                city.setName(result.getString("Name"));
                city.setPopulation(result.getInt("Population"));
                cities.add(city);
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }

        return cities;
    }

    public void insert(City city)
    {
        try(Statement statement = connection.createStatement())
        {
            String insert = "INSERT INTO city VALUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, null);
            ps.setString(2, city.getName());
            ps.setString(3, city.getCountryCode());
            ps.setString(4, city.getDistrict());
            ps.setInt(5, city.getPopulation());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next())
            {
                city.setID(keys.getInt(1));
            }


        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
    public Boolean update(City city)
    {
        Boolean success = true;
        String update = "UPDATE city SET population = ? WHERE ID = ?";

        try(PreparedStatement ps = connection.prepareStatement(update))
        {
            ps.setInt(1,city.getPopulation());
            ps.setInt(1,city.getID());

            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            success = false;
        }

        return success;
    }

    public City findById(Integer pk)
    {
        City city = new City();

        String select = "SELECT  * FROM city WHERE id = ?" ;
        try(PreparedStatement ps = connection.prepareStatement(select))
        {
            ps.setInt(1, pk);
            ResultSet result  = ps.executeQuery();

            if( result.next())
            {
                city.setCountryCode(result.getString("CountryCode"));
                city.setDistrict(result.getString("District"));
                city.setID(result.getInt("ID"));
                city.setName(result.getString("Name"));
                city.setPopulation(result.getInt("Population"));
            }

        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());

        }


        return city;
    }

    public Boolean delete(Integer pk)
    {
        Boolean success = false;
        String delete = "DELETE FROM city WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(delete))
        {
            ps.setInt(1, pk);
            if(ps.executeUpdate() !=0)
            {
                success = true;
            }

        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());

        }
        return success;
    }
    

   
