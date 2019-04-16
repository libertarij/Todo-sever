package by.andrew.todo.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Rates {
    @JsonProperty("Cur_ID")
    private int cur_ID;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("Cur_Abbreviation")
    private String cur_Abbreviation;
    @JsonProperty("Cur_Scale")
    private int cur_Scale;
    @JsonProperty("Cur_Name")
    private String cur_Name;
    @JsonProperty("Cur_OfficialRate")
    private double cur_OfficialRate;

    public int getCur_ID() {
        return cur_ID;
    }

    public void setCur_ID(int cur_ID) {
        this.cur_ID = cur_ID;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCur_Abbreviation() {
        return cur_Abbreviation;
    }

    public void setCur_Abbreviation(String cur_Abbreviation) {
        this.cur_Abbreviation = cur_Abbreviation;
    }

    public int getCur_Scale() {
        return cur_Scale;
    }

    public void setCur_Scale(int cur_Scale) {
        this.cur_Scale = cur_Scale;
    }

    public String getCur_Name() {
        return cur_Name;
    }

    public void setCur_Name(String cur_Name) {
        this.cur_Name = cur_Name;
    }

    public double getCur_OfficialRate() {
        return cur_OfficialRate;
    }

    public void setCur_OfficialRate(double cur_OfficialRate) {
        this.cur_OfficialRate = cur_OfficialRate;
    }

    @Override
    public String toString() {
        return "Rates{" +
                "cur_ID=" + cur_ID +
                ", date='" + date + '\'' +
                ", cur_Abbreviation='" + cur_Abbreviation + '\'' +
                ", cur_Scale=" + cur_Scale +
                ", cur_Name='" + cur_Name + '\'' +
                ", cur_OfficialRate=" + cur_OfficialRate +
                '}';
    }
}