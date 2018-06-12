package ssm.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;

import org.springframework.stereotype.Component;

@Component
public class DemoAspect {

	// @Before("bean(userService)")
	public void Hello() {
		System.out.println("Hello World!");
		String s = null;
		s.length();

	}

	// @Before("bean(userService)")
	public void before() {
		System.out.println("Before");
	}

	// @AfterReturning("bean(userService)")
	public void afterReturning() {
		System.out.println("AfterReturning");
	}

	// @AfterThrowing(pointcut="bean(userService)"
	// ,throwing="ex")
	public void afterThrowing(Exception ex) throws Exception {
		System.out.println("AfterThrowing");
		throw ex;
	}

	// @After("bean(userService)")
	public void after() {
		System.out.println("After");
	}

	// 环绕
	// @Around("bean(*Service)")
	// @Around("within(cn.tedu.note.service.*)")
	@Around("execution(* cn.tedu.note.service.*Service.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("开始");
		// 调用业务方法
		Signature m = joinPoint.getSignature();
		System.out.println(m);// 输出方法签名
		Object value = joinPoint.proceed();
		System.out.println("结束");
		return value;// 业务方法的返回值
	}

}
