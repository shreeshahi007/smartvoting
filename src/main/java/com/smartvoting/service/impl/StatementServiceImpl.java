package com.smartvoting.service.impl;

import com.smartvoting.dto.StatementDTO;
import com.smartvoting.entity.Statement;
import com.smartvoting.repository.RoomRepository;
import com.smartvoting.repository.StatementRepository;
import com.smartvoting.service.IStatementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

@Service
public class StatementServiceImpl implements IStatementService {
    @Autowired
    StatementRepository statementRepository;
    @Autowired
    RoomRepository roomRepository;
//
//    @Override
//    public Single<Object> addStatement(StatementDTO statementDTO) {
//        return Single.create(
//                singleSubscriber -> {
//                    Statement addedStatement = statementRepository.save(statementDTO);
//                    singleSubscriber.onSuccess(addedStatement);
//                }
//        );
//    }
//

@Override
public Single<Object> addStatement(StatementDTO statementDTO) {
    return Single.create(
            singleSubscriber -> {
                Statement addedStatement = statementRepository.save(toStatement(statementDTO));
                singleSubscriber.onSuccess(addedStatement);
            }
    );
}


    @Override
    public Statement toStatement(StatementDTO statementDTO){
        Statement addedStatement=new Statement();
        BeanUtils.copyProperties(statementDTO,addedStatement);
        addedStatement.setRoomId(statementDTO.getRoomId());

        return addedStatement;
    }


}
