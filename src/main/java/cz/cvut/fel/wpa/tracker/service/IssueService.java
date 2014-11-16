/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.dto.IssueDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface IssueService {
    /**
     * Get all issue in the system
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<IssueDto> getAllIssue();

    /**
     * Get all issues participated by the given user
     *
     * @param userId identifier of user
     * @return users issues
     */
    @Transactional(readOnly = true)
    public List<IssueDto> getUserIssues(Long userId);

    @Transactional(readOnly = true)
    public List<IssueDto> getUserLastIssues(Long userId, int count);

    @Transactional(readOnly = true)
    public List<IssueDto> getProductIssues(Long productId);

    @Transactional(readOnly = true)
    public List<IssueDto> getCustomerIssues(Long customerId);

    public IssueDto getIssueById(Long id);

    public Long editIssue(IssueDto issue);

    public Long addIssue(String topic, short state, Long product, List<Long> workers);

    public Long addIssue(String topic, short state, Long product, List<Long> workers, List<Long> operations);

    public Long addIssue(String topic, short state, Long product, List<Long> workers, List<Long> operations, List<Long> relations);


}
