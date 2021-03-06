package com.software.florence.service;

import com.software.florence.common.exception.NegocioException;
import com.software.florence.common.pattern.application.service.Service;
import com.software.florence.entity.Dva;
import com.software.florence.entity.ProcessoDoacao;

import java.util.List;

public interface DvaService extends Service<Dva, Long>{
    List<Dva> findByProcessoDoacao(ProcessoDoacao processoDoacao) throws NegocioException;
}
