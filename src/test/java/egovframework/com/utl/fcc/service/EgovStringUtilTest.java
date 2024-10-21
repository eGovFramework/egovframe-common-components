package egovframework.com.utl.fcc.service;

import org.junit.Assert;
import org.junit.Test;

public class EgovStringUtilTest {
    @Test
    public void 보조사_은(){
        String expect = EgovStringUtil.getAuxiliaryParticle("이름");
        Assert.assertEquals( expect , "이름은" );
    }

    @Test
    public void 보조사_는(){
        String expect = EgovStringUtil.getAuxiliaryParticle("나이");
        Assert.assertEquals( expect , "나이는" );
    }

    @Test
    public void 주격_이(){
        String expect = EgovStringUtil.getSubjectParticle("광현");
        Assert.assertEquals( expect , "광현이" );
    }
    @Test
    public void 주격_empty(){
        String expect = EgovStringUtil.getSubjectParticle("철수");
        Assert.assertEquals( expect , "철수" );
    }

    @Test
    public void 목적격_을(){
        String expect = EgovStringUtil.getObjectParticle("생일");
        Assert.assertEquals( expect , "생일을" );
    }
    @Test
    public void 목적격_를(){
        String expect = EgovStringUtil.getObjectParticle("나이");
        Assert.assertEquals( expect , "나이를" );
    }
}
