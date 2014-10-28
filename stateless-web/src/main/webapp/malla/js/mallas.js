var accion = 'aperturas';

jQuery(document).ready(function ($) {

    var ESTADO_INICIAL = 0;
    var ESTADO_TOMADO = 1;
    var ESTADO_PREREQUISITO = 2;

    function toTitleCase(str)
    {
        return str.replace(/\w\S*/g, function (txt) {
            return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
        });
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

    var asignaturas_sin_padres = GetAsignaturasSinPadres();

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

        var COLOR_NARANJO = '#FF7319';
        var COLOR_AZUL = '#0052CC';
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
            if (background == COLOR_BLANCO) {
                return ESTADO_INICIAL;
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
            if (estado == ESTADO_INICIAL) {
                return COLOR_BLANCO;
            }
            return 'black';
        }

        function CambiarEstadoById(id, estado) {
            var color = GetColorByEstado(estado);
            $('#asignatura_' + id).css('background', color);
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
            // console.log(union);
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
            // console.log(union);
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
                // console.log(prerequisitos);
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
            if (accion == 'proyeccion') {
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
            if (accion == 'proyeccion') {
                var nivel_proyeccion = parseInt($(this).val());
                $('.asignatura').css('background', 'white');
                CambiarEstadoArrayById(asignaturas_sin_padres, ESTADO_PREREQUISITO);
                ProyectarNivel(nivel_proyeccion);
            }
        });
    }
    $('.asignatura').each(function (key, esto) {
//        var elemento = GetNodeByJqueryElement(this);
        var id = GetIdByJqueryElement(esto);
        elemento = GetNodeById(id);

        console.log(elemento.resumen);
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
            style: {classes: 'qtip-cream'},
            position: position,
        });
//        $(this).find('div').css('font-size','16px');
    });
});