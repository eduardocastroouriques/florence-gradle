package com.software.florence.resource;

import com.software.florence.common.pattern.application.resource.AbstractResource;
import com.software.florence.entity.Orgao;
import com.software.florence.service.OrgaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/orgao")
public class OrgaoResource extends AbstractResource<Orgao, Long> {

    @Autowired
    OrgaoService orgaoService;

    public OrgaoResource(OrgaoService orgaoService) {
        super(orgaoService);
        this.orgaoService = orgaoService;
    }
}
