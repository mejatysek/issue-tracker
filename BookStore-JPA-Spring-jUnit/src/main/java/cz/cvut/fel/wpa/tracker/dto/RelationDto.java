package cz.cvut.fel.wpa.tracker.dto;

import java.util.List;

/**
 * Created by mejty on 14.11.14.
 */
public class RelationDto {
    private short type;
    private List<Long> issues;

    public RelationDto() {
    }

    public RelationDto(short type, List<Long> issues) {
        this.type = type;
        this.issues = issues;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public List<Long> getIssues() {
        return issues;
    }

    public void setIssues(List<Long> issues) {
        this.issues = issues;
    }
}
