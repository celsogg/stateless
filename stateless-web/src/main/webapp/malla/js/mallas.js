jQuery(document).ready(function($) {
	// Code using $ as usual goes here.
	var source = $("#asignatura").html();
	var template = Handlebars.compile(source);
	var context = [{
		nivel: 1,
		nivel_dos: 2,
		tiene_anual: true,
		tiene_semestral: true,
		anual: {
			nivel: 1,
			asignaturas: [{
				nombre: 'Calculo',
				id: 1111,
			}, {
				nombre: 'Algebra',
				id: 1112,
			}, {
				nombre: 'Fisica',
				id: 1113,
			}, ]
		},
		semestral: [{
			nivel: 1,
			asignaturas: [{
				nombre: 'Introduccion a la Quimica',
				id: 1114,
			}, ]
		}, {
			nivel: 2,
			asignaturas: [{
				nombre: 'Introduccion a la Programacion',
				id: 1115,
			}, ]
		}, ]
	}, {
		nivel: 3,
		tiene_anual: false,
		tiene_semestral: true,
		semestral: [{
			nivel: 3,
			asignaturas: [{
				nombre: 'Calculo Avanzado',
				id: 1116,
			}, {
				nombre: 'Ecuaciones diferenciales',
				id: 1117,
			}, {
				nombre: 'Electro Magnetismo y fisica moderna',
				id: 1118,
			}, {
				nombre: 'Lenguajes y Paradigmas de Programacion',
				id: 1119,
			}, ]
		}]
	}, {
		nivel: 4,
		tiene_anual: false,
		tiene_semestral: true,
		semestral: [{
			nivel: 4,
			asignaturas: [{
				nombre: 'Probabilidad y estadistica',
				id: 1120,
			}, {
				nombre: 'Microeconomia',
				id: 1121,
			}, {
				nombre: 'Topicos de Matematicas',
				id: 1122,
			}, {
				nombre: 'Estructuras de Datos y Algoritmos',
				id: 1123,
			}, {
				nombre: 'Estructuras Discretas',
				id: 1124,
			}, ]
		}]
	}, {
		nivel: 5,
		tiene_anual: false,
		tiene_semestral: true,
		semestral: [{
			nivel: 5,
			asignaturas: [{
				nombre: 'MACROECONOMÍA',
				id: 1125,
			}, {
				nombre: 'AUTÓMATAS Y LENGUAJES FORMALES',
				id: 1126,
			}, {
				nombre: 'FUNDAMENTOS DE PROCESOS PRODUCTIVOS',
				id: 1127,
			}, {
				nombre: 'ALGORITMOS AVANZADOS',
				id: 1128,
			}, {
				nombre: 'ORGANIZACIÓN DE COMPUTADORES',
				id: 1129,
			}, ]
		}]
	}, {
		nivel: 6,
		tiene_anual: false,
		tiene_semestral: true,
		semestral: [{
			nivel: 6,
			asignaturas: [{
				nombre: 'FINANZAS',
				id: 1130,
			}, {
				nombre: 'LÓGICA Y TEORÍA DE LA COMPUTACIÓN',
				id: 1131,
			}, {
				nombre: 'COMPILADORES',
				id: 1132,
			}, {
				nombre: 'COMUNICACIÓN DE DATOS',
				id: 1133,
			}, {
				nombre: 'SISTEMAS OPERATIVOS',
				id: 1134,
			}, ]
		}]
	}, {
		nivel: 7,
		tiene_anual: false,
		tiene_semestral: true,
		semestral: [{
			nivel: 7,
			asignaturas: [{
				nombre: 'MÉTODOS DE OPTIMIZACIÓN',
				id: 1135,
			}, {
				nombre: 'COMPUTACIÓN PARALELA',
				id: 1136,
			}, {
				nombre: 'INGENIERÍA DE SISTEMAS',
				id: 1137,
			}, {
				nombre: 'REDES DE COMUNICACIÓN',
				id: 1138,
			}, {
				nombre: 'FUNDAMENTOS DE BASE DE DATOS',
				id: 1139,
			}, ]
		}]
	}, {
		nivel: 8,
		tiene_anual: false,
		tiene_semestral: true,
		semestral: [{
			nivel: 8,
			asignaturas: [{
				nombre: 'ANTROPOLOGÍA E INGENIERÍA',
				id: 1140,
			}, {
				nombre: 'FUNDAMENTOS DE INGENIERÍA DE SOFTWARE',
				id: 1141,
			}, {
				nombre: 'CONTROL Y SIMULACIÓN',
				id: 1142,
			}, {
				nombre: 'SISTEMAS DISTRIBUIDOS',
				id: 1143,
			}, {
				nombre: 'PROYECTOS DE BASE DE DATOS',
				id: 1144,
			}, ]
		}]
	}, {
		nivel: 9,
		tiene_anual: false,
		tiene_semestral: true,
		semestral: [{
			nivel: 9,
			asignaturas: [{
				nombre: 'EVALUACIÓN DE PROYECTOS',
				id: 1145,
			}, {
				nombre: 'PROYECTOS DE INGENIERÍA DE SOFTWARE',
				id: 1146,
			}, {
				nombre: 'COMPORTAMIENTO HUMANO EN EL TRABAJO',
				id: 1147,
			}, {
				nombre: 'INTERACCIÓN HUMANO COMPUTADOR',
				id: 1148,
			}, {
				nombre: 'ELECTIVO PROFESIONAL I',
				id: 1149,
			}, ]
		}]
	}, {
		nivel: 10,
		tiene_anual: false,
		tiene_semestral: true,
		semestral: [{
			nivel: 10,
			asignaturas: [{
				nombre: 'ADMINISTRACIÓN DE EMPRESAS',
				id: 1150,
			}, {
				nombre: 'PROYECTOS DE INGENIERÍA INFORMÁTICA',
				id: 1151,
			}, {
				nombre: 'ELECTIVO PROFESIONAL II',
				id: 1152,
			}, {
				nombre: 'ELECTIVO PROFESIONAL III',
				id: 1153,
			}, {
				nombre: 'ELECTIVO DE PROFESIONAL IV',
				id: 1154,
			}, ]
		}]
	}, {
		nivel: 11,
		tiene_anual: false,
		tiene_semestral: true,
		semestral: [{
			nivel: 11,
			asignaturas: [{
				nombre: 'DIRECCIÓN Y GESTIÓN DE EMPRESAS',
				id: 1155,
			}, {
				nombre: 'ELECTIVO PROFESIONAL V',
				id: 1156,
			}, {
				nombre: 'ELECTIVO PROFESIONAL VI',
				id: 1157,
			}, {
				nombre: 'SEMINARIO DE TITULACIÓN',
				id: 1158,
			}, ]
		}]
	}, {
		nivel: 12,
		tiene_anual: false,
		tiene_semestral: true,
		semestral: [{
			nivel: 12,
			asignaturas: [{
				nombre: 'TRABAJO DE TITULACIÓN',
				id: 1159,
			}, ]
		}]
	}, ]
	console.log(context);
	var html = template(context);
	$('#canvas').html(html);
	var prerequisitos = {
		1159: [
			1156,
			1157,
			1158,
		]
	}
	var aperturas = {
		1156: [
			1157
		],
		1114: [
			1127
		]
	}
	var operacion;
	operacion = 'apertura';
	//operacion = 'prerequisito';
	//operacion = 'proyeccion';

	$('.outer').on('mouseenter', function() {
		if (operacion == 'prerequisito') {
			var id = $(this).attr('id').replace('asignatura_', '');
			var mis_aperturas = aperturas[id];
			console.log(aperturas);
			$.each(mis_aperturas, function(key, apertura) {
				var apertura_id = '#asignatura_' + apertura;
				$(apertura_id).css('background', 'red');
			});
		}
		if (operacion == 'apertura') {
			var id = $(this).attr('id').replace('asignatura_', '');
			var mis_prerequisitos = prerequisitos[id];
			console.log(prerequisitos);
			$.each(mis_prerequisitos, function(key, prerequisito) {
				var prerequisito_id = '#asignatura_' + prerequisito;
				$(prerequisito_id).css('background', '#ff7319');
			});
		}
	});
	$('.outer').on('mouseleave', function() {
		if (operacion == 'prerequisito') {
			var id = $(this).attr('id').replace('asignatura_', '');
			var mis_prerequisitos = prerequisitos[id];
			console.log(prerequisitos);
			$.each(mis_prerequisitos, function(key, prerequisito) {
				var prerequisito_id = '#asignatura_' + prerequisito;
				$(prerequisito_id).css('background', 'white');
			});
		}
		if (operacion == 'apertura') {
			var id = $(this).attr('id').replace('asignatura_', '');
			var mis_prerequisitos = prerequisitos[id];
			console.log(prerequisitos);
			$.each(mis_prerequisitos, function(key, prerequisito) {
				var prerequisito_id = '#asignatura_' + prerequisito;
				$(prerequisito_id).css('background', 'white');
			});
		}
	});
	// console.log(html);
});