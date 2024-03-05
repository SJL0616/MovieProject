/**
 * 
 */

const moveticketting = function(code) {
	let form = $('<form>')
		.attr({ 
			'action': `ticketing.do`, 
			'method': 'post' 
			});
	form.append($('<input>')
	.attr({ 
		'type': 'hidden', 
		'name': 'move', 
		'value': `${code}` 
		}));
	$('.header-content').append(form);
	form.submit();
}