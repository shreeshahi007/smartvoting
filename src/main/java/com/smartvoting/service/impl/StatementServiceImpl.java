package com.smartvoting.service.impl;

import com.smartvoting.entity.Statement;
import com.smartvoting.repository.StatementRepository;
import com.smartvoting.service.IStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Single;

@Service
public class StatementServiceImpl implements IStatementService {
    @Autowired
    StatementRepository statementRepository;
    @Override
    public Single<Statement> addStatement(Statement statement) {
        return Single.create(
                singleSubscriber -> {
                    Statement addedStatement = statementRepository.save(statement);
                    singleSubscriber.onSuccess(addedStatement);
                }
        );
    }
}
