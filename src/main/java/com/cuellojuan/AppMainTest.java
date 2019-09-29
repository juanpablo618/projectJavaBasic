//package com.concretepage;
//
//import org.apache.log4j.Logger;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import com.cuellojuan.config.AppConfig;
//import com.cuellojuan.dao.IPersonDao;
//
//
//public class Spring4Hibernate4Test {
//
//	private static org.apache.log4j.Logger log = Logger.getLogger(AppConfig.class);
//
//
//  public static void main(String[] args) {
//	  AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//  	  ctx.register(AppConfig.class);
//	  ctx.refresh();
//
//	  IPersonDao pdao = ctx.getBean(IPersonDao.class);
//	  pdao.savePerson();
//
//	  System.out.println("---------- Done------------ log de AppConfig");
//	  System.out.println("");
//
//	  if (log.isTraceEnabled())
//	  {
//		  log.trace("mensaje de trace");
//	  }
//
//	  if (log.isDebugEnabled())
//	  {
//		  log.debug("mensaje de debug");
//	  }
//
//	  if (log.isInfoEnabled())
//	  {
//		  log.info("mensaje de info");
//	  }
//
//
//
//  }
//}
