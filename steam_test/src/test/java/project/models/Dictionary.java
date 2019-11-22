package project.models;

public class Dictionary {
    private String language;
    private String genreName;
    private String tabName;

    public Dictionary(String language, String genreName, String tabName) {
        this.language = language;
        this.genreName = genreName;
        this.tabName = tabName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }
}
