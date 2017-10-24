package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SampleAdvice {

	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);

	@Before("execution(* org.zerock.service.MessageService*.*(..))")
	public void startLog(JoinPoint jp) {

		logger.info("----------------------------");
		logger.info("----------------------------");
		logger.info(Arrays.toString(jp.getArgs()));
		/* @Before의 주요 메소드 :
		 * Object[] getArgs():전달되는 모든 파라미터들을 Object로 가져온다.
		 * String getKind():해당 Advice의 타입을 알아낸다.
		 * Signature getSignature(): 실행하는 대상 객체의 메소드에 대한 정보를 알아낼때 사용, 
		 * Object getTarget(): target객체를 알아낼 때 사용.
		 * Object getThis(): Advice를 행하는 객체를 알아낼때 사용 
		 */
	}

	@Around("execution(* org.zerock.service.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {

		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));

		Object result = pjp.proceed();
		/* @Around의 주요 메소드 : Object proceed() 
		 * 다음 Advice를 실행하거나, 실제 Target객체의 메소드를 실행하는 기능  
		 */		
		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName() + " : " + (endTime - startTime));
		logger.info("=============================================");

		return result;
	}

}