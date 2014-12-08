package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.bo.Issue;
import cz.cvut.fel.wpa.tracker.bo.Operation;
import cz.cvut.fel.wpa.tracker.bo.Relation;
import cz.cvut.fel.wpa.tracker.bo.User;
import cz.cvut.fel.wpa.tracker.dto.RelationDto;
import cz.cvut.fel.wpa.tracker.dto.UserDto;
import cz.cvut.fel.wpa.tracker.helper.DtoTransformerHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Adam Uhlíř <uhlir.a@gmail.com>
 * Date: 8.12.14
 */
public class RelationServiceImpl extends AbstractDataAccessService implements RelationService {
    @Override
    public List<RelationDto> getAllRelations() {
        List<RelationDto> list = new ArrayList<RelationDto>();
        List<Relation> relations = genericDao.getAll(Relation.class);

        if(relations != null) {
            for (Relation relation : relations) {
                list.add(dtoFromBo(relation));
            }
        }

        return list;
    }

    @Override
    public List<RelationDto> getRelationsByType(short type) {
        List<RelationDto> list = new ArrayList<RelationDto>();
        List<Relation> relations = genericDao.getByProperty("type", type, Relation.class);
        if (relations != null) {
            for (Relation u : relations) {
                list.add(dtoFromBo(u));
            }
        }
        return list;
    }

    @Override
    public List<RelationDto> getIssueRelations(Long issueId) {
        List<RelationDto> list = new ArrayList<RelationDto>();
        List<Relation> relations = genericDao.getByProperty("issue", issueId, Relation.class);
        if (relations != null) {
            for (Relation u : relations) {
                list.add(dtoFromBo(u));
            }
        }
        return list;
    }

    @Override
    public List<RelationDto> getIssueRelationsTyped(Long issueId, short type) {
        List<RelationDto> list = new ArrayList<RelationDto>();
        List<Relation> relations = genericDao.getByProperty("issue", issueId, Relation.class);
        if (relations != null) {
            for (Relation u : relations) {
                if(u.getType() == type) list.add(dtoFromBo(u));
            }
        }
        return list;
    }

    @Override
    public RelationDto getRelationById(Long id) {
        Relation u = genericDao.getById(id, Relation.class);
        if (u != null) {
            return dtoFromBo(u);
        }
        return new RelationDto();
    }

    @Override
    public Long addRelation(short type, Long issue1, Long issue2) {
        Relation relation = new Relation();
        relation.setType(type);
        relation.addIssue(genericDao.getById(issue1, Issue.class));
        relation.addIssue(genericDao.getById(issue2, Issue.class));

        return genericDao.saveOrUpdate(relation).getId();
    }

    @Override
    public void deleteRelation(Long id) {
        genericDao.removeById(id, Relation.class);
    }

    private RelationDto dtoFromBo(Relation relation){
        return new RelationDto(relation.getId(), relation.getType(), DtoTransformerHelper.getIdentifiers(relation.getIssues()));
    }
}
