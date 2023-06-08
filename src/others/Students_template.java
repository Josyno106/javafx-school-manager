package others;

/**
 * Created by joshua on 12/3/2016.
 */
public class Students_template {

    String f_name;
    String l_name;
    Integer birth;
    Integer entry;
    String clas;
    String conact;
    Integer id;

    public Students_template(String f_name, String l_name, Integer birth, Integer entry, String clas, String conact, Integer id) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.birth = birth;
        this.entry = entry;
        this.clas = clas;
        this.conact = conact;

        this.id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public Integer getBirth() {
        return birth;
    }

    public void setBirth(Integer birth) {
        this.birth = birth;
    }

    public Integer getEntry() {
        return entry;
    }

    public void setEntry(Integer entry) {
        this.entry = entry;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getConact() {
        return conact;
    }

    public void setConact(String conact) {
        this.conact = conact;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

