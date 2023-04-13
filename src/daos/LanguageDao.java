package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Language;
import entities.Language.PK;

public class LanguageDao implements Dao<Language, Language.PK> {
    Connection connection;

    public LanguageDao(Connection connection)
    {
        this.connection = connection;
    }
    
    public List<Language> findAll() {
        List<Language> languageList = new ArrayList<>();
        try(Statement statement = connection.createStatement())
        {
            ResultSet result = statement.executeQuery("SELECT * FROM CountryLanguage");
            while(result.next()){
                Language language = new Language(
                    result.getString("CountryCode"), 
                    result.getString("entities.Language"),
                    result.getBoolean("IsOfficial"), 
                    result.getFloat("Percentage"));

                    languageList.add(language);
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return languageList;
    }

    public void insert(Language language){    
    }

    public Boolean update(Language language){
        Boolean success = true;
        return success;
    }

    public Language findById(PK pk)
    {
        Language language = new Language(null, null, null, null);
        return language;
    }

    public Boolean delete(PK pk){
        return false;
    }
}
