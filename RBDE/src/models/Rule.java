package models;

/**
 * Created by Ian Markind on 10/8/2016.
 */
public class Rule {
    private String title;
    private String action;

    public Rule() { }

    public Rule(String title, String action) {
        this.title = title;
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "title='" + title + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
