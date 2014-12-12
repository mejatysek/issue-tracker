package cz.cvut.fel.wpa.tracker.pres.bb;

import cz.cvut.fel.wpa.tracker.dto.IssueDto;
import cz.cvut.fel.wpa.tracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: Adam Uhlíř <uhlir.a@gmail.com>
 * Date: 13.12.14
 */
@Component("issueBean")
@Scope("request")
public class IssueBean {

    @Autowired
    private IssueService issueService;

    public List<IssueDto> getIssues() {return issueService.getAllIssue();}
}
