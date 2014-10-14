

function RemoverDuplicados(names) {
    var uniqueNames = [];
    $.each(names, function (i, el) {
        if ($.inArray(el, uniqueNames) === -1)
            uniqueNames.push(el);
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
