<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
   <style>
     .menunone{
        display:none;
      }
   </style>

    <div class="vernav2 iconmenu">
    	<!--运维信息-->
    	<ul id="left1" class="menunone">
            <li><a href="#projectul" class="gallery">项目管理</a>
                <span class="arrow"></span>
                <ul id="projectul">
                    <li id="projectli1" class="power_101"><a href="admin/userShow">项目列表</a></li>
                </ul>
            </li>
            <li><a href="#deviceul" class="gallery">设备管理</a>
                <span class="arrow"></span>
                <ul id="deviceul">
                    <li id="deviceli1" class="power_111"><a href="admin/userShow">会员列表</a></li>
                </ul>
            </li>
            <li><a href="#userul" class="gallery">客户管理</a>
                <span class="arrow"></span>
                <ul id="userul">
                    <li id="userli1" class="power_121"><a href="admin/userShow">会员列表</a></li>
                </ul>
            </li>
             <li><a href="#workerul" class="gallery">人员管理</a>
                <span class="arrow"></span>
                <ul id="workerul">
                    <li id="workerli1" class="power_131"><a href="admin/userShow">会员列表</a></li>
                </ul>
            </li>
             <li><a href="#newsul" class="gallery">新闻管理</a>
                <span class="arrow"></span>
                <ul id="newsul">
                    <li id="newsli1" class="power_141"><a href="admin/newShow">新闻列表</a></li>
                </ul>
            </li>
        </ul>
    	<!--维修信息-->
        <ul id="left2" class="menunone">
            <li><a href="#repairul" class="gallery">维修管理</a>
                <span class="arrow"></span>
                <ul id="repairul">
                    <li id="repairli1" class="power_201"><a href="admin/userShow">会员列表</a></li>
                </ul>
            </li>
        </ul>
        <!--系统信息-->
    	<ul id="left3" class="menunone">
            <li><a href="#sysul" class="gallery">管理员管理</a>
                <span class="arrow"></span>
                <ul id="sysul">
                    <li id="sysli1" class="power_301"><a href="admin/adminShow">管理员列表</a></li>
                   
                </ul>
            </li>
            <li><a href="#keywordsul" class="gallery">词条管理</a>
                <span class="arrow"></span>
                <ul id="keywordsul">
                    <li id="keywordsli1" class="power_311"><a href="admin/userShow">巡检词条</a></li>
                    <li id="keywordsli2" class="power_312"><a href="admin/userShow">维修词条</a></li>
                    <li id="keywordsli3" class="power_313"><a href="admin/userShow">部门词条</a></li>
                    <li id="keywordsli4" class="power_314"><a href="admin/userShow">职位词条</a></li>
                </ul>
            </li>
        </ul>
        <a class="togglemenu"></a>
        <br /><br />
    </div><!--leftmenu-->
