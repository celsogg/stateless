var accion = 'aperturas';

jQuery(document).ready(function ($) {
    jQuery.fn.verticalAlign = function ()
    {
        var height = $(this).parent().height();
        console.log(height + 'px');
        if (height > 0)
            return this.css("margin-top", height + 'px')
        return this;
    };
    Handlebars.registerHelper('ancho', function (anual) {
        if (anual)
            return '2';
        return '1';
    });

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
        }
        ;
    }

    $('#outercanvas').html(html);

    var mas_alto = 0;
    $('.contenedor_asignatura').each(function(){
       var alto = $(this).height();
       if(alto > mas_alto){
           mas_alto = alto;
       }
    });
    
    // Le asignamos a todas las asignaturas el alto del elemento mas alto
    $('.alto_asignatura').height(mas_alto);
    console.log(mas_alto);
    
    $('#contenedor_outercanvas').height( $('#outercanvas').height() + 50);
    console.log($('#outercanvas').height() + 100);
    
    //$('.centrar_vertical').css('padding-top', '10px');
    $('.centrar_vertical span').each(function () {
        var elemento = $(this);
        var height = elemento.height();
        var parent = elemento.parent();
        var parent_height = parent.parent().height();
        var nuevo_alto = (parent_height - height) / 3;
        parent.css('padding-top', nuevo_alto+'px');
    });
    //$('.centrar_vertical span').flexVerticalCenter();

    function ManejarAperturas(var_this, propiedades_elementos, propiedades_actual) {
        var id = $(var_this).attr('id').replace('asignatura_', '');

        var asignatura = getById(context, id);
        var mis_aperturas = asignatura.aperturas ? asignatura.aperturas : [];

        $.each(mis_aperturas, function (key, apertura) {
            var apertura_id = '#asignatura_' + apertura;
            $.each(propiedades_elementos, function (key, propiedad) {
                $(apertura_id).css(propiedad.propiedad, propiedad.valor);
            });
        });

        $.each(propiedades_actual, function (key, propiedad) {
            $('#asignatura_' + id).css(propiedad.propiedad, propiedad.valor);
        });
    }

    function ManejarPrerequisitos(var_this, propiedades_elementos, propiedades_actual) {
        var id = $(var_this).attr('id').replace('asignatura_', '');

        var asignatura = getById(context, id);
        var mis_aperturas = asignatura.prerequisitos ? asignatura.prerequisitos : [];

        $.each(mis_aperturas, function (key, apertura) {
            var apertura_id = '#asignatura_' + apertura;
            $.each(propiedades_elementos, function (key, propiedad) {
                $(apertura_id).css(propiedad.propiedad, propiedad.valor);
            });
        });

        $.each(propiedades_actual, function (key, propiedad) {
            $('#asignatura_' + id).css(propiedad.propiedad, propiedad.valor);
        });
    }

    $('.asignatura').on('mouseenter', function () {
        if (accion == 'aperturas') {
            var propiedades_elementos = [
                {propiedad: 'color', valor: 'white'},
                {propiedad: 'background', valor: '#FF7319'},
            ];
            var propiedades_actual = [
                {propiedad: 'color', valor: 'white'},
                {propiedad: 'background', valor: '#0052CC'},
            ]
            ManejarAperturas(this, propiedades_elementos, propiedades_actual);
        }
    });
    $('.asignatura').on('mouseleave', function () {
        if (accion == 'aperturas') {
            var propiedades_elementos = [
                {propiedad: 'color', valor: 'black'},
                {propiedad: 'background', valor: 'white'},
            ];
            var propiedades_actual = [
                {propiedad: 'color', valor: 'black'},
                {propiedad: 'background', valor: 'white'},
            ]
            ManejarAperturas(this, propiedades_elementos, propiedades_actual);
        }
    });

    $('.asignatura').on('mouseenter', function () {
        if (accion == 'prerequisitos') {
            var propiedades_elementos = [
                {propiedad: 'color', valor: 'white'},
                {propiedad: 'background', valor: '#FF7319'},
            ];
            var propiedades_actual = [
                {propiedad: 'color', valor: 'white'},
                {propiedad: 'background', valor: '#0052CC'},
            ]
            ManejarPrerequisitos(this, propiedades_elementos, propiedades_actual);
        }
    });
    $('.asignatura').on('mouseleave', function () {
        if (accion == 'prerequisitos') {
            var propiedades_elementos = [
                {propiedad: 'color', valor: 'black'},
                {propiedad: 'background', valor: 'white'},
            ];
            var propiedades_actual = [
                {propiedad: 'color', valor: 'black'},
                {propiedad: 'background', valor: 'white'},
            ]
            ManejarPrerequisitos(this, propiedades_elementos, propiedades_actual);
        }
    });

    // ----- APERTURAS -----

    $('#fw').on('click', function () {
        accion = 'aperturas';
    });
    $('#bw').on('click', function () {
        accion = 'prerequisitos';
    });
});