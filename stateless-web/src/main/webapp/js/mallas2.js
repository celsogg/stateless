var accion = 'aperturas';

jQuery(document).ready(function($) {
	jQuery.fn.verticalAlign = function ()
	{
		var height = $(this).parent().height();
		console.log(height + 'px');
		if(height > 0)
			return this.css("margin-top", height + 'px' )
		return this;
	};
	Handlebars.registerHelper('ancho', function(anual) {
		if(anual)
			return '2';
		return '1';
	});
	Handlebars.registerHelper('imprimir', function(asignaturas) {
		var html = '<div>';
		var nivel_actual = 1;
		var asignaturas_impresas = 0;
		var total_asignaturas = asignaturas.length;

		while(asignaturas_impresas < total_asignaturas){
			var imprimio = false;
			//console.log("======== Nivel: "+nivel_actual);
			for (var i = 0; i < asignaturas.length; i++) {
				if(!asignaturas[i].impresa){
				}
				if(!asignaturas[i].impresa && asignaturas[i].nivel == nivel_actual){
					// console.log("Nivel: "+nivel_actual);
					// console.log("Asignatura: "+asignaturas[i].nombre);
					imprimio = true;
					asignaturas[i].impresa = true;
	
					html += '<div class="contenedor_asignatura col-md-';
					html += asignaturas[i].anual ? '2' : '1';
					html += '"><div class="asignatura alto_asignatura centrar_vertical" id="asignatura_' + asignaturas[i].id + '"><span>'+asignaturas[i].nombre+'</span></div></div>';
					

					if(asignaturas[i].anual){
						nivel_actual = (nivel_actual + 1) % 12;
					}
					asignaturas_impresas++;
					break;
				}
			}
			if(!imprimio){
				html += '<div class="col-md-1 alto_asignatura"></div>';
			}

			nivel_actual = (nivel_actual) % 12 + 1;
		}
		html += '</div>';
		html = $(html);
		
		//html.find('.centrar_vertical').verticalAlign();

		$('.centrar_vertical').css('padding-top', '10px');

		return html.html();
	});
  // Code using $ as usual goes here.
	var source   = $("#asignaturas").html();
	var template = Handlebars.compile(source);
	var context = 
	[
		{
			nombre: 'Calculo',
			id: 1111,
			nivel: 1,
			anual: true,
			aperturas: [
				1122,
				1123,
				1124,
			]
		},
		{
			nombre: 'Algebra',
			id: 1112,
			nivel: 1,
			anual: true,
		},
		{
			nombre: 'Fisica',
			id: 1113,
			nivel: 1,
			anual: true,
		},
		{
			nombre: 'Introduccion a la Quimica',
			id: 1114,
			nivel: 1,
			anual: false,
		},
		{
			nombre: 'Introduccion a la Programacion',
			id: 1115,
			nivel: 2,
			anual: false,
		},
		{
			nombre: 'Calculo Avanzado',
			id: 1116,
			nivel: 3,
			anual: false,
		},
		{
			nombre: 'Ecuaciones diferenciales',
			id: 1117,
			nivel: 3,
			anual: false,
		},
		{
			nombre: 'Electro Magnetismo y fisica moderna',
			id: 1118,
			nivel: 3,
			anual: false,
		},
		{
			nombre: 'Lenguajes y Paradigmas de Programacion',
			id: 1119,
			nivel: 3,
			anual: false,
		},
		{
			nombre: 'Probabilidad y estadistica',
			id: 1120,
			nivel: 4,
			anual: false,
		},
		{
			nombre: 'Microeconomia',
			id: 1121,
			nivel: 4,
			anual: false,
		},
		{
			nombre: 'Topicos de Matematicas',
			id: 1122,
			nivel: 4,
			anual: false,
		},
		{
			nombre: 'Estructuras de Datos y Algoritmos',
			id: 1123,
			nivel: 4,
			anual: false,
		},
		{
			nombre: 'Estructuras Discretas',
			id: 1124,
			nivel: 4,
			anual: false,
		},
		{
			nombre: 'MACROECONOMÍA',
			id: 1125,
			nivel: 5,
			anual: false,
		},
		{
			nombre: 'AUTÓMATAS Y LENGUAJES FORMALES',
			id: 1126,
			nivel: 5,
			anual: false,
		},
		{
			nombre: 'FUNDAMENTOS DE PROCESOS PRODUCTIVOS',
			id: 1127,
			nivel: 5,
			anual: false,
		},
		{
			nombre: 'ALGORITMOS AVANZADOS',
			id: 1128,
			nivel: 5,
			anual: false,
		},
		{
			nombre: 'ORGANIZACIÓN DE COMPUTADORES',
			id: 1129,
			nivel: 5,
			anual: false,
		},
		{
			nombre: 'FINANZAS',
			id: 1130,
			nivel: 6,
			anual: false,
		},
		{
			nombre: 'LÓGICA Y TEORÍA DE LA COMPUTACIÓN',
			id: 1131,
			nivel: 6,
			anual: false,
		},
		{
			nombre: 'COMPILADORES',
			id: 1132,
			nivel: 6,
			anual: false,
		},
		{
			nombre: 'COMUNICACIÓN DE DATOS',
			id: 1133,
			nivel: 6,
			anual: false,
		},
		{
			nombre: 'SISTEMAS OPERATIVOS',
			id: 1134,
			nivel: 6,
			anual: false,
		},
		{
			nombre: 'MÉTODOS DE OPTIMIZACIÓN',
			id: 1135,
			nivel: 7,
			anual: false,
		},
		{
			nombre: 'COMPUTACIÓN PARALELA',
			id: 1136,
			nivel: 7,
			anual: false,
		},
		{
			nombre: 'INGENIERÍA DE SISTEMAS',
			id: 1137,
			nivel: 7,
			anual: false,
		},
		{
			nombre: 'REDES DE COMUNICACIÓN',
			id: 1138,
			nivel: 7,
			anual: false,
		},
		{
			nombre: 'FUNDAMENTOS DE BASE DE DATOS',
			id: 1139,
			nivel: 7,
			anual: false,
		},
		{
			nombre: 'ANTROPOLOGÍA E INGENIERÍA',
			id: 1140,
			nivel: 8,
			anual: false,
		},
		{
			nombre: 'FUNDAMENTOS DE INGENIERÍA DE SOFTWARE',
			id: 1141,
			nivel: 8,
			anual: false,
		},
		{
			nombre: 'CONTROL Y SIMULACIÓN',
			id: 1142,
			nivel: 8,
			anual: false,
		},
		{
			nombre: 'SISTEMAS DISTRIBUIDOS',
			id: 1143,
			nivel: 8,
			anual: false,
		},
		{
			nombre: 'PROYECTOS DE BASE DE DATOS',
			id: 1144,
			nivel: 8,
			anual: false,
		},
		{
			nombre: 'EVALUACIÓN DE PROYECTOS',
			id: 1145,
			nivel: 9,
			anual: false,
		},
		{
			nombre: 'PROYECTOS DE INGENIERÍA DE SOFTWARE',
			id: 1146,
			nivel: 9,
			anual: false,
		},
		{
			nombre: 'COMPORTAMIENTO HUMANO EN EL TRABAJO',
			id: 1147,
			nivel: 9,
			anual: false,
		},
		{
			nombre: 'INTERACCIÓN HUMANO COMPUTADOR',
			id: 1148,
			nivel: 9,
			anual: false,
		},
		{
			nombre: 'ELECTIVO PROFESIONAL I',
			id: 1149,
			nivel: 9,
			anual: false,
		},
		{
			nombre: 'ADMINISTRACIÓN DE EMPRESAS',
			id: 1150,
			nivel: 10,
			anual: false,
		},
		{
			nombre: 'PROYECTOS DE INGENIERÍA INFORMÁTICA',
			id: 1151,
			nivel: 10,
			anual: false,
		},
		{
			nombre: 'ELECTIVO PROFESIONAL II',
			id: 1152,
			nivel: 10,
			anual: false,
		},
		{
			nombre: 'ELECTIVO PROFESIONAL III',
			id: 1153,
			nivel: 10,
			anual: false,
		},
		{
			nombre: 'ELECTIVO DE PROFESIONAL IV',
			id: 1154,
			nivel: 10,
			anual: false,
		},
		{
			nombre: 'DIRECCIÓN Y GESTIÓN DE EMPRESAS',
			id: 1155,
			nivel: 11,
			anual: false,
		},
		{
			nombre: 'ELECTIVO PROFESIONAL V',
			id: 1156,
			nivel: 11,
			anual: false,
		},
		{
			nombre: 'ELECTIVO PROFESIONAL VI',
			id: 1157,
			nivel: 11,
			anual: false,
		},
		{
			nombre: 'SEMINARIO DE TITULACIÓN',
			id: 1158,
			nivel: 11,
			anual: false,
		},

		{
			nombre: 'TRABAJO DE TITULACIÓN',
			id: 1159,
			nivel: 12,
			anual: false,
			prerequisitos: [
				1111,
				1112,
			],
		},
	]
	var html    = template(context);

	// Insertar titulos
	var nivel_mas_alto = 1;
	for (var i = context.length - 1; i >= 0; i--) {
		if(context[i].nivel > nivel_mas_alto)
			nivel_mas_alto = context[i].nivel;
	};
	var texto_niveles = '';
	for (var nivel = 1; nivel <= nivel_mas_alto; nivel++) {
		texto_niveles += '<div class="col-xs-1 texto_nivel">Nivel ' + nivel + '</div>';
	};

	html = texto_niveles + html;


	// ===== APERTURAS =====

	function getById(context, id){
		for (var i = 0; i < context.length; i++) {
			if(context[i].id == id){
				return context[i];
			}
		};
	}

	$('#outercanvas').html(html);

	function ManejarAperturas(var_this, propiedades_elementos, propiedades_actual){
		var id = $(var_this).attr('id').replace('asignatura_', '');
		
		var asignatura = getById(context, id);
		var mis_aperturas = asignatura.aperturas ? asignatura.aperturas : [];

		$.each(mis_aperturas, function(key, apertura) {
			var apertura_id = '#asignatura_' + apertura;
			$.each(propiedades_elementos, function(key, propiedad){
				$(apertura_id).css(propiedad.propiedad, propiedad.valor);
			});
		});

		$.each(propiedades_actual, function(key, propiedad){
			$('#asignatura_'+id).css(propiedad.propiedad, propiedad.valor);
		});
	}

	function ManejarPrerequisitos(var_this, propiedades_elementos, propiedades_actual){
		var id = $(var_this).attr('id').replace('asignatura_', '');
		
		var asignatura = getById(context, id);
		var mis_aperturas = asignatura.prerequisitos ? asignatura.prerequisitos : [];

		$.each(mis_aperturas, function(key, apertura) {
			var apertura_id = '#asignatura_' + apertura;
			$.each(propiedades_elementos, function(key, propiedad){
				$(apertura_id).css(propiedad.propiedad, propiedad.valor);
			});
		});

		$.each(propiedades_actual, function(key, propiedad){
			$('#asignatura_'+id).css(propiedad.propiedad, propiedad.valor);
		});
	}

	$('.asignatura').on('mouseenter', function(){
		if(accion == 'aperturas'){
			var propiedades_elementos = [
				{ propiedad: 'color', valor: 'white' },
				{ propiedad: 'background', valor: '#FF7319' },
			];
			var propiedades_actual = [
				{ propiedad: 'color', valor: 'white' },
				{ propiedad: 'background', valor: '#0052CC' },
			]
			ManejarAperturas(this, propiedades_elementos, propiedades_actual);
		}
	});
	$('.asignatura').on('mouseleave', function(){
		if(accion == 'aperturas'){
			var propiedades_elementos = [
				{ propiedad: 'color', valor: 'black' },
				{ propiedad: 'background', valor: 'white' },
			];
			var propiedades_actual = [
				{ propiedad: 'color', valor: 'black' },
				{ propiedad: 'background', valor: 'white' },
			]
			ManejarAperturas(this, propiedades_elementos, propiedades_actual);
		}
	});

	$('.asignatura').on('mouseenter', function(){
		if(accion == 'prerequisitos'){
			var propiedades_elementos = [
				{ propiedad: 'color', valor: 'white' },
				{ propiedad: 'background', valor: '#FF7319' },
			];
			var propiedades_actual = [
				{ propiedad: 'color', valor: 'white' },
				{ propiedad: 'background', valor: '#0052CC' },
			]
			ManejarPrerequisitos(this, propiedades_elementos, propiedades_actual);
		}
	});
	$('.asignatura').on('mouseleave', function(){
		if(accion == 'prerequisitos'){
			var propiedades_elementos = [
				{ propiedad: 'color', valor: 'black' },
				{ propiedad: 'background', valor: 'white' },
			];
			var propiedades_actual = [
				{ propiedad: 'color', valor: 'black' },
				{ propiedad: 'background', valor: 'white' },
			]
			ManejarPrerequisitos(this, propiedades_elementos, propiedades_actual);
		}
	});

	// ----- APERTURAS -----

	$('#fw').on('click', function(){
		accion = 'aperturas';
	});
	$('#bw').on('click', function(){
		accion = 'prerequisitos';
	});
});