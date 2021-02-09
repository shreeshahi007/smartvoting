package com.smartvoting.service;

import com.smartvoting.entity.Statement;
import org.springframework.stereotype.Service;
import rx.Single;


public interface IStatementService {
    Single<Statement> addStatement(Statement statement);
}
