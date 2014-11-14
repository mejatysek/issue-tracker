package cz.cvut.fel.wpa.tracker.dto;

import java.util.Date;

/**
 * Created by mejty on 14.11.14.
 */
public class OperationDto {
    private Date time;
    private Long owner;
    private String body;

    public OperationDto() {
    }

    public OperationDto(Date time, Long owner, String body) {
        this.time = time;
        this.owner = owner;
        this.body = body;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
