jQuery(".data-money").live("keyup", function() {
	var reg = jQuery(this).val().match(/\d+\.?\d{0,2}/);
	var txt = '';
	if (reg != null) {
		txt = reg[0];
	}
	jQuery(this).val(txt);
}).change(function() {
	jQuery(this).keypress();
	var v = jQuery(this).val();
	if (/\.$/.test(v)) {
		jQuery(this).val(v.substr(0, v.length - 1));
	}
});
//排除所有 ',' 
jQuery(".data-code").live("keyup", function() {
	var v = jQuery(this).val();
	jQuery(this).val(v.replace(/,/g,''));
	
}).change(function() {
	jQuery(this).keypress();
	var v = jQuery(this).val();
		jQuery(this).val(v.replace(/,/g,''));
});

/**
 * 正整数
 */
jQuery(".data-number").live("keyup", function() {
	var reg = jQuery(this).val().match(/^\+?[0-9][0-9]*$/);
	var txt = '';
	if (reg != null) {
		txt = reg[0];
	}
	jQuery(this).val(txt);
}).change(function() {
	jQuery(this).keypress();
	var v = jQuery(this).val();
	if (/\.$/.test(v)) {
		jQuery(this).val(v.substr(0, v.length - 1));
	}
});








