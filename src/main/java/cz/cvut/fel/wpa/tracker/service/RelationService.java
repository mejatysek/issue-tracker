/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.dto.RelationDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface RelationService {

    @Transactional(readOnly = true)
    public List<RelationDto> getAllRelations();

    @Transactional(readOnly = true)
    public List<RelationDto> getRelationsByType(short type);

    @Transactional(readOnly = true)
    public List<RelationDto> getIssueRelations(Long issueId);

    @Transactional(readOnly = true)
    public List<RelationDto> getIssueRelationsTyped(Long issueId, short type);

    @Transactional(readOnly = true)
    public RelationDto getRelationById(Long id);

    public Long addRelation(short type, Long issue1, Long issue2);

    public void deleteRelation(Long id);

}
