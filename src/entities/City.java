package entities;

public class City {
    Integer ID; //PK
    String Name;
    String CountryCode;
    String District;
    Integer Population;
    
    public Integer getID(){ return ID; }

    public void setID(Integer ID){this.ID = ID;}

    public String getName(){ return Name; }

    public void setName(String name){Name = name;}

    public String getCountryCode(){ return CountryCode; }

    public void setCountryCode(String CountryCode){this.CountryCode = CountryCode;}

    public String getDistrict(){ return District; }

    public void setDistrict(String District){ this.District = District;}

    public Integer getPopulation() { return Population; }

    public void setPopulation(Integer Population) {this.Population = Population; }

    @Override
    public String toString() {
        return "entities.City [CountryCode=]" + CountryCode + ", District=" + District + ", ID="
        + ID + ", Name=" + Name + ", Population=" + Population + "]";
    }
}
