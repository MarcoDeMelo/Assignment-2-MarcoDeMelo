package entities;

public class Language {
    PK countryLanguage;
    Boolean isOfficial;
    Float percentage;

    public Language(String code, String language, Boolean isOfficial, Float percentage)
    {
        countryLanguage = new PK(code, language);
        this.isOfficial = isOfficial;
        this.percentage = percentage;
    }
    

    
    public static class PK
    {
        public PK(String countryCode, String language)
        {
            this.countryCode = countryCode;
            this.language = language;
        }
        String countryCode;
        String language;
    }

    @Override
    public String toString()
    {
        return "countryCode: " + countryLanguage.countryCode +
               ", isOfficial: " + isOfficial +
               ", percentage: " + percentage +
               ", language: " + countryLanguage.language;
    }
}
