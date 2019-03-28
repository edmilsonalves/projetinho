package com.sisteminha.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import com.sisteminha.repository.entity.LancamentoEntity;

public class LancamentoModel { 
  private Long oid; 
  private LocalDateTime  dataInicial; 
  private LocalDateTime  dataFinal; 
  private String observacao; 
  private BigDecimal valorTotal;
  private String valorFormatado;
  private String dtFinal;
  
  //Construtor personalizado
  public LancamentoModel(LancamentoEntity e) {
	  this.oid = e.getId();
	  this.dataInicial = e.getDataInicial();
	  this.dataFinal = e.getDataFinal();
	  this.observacao = e.getObservacao();
	  this.valorTotal = e.getValorTotal();
  }
  
  //Construtor default
  public LancamentoModel() {}
  
  public Long getId() { 
    return oid; 
  } 
 
  public void setId(Long oid) { 
    this.oid = oid; 
  } 
 
  public LocalDateTime getDataInicial() { 
    return dataInicial; 
  } 
 
  public void setDataInicial(LocalDateTime dataInicial) { 
    this.dataInicial = dataInicial; 
  } 
 
  public LocalDateTime getDataFinal() { 
    return dataFinal; 
  } 
 
  public void setDataFinal(LocalDateTime dataFinal) { 
    this.dataFinal = dataFinal; 
  } 
 
  public String getObservacao() { 
    return observacao; 
  } 
 
  public void setObservacao(String observacao) { 
    this.observacao = observacao; 
  } 
 
  public BigDecimal getValorTotal() { 
    return valorTotal; 
  } 
 
  public void setValorTotal(BigDecimal valorTotal) { 
    this.valorTotal = valorTotal; 
  }

  public String getValorFormatado() {
	  //Se for nulo
	  if (valorTotal == null) {
		  return "";
	  }else {
		  //Formatar o valor para R$
		  Locale locale = new Locale("pt", "BR");      
		  NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		  return currencyFormatter.format(this.valorTotal.doubleValue());
	  }
  }

  public String getDtFinal() {
	    //Converter para date
	    LocalDateTime localDateTime = (LocalDateTime) dataFinal; 
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant(); 
		Date  date =  Date.from(instant);
		
		return "";
  }
  
  

  
   
} 
