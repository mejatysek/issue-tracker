/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.dto.OperationDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface OperationService {

    @Transactional(readOnly = true)
    public List<OperationDto> getAllOperations();

    @Transactional(readOnly = true)
    public List<OperationDto> getUserOperations(Long userId);

    @Transactional(readOnly = true)
    public List<OperationDto> getUserLastOperations(Long userId, int number);

    @Transactional(readOnly = true)
    public List<OperationDto> getIssueOperations(Long issueId);

    @Transactional(readOnly = true)
    public OperationDto getOperationById(Long id);

    public Long addOperation(short type, Long issue1, Long issue2);

}
