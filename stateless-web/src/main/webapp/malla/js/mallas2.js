var accion = 'aperturas';

toastr.options = {
  "closeButton": false,
  "debug": false,
  "positionClass": "toast-bottom-full-width",
  "onclick": null,
  "showDuration": "300",
  "hideDuration": "1000",
  "timeOut": "5000",
  "extendedTimeOut": "1000",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
}

jQuery(document).ready(function($) {

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
                if (parts[i].length == 1) parts[i] = '0' + parts[i];
            }
            hexString = '#' + parts.join('').toUpperCase(); // "#0070FF"
        }
        if(hexString == '#FFFFFF'){
            return 'white';
        }
        return hexString;
    }

    if (!pruebas) {

        function GetId(esto) {
            return $(esto).attr('id').replace('asignatura_', '');
        }
        jQuery.fn.verticalAlign = function() {
            var height = $(this).parent().height();
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

        $('#contenedor_outercanvas').height($('#outercanvas').height() + 150);

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
                // var conjunto = asignatura.prerequisitos ? asignatura.prerequisitos : [];
                var conjunto = [];
                var conjunto_de_prerequisitos = asignatura.prerequisitos;

                console.log(conjunto_de_prerequisitos);
            
                var largo_anterior = 0;
                break;
                while(conjunto_de_prerequisitos.length != largo_anterior){
                    largo_anterior = conjunto_de_prerequisitos.length;
                        console.log("Hola: ");
                    for (var i = 0; i < conjunto_de_prerequisitos.length; i++) {
                        var nodo = getById(context, conjunto_de_prerequisitos[i]);
                        for (var i = 0; i < nodo.prerequisitos.length; i++) {
                            conjunto_de_prerequisitos.push(nodo.prerequisitos[i]);
                        };
                    };
                    console.log(conjunto_de_prerequisitos);
                    conjunto_de_prerequisitos = RemoverDuplicados(conjunto_de_prerequisitos);
                }
                // console.log(conjunto_de_prerequisitos);
                console.log(conjunto_de_prerequisitos);
            }

            // Coloreo el conjunto de elementos
            $.each(conjunto, function(key, apertura) {
                $('#asignatura_' + apertura).css('background', color_nodos_activados);
                if (color_nodos_activados == 'COLOR_BLANCO') {
                    $('#asignatura_' + apertura).css('color', 'black');
                } else if (color_nodos_activados == 'COLOR_NARANJO') {
                    $('#asignatura_' + apertura).css('color', 'black');
                } else {
                    $('#asignatura_' + apertura).css('color', 'black');

                    if (color_texto) {
                        $('#asignatura_' + apertura).css('color', color_texto);
                    }
                }
            });
        }

        var asignaturas_proyectadas = [];

        function ColorearAsignatura(id, color) {
            $('#asignatura_' + id).css('background', color);
            if (color == COLOR_BLANCO) {
                $('#asignatura_' + id).css('color', 'black');
            } else {
                $('#asignatura_' + id).css('color', 'white');
            }
        }

        var COLOR_NARANJO = '#FF7319';
        var COLOR_AZUL = '#0052CC';
        var COLOR_BLANCO = 'white';

        function arr_diff(a1, a2) {
            var a = [],
                diff = [];
            for (var i = 0; i < a1.length; i++)
                a[a1[i]] = true;
            for (var i = 0; i < a2.length; i++)
                if (a[a2[i]]) delete a[a2[i]];
                else a[a2[i]] = true;
            for (var k in a)
                diff.push(k);
            return diff;
        }

        var asignaturas_sin_padres = [];
        var todos_los_hijos = [];
        for (var i = 0; i < context.length; i++) {
            for (var j = 0; j < context[i].aperturas.length; j++) {
                todos_los_hijos.push(context[i].aperturas[j]);
                todos_los_hijos = RemoverDuplicados(todos_los_hijos);
            };
        };
        var context_ids = [];
        for (var i = 0; i < context.length; i++) {
            context_ids.push(parseInt(context[i].id));
        }

        asignaturas_sin_padres = arr_diff(todos_los_hijos, context_ids);
        var asignaturas_sin_padres_aux = [];
        for (var i = 0; i < asignaturas_sin_padres.length; i++) {
            if ($.isNumeric(asignaturas_sin_padres[i]) && getById(context, asignaturas_sin_padres[i]).nivel < 3) {
                asignaturas_sin_padres_aux.push(parseInt(asignaturas_sin_padres[i]));
            }
        };
        asignaturas_sin_padres = asignaturas_sin_padres_aux;

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
                if ($.inArray(context[i].id, asignaturas_sin_padres) != -1) {
                    ColorearAsignatura(context[i].id, COLOR_NARANJO);
                }
            };
            for (var i = 0; i < asignaturas_proyectadas.length; i++) {
                for (var j = 0; j < asignaturas_proyectadas[i].aperturas.length; j++) {
                    ColorearAsignatura(asignaturas_proyectadas[i].aperturas[j], COLOR_NARANJO);
                };
            };

            for (var i = 0; i < asignaturas_proyectadas.length; i++) {
                ColorearAsignatura(asignaturas_proyectadas[i].id, COLOR_AZUL);
            };
        }

        function ProyectarNivel(nivel) {
            asignaturas_proyectadas = [];
            for (var i = 0; i < context.length; i++) {
                if (context[i].nivel <= nivel) {
                    var asignatura = context[i];
                    asignaturas_proyectadas.push(asignatura);
                }
            };
            for (var i = 0; i < context.length; i++) {
                ColorearAsignatura(context[i].id, COLOR_BLANCO);
            };
            asignaturas_proyectadas = RemoverDuplicados(asignaturas_proyectadas);
            ColorearAsignaturasProyectadas();
            if (nivel == 0) {
                for (var i = 0; i < context.length; i++) {
                    ColorearAsignatura(context[i].id, COLOR_BLANCO);
                };
                for (var i = 0; i < context.length; i++) {
                    console.log(asignaturas_sin_padres);
                    if ($.inArray(context[i].id, asignaturas_sin_padres) != -1) {
                        ColorearAsignatura(context[i].id, COLOR_NARANJO);
                        // $('#asignatura_'+context[i].id).css('border', '1px solid ' + COLOR_NARANJO);
                    } else if (context[i].nivel == 1) {
                        ColorearAsignatura(context[i].id, COLOR_NARANJO);
                    }
                };
            }
        }

        function DesProyectarNivel(nivel) {
            for (var i = 0; i < context.length; i++) {
                if (context[i].nivel == nivel) {
                    if (GetHexString($('#asignatura_' + context[i].id).css('backgroundColor')) == '#FF7319') {
                        ColorearElementos($('#asignatura_' + context[i].id), 'aperturas', 'white', 'white');
                    }
                }
            };
        }

        $('.asignatura').on('mouseenter', function() {
            if (accion != 'proyeccion') {
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
            };
        }

        $('.asignatura').on('click', function() {
            var hexString = GetHexString($(this).css('backgroundColor'));

            if (accion == 'proyeccion') {
                var id = GetId(this);
                var nodo = getById(context, id);

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
                    toastr.error('Error: Solo puedes seleccionar las asignaturas azules o naranjas')
                    var tiene_algun_padre_en_array = false;
                    for (var i = 0; i < asignaturas_proyectadas.length; i++) {
                        var nodo = getById(asignaturas_proyectadas[i]);
                        for (var i = 0; i < nodo.aperturas.length; i++) {
                            if (nodo.aperturas[i] == id) {
                                asignaturas_proyectadas.push(nodo);
                                asignaturas_proyectadas = RemoverDuplicados(asignaturas_proyectadas);
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
            $('#outline').hide();
        });
        $('#bw').on('click', function() {
            accion = 'prerequisitos';
            LimpiarAsignaturas();
            $('#outline').hide();
        });
        $('#fwbw').on('click', function() {
            accion = 'proyeccion';
            $('#spinme').change();
            $('#spinme').val(0);
            $('#spinme').click();
            $('#outline').slideToggle();
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
                $('.asignatura').css('color', 'black');
                for (var i = 0; i < nivel_mas_alto + 1; i++) {
                    if (i < nivel_proyeccion + 1) {
                        ProyectarNivel(i);
                    }
                };
            }
        });

        $('#bw').click();

    } else {
        function OrdenarNumeros(a, b) {
            return a > b;
        }
        QUnit.test("Remover elementos duplicados de un arreglo", function(assert) {
            // assert.ok(1 == "1", "Passed!");

            var remover_duplicados_A_1 = RemoverDuplicados([2, 1, 5, 5, 10, 25, 6]).sort(OrdenarNumeros);
            var remover_duplicados_B_1 = [6, 2, 5, 1, 10, 25].sort(OrdenarNumeros);
            assert.deepEqual(remover_duplicados_A_1, remover_duplicados_B_1, "Remover Duplicados 1");

            var remover_duplicados_A_2 = RemoverDuplicados([2, 1, 5, 5, 10, 6]).sort(OrdenarNumeros);
            var remover_duplicados_B_2 = [6, 2, 5, 1, 10, 25].sort(OrdenarNumeros);
            assert.notDeepEqual(remover_duplicados_A_2, remover_duplicados_B_2, "Remover Duplicados 2");

            var remover_duplicados_A_3 = RemoverDuplicados([1, 1]).sort(OrdenarNumeros);
            var remover_duplicados_B_3 = [1].sort(OrdenarNumeros);
            assert.deepEqual(remover_duplicados_A_3, remover_duplicados_B_3, "Remover Duplicados 3");
        });

        QUnit.test("Remover elementos de un arreglo", function(assert) {
            // Eliminar
            // assert.ok(1 == "1", "Passed!");

            var remover_A_1 = RemoverDuplicados([2, 1, 5, 5, 10, 25, 6]).sort(OrdenarNumeros);
            var remover_B_1 = [6, 2, 5, 1, 10, 25].sort(OrdenarNumeros);
            assert.deepEqual(remover_A_1, remover_B_1, "Arreglo largo (7 y 6 elementos) con numeros repetidos");

            var remover_A_2 = RemoverDuplicados([2, 1, 5, 5, 10, 6]).sort(OrdenarNumeros);
            var remover_B_2 = [6, 2, 5, 1, 10, 25].sort(OrdenarNumeros);
            assert.notDeepEqual(remover_A_2, remover_B_2, "Arreglo con la misma cantidad de elementos (6) pero numeros distintos");

            var remover_A_3 = RemoverDuplicados([1, 1]).sort(OrdenarNumeros);
            var remover_B_3 = [1].sort(OrdenarNumeros);
            assert.deepEqual(remover_A_3, remover_B_3, "Arreglo de largo 1 y arreglo de largo 2, ambos tienen solo el numero 1");
        });

        QUnit.test("Transformar de RGB a Hex ( Funcion GetHexString() )", function(assert) {
            // Eliminar
            // assert.ok(1 == "1", "Passed!");

            var entrada_A_1 = GetHexString('rgb(0, 0, 0)');
            var salida_A_1 = '#000000';
            assert.equal(entrada_A_1, salida_A_1, "RGB(0, 0, 0)");

            var entrada_A_1 = GetHexString('rgb(12, 86, 12)');
            var salida_A_1 = '#0C560C';
            assert.equal(entrada_A_1, salida_A_1, "RGB(12, 86, 12)");

            var entrada_A_1 = GetHexString('rgb(666, 666, 666)');
            var salida_A_1 = 'white';
            assert.equal(entrada_A_1, salida_A_1, "Colores fuera de limites positivos");

            var entrada_A_1 = GetHexString('rgb(-12, -6, -23)');
            var salida_A_1 = 'white';
            assert.equal(entrada_A_1, salida_A_1, "Colores fuera de limites negativos");

            var entrada_A_1 = GetHexString('rgb(12, 256, -23)');
            var salida_A_1 = 'white';
            assert.equal(entrada_A_1, salida_A_1, "Colores fuera de limites negativos y positivos");

            var entrada_A_1 = GetHexString('daffdfsgs');
            var salida_A_1 = 'white';
            assert.equal(entrada_A_1, salida_A_1, "Texto erroneo");
        });
    }
});