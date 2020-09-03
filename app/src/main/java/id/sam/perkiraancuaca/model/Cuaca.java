
package id.sam.perkiraancuaca.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cuaca implements Serializable, Parcelable
{

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Integer message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<id.sam.perkiraancuaca.model.List> list = null;
    @SerializedName("city")
    @Expose
    private City city;
    public final static Creator<Cuaca> CREATOR = new Creator<Cuaca>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Cuaca createFromParcel(Parcel in) {
            return new Cuaca(in);
        }

        public Cuaca[] newArray(int size) {
            return (new Cuaca[size]);
        }

    }
    ;
    private final static long serialVersionUID = 4736517972081931293L;

    protected Cuaca(Parcel in) {
        this.cod = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.cnt = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.list, (id.sam.perkiraancuaca.model.List.class.getClassLoader()));
        this.city = ((City) in.readValue((City.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Cuaca() {
    }

    /**
     * 
     * @param city
     * @param cnt
     * @param cod
     * @param message
     * @param list
     */
    public Cuaca(String cod, Integer message, Integer cnt, java.util.List<id.sam.perkiraancuaca.model.List> list, City city) {
        super();
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<id.sam.perkiraancuaca.model.List> getList() {
        return list;
    }

    public void setList(java.util.List<id.sam.perkiraancuaca.model.List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cod);
        dest.writeValue(message);
        dest.writeValue(cnt);
        dest.writeList(list);
        dest.writeValue(city);
    }

    public int describeContents() {
        return  0;
    }

}
