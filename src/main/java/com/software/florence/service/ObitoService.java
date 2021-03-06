package com.software.florence.service;

import com.software.florence.common.exception.NegocioException;
import com.software.florence.common.pattern.application.service.Service;
import com.software.florence.entity.Obito;
import com.software.florence.entity.ProcessoDoacao;

import java.util.List;

public interface ObitoService extends Service<Obito, Long>{
    List<Obito> findByProcessoDoacao(ProcessoDoacao processoDoacao) throws NegocioException;
}
