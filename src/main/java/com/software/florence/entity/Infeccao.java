package com.software.florence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.software.florence.common.pattern.application.model.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "INFECCAO")
public class Infeccao extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long oid;

    @Column(name = "CULT_LOCAL")
    private String CultLocal;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA")
    private Date Data;

    @Column(name = "TTO")
    private String TTO;

    @OneToOne
    @JoinColumn(name = "DOENCA_ATUAL", nullable = false)
    private DoencaAtual doencaAtual;

    @Override
    public Long getOid() {
        return oid;
    }

    @Override
    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getCultLocal() {
        return CultLocal;
    }

    public void setCultLocal(String cultLocal) {
        CultLocal = cultLocal;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date data) {
        Data = data;
    }

    public String getTTO() {
        return TTO;
    }

    public void setTTO(String TTO) {
        this.TTO = TTO;
    }

    public DoencaAtual getDoencaAtual() {
        return doencaAtual;
    }

    public void setDoencaAtual(DoencaAtual doencaAtual) {
        this.doencaAtual = doencaAtual;
    }
}
