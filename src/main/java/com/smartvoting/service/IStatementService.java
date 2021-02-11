package com.smartvoting.service;

import com.smartvoting.dto.StatementDTO;
import com.smartvoting.entity.Statement;
import org.springframework.stereotype.Service;
import rx.Single;

import java.util.List;


@Service
public interface IStatementService {
//    Single<Object> addStatement(Statement statement);

    Statement toStatement(StatementDTO statementDTO);

    Single<Object> addStatement(StatementDTO statementDTO);

    Single<List<Statement>>displayStatement(String roomId);

}
