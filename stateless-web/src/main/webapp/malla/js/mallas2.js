var accion = 'aperturas';

jQuery(document).ready(function($) {
    function GetId(esto) {
        return $(esto).attr('id').replace('asignatura_', '');
    }
    jQuery.fn.verticalAlign = function() {
        var height = $(this).parent().height();
        console.log(height + 'px');
        if (height > 0)
            return this.css("margin-top", height + 'px')
        return this;
    };
    Handlebars.registerHelper('ancho', function(anual) {
        if (anual)
            return '2';
        return '1';
    });

    var nivel_mas_alto = 1;
    for (var i = context.length - 1; i >= 0; i--) {
        if (context[i].nivel > nivel_mas_alto)
            nivel_mas_alto = context[i].nivel;
    }

    Handlebars.registerHelper('imprimir', function(asignaturas) {
        var html = '<div>';
        var nivel_actual = 1;
        var asignaturas_impresas = 0;
        var total_asignaturas = asignaturas.length;
        var asignatura_impresas_por_este_ciclo = 0;

        while (asignaturas_impresas < total_asignaturas) {
            var imprimio = false;
            //console.log("======== Nivel: "+nivel_actual);
            for (var i = 0; i < asignaturas.length; i++) {
                if (!asignaturas[i].impresa && asignaturas[i].nivel == nivel_actual) {
                    // console.log("Nivel: "+nivel_actual);
                    // console.log("Asignatura: "+asignaturas[i].nombre);
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


    // ===== APERTURAS =====

    function getById(context, id) {
        for (var i = 0; i < context.length; i++) {
            if (context[i].id == id) {
                return context[i];
            }
        };
    }

    $('#outercanvas').html(html);

    var mas_alto = 0;
    $('.contenedor_asignatura').each(function() {
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
    // $('.alto_asignatura').height(mas_alto);
    console.log(mas_alto);

    $('#contenedor_outercanvas').height($('#outercanvas').height() + 150);
    console.log($('#outercanvas').height() + 300);

    //$('.centrar_vertical').css('padding-top', '10px');
    $('.centrar_vertical span').each(function() {
        var elemento = $(this);
        var height = elemento.height();
        var parent = elemento.parent();
        var parent_height = parent.parent().height();
        var nuevo_alto = (parent_height - height) / 3;
        parent.css('padding-top', nuevo_alto + 'px');
    });
    //$('.centrar_vertical span').flexVerticalCenter();

    function ColorearElementos(esto, accion, color_elemento, color_nodos_activados, color_texto) {
        var id = $(esto).attr('id').replace('asignatura_', '');

        $('#asignatura_' + id).css('background', color_elemento);
        console.log("A");
        if (color_elemento == COLOR_BLANCO) {
            $('#asignatura_' + id).css('color', 'black');
        } else {
            $('#asignatura_' + id).css('color', 'white');
        }

        var asignatura = getById(context, id);

        // Obtengo los elementos
        if (accion == 'aperturas') {
            var conjunto = asignatura.aperturas ? asignatura.aperturas : [];
        } else if (accion == 'prerequisitos') {
            var conjunto = asignatura.prerequisitos ? asignatura.prerequisitos : [];
        }

        // Coloreo el conjunto de elementos
        $.each(conjunto, function(key, apertura) {
            console.log("B");
            $('#asignatura_' + apertura).css('background', color_nodos_activados);
            console.log(color_nodos_activados);
            if (color_nodos_activados == 'COLOR_BLANCO') {
                $('#asignatura_' + apertura).css('color', 'black');
            } else if (color_nodos_activados == 'COLOR_NARANJO') {
                $('#asignatura_' + apertura).css('color', 'black');
            } else {
                console.log("$(#asignatura_" + apertura + ").css('color', 'black');");
                $('#asignatura_' + apertura).css('color', 'black');

                if (color_texto) {
                    $('#asignatura_' + apertura).css('color', color_texto);
                }
            }
        });
    }

    var asignaturas_proyectadas = [];


    function RemoverDuplicados(names) {
        var uniqueNames = [];
        $.each(names, function(i, el) {
            if ($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
        });
        return uniqueNames;
    }

    function Eliminar(nodo, array) {
        var arreglo_nuevo = [];
        for (var i = array.length - 1; i >= 0; i--) {
            if (array[i] !== nodo) {
                arreglo_nuevo.push(array[i]);
            }
        }
        return arreglo_nuevo;
    }

    function ColorearAsignatura(id, color) {
        $('#asignatura_' + id).css('background', color);
        console.log("C");
        if (color == COLOR_BLANCO) {
            $('#asignatura_' + id).css('color', 'black');
        } else {
            $('#asignatura_' + id).css('color', 'white');
        }
    }

    var COLOR_NARANJO = '#FF7319';
    var COLOR_AZUL = '#0052CC';
    var COLOR_BLANCO = 'white';

    function ColorearAsignaturasProyectadas() {
        for (var i = 0; i < context.length; i++) {
            ColorearAsignatura(context[i].id, COLOR_BLANCO);
        };
        for (var i = 0; i < context.length; i++) {
            if (context[i].nivel == 1) {
                // var asignatura = context[i];
                ColorearAsignatura(context[i].id, COLOR_NARANJO);
                // asignaturas_proyectadas.push(asignatura);
            }
        };
        for (var i = 0; i < asignaturas_proyectadas.length; i++) {
            for (var j = 0; j < asignaturas_proyectadas[i].aperturas.length; j++) {
                // console.log("Coloreando: " + asignaturas_proyectadas[i].aperturas[j]);
                ColorearAsignatura(asignaturas_proyectadas[i].aperturas[j], COLOR_NARANJO);
            };
        };

        for (var i = 0; i < asignaturas_proyectadas.length; i++) {
            ColorearAsignatura(asignaturas_proyectadas[i].id, COLOR_AZUL);
        };
    }

    function ProyectarNivel(nivel) {
        for (var i = 0; i < context.length; i++) {
            if (context[i].nivel <= nivel) {
                var asignatura = context[i];
                asignaturas_proyectadas.push(asignatura);
            }
        };
        asignaturas_proyectadas = RemoverDuplicados(asignaturas_proyectadas);
        console.log(asignaturas_proyectadas);
        ColorearAsignaturasProyectadas();
        console.log("===============");
        if (nivel == 0) {
            console.log("===============");
            for (var i = 0; i < context.length; i++) {
                ColorearAsignatura(context[i].id, COLOR_BLANCO);
            };
            for (var i = 0; i < context.length; i++) {
                if (context[i].nivel == 1) {
                    // var asignatura = context[i];
                    ColorearAsignatura(context[i].id, COLOR_NARANJO);
                    // asignaturas_proyectadas.push(asignatura);
                }
            };
        }
    }

    function DesProyectarNivel(nivel) {
        for (var i = 0; i < context.length; i++) {
            if (context[i].nivel == nivel) {
                console.log("if(" + GetHexString($('#asignatura_' + context[i].id).css('backgroundColor')) + ")");
                if (GetHexString($('#asignatura_' + context[i].id).css('backgroundColor')) == '#FF7319') {
                    ColorearElementos($('#asignatura_' + context[i].id), 'aperturas', 'white', 'white');
                }
                // console.log("Nivel: " + nivel);
            }
        };
    }

    $('.asignatura').on('mouseenter', function() {
        if (accion != 'proyeccion') {
            // ColorearElementos(this, accion, '#0052CC', '#FF7319');
            ColorearElementos(this, accion, '#0052CC', '#FF7319', 'white');
        }
    });
    $('.asignatura').on('mouseleave', function() {
        if (accion != 'proyeccion') {
            ColorearElementos(this, accion, 'white', 'white', 'black');
        }
    });

    function hexc(colorval) {
        var parts = colorval.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
        delete(parts[0]);
        for (var i = 1; i <= 3; ++i) {
            parts[i] = parseInt(parts[i]).toString(16);
            if (parts[i].length == 1) parts[i] = '0' + parts[i];
        }
        color = '#' + parts.join('');
    }

    function GetHexString(rgbString) {
        var parts = rgbString.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
        var hexString;
        if (parts == null) {
            hexString = 'white';
        } else {
            // parts now should be ["rgb(0, 70, 255", "0", "70", "255"]

            delete(parts[0]);
            for (var i = 1; i <= 3; ++i) {
                parts[i] = parseInt(parts[i]).toString(16);
                if (parts[i].length == 1) parts[i] = '0' + parts[i];
            }
            hexString = '#' + parts.join('').toUpperCase(); // "#0070FF"
        }
        return hexString
    }

    function ApagarAsignatura(asignatura) {
        ColorearAsignatura(asignatura.id, COLOR_BLANCO);
        asignaturas_proyectadas = Eliminar(asignatura, asignaturas_proyectadas);
        for (var i = 0; i < asignatura.aperturas.length; i++) {
            var nodo = getById(context, asignatura.aperturas[i]);
            // console.log(nodo);
            var color = GetHexString($('#asignatura_' + nodo.id).css('backgroundColor'));
            // console.log(color);
            console.log(nodo.id);
            console.log("Pinte " + '#asignatura_' + nodo.id + " de color: " + COLOR_BLANCO)
            ColorearAsignatura(nodo.id, COLOR_BLANCO);
            asignaturas_proyectadas = Eliminar(nodo, asignaturas_proyectadas);
            if (color == COLOR_AZUL) {
                ApagarAsignatura(nodo);
            }
        };
    }

    $('.asignatura').on('click', function() {
        var hexString = GetHexString($(this).css('backgroundColor'));

        // if (hexString != 'white') {
        //     if (accion == 'proyeccion') {
        //         var id = $(this).attr('id').replace('asignatura_', '');
        //         ColorearElementos(this, accion, '#0052CC', '#FF7319');
        //     }
        // }
        if (accion == 'proyeccion') {
            var id = GetId(this);
            var nodo = getById(context, id);

            console.log("============== ")
            if (hexString == COLOR_AZUL) {

                ApagarAsignatura(nodo);

                // asignaturas_proyectadas = Eliminar(nodo, asignaturas_proyectadas);
                ColorearAsignaturasProyectadas();
            }
            if (hexString == COLOR_NARANJO) {
                // asignaturas_proyectadas = Eliminar(nodo, asignaturas_proyectadas);
                asignaturas_proyectadas.push(nodo);
                ColorearAsignaturasProyectadas();
            }
            if (hexString == COLOR_BLANCO) {
                console.log("Busco: " + id)
                var tiene_algun_padre_en_array = false;
                for (var i = 0; i < asignaturas_proyectadas.length; i++) {
                    var nodo = getById(asignaturas_proyectadas[i]);
                    for (var i = 0; i < nodo.aperturas.length; i++) {
                        if (nodo.aperturas[i] == id) {
                            console.log(asignaturas_proyectadas);
                            asignaturas_proyectadas.push(nodo);
                            asignaturas_proyectadas = RemoverDuplicados(asignaturas_proyectadas);
                            console.log(asignaturas_proyectadas);
                            ColorearAsignaturasProyectadas();
                        }
                    };
                };
            }

        }

    });

    function LimpiarAsignaturas() {
        for (var i = 0; i < context.length; i++) {
            ColorearAsignatura(context[i].id, COLOR_BLANCO);
        };
    }

    $('#fw').on('click', function() {
        accion = 'aperturas';
        LimpiarAsignaturas();
    });
    $('#bw').on('click', function() {
        accion = 'prerequisitos';
        LimpiarAsignaturas();
    });
    $('#fwbw').on('click', function() {
        accion = 'proyeccion';
        $('#spinme').change();
    });
    // accion = 'proyeccion';
    $('#spinme').spin({
        max: nivel_mas_alto,
        min: 0
    });
    // ProyectarNivel(1);
    $('#spinme').on('change click', function() {
        accion = 'proyeccion';
        $('#fwbw').attr('checked', 'checked');
        if (accion == 'proyeccion') {
            var nivel_proyeccion = parseInt($(this).val());
            $('.asignatura').css('background', 'white');
            console.log("D");
            $('.asignatura').css('color', 'black');
            for (var i = 0; i < nivel_mas_alto + 1; i++) {
                if (i < nivel_proyeccion + 1) {
                    ProyectarNivel(i);
                }
            };
        }
    });
});