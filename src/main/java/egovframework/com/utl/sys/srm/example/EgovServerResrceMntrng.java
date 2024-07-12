package egovframework.com.utl.sys.srm.example;

//import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
//import java.net.MalformedURLException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;

//import javax.management.InstanceAlreadyExistsException;
//import javax.management.MBeanRegistrationException;
//import javax.management.MBeanServer;
//import javax.management.MalformedObjectNameException;
//import javax.management.NotCompliantMBeanException;
//import javax.management.ObjectName;
//import javax.management.remote.JMXConnectorServer;
//import javax.management.remote.JMXConnectorServerFactory;
//import javax.management.remote.JMXServiceURL;

/*
 *       수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 * 2017.02.07 	이정은 	시큐어코딩(ES)-오류 메시지를 통한 정보노출[CWE-210]
 */

public class EgovServerResrceMntrng implements EgovServerResrceMntrngMBean {
	
	private Object getOSInfo(String getMethod) {
		
		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		
		for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
			method.setAccessible(true);
			if (method.getName().startsWith("get") && Modifier.isPublic(method.getModifiers()) && method.getName().equals(getMethod)) {
				Object value;
				try {
					value = method.invoke(operatingSystemMXBean);
					
				} catch (IllegalAccessException e) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
					value = e;
				} catch (IllegalArgumentException e) {
					value = e;
				} catch (InvocationTargetException e) {
					value = e;
				}
				//System.out.println(method.getName() + " = " + value);
				return value;
			} // if
		} // for
		
		return null;
	}
	
	@Override
	public double getCpuUsage() {
		OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		RuntimeMXBean runbean = (RuntimeMXBean) ManagementFactory.getRuntimeMXBean();

		long bfprocesstime = (Long)getOSInfo("getProcessCpuTime");
		long bfuptime = runbean.getUptime();
		@SuppressWarnings("unused")
		long ncpus = osbean.getAvailableProcessors();

		for (int i = 0; i < 1000000; ++i) {
			ncpus = osbean.getAvailableProcessors();
		}

		long afprocesstime = (Long)getOSInfo("getProcessCpuTime");
		long afuptime = runbean.getUptime();

		double cal = (afprocesstime - bfprocesstime) / ((afuptime - bfuptime) * 10000f);

		double usage = Math.min(99f, cal);

		//System.out.println("Calculation: " + cal);
		//System.out.println("CPU Usage: " + usage);
		
		return usage;
	}

	@Override
	public double getMemoryUsage() {
		long freeMemory = (Long)getOSInfo("getFreePhysicalMemorySize");
		long totalMemory = (Long)getOSInfo("getTotalPhysicalMemorySize");
		
		return (totalMemory - freeMemory) / (double)totalMemory * 100D;
	}
	
	//KISA 보안약점 조치 (2018-10-29, 윤창원)
/*	public static void main(String[] args) {
		try {
			
			Registry rmi = LocateRegistry.createRegistry(9999);
			
			System.out.println("RMI Server started : " + rmi.toString());
			
			MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
			
			ObjectName name = new ObjectName("egovframework.com.utl.sys.srm.service:type=EgovServerResrceMntrng"); 
			EgovServerResrceMntrng mbean = new EgovServerResrceMntrng(); 
			mbeanServer.registerMBean(mbean, name); 
			
			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://127.0.0.1:9999/server"); 

			JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbeanServer); 
			
			cs.start(); 
			
		//2017.02.08 	이정은 	시큐어코딩(ES)-오류 메시지를 통한 정보노출[CWE-210]
		} catch (MalformedObjectNameException e) {
			LOGGER.error("[MalformedObjectNameException] : server connection");
		} catch (InstanceAlreadyExistsException e) {
			LOGGER.error("[InstanceAlreadyExistsException] : server connection ");
		} catch (MBeanRegistrationException e) {
			LOGGER.error("[MBeanRegistrationException] : server connection");
		} catch (NotCompliantMBeanException e) {
			LOGGER.error("[NotCompliantMBeanException] : server connection");
		} catch (MalformedURLException e) {
			LOGGER.error("[MalformedURLException] : server connection");
		} catch (IOException e) {
			LOGGER.error("[IOException] : server connection");
		} catch (Exception e) {
			LOGGER.error("["+ e.getClass() +" ] server connection : " + e.getMessage());
		}
	}*/
	
}
