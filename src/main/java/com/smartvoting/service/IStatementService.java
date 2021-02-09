package com.smartvoting.service;

import com.smartvoting.entity.Statement;
import rx.Single;

public interface IStatementService {
    Single<Statement> addStatement(Statement statement);
}
