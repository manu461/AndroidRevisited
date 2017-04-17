
package in.codekamp.myapplication;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactList {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;



    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



}
