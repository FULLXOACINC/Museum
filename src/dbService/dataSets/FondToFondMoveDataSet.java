package dbService.dataSets;

import java.sql.Date;

/**
 * Created by alex on 24.9.17.
 */
public class FondToFondMoveDataSet {
    int id;
    String startFond;
    String endFond;
    Date transfer;
    public FondToFondMoveDataSet(int id, String startFond, String endFond, Date transfer) {
        this.id = id;
        this.startFond = startFond;
        this.endFond = endFond;
        this.transfer = transfer;
    }

    public int getExhibitName() {
        return id;
    }

    public String getStartFond() {
        return startFond;
    }

    public String getEndFond() {
        return endFond;
    }

    public Date getTransfer() {
        return transfer;
    }

    @Override
    public String toString() {
        return "FondToFondMoveDataSet{" +
                "id=" + id +
                ", startFond='" + startFond + '\'' +
                ", endFond='" + endFond + '\'' +
                ", transfer=" + transfer +
                '}';
    }
}
