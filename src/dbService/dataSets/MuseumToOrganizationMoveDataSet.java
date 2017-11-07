package dbService.dataSets;

import java.sql.Date;

/**
 * Created by alex on 24.9.17.
 */
public class MuseumToOrganizationMoveDataSet {
    int id;
    String exhibitName;
    String adress;
    String phoneNumber;
    String FIO;
    String exhibitionAdress;
    String exhibitionName;
    Date exhibitionStart;
    Date exhibitionEnd;

    public MuseumToOrganizationMoveDataSet(int id,String exhibitName, String adress, String phoneNumber, String FIO, String exhibitionAdress, String exhibitionName, Date exhibitionStart, Date exhibitionEnd) {
        this.exhibitName = exhibitName;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.FIO = FIO;
        this.exhibitionAdress = exhibitionAdress;
        this.exhibitionName = exhibitionName;
        this.exhibitionStart = exhibitionStart;
        this.exhibitionEnd = exhibitionEnd;
    }

    public String getExhibitName() {
        return exhibitName;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFIO() {
        return FIO;
    }

    public String getExhibitionAdress() {
        return exhibitionAdress;
    }

    public String getExhibitionName() {
        return exhibitionName;
    }

    public Date getExhibitionStart() {
        return exhibitionStart;
    }

    public Date getExhibitionEnd() {
        return exhibitionEnd;
    }
}
