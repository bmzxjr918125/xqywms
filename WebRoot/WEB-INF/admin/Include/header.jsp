<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

    <div class="header">
    	<ul class="headermenu">
        	<li id="headermenuli1" leftid="left1"><a href="javascript:void(0);">运维信息</a></li>
            <li id="headermenuli2" leftid="left2"><a href="javascript:void(0);">维修信息</a></li>
            <li id="headermenuli3" leftid="left3"><a href="javascript:void(0);">系统信息</a></li>
        </ul>
        <input type="hidden" id="hidden_powers" value="${session.adminInfo.type}" />
       <div class="headerwidget">
        	<div class="earnings" style="min-width: 350px; text-align: center;">
            	<div class="one_half" style="width: 30%; display: inline-block; text-align: center; float: none; margin-right: 0px;">
                	<h4>总会员数</h4>
                    <h3>${sessionScope.numberJo.allNumber}</h3>
                </div><!--one_half-->
                <div class="one_half" style="width: 30%; display: inline-block; text-align: center; float: none; margin-right: 0px;">
                	<h4>今日注册会员</h4>
                    <h2 style="color: #78ce07;">${sessionScope.numberJo.dayNumber}</h2>
                </div>
               
                <!--one_half last-->
            </div><!--earnings-->
        </div><!--headerwidget-->
    </div><!--header-->
