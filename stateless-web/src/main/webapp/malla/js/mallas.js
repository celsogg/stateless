var accion = 'aperturas';

jQuery(document).ready(function ($) {

    for (var i = context.length - 1; i >= 0; i--) {
        context[i].aperturas = [];
    };
    
    for (var i = 0, max = context.length; i < max; i++) {
        var aperturas = [];
        for (var j = 0; j < max; j++) {
            for (var k = 0; k < context[ j ].prerequisitos.length; k++) {
                if(context[ j ].prerequisitos[ k ] == context[ i ].id){
                    aperturas.push(context[ j ].id);
                    break;
                }
            }
        }
        context[ i ].aperturas = aperturas;
    }

    function GetAsignaturasSinPadres() {
        var asignaturas_sin_padres = [];
        var son_hijos = [];
        var context_ids = _.pluck(context, 'id');

        for (var i = 0; i < context.length; i++) {
            son_hijos = _.union(son_hijos, context[i].aperturas);
        }
        ;
        asignaturas_sin_padres = _.difference(context_ids, son_hijos);

        return asignaturas_sin_padres;
    }

    function CambiarValorSCT(sct) {
        $('#sct_y_tel').text('SCT: ' + sct);
        if (sct < 25) {
            $('#sct_y_tel').addClass('label-info');

            $('#sct_y_tel').removeClass('label-danger');
            $('#sct_y_tel').removeClass('label-warning');
        } else if (sct < 30) {
            $('#sct_y_tel').removeClass('label-info');
            $('#sct_y_tel').removeClass('label-danger');

            $('#sct_y_tel').addClass('label-warning');

            $('#sct_y_tel').qtip({
                content: {
                    text: "Tener entre 25 y 30 SCT implica una carga superior a la recomendada."
                },
                style: {
                    classes: 'qtip-cream'
                },
                position: {
                    my: 'top center', // Position my top left...
                    at: 'bottom center', // at the bottom right of...
                    target: $('#sct_y_tel') // my target
                },
            });
        } else {
            $('#sct_y_tel').removeClass('label-info');

            $('#sct_y_tel').addClass('label-danger');

            $('#sct_y_tel').removeClass('label-warning');

            $('#sct_y_tel').qtip({
                content: {
                    text: "Tener mas de 30 SCT en un semestre implica una gran carga académica y no es recomendable."
                },
                style: {
                    classes: 'qtip-cream'
                },
                position: {
                    my: 'top center', // Position my top left...
                    at: 'bottom center', // at the bottom right of...
                    target: $('#sct_y_tel') // my target
                },
            });
        }
    }

    var asignaturas_sin_padres = GetAsignaturasSinPadres();

    var se_esta_realizando_toma_de_ramos = false;

    if (tomar_ramos) {
        $('#apertura').hide();
        $('#prerequisitos').hide();
        $('#proyeccion').hide();
        // se_esta_realizando_toma_de_ramos = true;
    } else {
        $('#tomar_ramos_div').hide();
    }

    var ESTADO_INICIAL = 0;
    var ESTADO_TOMADO = 1;
    var ESTADO_PREREQUISITO = 2;
    var ESTADO_SIMULANDO_TOMA = 3;
    var ESTADO_PREREQUISITO_NO_TOMABLE = 4;


    function toTitleCase(str) {
        return str.replace(/\w\S*/g, function (txt) {
            return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
        });
    }

    function GetHexString(rgbString) {
        var parts = rgbString.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
        var hexString;
        if (parts == null || parts[0] > 255 || parts[1] > 255 || parts[2] > 255 || parts[0] < 0 || parts[1] < 0 || parts[2] < 0) {
            hexString = 'white';
        } else {
            // parts now should be ["rgb(0, 70, 255", "0", "70", "255"]

            delete(parts[0]);
            for (var i = 1; i <= 3; ++i) {
                parts[i] = parseInt(parts[i]).toString(16);
                if (parts[i].length == 1)
                    parts[i] = '0' + parts[i];
            }
            hexString = '#' + parts.join('').toUpperCase(); // "#0070FF"
        }
        if (hexString == '#FFFFFF') {
            return 'white';
        }
        return hexString;
    }

    if (!pruebas) {

        var nivel_mas_alto = 1;
        for (var i = context.length - 1; i >= 0; i--) {
            if (context[i].nivel > nivel_mas_alto)
                nivel_mas_alto = context[i].nivel;
        }

        Handlebars.registerHelper('imprimir', function (asignaturas) {
            var html = '<div>';
            var nivel_actual = 1;
            var asignaturas_impresas = 0;
            var total_asignaturas = asignaturas.length;
            var asignatura_impresas_por_este_ciclo = 0;

            while (asignaturas_impresas < total_asignaturas) {
                var imprimio = false;
                for (var i = 0; i < asignaturas.length; i++) {
                    if (!asignaturas[i].impresa && asignaturas[i].nivel == nivel_actual) {
                        imprimio = true;
                        asignaturas[i].impresa = true;

                        html += '<div class="contenedor_asignatura ' + asignaturas[i].nivel + ' col-md-';
                        html += asignaturas[i].anual ? '2' : '1';
                        html += '"><div class="asignatura alto_asignatura centrar_vertical" id="asignatura_' + asignaturas[i].id + '"><span>' + asignaturas[i].nombre + '</span></div></div>';


                        if (asignaturas[i].anual) {
                            nivel_actual = (nivel_actual + 1) % nivel_mas_alto;
                        }
                        asignaturas_impresas++;
                        asignatura_impresas_por_este_ciclo++;
                        break;
                    }
                }
                if (!imprimio) {
                    html += '<div class="col-md-1 alto_asignatura"></div>';
                }
                if (nivel_actual == nivel_mas_alto) {
                    for (var i = nivel_actual + 1; i <= 12; i++) {
                        html += '<div class="col-md-1 alto_asignatura"></div>';
                    }
                }

                nivel_actual = (nivel_actual) % nivel_mas_alto + 1;
            }
            html += '</div>';
            html = $(html);

            //html.find('.centrar_vertical').verticalAlign();
            return html.html();
        });
        // Code using $ as usual goes here.
        var source = $("#asignaturas").html();
        var template = Handlebars.compile(source);


        var html = template(context);

        var texto_niveles = '';
        for (var nivel = 1; nivel <= nivel_mas_alto; nivel++) {
            texto_niveles += '<div class="col-xs-1 texto_nivel">Nivel ' + nivel + '</div>';
        }
        for (var i = nivel_mas_alto; i < 12; i++) {
            texto_niveles += '<div class="col-xs-1 texto_nivel"></div>';
        }

        html = texto_niveles + html;

        $('#outercanvas').html(html);

        var mas_alto = 0;
        $('.contenedor_asignatura').each(function () {
            var alto = $(this).height();
            if (alto > mas_alto) {
                mas_alto = alto;
            }
        });

        // Le asignamos a todas las asignaturas el alto del elemento mas alto
        $('.alto_asignatura').height(50);
        if (mas_alto > 50) {
            $('.asignatura').css('font-size', '9px');
        }

        // $('#contenedor_outercanvas').height($('#outercanvas').height() + 250);

        $('.centrar_vertical span').each(function () {
            var elemento = $(this);
            var height = elemento.height();
            var parent = elemento.parent();
            var parent_height = parent.parent().height();
            var nuevo_alto = (parent_height - height) / 3;
            parent.css('padding-top', nuevo_alto + 'px');
        });

        // TIENEN QUE ESTAR EN MAYUSCULA
        var COLOR_NARANJO = '#FF7319';
        var COLOR_NARANJO_NO_TOMABLE = '#F39C12';
        var COLOR_AZUL = '#0052CC';
        var COLOR_ROJO = '#E74C3C';
        var COLOR_BLANCO = 'white';

        function ProyectarNivel(nivel) {
            for (var i = 0; i < context.length; i++) {
                if (context[i].nivel <= nivel) {
                    CambiarEstadoById(context[i].id, ESTADO_TOMADO);
                    MostrarAperturasById(context[i].id);
                    // CambiarEstadoById(context[i].id, ESTADO_TOMADO);
                }
            }
            ;
            if (nivel == 0) {
                for (var i = 0; i < context.length; i++) {
                    if (context[i].nivel == 1) {
                        CambiarEstadoById(context[i].id, ESTADO_PREREQUISITO);
                    }
                }
                ;
            }
        }

        function GetIdByJqueryElement(esto) {
            var id = $(esto).attr('id').replace('asignatura_', '');
            return id;
        }

        function GetEstadoByJqueryElement(esto) {
            var background = GetHexString($(esto).css('backgroundColor'));
            if (background == COLOR_AZUL) {
                return ESTADO_TOMADO;
            }
            if (background == COLOR_NARANJO) {
                return ESTADO_PREREQUISITO;
            }
            if (background == COLOR_NARANJO_NO_TOMABLE) {
                return ESTADO_PREREQUISITO_NO_TOMABLE;
            }
            if (background == COLOR_BLANCO) {
                return ESTADO_INICIAL;
            }
            if (background == COLOR_ROJO) {
                return ESTADO_SIMULANDO_TOMA;
            }
            return ESTADO_SIN_ESTADO;

        }

        function GetEstadoById(id) {
            return GetEstadoByJqueryElement($('#asignatura_' + id));
        }

        function GetColorByEstado(estado) {
            if (estado == ESTADO_TOMADO) {
                return COLOR_AZUL;
            }
            if (estado == ESTADO_PREREQUISITO) {
                return COLOR_NARANJO;
            }
            if (estado == ESTADO_PREREQUISITO_NO_TOMABLE) {
                return COLOR_NARANJO_NO_TOMABLE;
            }
            if (estado == ESTADO_INICIAL) {
                return COLOR_BLANCO;
            }
            if (estado == ESTADO_SIMULANDO_TOMA) {
                return COLOR_ROJO;
            }
            return 'BLACK';
        }

        function CambiarEstadoById(id, estado) {
            var color = GetColorByEstado(estado);
            $('#asignatura_' + id).css('background', color);

            var color_texto = '#000000'; // Negro
            if (color == COLOR_AZUL || color == COLOR_NARANJO || color == COLOR_ROJO) {
                color_texto = '#FFFFFF'; // Blanco
            }
            $('#asignatura_' + id).css('color', color_texto); // Blanco
        }

        function GetNodeById(id) {
            for (var i = 0; i < context.length; i++) {
                if (context[i].id == id) {
                    return context[i];
                }
            }
            ;
        }

        function GetNodeByJqueryElement(esto) {
            var id = GetIdByJqueryElement(esto);
            for (var i = 0; i < context.length; i++) {
                if (context[i].id == id) {
                    return GetNodeById(context[i]);
                }
            }
            ;
        }

        function GetPrerequisitosById(id) {
            return GetNodeById(id).prerequisitos;
        }

        function CambiarEstadoArrayById(array, estado) {
            for (var i = 0; i < array.length; i++) {
                CambiarEstadoById(array[i], estado);
            }
            ;
        }

        function GetPrerequisitosRecursiveById(id) {
            var prerequisitos = GetNodeById(id).prerequisitos;
            var union = prerequisitos;
            for (var i = prerequisitos.length - 1; i >= 0; i--) {
                union = _.union(union, GetPrerequisitosRecursiveById(prerequisitos[i]));
            }
            ;
            return union;
        }

        function GetAperturasById(id) {
            return GetNodeById(id).aperturas;
        }

        function GetAperturasRecursiveById(id) {
            var aperturas = GetNodeById(id).aperturas;
            var union = aperturas;
            for (var i = aperturas.length - 1; i >= 0; i--) {
                union = _.union(union, GetAperturasRecursiveById(aperturas[i]));
            }
            ;
            return union;
        }

        function MostrarAperturasById(id, recursive) {
            if (recursive) {
                var aperturas = GetAperturasRecursiveById(id);
            } else {
                var aperturas = GetAperturasById(id);
            }
            for (var i = aperturas.length - 1; i >= 0; i--) {
                CambiarEstadoById(aperturas[i], ESTADO_PREREQUISITO);
            }
            ;
        }

        $('.asignatura').on('mouseenter', function () {
            if (accion == 'prerequisitos') {
                var id = GetIdByJqueryElement(this);
                CambiarEstadoById(id, ESTADO_TOMADO);
                var prerequisitos = GetPrerequisitosRecursiveById(id);
                for (var i = prerequisitos.length - 1; i >= 0; i--) {
                    CambiarEstadoById(prerequisitos[i], ESTADO_PREREQUISITO);
                }
                ;
            }
            if (accion == 'aperturas') {
                var id = GetIdByJqueryElement(this);
                CambiarEstadoById(id, ESTADO_TOMADO);
                MostrarAperturasById(id);
            }
        });
        $('.asignatura').on('mouseleave', function () {
            if (accion == 'prerequisitos') {
                var id = GetIdByJqueryElement(this);
                CambiarEstadoById(id, ESTADO_INICIAL);
                // var prerequisitos = GetPrerequisitosById(id);
                var prerequisitos = GetPrerequisitosRecursiveById(id);
                for (var i = prerequisitos.length - 1; i >= 0; i--) {
                    CambiarEstadoById(prerequisitos[i], ESTADO_INICIAL);
                }
                ;
            }
            if (accion == 'aperturas') {
                var id = GetIdByJqueryElement(this);
                CambiarEstadoById(id, ESTADO_INICIAL);
                var aperturas = GetAperturasRecursiveById(id);
                for (var i = aperturas.length - 1; i >= 0; i--) {
                    CambiarEstadoById(aperturas[i], ESTADO_INICIAL);
                }
                ;
            }
        });

        function hexc(colorval) {
            var parts = colorval.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
            delete(parts[0]);
            for (var i = 1; i <= 3; ++i) {
                parts[i] = parseInt(parts[i]).toString(16);
                if (parts[i].length == 1)
                    parts[i] = '0' + parts[i];
            }
            color = '#' + parts.join('');
        }

        function ApagarAsignatura(asignatura) {
            ColorearAsignatura(asignatura.id, COLOR_BLANCO);
            asignaturas_proyectadas = Eliminar(asignatura, asignaturas_proyectadas);
            for (var i = 0; i < asignatura.aperturas.length; i++) {
                var nodo = getById(context, asignatura.aperturas[i]);
                var color = GetHexString($('#asignatura_' + nodo.id).css('backgroundColor'));
                ColorearAsignatura(nodo.id, COLOR_BLANCO);
                asignaturas_proyectadas = Eliminar(nodo, asignaturas_proyectadas);
                if (color == COLOR_AZUL) {
                    ApagarAsignatura(nodo);
                }
            }
            ;
        }

        $('.asignatura').on('click', function () {
            if (accion == 'proyeccion' || accion == 'tomar_ramos') {
                if (!se_esta_realizando_toma_de_ramos) {
                    var estado = GetEstadoByJqueryElement(this);
                    var id = GetIdByJqueryElement(this);

                    if (estado == ESTADO_PREREQUISITO) {

                        CambiarEstadoById(id, ESTADO_TOMADO);

                        var asignatura = GetNodeById(id);
                        aperturas = asignatura.aperturas;

                        // mostramos las aperturas que esten en blanco, de color naranjo
                        for (var i = 0; i < aperturas.length; i++) {
                            if (GetEstadoById(aperturas[i]) == ESTADO_INICIAL) {
                                CambiarEstadoById(aperturas[i], ESTADO_PREREQUISITO);
                            }
                        }
                        ;
                        // MostrarAperturasById(id);
                    } else if (estado == ESTADO_TOMADO) {
                        var aperturas_recursiva = GetAperturasRecursiveById(id);
                        CambiarEstadoById(id, ESTADO_PREREQUISITO);
                        CambiarEstadoArrayById(aperturas_recursiva, ESTADO_INICIAL);
                    } else {
                        $('#myModal').modal({
                            keyboard: true
                        });
                    }
                } else { // se está relizando toma de ramos
                    var estado = GetEstadoByJqueryElement(this);
                    var id = GetIdByJqueryElement(this);

                    if (estado == ESTADO_PREREQUISITO) {

                        CambiarEstadoById(id, ESTADO_SIMULANDO_TOMA);

                        var asignatura = GetNodeById(id);
                        aperturas = asignatura.aperturas;

                        // mostramos las aperturas que esten en blanco, de color naranjo
                        for (var i = 0; i < aperturas.length; i++) {
                            if (GetEstadoById(aperturas[i]) == ESTADO_INICIAL) {
                                CambiarEstadoById(aperturas[i], ESTADO_PREREQUISITO_NO_TOMABLE);
                            }
                        }
                        ;
                    } else if (estado == ESTADO_SIMULANDO_TOMA) {
                        CambiarEstadoById(id, ESTADO_PREREQUISITO);

                        var asignatura = GetNodeById(id);
                        aperturas = asignatura.aperturas;

                        // mostramos las aperturas que esten en blanco, de color naranjo
                        for (var i = 0; i < aperturas.length; i++) {
                            if (GetEstadoById(aperturas[i]) == ESTADO_PREREQUISITO_NO_TOMABLE) {
                                CambiarEstadoById(aperturas[i], ESTADO_INICIAL);
                            }
                        }
                        ;
                    } else {
                        $('#myModal').modal({
                            keyboard: true
                        });
                    }
                }
                var sct_totales = 0;
                var tiene_sct = false;
                for (var i = context.length - 1; i >= 0; i--) {
                    if (GetEstadoById(context[i].id) == ESTADO_SIMULANDO_TOMA) {
                        if (context[i].sct) {
                            tiene_sct = true;
                            sct_totales += context[i].sct;
                        }
                    }
                }
                ;

                CambiarValorSCT(sct_totales);
            }
        });

        $('#fw').on('click', function () {
            accion = 'aperturas';
            CambiarEstadoArrayById(_.pluck(context, 'id'), ESTADO_INICIAL);
            $('#outline').hide();
            // LimpiarAsignaturas();
        });
        $('#bw').on('click', function () {
            accion = 'prerequisitos';
            CambiarEstadoArrayById(_.pluck(context, 'id'), ESTADO_INICIAL);
            // LimpiarAsignaturas();
            $('#outline').hide();
        });
        $('#fwbw').on('click', function () {
            accion = 'proyeccion';
            $('#spinme').val(0);
            $('#spinme').change();
            $('#outline').show();
            // $('#outline').slideToggle();
        });

        $('#spinme').spin({
            max: nivel_mas_alto,
            min: 0
        });

        $('#spinme').on('change', function () {
            accion = 'proyeccion';
            CambiarEstadoArrayById(_.pluck(context, 'id'), ESTADO_TOMADO);
            $('#fwbw').attr('checked', 'checked');
            if (accion == 'proyeccion' || accion == 'tomar_ramos') {
                var nivel_proyeccion = parseInt($(this).val());
                for (var i = context.length - 1; i >= 0; i--) {
                    CambiarEstadoById(context[i].id, ESTADO_INICIAL);
                }
                ;
                $('.asignatura').css('background', 'white');
                CambiarEstadoArrayById(asignaturas_sin_padres, ESTADO_PREREQUISITO);
                ProyectarNivel(nivel_proyeccion);
                CambiarValorSCT(0);
            }
        });

        $('#boton_fijar').on('click', function () {
            if (se_esta_realizando_toma_de_ramos) {
                $(this).removeClass('btn-warning');
                $(this).addClass('btn-success');
                $(this).text('Fijar');
                for (var i = context.length - 1; i >= 0; i--) {
                    if (GetEstadoById(context[i].id) == ESTADO_SIMULANDO_TOMA) {
                        CambiarEstadoById(context[i].id, ESTADO_PREREQUISITO);
                        var aperturas_recursiva = GetAperturasRecursiveById(context[i].id);
                        CambiarEstadoArrayById(aperturas_recursiva, ESTADO_INICIAL);
                        CambiarValorSCT(0);
                    }
                }
                ;
                se_esta_realizando_toma_de_ramos = false;
            } else {
                se_esta_realizando_toma_de_ramos = true;
                $(this).removeClass('btn-success');
                $(this).addClass('btn-warning');
                $(this).text('Liberar');
            }
        });

        if (tomar_ramos) {
            accion = 'tomar_ramos';
            // console.log(_.pluck(context, 'id'));
            CambiarEstadoArrayById(_.pluck(context, 'id'), ESTADO_TOMADO);
            var nivel_proyeccion = parseInt($(this).val());
            $('.asignatura').css('background', 'white');
            CambiarEstadoArrayById(asignaturas_sin_padres, ESTADO_PREREQUISITO);
            ProyectarNivel(nivel_proyeccion);
        }
    }

    for (var i = context.length - 1; i >= 0; i--) {
        CambiarEstadoById(context[i].id, GetEstadoById(context[i].id));
    }
    ;

    if (!tomar_ramos) {
        $('.asignatura').each(function (key, esto) {
            //        var elemento = GetNodeByJqueryElement(this);
            var id = GetIdByJqueryElement(esto);
            elemento = GetNodeById(id);

            var text = '<p style="font-size: 14px; font-weight: bold;">' + toTitleCase(elemento.nombre) + '</p>'
            if (elemento.resumen && elemento.resumen != 'null' && elemento.resumen != null) {
                text += elemento.resumen;
            }
            text += "<br/>";
            if (elemento.sct) {
                text += '<span style="color: #1abc9c;">SCT: ' + elemento.sct + '</span>';
            }
            if (elemento.t) {
                text += '&nbsp;<span style="color: #3498db;">TEL: ' + elemento.t + ' - ' + elemento.e + ' - ' + elemento.l + '</span>';
            }

            var position = {
                my: 'top center', // Position my top left...
                at: 'bottom center', // at the bottom right of...
                target: $(this) // my target
            }

            if (elemento.nivel == 1) {
                position = {
                    my: 'top left', // Position my top left...
                    at: 'bottom right', // at the bottom right of...
                    target: $(this) // my target
                }
            }

            if (elemento.nivel == nivel_mas_alto) {
                position = {
                    my: 'top right', // Position my top left...
                    at: 'bottom left', // at the bottom right of...
                    target: $(this) // my target
                }
            }

            $(this).qtip({
                content: {
                    text: text
                },
                style: {
                    classes: 'qtip-cream'
                },
                position: position,
            });
            //        $(this).find('div').css('font-size','16px');
        });
    }

    var tour;

    tour = new Shepherd.Tour({
        defaults: {
            classes: 'shepherd-theme-arrows'
        }
    });

    var primera_asignatura = "#asignatura_" + context[ 0 ].id;
    var boton_anterior = {
        text: 'Anterior',
        action: tour.back
    };
    var boton_siguiente = {
        text: 'Siguiente',
        action: tour.next
    };

    var boton_siguiente_listo = {
        text: 'Listo',
        action: tour.next
    };

    var boton_finalizar = {
        text: 'Fin',
        action: tour.cancel
    };

    tour.addStep('example', {
        title: 'Indica tus asignaturas aprobadas',
        text: 'Selecciona esta asignatura para indicar que está aprobada',
        attachTo: "#asignatura_" + context[ 0 ].id,
        advanceOn: '.docs-link click',
        buttons: [boton_siguiente_listo]
    });
    tour.addStep('example', {
        title: 'Iniciar la simulación',
        text: 'Ahora haz click aquí para indicar que ya ingresaste tus asignaturas aprobadas y deseas iniciar la simulación',
        attachTo: "#boton_fijar",
        advanceOn: '.docs-link click',
        buttons: [boton_anterior, boton_siguiente_listo]
    });
    tour.addStep('example', {
        title: '¡Simula!',
        text: 'Ahora haz click en esta asignatura, cambiará su color a rojo indicando que está elegida',
        attachTo: "#asignatura_" + context[ 1 ].id,
        advanceOn: '.docs-link click',
        buttons: [boton_anterior, boton_siguiente]
    });
    tour.addStep('example', {
        title: 'SCT',
        text: 'En esta ventana se mostrarán los SCT acumulados de tu simulación',
        attachTo: "#sct_y_tel",
        advanceOn: '.docs-link click',
        buttons: [boton_anterior, boton_finalizar]
    });

    $('#boton_tutorial').on('click', function () {
        tour.start();
    })
});