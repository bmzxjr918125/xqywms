/**
 * 控制上 左部菜单选中
 */
/*$.cookie('the_cookie'); // 读取 cookie
$.cookie('the_cookie', 'the_value'); // 存储 cookie
$.cookie('the_cookie', 'the_value', { expires: 7 }); // 存储一个带7天期限的 cookie
$.cookie('the_cookie', '', { expires: -1 }); // 删除 cookie
*/
//当前控制面板选中项
//var current_left_ul,current_left_li,current_top_li;
//$.cookie('current_left_ul', 'the_value');
	//顶部点击绑定

      jQuery(document).ready(function(){
		  jQuery(".headermenu").find("li").live("click",function(){
      		clickTopMenu(this,jQuery(this).attr("leftid"));
      	});
      	//左侧点击绑定
      	jQuery(".iconmenu").find("ul li ul li").live("click",function(){
      		clickLeftMenu(this);

      	});
	    chioceMenu();
	    hiddenMenu();
	});

/**
 * 左部菜单点击
 * @param obj
 */
function clickLeftMenu(obj){

	var current_left_li=jQuery(obj).attr("id");
	jQuery.cookie('current_left_li',current_left_li);

}
/**
 * 顶部菜单点击
 * @param obj
 */
function clickTopMenu(obj,leftid){

	var current_top_li=jQuery(obj).attr("id");
	jQuery.cookie('current_top_li',current_top_li);
	jQuery.cookie('current_left_ul',leftid);
	//清除左侧cookie
	jQuery.cookie('current_left_li', '', { expires: -1 });
	chioceMenu();
}
/**
 * 选中菜单
 */
function chioceMenu(){

	//上部菜单
	var current_top_li=jQuery.cookie('current_top_li');
	jQuery(".headermenu").find(".current").removeClass();
	if(typeof current_top_li=='undefined' || current_top_li==null){
		jQuery(".headermenu").find("li").eq(0).addClass("current");
	}else{
		jQuery("#"+current_top_li).addClass("current");
	}

	//左侧菜单
	var current_left_ul=jQuery.cookie('current_left_ul');
	var current_left_li=jQuery.cookie('current_left_li');

	jQuery(".iconmenu").find("ul").addClass("menunone");
	if(typeof current_left_ul!='undefined' && current_left_ul!=null){
		jQuery("#"+current_left_ul).attr("class","");
	}else{
		jQuery(".iconmenu").find("ul").eq(0).attr("class","");
	}
	if(typeof current_left_li!='undefined' && current_left_li!=null){

		jQuery("#"+current_left_li).addClass("current");
		jQuery("#"+current_left_li).parent().parent().addClass("current");
	}
}
/**
 * 隐藏菜单
 */
function hiddenMenu() {
    
    var power = jQuery('#hidden_powers').val();
    
    if (power == 1) {
                jQuery('#left2').hide();
                jQuery('#headermenuli2').hide();
            
    }
    
}

