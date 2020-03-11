<%@ page language="java" import= "java.io.*,java.util.*" contentType="text/html; charset=euc-kr" session="false" %>

<html lang="ko">
<%

    String command = "netstat -an";  // <---- 실행할 쉘명령어
    int lineCount = 0;
    String line="";

    Runtime rt = Runtime.getRuntime();
    Process ps = null;

    try {
      ps = rt.exec(command);

      BufferedReader br =
            new BufferedReader(
                    new InputStreamReader(
                          new SequenceInputStream(ps.getInputStream(), ps.getErrorStream())));

      while((line = br.readLine()) != null){
%>
    <%=line%><br> <!-- 결과 화면에 뿌리기... -->
<%
      }
      br.close();

   } catch(IOException e) {
     throw new RuntimeException(e);
   }
%>
</html>
