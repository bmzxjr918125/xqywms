<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--公共头文件引入-->
<%@ include file="/WEB-INF/admin/Include/include.inc.new.jsp"  %>
<!DOCTYPE HTML>
<html>
<base href="<%=basePath%>">
<title>雪球能源运维后台管理系统</title>
</head>

<body class="withvernav">
<div class="bodywrapper">
    <!--topheader引入-->
    <jsp:include page="/WEB-INF/admin/Include/topheader.jsp" />
    <!--header引入-->
    <jsp:include page="/WEB-INF/admin/Include/header.jsp" />
    <!--vaernav2iconmenu引入-->
    <jsp:include page="/WEB-INF/admin/Include/vernav2iconmenu.jsp" />
    <!--右部类容--> 
    <div class="centercontent">
          <div class="pageheader" style="padding-bottom: 20px;">
            <h1 class="pagetitle">控制台</h1>
            <span class="pagedesc">欢迎使用雪球能源运维后台管理系统，${sessionScope.adminInfo.username}</span>
            
            <ul class="hornav" style="display: none;">
                <!-- <li class="current"><a href="#updates">远端工具</a></li>
                <li><a href="#activities">最近活动</a></li> -->
            </ul>
         </div><!--pageheader-->
        
        <div id="contentwrapper" class="contentwrapper">
      
        	<div id="updates" class="subcontent">
                    
                    <div class="two_third dashboard_left" style="margin-top: 20px;width: 100%;">
                    
                    <div class="notibar announcement">
                        <a class="close"></a>
                        <h3>JVM 内存使用情况:</h3>

								<%
									double total = (Runtime.getRuntime().totalMemory())
											/ (1024.0 * 1024);
									double max = (Runtime.getRuntime().maxMemory()) / (1024.0 * 1024);
									double free = (Runtime.getRuntime().freeMemory()) / (1024.0 * 1024);
									out.println("<h5 style='margin-left:10%;margin-top:1%'>当前JVM的最大可用内存:&nbsp;&nbsp;&nbsp;&nbsp;" + max + "MB</h5><br/>");
									out.println("<h5 style='margin-left:10%;'>当前JVM占用的内存总数:&nbsp;&nbsp;&nbsp;&nbsp;" + total
											+ "MB</h5><br/>");
									out.println("<h5 style='margin-left:10%;'>当前JVM空闲内存:&nbsp;&nbsp;&nbsp;&nbsp;" + free
											+ "MB</h5><br/>");
									out.println("<h5 style='margin-left:10%;'>JVM实际可用内存:&nbsp;&nbsp;&nbsp;&nbsp;" + (max - total + free) + "MB</h5><br/>");
								%>
						<h3>服务器线程信息:</h3>		
							<% 
							   for(Map.Entry<Thread,StackTraceElement[]> stackTrace:Thread.getAllStackTraces().entrySet()){
							       Thread thread=(Thread)stackTrace.getKey();
							       StackTraceElement[] stack=(StackTraceElement[]) stackTrace.getValue();
							        if(thread.equals(Thread.currentThread())){
							          continue;
							        }
							     out.println("<h5 style='margin-left:10%;margin-top:1%'>线程:"+thread.getName()+"</h5>");
							     
							     for(StackTraceElement element:stack){
							          out.println("<h5 style='margin-left:10%;margin-top:1%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+element+"</h5><br/>");
							       }
							       
							   }
							%>	
                    </div>
      
                    	<!-- <ul class="shortcuts">
                        	<li><a href="" class="settings"><span>设置</span></a></li>
                            <li><a href="" class="users"><span>用户</span></a></li>
                            <li><a href="" class="gallery"><span>相册</span></a></li>
                            <li><a href="" class="events"><span>事件</span></a></li>
                            <li><a href="" class="analytics"><span>分析</span></a></li>
                        </ul> -->
                        
                    </div><!--two_third dashboard_left -->
                    
            </div><!-- #updates -->
            
            <div id="activities" class="subcontent" style="display: none;">
            	&nbsp;
            </div><!-- #activities -->
        
        </div><!--contentwrapper-->
        
        <br clear="all" />
	</div><!-- centercontent -->
    
</div><!--bodywrapper-->

</body>
</html>

