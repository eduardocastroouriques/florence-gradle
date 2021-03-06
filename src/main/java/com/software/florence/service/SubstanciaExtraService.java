package com.software.florence.service;

import com.software.florence.common.exception.NegocioException;
import com.software.florence.common.pattern.application.service.Service;
import com.software.florence.entity.DoencaPrevia;
import com.software.florence.entity.SubstanciaExtra;

public interface SubstanciaExtraService extends Service<SubstanciaExtra, Long>{
    Iterable<SubstanciaExtra> findByDoencaPrevia(DoencaPrevia doencaPrevia) throws NegocioException;
}
