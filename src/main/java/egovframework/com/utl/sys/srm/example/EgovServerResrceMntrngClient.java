package egovframework.com.utl.sys.srm.example;

import java.io.IOException;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.egovframe.rte.fdl.cmmn.exception.EgovBizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 *       수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 * 2017.02.07 	이정은 	시큐어코딩(ES)-오류 메시지를 통한 정보노출[CWE-211]
 */


public class EgovServerResrceMntrngClient {
			
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovServerResrceMntrngClient.class);
		
	private JMXServiceURL address = null;
    private JMXConnector connector = null;
    private MBeanServerConnection mbs = null;
    private ObjectName name = null;
    private MBeanInfo mBeanInfo = null;
    private MBeanAttributeInfo[] attrInfos = null;
    
	public void connect() throws Exception {
		try {
    		address = new JMXServiceURL("service:jmx:rmi://127.0.0.1:9999/jndi/rmi://127.0.0.1:9999/server");
    		connector = JMXConnectorFactory.connect(address);

            mbs = connector.getMBeanServerConnection();

            name = new ObjectName("egovframework.com.utl.sys.srm.service:type=EgovServerResrceMntrng");

            mBeanInfo = mbs.getMBeanInfo(name);
            attrInfos = mBeanInfo.getAttributes();
            
            LOGGER.info("MBean info : " + mBeanInfo.getClassName());

            for (MBeanAttributeInfo attrInfo : attrInfos) {
            	if (attrInfo.getName().equals("CpuUsage")) {
            		LOGGER.info("CPU : " + mbs.getAttribute(name, attrInfo.getName()).toString());
            	} else if (attrInfo.getName().equals("MemoryUsage")){ 
            		LOGGER.info("Memory : " + mbs.getAttribute(name, attrInfo.getName()).toString());
            	
            	} else {
            		LOGGER.info(attrInfo.getName() + " = " + mbs.getAttribute(name, attrInfo.getName()));
            	}
            }
    	} catch (IOException ex) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
    		LOGGER.error("["+ex.getClass()+"] server connection : " + ex.getMessage());
    		throw new EgovBizException("[server resource monitoring] : create connection fail");
        
        //2017.02.07 	이정은 	시큐어코딩(ES)-오류 메시지를 통한 정보노출[CWE-211]
    	} finally {

    		if (connector != null)
            	try { 
            		connector.close();
            		
            	} catch(IOException ignore) {
            		LOGGER.error("[IOException] : connection close fail");
//            		throw new RuntimeException(ignore);
            		throw new EgovBizException("[server resource monitoring] : connection close fail");            		
            	}
        }
	}
	
	//KISA 보안약점 조치 (2018-10-29, 윤창원)
/*	public static void main(String[] args) throws Exception {
		EgovServerResrceMntrngClient client = new EgovServerResrceMntrngClient();
		
		client.connect();
	}
*/
}
