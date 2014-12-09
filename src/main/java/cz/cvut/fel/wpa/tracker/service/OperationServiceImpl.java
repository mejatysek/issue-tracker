package cz.cvut.fel.wpa.tracker.service;

import cz.cvut.fel.wpa.tracker.bo.Issue;
import cz.cvut.fel.wpa.tracker.bo.Operation;
import cz.cvut.fel.wpa.tracker.bo.User;
import cz.cvut.fel.wpa.tracker.dto.OperationDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Adam Uhlíř <uhlir.a@gmail.com>
 * Date: 7.12.14
 */
@Component
public class OperationServiceImpl extends AbstractDataAccessService implements OperationService {
    @Override
    public List<OperationDto> getAllOperations() {
        List<OperationDto> operationDtos = new ArrayList<OperationDto>();
        List<Operation> operations = genericDao.getAll(Operation.class);

        if (operations != null) {
            for (Operation operation : operations) {
                operationDtos.add(dtoFromBo(operation));
            }
        }

        return operationDtos;
    }

    @Override
    public List<OperationDto> getUserOperations(Long userId) {
        return getUserLastOperations(userId, 0);
    }

    @Override
    public List<OperationDto> getUserLastOperations(Long userId, int number) {
        List<OperationDto> operationDtos = new ArrayList<OperationDto>();

        List<Operation> operations;
        if (number > 0) {
            operations = genericDao.getByPropertyOrderedDesc("owner", userId, "time", Operation.class);
        }else{
            operations = genericDao.getByProperty("owner", userId, Operation.class);
        }

        if (operations != null) {
            int counter = 0;
            for (Operation operation : operations) {
                if (number > 0 && counter >= number) break;
                operationDtos.add(dtoFromBo(operation));

                counter++;
            }
        }

        return operationDtos;
    }

    @Override
    public List<OperationDto> getIssueOperations(Long issueId) {
        List<OperationDto> operationDtos = new ArrayList<OperationDto>();
        List<Operation> operations = genericDao.getByProperty("issue", issueId, Operation.class);

        if (operations != null) {
            for (Operation operation : operations) {
                operationDtos.add(dtoFromBo(operation));
            }
        }

        return operationDtos;
    }

    @Override
    public OperationDto getOperationById(Long id) {
        Operation u = genericDao.getById(id, Operation.class);
        if (u != null) {
            return dtoFromBo(u);
        }
        return new OperationDto();
    }

    @Override
    public Long addOperation(Date time, Long ownerId, Long issueId, String body) {
        Operation operation = new Operation();
        operation.setTime(time);
        operation.setOwner(genericDao.getById(ownerId, User.class));
        operation.setIssue(genericDao.getById(issueId, Issue.class));
        operation.setBody(body);

        return genericDao.saveOrUpdate(operation).getId();
    }

    @Override
    public void deleteOperation(Long id) {
        genericDao.remove(genericDao.getById(id, Operation.class));
    }

    private OperationDto dtoFromBo(Operation operation) {
        return new OperationDto(operation.getId(), operation.getTime(), operation.getOwner().getId(), operation.getBody());
    }

}
