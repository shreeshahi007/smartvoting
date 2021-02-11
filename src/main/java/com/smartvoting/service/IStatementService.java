package com.smartvoting.service;

import com.smartvoting.dto.StatementDTO;
import com.smartvoting.entity.Statement;
import rx.Single;


public interface IStatementService {
//    Single<Object> addStatement(Statement statement);

    Statement toStatement(StatementDTO statementDTO);

    Single<Object> addStatement(StatementDTO statementDTO);


}
