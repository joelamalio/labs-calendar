var JAProject = JAProject || {};

JAProject.MaskDate = (function() {
	
	function MaskDate() {
		this.inputDate = $('.ja-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.inputmask("99/99/9999", { 
			"placeholder" : "dd/mm/aaaa"
		});

		this.inputDate.datepicker({
			format: 'dd/mm/yyyy',
			orientation: 'bottom',
			language: 'pt-BR',
			todayHighlight: true,
			autoclose: true
		});
	}
	
	return MaskDate;
	
}());


$(function() {
	var maskDate = new JAProject.MaskDate();
	maskDate.enable();
});