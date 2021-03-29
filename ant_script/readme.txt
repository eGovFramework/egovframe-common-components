----------------------------------------------------------------------------------
Build v3.6

ant-contrib.jar 설치 
https://sourceforge.net/projects/ant-contrib/files/ant-contrib/
---------------------------------------------
  참고 : http://ant-contrib.sourceforge.net/
           http://ant-contrib.sourceforge.net/tasks/tasks/index.html  
           http://sourceforge.net/project/showfiles.php?group_id=36177&package_id=28636
 
 세번째 주소로 들어가서 ant-contrib를 다운받는다.
 ant-contrib.jar 를 eclipse\plugins\org.apache.ant_1.6.5\lib\ 안에 복사한다.
 Window > Preferences > Ant > Runtime 에 들어가서
 Classpath탭 >  Ant Home Entries에 ant-contrib.jar를 추가한다.

 
 
 <target name="init" description="common ant" >
	<condition property="auth">
		  <equals arg1="${auth}" arg2="session"/>
	</condition>
	<echo>execute  auth:${auth} ~~~~~asdfasdfasdfasfd</echo>
</target>
<antcall target="init"></antcall>



	<!-- EgovWebApplicationInitializer.java -->		
		<!--
		<copy todir="src/main/java/egovframework/com/cmm/config" overwrite="true">
			<fileset dir="auth/servlet"/>
			<globmapper from="EgovWebApplicationInitializer.java" to="EgovWebApplicationInitializer.java"/>
		</copy>
		-->
		
		<!-- EgovWebApplicationInitializer.java -->
		<copy file="auth/servlet/EgovWebApplicationInitializer.java" tofile="${path.java}/cmm/config/EgovWebApplicationInitializer.java" overwrite="true"/>